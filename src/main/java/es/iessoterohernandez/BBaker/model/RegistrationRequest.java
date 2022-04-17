package es.iessoterohernandez.BBaker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class RegistrationRequest {
    
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
