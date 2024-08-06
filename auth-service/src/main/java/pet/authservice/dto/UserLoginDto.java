package pet.authservice.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    private String email;
    private String password;
}
