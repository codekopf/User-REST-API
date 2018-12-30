package com.codekopf.api.domain.users;

import com.codekopf.api.UserFactory;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class UserTest {

    private User user;
    
    @Before
    public void setUp(){
        this.user = UserFactory.USER;
    }

    @Test
    public void testConstructor(){
        Assert.assertThat(this.user.getId(), is(UserFactory.ID));
        Assert.assertThat(this.user.getFirstName(), is(UserFactory.FIRST_NAME));
        Assert.assertThat(this.user.getLastName(), is(UserFactory.LAST_NAME));
        Assert.assertThat(this.user.getEmail(), is(UserFactory.EMAIL));
        Assert.assertThat(this.user.getPhone(), is(UserFactory.PHONE));
        Assert.assertThat(this.user.getPassword(), is(UserFactory.PASSWORD));
        Assert.assertThat(this.user.getRoles(), is(UserFactory.ROLES_USER));
        Assert.assertThat(this.user.getCreatedAt(), is(UserFactory.NOW));
        Assert.assertThat(this.user.getCreatedBy(), is(UserFactory.CREATOR_ID));
    }

    @Test
    public void testMethod_EqualsAndHash(){
        val otherUser = new User(
                UserFactory.ID,
                UserFactory.FIRST_NAME,
                UserFactory.LAST_NAME,
                UserFactory.EMAIL,
                UserFactory.PHONE,
                UserFactory.PASSWORD,
                UserFactory.ROLES_USER,
                UserFactory.NOW,
                UserFactory.CREATOR_ID);
        Assert.assertThat(this.user, is(otherUser));
    }
}
