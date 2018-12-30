package com.codekopf.api.domain.users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T> {
    void store(final T t);

    List<T> findAll();

    Optional<T> findById(final UUID id);

    void delete(final UUID id);
}
