package pet.authservice.dto;

import lombok.Data;
import pet.authservice.lib.ValidEmail;
import pet.authservice.lib.ValidPassword;
import pet.authservice.lib.ValidRepeatPassword;

@Data
@ValidRepeatPassword
public class UserRegistrationDto {
    @ValidEmail
    private String email;
    @ValidPassword
    private String password;
    private String repeatPassword;
}
