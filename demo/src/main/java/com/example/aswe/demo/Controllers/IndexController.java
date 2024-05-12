package com.example.aswe.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.aswe.demo.Models.Category;
import com.example.aswe.demo.Models.Product;
import com.example.aswe.demo.Models.User;
import com.example.aswe.demo.Repositories.CategoryRepository;
import com.example.aswe.demo.Repositories.ProductRepository;
import com.example.aswe.demo.Repositories.UserRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/")

public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    // @GetMapping("/index")
    // public ModelAndView index(HttpSession session) {
    //     ModelAndView mav = new ModelAndView("index.html");
    //     mav.addObject("username",(String) session.getAttribute("username"));
    //     return mav;
    // }

    // @GetMapping("/")
    // public ModelAndView getallUsers() {
    //     ModelAndView mav = new ModelAndView("index.html");
    //     List<User> users = this.userRepository.findAll();
    //     mav.addObject("users", users);
    //     return mav;
    // }

    @GetMapping("/signup")
    public ModelAndView addUser() {
        ModelAndView mav = new ModelAndView("signup.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView saveUser(@Validated @ModelAttribute User user, @RequestParam("usertype") String userType) {
        ModelAndView mav = new ModelAndView("signup.html");
        mav.addObject("user", user);

        if (user.isEmpty(user.getFullname()) || user.isEmpty(user.getUsername()) ||
            user.isEmpty(user.getEmail()) || user.isEmpty(String.valueOf(user.getPhonenumber())) ||
            user.isEmpty(user.getPassword()) || user.isEmpty(user.getConfirmpassword())) {
            mav.addObject("emptyFieldsError", "Please fill in all fields");
            mav.addObject("hasEmptyFieldsError", true);
        } else {

            if (!user.isValidEmail(user.getEmail())) {
                mav.addObject("emailError", "Invalid email format");
                mav.addObject("hasEmailError", true);
            }
            if (isUsernameTaken(user.getUsername())) {
                mav.addObject("usernameTakenError", "Username is already taken");
                mav.addObject("hasUsernameTakenError", true);
            }

            if (!user.isPasswordValid(user.getPassword(), user.getConfirmpassword())) {
                if (user.getPassword().length() < 8) {
                    mav.addObject("passwordLengthError", "Password is too short (minimum 8 characters)");
                    mav.addObject("hasPasswordLengthError", true);
                }

                if (!user.getPassword().equals(user.getConfirmpassword())) {
                    mav.addObject("passwordMatchError", "Passwords do not match");
                    mav.addObject("hasPasswordMatchError", true);
                }
               
            }
            if (String.valueOf(user.getPhonenumber()).length() != 11) {
                mav.addObject("phoneNumberError", "Phone number must be 11 digits");
                mav.addObject("hasPhoneNumberError", true);
            }

            if (mav.getModel().containsKey("hasUsernameTakenError") ||
                    mav.getModel().containsKey("hasEmailError") ||
                    mav.getModel().containsKey("hasPasswordLengthError") ||
                    mav.getModel().containsKey("hasPasswordMatchError") ||
                    mav.getModel().containsKey("hasEmptyFieldsError")||
                    mav.getModel().containsKey("hasPhoneNumberError"))
                return mav;

            String encodedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
            user.setPassword(encodedPassword);
            String encodedConfirmPassword = BCrypt.hashpw(user.getConfirmpassword(), BCrypt.gensalt(12));
            user.setConfirmpassword(encodedConfirmPassword);
            user.setUsertype(userType);
            this.userRepository.save(user);

            return new ModelAndView("redirect:/login");
        }
        return mav;
    }

    public boolean isUsernameTaken(String username) {
        User existingUser = userRepository.findByUsername(username);
        return existingUser != null;
    }


    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login.html");
        mav.addObject("username");
        mav.addObject("usertype");
        return mav;
    }
    @GetMapping("/")
    public ModelAndView viewIndex(HttpSession session) {
        ModelAndView mav = new ModelAndView("index.html");
        mav.addObject ("username", (String) session.getAttribute("username"));
        mav.addObject ("usertype", (String) session.getAttribute("usertype"));
        List<Category> categories = this.categoryRepository.findAll();
        mav.addObject("categories", categories);
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
        mav.addObject("usertype", dbUser.getUsertype());
        return new ModelAndView("redirect:/");

    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/index")
    public ModelAndView getAllCategories() {
        ModelAndView mav = new ModelAndView("index.html");
        List<Category> categories = this.categoryRepository.findAll();
        mav.addObject("categories", categories);
        return mav;
    }

    @GetMapping("/category/{id}")
    public ModelAndView getCategory(@PathVariable("id") int id, HttpSession session, @RequestParam(defaultValue = "1") int page) {
        ModelAndView mav = new ModelAndView("category.html");
        mav.addObject ("username", (String) session.getAttribute("username"));
        Category category = this.categoryRepository.findById(id);
        if (category == null) {
            mav.addObject("errorMessage", "Category not found");
            return mav;
        }
        Page<Product> productsPage = this.productRepository.findAllByCategoryId(id, PageRequest.of(page-1, 3));

        mav.addObject("category", category);
        mav.addObject("products", productsPage.getContent());
        mav.addObject("currentPage", page); 
        mav.addObject("totalPages", productsPage.getTotalPages());
        return mav;
    }

    @GetMapping("/productDetails/{id}")
    public ModelAndView getProductInfo(@PathVariable("id") int id, HttpSession session) {
        ModelAndView mav = new ModelAndView("productDetails.html");
        mav.addObject ("username", (String) session.getAttribute("username"));

        Product product = this.productRepository.findById(id);
        mav.addObject("product", product);
        return mav;
    }
   
@GetMapping("/profile")
public ModelAndView showUserProfile(HttpSession session) {
    ModelAndView mav = new ModelAndView("profile.html");
    String username = (String) session.getAttribute("username");
    mav.addObject("username", username);
    return mav;
}


@PostMapping("/profile")
public ModelAndView updateProfile(@RequestParam("newUsername") String newUsername,
                                   @RequestParam("newFullname") String newFullname,
                                   @RequestParam("newEmail") String newEmail,
                                   @RequestParam("newPhoneNumber") String newPhoneNumber,
                                   HttpSession session) {
    String username = (String) session.getAttribute("username");
    if (username != null) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setUsername(newUsername);
            user.setFullname(newFullname);
            user.setEmail(newEmail);
            user.setPhonenumber(newPhoneNumber);
            userRepository.save(user);
            session.setAttribute("username", newUsername);
        }
    }
    return new ModelAndView("redirect:/profile");
}
@PostMapping("/profile/updatePassword")
public ModelAndView updatePassword(@RequestParam("oldPassword") String oldPassword,
                                   @RequestParam("newPassword") String newPassword,
                                   HttpSession session) {
    String username = (String) session.getAttribute("username");
    if (username != null) {
        User user = userRepository.findByUsername(username);
        if (user != null && BCrypt.checkpw(oldPassword, user.getPassword())) {
            // Check if the new password is not empty and meets any other criteria
            if (newPassword != null && !newPassword.isEmpty()) {
                // Encrypt the new password
                String encodedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
                // Update the user's password
                user.setPassword(encodedPassword);
                userRepository.save(user);
            } else {
                // Handle empty new password error
                ModelAndView mav = new ModelAndView("profile.html");
                mav.addObject("passwordUpdateError", "New password cannot be empty");
                return mav;
            }
        } else {
            // Handle incorrect old password error
            ModelAndView mav = new ModelAndView("profile.html");
            mav.addObject("passwordUpdateError", "Incorrect old password");
            return mav;
        }
    }
    // Redirect back to the profile page
    return new ModelAndView("redirect:/profile");
}

}