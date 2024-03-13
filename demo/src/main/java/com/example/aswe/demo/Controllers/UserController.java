package com.example.aswe.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.aswe.demo.Models.User;
import com.example.aswe.demo.Repositories.UserRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/User")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    // @GetMapping("/index")
    // public ModelAndView index(HttpSession session) {
    //     ModelAndView mav = new ModelAndView("index.html");
    //     mav.addObject("username",(String) session.getAttribute("username"));
    //     return mav;
    // }

    @GetMapping("/")
    public ModelAndView getallUsers() {
        ModelAndView mav = new ModelAndView("index.html");
        List<User> users = this.userRepository.findAll();
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
    public ModelAndView saveUser(@Validated @ModelAttribute User user) {
        ModelAndView mav = new ModelAndView("signup.html");
        mav.addObject("user", user);

        if (isEmpty(user.getFullname()) || isEmpty(user.getUsername()) ||
                isEmpty(user.getEmail()) || isEmpty(String.valueOf(user.getPhonenumber())) ||
                isEmpty(user.getPassword()) || isEmpty(user.getConfirmpassword())) {
            mav.addObject("emptyFieldsError", "Please fill in all fields");
            mav.addObject("hasEmptyFieldsError", true);
        } else {

            if (!isValidEmail(user.getEmail())) {
                mav.addObject("emailError", "Invalid email format");
                mav.addObject("hasEmailError", true);
            }
            if (isUsernameTaken(user.getUsername())) {
                mav.addObject("usernameTakenError", "Username is already taken");
                mav.addObject("hasUsernameTakenError", true);
            }

            if (!isPasswordValid(user.getPassword(), user.getConfirmpassword())) {
                if (user.getPassword().length() < 8) {
                    mav.addObject("passwordLengthError", "Password is too short (minimum 8 characters)");
                    mav.addObject("hasPasswordLengthError", true);
                }

                if (!user.getPassword().equals(user.getConfirmpassword())) {
                    mav.addObject("passwordMatchError", "Passwords do not match");
                    mav.addObject("hasPasswordMatchError", true);
                }
            }

            if (mav.getModel().containsKey("hasUsernameTakenError") ||
                    mav.getModel().containsKey("hasEmailError") ||
                    mav.getModel().containsKey("hasPasswordLengthError") ||
                    mav.getModel().containsKey("hasPasswordMatchError") ||
                    mav.getModel().containsKey("hasEmptyFieldsError"))
                return mav;

            String encodedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
            user.setPassword(encodedPassword);
            this.userRepository.save(user);

            return new ModelAndView("redirect:/User/login");
        }
        return mav;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        // Use a more sophisticated email validation regex if needed
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    private boolean isUsernameTaken(String username) {
        User existingUser = userRepository.findByUsername(username);
        return existingUser != null;
    }

    private boolean isPasswordValid(String password, String confirmPassword) {
        return password != null && password.length() >= 8 && password.equals(confirmPassword);
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login.html");
        mav.addObject("username");
        return mav;
    }
    @GetMapping("/index")
    public ModelAndView viewindex(HttpSession session) {
    ModelAndView mav = new ModelAndView("index.html");
    mav.addObject ("username", (String) session.getAttribute("username"));
    return mav;
    }

    @PostMapping("/login")
    public ModelAndView loginProcess(@RequestParam("username") String username,
            @RequestParam("password") String password, HttpSession session) {
        ModelAndView mav = new ModelAndView("login.html");

        if (username == null || password == null) {
            mav.addObject("loginError", "Please provide both username and password");
            return mav;
        }

        User dbUser = userRepository.findByUsername(username);
        if (dbUser == null) {
            mav.addObject("loginError", "Username not found");
            mav.addObject("loginErrorField", "username");
            return mav;
        }

        boolean isPasswordMatched = BCrypt.checkpw(password, dbUser.getPassword());
        if (!isPasswordMatched) {
            mav.addObject("loginError", "Incorrect password");
            mav.addObject("loginErrorField", "password");
            return mav;
        }

        // Redirect to the index page after successful login
        session.setAttribute("username", dbUser.getUsername());
        // return "Welcome " + dbUser.getUsername();
        return new ModelAndView("redirect:/User/index");

    }
   
}