package com.product.ecommerce.commons;

import com.product.ecommerce.DTOs.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class AuthenticationCommons {
    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public UserDto validateToken(String token) {
        //Call UserService validateToken API to validate.

        ResponseEntity<UserDto> response = restTemplate.postForEntity(
                "http://localhost:8080/users/validate/" + token,
                null,
                UserDto.class
        );

        if (response.getBody() == null) {
            //Token is invalid.
            return null;
        }

        return response.getBody();
    }
}