package pet.authservice.service;

import org.springframework.stereotype.Service;
import pet.authservice.dto.UserLoginDto;
import pet.authservice.dto.UserRegistrationDto;
import pet.authservice.model.User;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public User register(UserRegistrationDto userRegistrationDto) {

        return null;
    }

    @Override
    public User login(UserLoginDto userLoginDto) {
        return null;
    }
}
