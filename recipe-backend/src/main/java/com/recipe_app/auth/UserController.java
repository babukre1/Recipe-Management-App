package com.recipe_app.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> CreateUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signIn(@RequestBody User user) {
        User userFound = userService.getUserByUsername(user.getUsername());
        if (userFound != null && userFound.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body(userFound);
        } else {
            // Handle incorrect credentials or user not found
            // You might want to return a different HTTP status code, e.g., UNAUTHORIZED or NOT_FOUND
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
