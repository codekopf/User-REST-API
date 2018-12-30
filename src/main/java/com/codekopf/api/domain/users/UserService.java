package com.codekopf.api.domain.users;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(final User user) {
        this.userRepository.store(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(final UUID id) {
        return this.userRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public void updateUser(final User userNew) {
        createUser(userNew);
    }

    public void deleteUser(final UUID id) {
        this.userRepository.delete(id);
    }
}
