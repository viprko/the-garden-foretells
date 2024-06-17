package pet.authservice.service;

import pet.authservice.dto.UserLoginDto;
import pet.authservice.dto.UserRegistrationDto;
import pet.authservice.model.User;

public interface AuthenticationService {
    User register(UserRegistrationDto userRegistrationDto);

    User login(UserLoginDto userLoginDto);
}
