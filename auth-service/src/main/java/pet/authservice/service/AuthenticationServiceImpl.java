package pet.authservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pet.authservice.exception.IncorrectPasswordException;
import pet.authservice.model.User;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final User.Role DEFAULT_ROLE = User.Role.CLIENT;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        User encodedUser = new User();
        encodedUser.setEmail(user.getEmail());
        encodedUser.setRole(DEFAULT_ROLE);
        encodedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(encodedUser);
    }

    @Override
    public User login(User user) {
        User userFromDb = userService.findByEmail(user.getEmail());
        if (passwordEncoder.matches(user.getPassword(), userFromDb.getPassword())) {
            return userFromDb;
        }
        throw new IncorrectPasswordException(String.format("Entered password for user with email:"
                + " {%s} is incorrect", user.getEmail()));
    }
}
