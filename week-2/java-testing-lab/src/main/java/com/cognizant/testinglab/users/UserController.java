package com.cognizant.testinglab.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = service.getUserById(id);
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(service.saveUser(user));
    }
}
