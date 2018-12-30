package com.codekopf.api.integration.tests;

import com.codekopf.api.application.users.OutgoingUserDTO;
import com.codekopf.api.seeds.UserLoader;
import lombok.val;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.hamcrest.Matchers.is;

@SuppressWarnings("WeakerAccess")
@Category(IntegrationTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserRestClient {
    public static final String REST_SERVICE_URI = "http://localhost:8443/api/v1/users/";

    private static HttpHeaders getHeaders(){
        String plainCredentials="John:test";
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getAllUsers(){
        val restTemplate = new RestTemplate();
        val request = new HttpEntity<>(getHeaders());
        val response = restTemplate.exchange(REST_SERVICE_URI+"/", HttpMethod.GET, request, List.class);
        val users = (List<LinkedHashMap<String, Object>>)response.getBody();

        if(users!=null){
            for(LinkedHashMap<String, Object> map : users){
                System.out.println("User : id = " + map.get("id") + ", name = " + map.get("firstName") + " "
                        + map.get("lastName") );
            }
        }else{
            System.out.println("No user exist.");
        }
    }

    @Test
    public void getUserTest(){
        val restTemplate = new RestTemplate();
        val request = new HttpEntity<>(getHeaders());
        val response = restTemplate.exchange(REST_SERVICE_URI+"/" + UserLoader.ADMIN_ID, HttpMethod.GET, request, OutgoingUserDTO.class);
        val outgoingUserDTO = response.getBody();
        Assert.assertThat(outgoingUserDTO.getId(), is(UUID.fromString(UserLoader.ADMIN_ID)));
        // System.out.println(outgoingUserDTO.toString());
    }

    @Test
    public void createUser() {
// TODO: Finish this test
//        val restTemplate = new RestTemplate();
//        restTemplate.setMessageConverters(getMessageConverters());
//        val user = new IncomingUserDTO(
//                UserFactory.FIRST_NAME,
//                UserFactory.LAST_NAME,
//                UserFactory.EMAIL,
//                UserFactory.PHONE,
//                UserFactory.PASSWORD,
//                UserFactory.ROLES_USER,
//                UserFactory.CREATOR_ID);
//        val request = new HttpEntity<>(user, getHeaders());
//        val uri = restTemplate.postForLocation(
//                REST_SERVICE_URI+"/",
//                request,
//                IncomingUserDTO.class);
//        System.out.println("Location : "+uri.toASCIIString());
    }

    @Test
    public void updateUser() {
// TODO: Finish this test
//        val restTemplate = new RestTemplate();
//        val user = new IncomingUserDTO(
//                UserFactory.FIRST_NAME,
//                UserFactory.LAST_NAME,
//                UserFactory.EMAIL,
//                UserFactory.PHONE,
//                UserFactory.PASSWORD,
//                UserFactory.ROLES_USER,
//                UserFactory.CREATOR_ID);
//        val request = new HttpEntity<Object>(user, getHeaders());
//        val response = restTemplate.exchange(
//                REST_SERVICE_URI + "/" + UserFactory.ID,
//                HttpMethod.PUT,
//                request,
//                OutgoingUserDTO.class);
//        System.out.println(response.getBody());
    }


    @Test
    public void deleteUser() {
// TODO: Finish this test
//        val restTemplate = new RestTemplate();
//        val request = new HttpEntity<String>(getHeaders());
//        restTemplate.exchange(REST_SERVICE_URI + "/" + UserFactory.ID, HttpMethod.DELETE, request, OutgoingUserDTO.class);
    }
}
