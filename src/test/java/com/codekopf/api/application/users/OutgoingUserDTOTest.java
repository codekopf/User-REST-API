package com.codekopf.api.application.users;

import com.codekopf.api.UserFactory;
import com.codekopf.api.domain.users.User;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class OutgoingUserDTOTest {

    private OutgoingUserDTO outgoingUserDTO;

    @Before
    public void setUp(){
       val user = UserFactory.USER;
        this.outgoingUserDTO = new OutgoingUserDTO(user);
    }

    @Test
    public void testConstructor(){
        Assert.assertThat(this.outgoingUserDTO.getId(), is(UserFactory.ID));
        Assert.assertThat(this.outgoingUserDTO.getFirstName(), is(UserFactory.FIRST_NAME));
        Assert.assertThat(this.outgoingUserDTO.getLastName(), is(UserFactory.LAST_NAME));
        Assert.assertThat(this.outgoingUserDTO.getEmail(), is(UserFactory.EMAIL));
        Assert.assertThat(this.outgoingUserDTO.getPhone(), is(UserFactory.PHONE));
        Assert.assertThat(this.outgoingUserDTO.getPassword(), is(UserFactory.PASSWORD));
        Assert.assertThat(this.outgoingUserDTO.getRoles(), is(UserFactory.ROLES_USER));
        Assert.assertThat(this.outgoingUserDTO.getCreatedAt(), is(UserFactory.NOW));
        Assert.assertThat(this.outgoingUserDTO.getCreatedBy(), is(UserFactory.CREATOR_ID));
    }

    @Test
    public void testMethod_EqualsAndHashCode(){
        val otherOutgoingUserDTO = new OutgoingUserDTO(new User(
                UserFactory.ID,
                UserFactory.FIRST_NAME,
                UserFactory.LAST_NAME,
                UserFactory.EMAIL,
                UserFactory.PHONE,
                UserFactory.PASSWORD,
                UserFactory.ROLES_USER,
                UserFactory.NOW,
                UserFactory.CREATOR_ID));
        Assert.assertThat(this.outgoingUserDTO, is(otherOutgoingUserDTO));
    }
}