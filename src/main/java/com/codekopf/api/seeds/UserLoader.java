package com.codekopf.api.seeds;

import com.codekopf.api.domain.users.Role;
import com.codekopf.api.domain.users.User;
import com.codekopf.api.domain.users.UserRepository;
import com.codekopf.api.domain.users.UserService;
import com.github.javafaker.Faker;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Component
public class UserLoader {

    public static final String ADMIN_ID = "51a7dda1-4984-43d4-b625-0000959a0b3e";

    private final UserService userService;

    // TODO: Fix issue with double loggers
    private static final Logger logger = LoggerFactory.getLogger(UserLoader.class);

    @Autowired
    public UserLoader(UserRepository userRepository) {
        this.userService = new UserService(userRepository);
        LoadUsers();
    }

    private void LoadUsers() {
        logger.info("Loading users: ");

        userService.createUser(generateAdmin());

        for(int i = 0; i < 10; i++) {
            userService.createUser(generateNewUser());
        }
        List<User> userList = userService.findAll();

        logger.info("User size: {}", userList.size());
        userList.forEach(user -> logger.info(user.toString()));
    }

    private User generateAdmin() {
        return new User(UUID.fromString(UserLoader.ADMIN_ID),
                "John",
                "Doe",
                "john.doe@gmail.com",
                "000000000",
                "test",
                EnumSet.of(Role.ADMIN, Role.MODERATOR, Role.USER),
                LocalDate.now(),
                UUID.fromString(UserLoader.ADMIN_ID));
    }

    private User generateNewUser() {
        val faker = new Faker();
        return new User(UUID.randomUUID(),
                faker.firstName(),
                faker.lastName(),
                faker.firstName() + "." + faker.lastName() + "@gmail.com",
                faker.phoneNumber(),
                generatePassword(),
                EnumSet.of(Role.USER),
                LocalDate.now(),
                UUID.fromString(UserLoader.ADMIN_ID));
    }

    private String generatePassword() {
        val chars = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
        return IntStream.range(0,5)
                .mapToObj(i -> ThreadLocalRandom.current().nextInt(0, chars.length()))
                .map(chars::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
