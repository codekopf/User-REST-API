package com.codekopf.api.infrastructure.users;

import com.codekopf.api.domain.users.User;
import com.codekopf.api.domain.users.UserRepository;
import lombok.val;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.*;

public class UserRepositoryInMemory implements UserRepository {

    private final Set<User> records;

    public UserRepositoryInMemory() {
        this.records = new HashSet<>();
    }

    @Override
    public void store(final User storeUser) {
        for(User user: this.records) {
            if(Objects.equals(user.getId(), storeUser.getId())) {
                this.records.remove(user);
            }
        }
        this.records.add(storeUser);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.records);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return this.records.stream().filter(user -> Objects.equals(user.getId(), id)).findFirst();
    }

    @Override
    public void delete(UUID id) {
        val user = this.findById(id);
        if(!user.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        this.records.remove(user.get());
    }
}
