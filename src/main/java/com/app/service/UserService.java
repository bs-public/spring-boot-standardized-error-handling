package com.app.service;

import com.app.entity.User;
import com.app.exception.DuplicateEmailException;
import com.app.exception.UserNotFoundException;
import com.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User createUser(User user) {
    userRepository
        .findByEmail(user.getEmail())
        .ifPresent(
            u -> {
              throw new DuplicateEmailException("Email already exists: " + user.getEmail());
            });
    return userRepository.save(user);
  }

  public User getUser(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User updateUser(Long id, User updatedUser) {
    User user = getUser(id);
    user.setName(updatedUser.getName());
    user.setEmail(updatedUser.getEmail());
    return userRepository.save(user);
  }

  public void deleteUser(Long id) {
    User user = getUser(id);
    userRepository.delete(user);
  }
}
