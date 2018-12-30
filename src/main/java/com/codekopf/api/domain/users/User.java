package com.codekopf.api.domain.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of="id")
public final class User {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String password;
    private final Set<Role> roles;
    private final LocalDate createdAt;
    private final UUID createdBy;

    public User(final UUID id,
                final String firstName,
                final String lastName,
                final String email,
                final String phone,
                final String password,
                final Set<Role> roles,
                final LocalDate createdAt,
                final UUID createdBy) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.roles = roles;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public User update(final User newUser) {
        return new User(
                this.id,
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getEmail(),
                newUser.getPhone(),
                newUser.getPassword(),
                newUser.getRoles(),
                this.getCreatedAt(),
                this.getCreatedBy()
        );
    }
}
