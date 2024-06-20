package pet.authservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pet.authservice.dto.UserLoginDto;
import pet.authservice.dto.UserRegistrationDto;
import pet.authservice.dto.UserResponseDto;
import pet.authservice.service.AuthenticationService;
import pet.authservice.service.mapper.UserMapper;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        return userMapper.toDto(
                authenticationService.register(userMapper.toEntity(userRegistrationDto)));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto login(@RequestBody UserLoginDto userLoginDto) {
        return userMapper.toDto(authenticationService.login(userMapper.toEntity(userLoginDto)));
    }
}
