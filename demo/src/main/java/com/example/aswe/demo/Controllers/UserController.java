package com.example.aswe.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.example.aswe.demo.Models.User;
import com.example.aswe.demo.Repositories.UserRepository;

@RestController


public class UserController {
     @Autowired
    private UserRepository userRepository;
    @GetMapping("")
    
    public ModelAndView getUsers() {
        ModelAndView mav = new ModelAndView("index.html");
        List<User>users = this.userRepository.findAll();
        mav.addObject("users", users);
        return mav;
    }

     @GetMapping("/signup")
    public ModelAndView addUser() {
        ModelAndView mav = new ModelAndView("signup.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }
    
    @PostMapping("/signup")
public ModelAndView saveUser(@ModelAttribute User user) {
    String encodedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
    user.setPassword(encodedPassword);
    this.userRepository.save(user);

    ModelAndView mav = new ModelAndView("redirect:/login");
    return mav;
}
    
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }
     @PostMapping("/login")
    public String LoginProcess(@RequestParam("username") String username, @RequestParam("password") String password) {
       User dbUser = this.userRepository.findByUsername(username);
       if(dbUser == null) return "Login Failed";
       Boolean isPasswordMatched = BCrypt.checkpw(password, dbUser.getPassword());
       if(isPasswordMatched)
        return "Welcome " + dbUser.getFullname();
        else
        return "Login Failed";
    }
 
    
}


