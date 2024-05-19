package com.example.aswe.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.aswe.demo.Models.UserLog;
import com.example.aswe.demo.Repositories.UserLogRepository;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class LogController {

    @Autowired
    private UserLogRepository userLogRepository;

    @GetMapping("/logs")
    public ModelAndView viewLogs(HttpSession session) {
        ModelAndView mav = new ModelAndView("admin_logs"); // Ensure this matches your Thymeleaf template name
        String userType = (String) session.getAttribute("usertype");

        if (userType == null || !userType.equals("admin")) {
            return new ModelAndView("redirect:/login");
        }

        List<UserLog> userLogs = userLogRepository.findAll();
        mav.addObject("userLogs", userLogs);
        return mav;
    }
}


