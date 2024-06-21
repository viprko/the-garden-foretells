package pet.authservice.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
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
    @Modifying
    public User save(User user) throws UserAlreadyExistException {
        if (isPresentByEmail(user.getEmail())) {
            throw new UserAlreadyExistException(
                    String.format("User with email {%s} already exist", user.getEmail()));
        }
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(
                String.format("User with email: {%s} not found", email)
        ));
    }

    @Override
    public boolean isPresentByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
