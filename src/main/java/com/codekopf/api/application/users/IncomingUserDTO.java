package com.codekopf.api.application.users;

import com.codekopf.api.domain.users.Role;
import com.codekopf.api.domain.users.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("WeakerAccess")
public final class IncomingUserDTO {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String password;
    private final LocalDate createdAt;
    private final Set<Role> roles;
    private final UUID createdBy;

    @JsonCreator
    public IncomingUserDTO(@JsonProperty("firstName") final String firstName,
                           @JsonProperty("lastName") final String lastName,
                           @JsonProperty("email") final String email,
                           @JsonProperty("phone") final String phone,
                           @JsonProperty("password") final String password,
                           @JsonProperty("roles") final Set<Role> roles,
                           @JsonProperty("createdBy") final UUID createdBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.createdAt = LocalDate.now();
        this.roles = roles;
        this.createdBy = createdBy;
    }

    public User toDomainObject() {
        return new User(UUID.randomUUID(),
                this.firstName,
                this.lastName,
                this.email,
                this.phone,
                this.password,
                this.roles,
                this.createdAt,
                this.createdBy);
    }
}
