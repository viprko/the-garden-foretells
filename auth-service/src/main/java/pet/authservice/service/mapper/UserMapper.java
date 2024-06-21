package pet.authservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pet.authservice.config.MapperConfig;
import pet.authservice.dto.UserLoginDto;
import pet.authservice.dto.UserRegistrationDto;
import pet.authservice.dto.UserResponseDto;
import pet.authservice.model.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    @Mappings({
            @Mapping(source = "role", target = "role", qualifiedByName = "roleToString")
    })
    UserResponseDto toDto(User user);

    User toEntity(UserRegistrationDto userRegistrationDto);

    User toEntity(UserLoginDto userLoginDto);

    default String roleToString(User.Role role) {
        return role != null ? role.name() : null;
    }
}
