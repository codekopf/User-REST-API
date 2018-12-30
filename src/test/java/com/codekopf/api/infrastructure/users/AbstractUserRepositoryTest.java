package com.codekopf.api.infrastructure.users;

import com.codekopf.api.UserFactory;
import com.codekopf.api.domain.users.User;
import com.codekopf.api.domain.users.UserRepository;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.LinkedList;
import java.util.UUID;

import static org.hamcrest.Matchers.is;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public abstract class AbstractUserRepositoryTest {

    private User user;

    private UserRepository userRepository;

    @Before
    public final void setup() {
        this.user = UserFactory.USER;
        this.userRepository = initRepository();
    }

    protected abstract UserRepository initRepository();

    @Test
    public final void testStore() {
        this.userRepository.store(this.user);
        Assert.assertThat(this.userRepository.findById(this.user.getId()).get().getId(), is(this.user.getId()));
    }

    @Test
    public final void testStore_twoDifferentUsers() {
        val id2 = UUID.randomUUID();
        val user2 = new User(
                id2,
                UserFactory.FIRST_NAME,
                UserFactory.LAST_NAME,
                UserFactory.EMAIL,
                UserFactory.PHONE,
                UserFactory.PASSWORD,
                UserFactory.ROLES_USER,
                UserFactory.NOW,
                UserFactory.CREATOR_ID);
        this.userRepository.store(this.user);
        this.userRepository.store(user2);
        Assert.assertThat(this.userRepository.findAll().size(), is(2));
        Assert.assertThat(this.userRepository.findById(this.user.getId()).get().getId(), is(this.user.getId()));
        Assert.assertThat(this.userRepository.findById(user2.getId()).get().getId(), is(user2.getId()));
    }

    @Test
    public final void testUpdate(){
        this.userRepository.store(this.user);
        val storedUser = this.userRepository.findById(this.user.getId()).get();
        val changedEmail = "super.awesome@email.com";
        val updatedUser = new User(
                storedUser.getId(),
                storedUser.getFirstName(),
                storedUser.getLastName(),
                changedEmail,
                storedUser.getPhone(),
                storedUser.getPassword(),
                storedUser.getRoles(),
                storedUser.getCreatedAt(),
                storedUser.getCreatedBy());
        this.userRepository.store(updatedUser);
        Assert.assertThat(this.userRepository.findById(this.user.getId()).get().getEmail(), is(changedEmail));
    }

    @Test
    public final void testDelete() {
        val id2 = UUID.randomUUID();
        val user2 = new User(
                id2,
                UserFactory.FIRST_NAME,
                UserFactory.LAST_NAME,
                UserFactory.EMAIL,
                UserFactory.PHONE,
                UserFactory.PASSWORD,
                UserFactory.ROLES_USER,
                UserFactory.NOW,
                UserFactory.CREATOR_ID);
        this.userRepository.store(this.user);
        this.userRepository.store(user2);
        Assert.assertThat(this.userRepository.findAll().size(), is(2));
        this.userRepository.delete(user2.getId());
        Assert.assertThat(this.userRepository.findAll().size(), is(1));
        Assert.assertThat(this.userRepository.findById(this.user.getId()).get().getId(), is(this.user.getId()));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public final void testDelete_failsOnEmptyStorage() {
        this.userRepository.delete(this.user.getId());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public final void testDelete_failsOnNonEmptyStorage() {
        padDataIntoRepo();
        this.userRepository.delete(UUID.randomUUID());
    }

    @Test
    public final void testFindAll_onNonEmptyStorage() {
        val user1 = UserFactory.generateNewUser();
        val user2 = UserFactory.generateNewUser();
        val user3 = UserFactory.generateNewUser();
        val allUsers = new LinkedList<User>();
        allUsers.add(user1);
        allUsers.add(user2);
        allUsers.add(user3);
        this.userRepository.store(user1);
        this.userRepository.store(user2);
        this.userRepository.store(user3);
        Assert.assertThat(this.userRepository.findAll().size(), is(allUsers.size()));
        Assert.assertThat(this.userRepository.findById(user1.getId()).get().getId(), is(user1.getId()));
        Assert.assertThat(this.userRepository.findById(user2.getId()).get().getId(), is(user2.getId()));
        Assert.assertThat(this.userRepository.findById(user3.getId()).get().getId(), is(user3.getId()));
    }

    @Test
    public final void testFindAll_onEmptyStorage() {
        Assert.assertThat(this.userRepository.findAll().size(), is(0));
    }

    @Test
    public final void testFindById() {
        this.userRepository.store(this.user);
        val foundUser = this.userRepository.findById(this.user.getId());
        Assert.assertThat(foundUser.get().getId(), is(this.user.getId()));
    }

    @Test
    public final void testFindById_fails() {
        val foundUser = this.userRepository.findById(this.user.getId());
        Assert.assertThat(foundUser.isPresent(), is(false));
    }

    private void padDataIntoRepo() {
        val user1 = UserFactory.generateNewUser();
        val user2 = UserFactory.generateNewUser();
        val user3 = UserFactory.generateNewUser();
        this.userRepository.store(user1);
        this.userRepository.store(user2);
        this.userRepository.store(user3);
    }
}
