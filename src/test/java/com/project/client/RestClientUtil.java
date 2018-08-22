package com.project.client;

import com.project.entity.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class RestClientUtil {

    public void getUserByIdDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-app/user/user/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, User.class, 1);
        User user = responseEntity.getBody();
        System.out.println("Id:"+user.getUserId()+", Last Name:"+user.getLastName()
                +", First Name:"+user.getFirstName()
                +", E-mail:"+user.getEmail());
    }

    public void getAllUsersDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-app/user/users";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<User[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, User[].class);
        User[] users = responseEntity.getBody();
        for(User user : users) {
            System.out.println("Id:"+user.getUserId()+", Last Name:"+user.getLastName()
                    +", First Name:"+user.getFirstName()
                    +", E-mail:"+user.getEmail());
        }
    }

    public void addUserDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-app/user/user";
        User objUser = new User();
        objUser.setLastName("Rackham");
        objUser.setFirstName("John");
        objUser.setEmail("john.rackham@gmail.com");
        HttpEntity<User> requestEntity = new HttpEntity<>(objUser, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }

    public void updateUserDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-app/user/user";
        User objUser = new User();
        objUser.setUserId(1);
        objUser.setLastName("Silver");
        objUser.setFirstName("John");
        objUser.setEmail("john.silver@yahoo.com");
        HttpEntity<User> requestEntity = new HttpEntity<>(objUser, headers);
        restTemplate.put(url, requestEntity);
    }

    public void deleteUserDemo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-app/user/user/{id}";
        HttpEntity<User> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);
    }

    public static void main(String args[]) {
        RestClientUtil util = new RestClientUtil();
        util.getUserByIdDemo();
        util.addUserDemo();
        util.updateUserDemo();
        util.deleteUserDemo();
        util.getAllUsersDemo();
    }

}
