package com.codekopf.api.infrastructure.users;

import com.codekopf.api.domain.users.UserRepository;

public class UserRepositoryInMemoryTest extends AbstractUserRepositoryTest {

    @Override
    protected UserRepository initRepository() {
        return new UserRepositoryInMemory();
    }
}
