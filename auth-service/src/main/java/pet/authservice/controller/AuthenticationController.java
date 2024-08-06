package pet.authservice.controller;

import jakarta.validation.Valid;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pet.authservice.dto.UserLoginDto;
import pet.authservice.dto.UserRegistrationDto;
import pet.authservice.dto.UserResponseDto;
import pet.authservice.model.User;
import pet.authservice.security.JwtTokenProvider;
import pet.authservice.service.AuthenticationService;
import pet.authservice.service.mapper.UserMapper;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        return userMapper.toDto(
                authenticationService.register(userMapper.toEntity(userRegistrationDto)));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto) {
        User user = authenticationService.login(userMapper.toEntity(userLoginDto));
        String token = jwtTokenProvider.createToken(user);
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck () {
        return new ResponseEntity<>("Auth service is up", HttpStatus.OK);
    }
}
