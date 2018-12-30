package com.codekopf.api.application.users;

import com.codekopf.api.domain.users.Role;
import com.codekopf.api.domain.users.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of="id")
public final class OutgoingUserDTO {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String password;
    private final Set<Role> roles;
    private final LocalDate createdAt;
    private final UUID createdBy;

    // Constructor only for testing purposes
    @JsonCreator
    public OutgoingUserDTO(@JsonProperty("id") final UUID id,
                           @JsonProperty("firstName") final String firstName,
                           @JsonProperty("lastName") final String lastName,
                           @JsonProperty("email") final String email,
                           @JsonProperty("phone") final String phone,
                           @JsonProperty("password") final String password,
                           @JsonProperty("roles") final Set<Role> roles,
                           @JsonProperty("createdAt") final LocalDate createdAt,
                           @JsonProperty("createdBy") final UUID createdBy) {
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

    public OutgoingUserDTO(final User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.createdAt = user.getCreatedAt();
        this.createdBy = user.getCreatedBy();
    }

    // Constructor only for testing purposes
    @Override
    public String toString() {
        return "User: " + this.id + " - " + this.firstName + " " + this.lastName + ", " + this.email + ", "
                + this.phone + "; password: " + this.password;
    }
}
