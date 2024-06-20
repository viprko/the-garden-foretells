package pet.authservice.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String email;
    private Long id;
    private String role;
}
