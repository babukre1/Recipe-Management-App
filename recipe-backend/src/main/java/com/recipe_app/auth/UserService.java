package com.recipe_app.auth;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
//    private final PasswordEncoder encoder;

    public UserService(UserRepository repo) {
        this.repo = repo;
//        this.encoder = encoder;
    }


    // save a user
    public void createUser(User user) {
        repo.save(user);
    }

    public User getUserByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
