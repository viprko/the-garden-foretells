package pet.authservice.service;

import static org.springframework.security.core.userdetails.User.withUsername;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pet.authservice.exception.UserNotFoundException;
import pet.authservice.model.User;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findByEmail(username);
            return withUsername(username)
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        } catch (UserNotFoundException ex) {
            throw new UsernameNotFoundException(String.format("User with email: {%s} not found",
                    username));
        }
    }
}
