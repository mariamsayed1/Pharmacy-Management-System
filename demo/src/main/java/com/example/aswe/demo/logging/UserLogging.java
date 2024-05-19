package com.example.aswe.demo.logging;

import com.example.aswe.demo.Models.UserLog;
import com.example.aswe.demo.Repositories.UserLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Aspect
@Component
public class UserLogging {

    @Autowired
    private UserLogRepository userLogRepository;

    @Before("execution(* com.example.aswe.demo.Controllers.*.*(..)) && !execution(* com.example.aswe.demo.Controllers.IndexController.loginProcess(..)) && !execution(* com.example.aswe.demo.Controllers.IndexController.saveUser(..))")
    public void logUserActivity(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            HttpSession session = request.getSession(false);

            if (session != null) {
                String username = (String) session.getAttribute("username");

                // Skip logging if username is null or "admin"
                if (username == null || "admin".equals(username)) {
                    return; // Skip logging
                }

                String pageVisited = request.getRequestURI();
                Date loginTime = new Date();

                UserLog userLog = new UserLog();
                userLog.setUserId(username);
                userLog.setLoginTime(loginTime);
                userLog.setPageVisited(pageVisited);

                try {
                    userLogRepository.save(userLog);
                } catch (Exception e) {
                    System.err.println("Failed to save user log: " + e.getMessage());
                }
            }
        }
    }
}
