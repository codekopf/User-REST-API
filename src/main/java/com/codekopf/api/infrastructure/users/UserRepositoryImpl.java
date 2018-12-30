package com.codekopf.api.infrastructure.users;

import com.codekopf.api.domain.users.User;
import com.codekopf.api.domain.users.UserRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserRepositoryImpl(final UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public void store(User user) {
        this.userJpaRepository.save(new UserEntity(user));
    }

    @Override
    public List<User> findAll() {
        return IteratorUtils.toList(this.userJpaRepository.findAll().iterator())
                .stream()
                .sorted(Comparator.comparing(UserEntity::getLastName))
                .map(UserEntity::convertToDomainObject)
                .collect(toList());
    }

    @Override
    public Optional<User> findById(UUID id) {
        return this.userJpaRepository.findById(id).map(UserEntity::convertToDomainObject);
    }

    @Override
    public void delete(UUID id) {
        this.userJpaRepository.deleteById(id);
    }
}
