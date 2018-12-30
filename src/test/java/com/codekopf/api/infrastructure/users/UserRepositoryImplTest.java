package com.codekopf.api.infrastructure.users;

import com.codekopf.api.domain.users.UserRepository;
import com.codekopf.api.integration.tests.IntegrationTests;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Category(IntegrationTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserRepositoryImplTest extends AbstractUserRepositoryTest {

    @Autowired
    private UserRepositoryImpl repository;


    @Override
    protected UserRepository initRepository() {
        return this.repository;
    }
}
