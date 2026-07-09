package com.cognizant.microservices.users;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public AppUser findById(@PathVariable Long id) {
        return loadUser(id);
    }

    @PostMapping
    public ResponseEntity<AppUser> create(@Valid @RequestBody UserRequest request) {
        AppUser user = userRepository.save(new AppUser(request.name(), request.email()));
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }

    @PutMapping("/{id}")
    public AppUser update(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        AppUser user = loadUser(id);
        user.setName(request.name());
        user.setEmail(request.email());
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        AppUser user = loadUser(id);
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    private AppUser loadUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found: " + id));
    }
}
