package es.iessoterohernandez.BBaker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeRoleRequest {
    private String email;
    private String role;

    public ChangeRoleRequest(String email, String role) {
        this.email = email;
        this.role = role;
    }
}
