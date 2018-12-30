package com.codekopf.api.infrastructure.users;

import com.codekopf.api.UserFactory;
import com.codekopf.api.domain.users.User;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class UserEntityTest {

    private User user;

    @Before
    public void setUp(){
        this.user = UserFactory.USER;
    }

    @Test
    public void testConstructor(){
        val userEntity = new UserEntity(this.user);
        Assert.assertThat(userEntity.getId(), is(UserFactory.ID));
        Assert.assertThat(userEntity.getFirstName(), is(UserFactory.FIRST_NAME));
        Assert.assertThat(userEntity.getLastName(), is(UserFactory.LAST_NAME));
        Assert.assertThat(userEntity.getEmail(), is(UserFactory.EMAIL));
        Assert.assertThat(userEntity.getPhone(), is(UserFactory.PHONE));
        Assert.assertThat(userEntity.getPassword(), is(UserFactory.PASSWORD));
        Assert.assertThat(userEntity.getRoles(), is(UserFactory.ROLES_USER));
        Assert.assertThat(userEntity.getCreatedAt(), is(UserFactory.NOW));
        Assert.assertThat(userEntity.getCreatedBy(), is(UserFactory.CREATOR_ID));
    }

    @Test
    public void testMethod_convertToDomainObject(){
        val userEntity = new UserEntity(this.user);
        val user = userEntity.convertToDomainObject();
        Assert.assertThat(user.getId(), is(UserFactory.ID));
        Assert.assertThat(user.getFirstName(), is(UserFactory.FIRST_NAME));
        Assert.assertThat(user.getLastName(), is(UserFactory.LAST_NAME));
        Assert.assertThat(user.getEmail(), is(UserFactory.EMAIL));
        Assert.assertThat(user.getPhone(), is(UserFactory.PHONE));
        Assert.assertThat(user.getPassword(), is(UserFactory.PASSWORD));
        Assert.assertThat(user.getRoles(), is(UserFactory.ROLES_USER));
        Assert.assertThat(user.getCreatedAt(), is(UserFactory.NOW));
        Assert.assertThat(user.getCreatedBy(), is(UserFactory.CREATOR_ID));
    }

    @Test
    public void testMethod_EqualsAndHashCode(){
        val userEntity = new UserEntity(this.user);
        val otherUserEntity = new UserEntity(this.user);
        Assert.assertThat(userEntity, is(otherUserEntity));
    }
}
