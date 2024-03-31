package org.RestAPIConsumer;


import org.RestAPIConsumer.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Consumer {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        // Cookie
        ResponseEntity<String> response = restTemplate.getForEntity("http://94.198.50.185:7081/api/users", String.class);
        headers.add("Cookie", response.getHeaders().getFirst("Set-Cookie"));

        //POST
        User user = new User(3L,"James", "Brown",(byte)30);
        HttpEntity<User> entityToAdd = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseAdd = restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.POST, entityToAdd, String.class);
        String part1 = responseAdd.getBody();

        //PUT
        user.setName("Thomas");
        user.setLastName("Shelby");
        HttpEntity<User> entityToUpdate = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseUpdate = restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.PUT, entityToUpdate, String.class);
        String part2 = responseUpdate.getBody();

        //DELETE
        ResponseEntity<String> responseDelete = restTemplate.exchange("http://94.198.50.185:7081/api/users/3", HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        String part3 = responseDelete.getBody();
        System.out.println("Итоговый код: " + part1 + part2 + part3);
    }
}
