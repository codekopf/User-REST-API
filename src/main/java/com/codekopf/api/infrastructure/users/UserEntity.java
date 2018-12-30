package com.codekopf.api.infrastructure.users;

import com.codekopf.api.domain.users.Role;
import com.codekopf.api.domain.users.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("WeakerAccess")
@Entity
@Getter
@Table(name="users")
@EqualsAndHashCode(of="id")
public class UserEntity {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    private LocalDate createdAt;
    private UUID createdBy;

    @UsedByJpa
    private UserEntity(){
    }

    public UserEntity(final User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.password = user.getPassword();
        this.roles = EnumSet.copyOf(user.getRoles());
        this.createdAt = user.getCreatedAt();
        this.createdBy = user.getCreatedBy();
    }

    public User convertToDomainObject() {
        return new User(
                this.id,
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
