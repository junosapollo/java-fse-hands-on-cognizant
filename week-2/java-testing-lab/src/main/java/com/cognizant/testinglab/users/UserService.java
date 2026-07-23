package com.cognizant.testinglab.users;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User requireUser(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> findByName(String name) {
        return repository.findByName(name);
    }
}
