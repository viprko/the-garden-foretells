package pet.authservice.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class UserResponseDto {
    private String email;
    private UUID id;
    private String role;
}
