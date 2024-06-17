package pet.authservice.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.authservice.exception.UserAlreadyExistException;
import pet.authservice.exception.UserNotFoundException;
import pet.authservice.model.User;
import pet.authservice.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user) throws UserAlreadyExistException {

        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) throws UserNotFoundException {
        return Optional.empty();
    }
}
