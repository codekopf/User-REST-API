package com.codekopf.api;

import com.codekopf.api.domain.users.Role;
import com.codekopf.api.domain.users.User;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

public final class UserFactory {
    public static final UUID ID = UUID.randomUUID();
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Average";
    public static final String EMAIL = "john.average@gmail.com";
    public static final String PHONE = "00420 000 000 000";
    public static final String PASSWORD = "test";
    public static final Set<Role> ROLES_USER = EnumSet.of(Role.USER);
    public static final LocalDate NOW = LocalDate.now();
    public static final UUID CREATOR_ID = UUID.randomUUID();

    public static final User USER =  new User(
            UserFactory.ID,
            UserFactory.FIRST_NAME,
            UserFactory.LAST_NAME,
            UserFactory.EMAIL,
            UserFactory.PHONE,
            UserFactory.PASSWORD,
            UserFactory.ROLES_USER,
            UserFactory.NOW,
            UserFactory.CREATOR_ID);

    public static User generateNewUser() {
            return new User(
                    UUID.randomUUID(),
                    UserFactory.FIRST_NAME,
                    UserFactory.LAST_NAME,
                    UserFactory.EMAIL,
                    UserFactory.PHONE,
                    UserFactory.PASSWORD,
                    UserFactory.ROLES_USER,
                    UserFactory.NOW,
                    UserFactory.CREATOR_ID);
    }
}
