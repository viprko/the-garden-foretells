package pet.authservice.service.mapper;

import org.mapstruct.Mapper;
import pet.authservice.config.MapperConfig;
import pet.authservice.dto.UserLoginDto;
import pet.authservice.dto.UserRegistrationDto;
import pet.authservice.dto.UserResponseDto;
import pet.authservice.model.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toEntity(UserRegistrationDto userRegistrationDto);

    User toEntity(UserLoginDto userLoginDto);
}
