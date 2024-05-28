// package com.example.aswe.userservice.Controllers;

// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.bcrypt.BCrypt;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.aswe.userservice.Models.User;
// import com.example.aswe.userservice.Repositories.UserRepository;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpSession;

// @RestController
// @RequestMapping("/User")
// public class UserController {
//     @Autowired
//     private UserRepository userRepositry;
   
//     @GetMapping("")
//     public ResponseEntity getUsers(){    //Respone Entity btraga3 jason
//         List<User> users= this.userRepositry.findAll(); 
//         return new ResponseEntity(users, HttpStatus.OK);
//     }

//     // @PostMapping("Registration")
//     // public ResponseEntity createPost(@RequestBody Map<String,String> body){
//     //     User myUser = new User(); 
        
//     //     this.userRepositry.save(myUser); 
//     //     return new ResponseEntity(myUser, HttpStatus.CREATED);
//     // }

//     @PostMapping("/signup")
//     public ResponseEntity<?> saveUser(@Validated @RequestBody User user, @RequestParam("usertype") String userType, HttpSession session, HttpServletRequest request) {
//         if (user.isEmpty(user.getFullname()) || user.isEmpty(user.getUsername()) ||
//             user.isEmpty(user.getEmail()) || user.isEmpty(String.valueOf(user.getPhonenumber())) ||
//             user.isEmpty(user.getPassword()) || user.isEmpty(user.getConfirmpassword())) {
//             return new ResponseEntity<>("Please fill in all fields", HttpStatus.BAD_REQUEST);
//         }

//         if (!user.isValidEmail(user.getEmail())) {
//             return new ResponseEntity<>("Invalid email format", HttpStatus.BAD_REQUEST);
//         }

//         if (isUsernameTaken(user.getUsername())) {
//             return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
//         }

//         if (!user.isPasswordValid(user.getPassword(), user.getConfirmpassword())) {
//             if (user.getPassword().length() < 8) {
//                 return new ResponseEntity<>("Password is too short (minimum 8 characters)", HttpStatus.BAD_REQUEST);
//             }

//             if (!user.getPassword().equals(user.getConfirmpassword())) {
//                 return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
//             }
//         }

//         if (String.valueOf(user.getPhonenumber()).length() != 11) {
//             return new ResponseEntity<>("Phone number must be 11 digits", HttpStatus.BAD_REQUEST);
//         }

//         String encodedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
//         user.setPassword(encodedPassword);
//         String encodedConfirmPassword = BCrypt.hashpw(user.getConfirmpassword(), BCrypt.gensalt(12));
//         user.setConfirmpassword(encodedConfirmPassword);
//         user.setUsertype(userType);
//         this.userRepositry.save(user);

//         //session.setAttribute("username", user.getUsername());
//         //logUserActivity(session, request.getRequestURI());

//         return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
//     }

//     private boolean isUsernameTaken(String username) {
//         User existingUser = userRepositry.findByUsername(username);
//         return existingUser != null;
//     }

//     // private void logUserActivity(HttpSession session, String pageVisited) {
//     //     String username = (String) session.getAttribute("username");

//     //     // Skip logging if username is null or "admin"
//     //     if (username != null && !"admin".equals(username)) {
//     //         Date loginTime = new Date();

//     //         UserLog userLog = new UserLog();
//     //         userLog.setUserId(username);
//     //         userLog.setLoginTime(loginTime);
//     //         userLog.setPageVisited(pageVisited);

//     //         try {
//     //             userLogRepository.save(userLog);
//     //         } catch (Exception e) {
//     //             System.err.println("Failed to save user log: " + e.getMessage());
//     //         }
//     //     }
//     // }
// }
