package com.codekopf.api.application.users;

import com.codekopf.api.UserFactory;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IncomingUserDTOTest {

    @Test
    public void testMethod_toDomainObject(){
        val incomingUserDTO = new IncomingUserDTO(
                UserFactory.FIRST_NAME,
                UserFactory.LAST_NAME,
                UserFactory.EMAIL,
                UserFactory.PHONE,
                UserFactory.PASSWORD,
                UserFactory.ROLES_USER,
                UserFactory.CREATOR_ID);
        val user = incomingUserDTO.toDomainObject();
        assertThat(user.getId()).isNotNull();
        assertThat(user.getFirstName()).isEqualTo(UserFactory.FIRST_NAME);
        assertThat(user.getLastName()).isEqualTo(UserFactory.LAST_NAME);
        assertThat(user.getEmail()).isEqualTo(UserFactory.EMAIL);
        assertThat(user.getPhone()).isEqualTo(UserFactory.PHONE);
        assertThat(user.getPassword()).isEqualTo(UserFactory.PASSWORD);
        assertThat(user.getRoles()).isEqualTo(UserFactory.ROLES_USER);
        assertThat(user.getCreatedAt()).isNotNull();
        assertThat(user.getCreatedBy()).isEqualTo(UserFactory.CREATOR_ID);
    }
}
