package com.vaibhav.knowledgebase.services;

import com.vaibhav.knowledgebase.entity.User;
import com.vaibhav.knowledgebase.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("email already in use");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
