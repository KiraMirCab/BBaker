package es.iessoterohernandez.BBaker.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import es.iessoterohernandez.BBaker.model.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long userID;
    private String userEmail;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String userFName;
    private String userLName;

    public UserDTO(Long userID, String userEmail, UserRole userRole, String userFName, String userLName) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.userFName = userFName;
        this.userLName = userLName;
    }

    public UserDTO() {}
}
