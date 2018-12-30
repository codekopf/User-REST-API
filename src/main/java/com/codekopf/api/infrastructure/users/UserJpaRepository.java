package com.codekopf.api.infrastructure.users;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface UserJpaRepository extends PagingAndSortingRepository<UserEntity, UUID> {
}
