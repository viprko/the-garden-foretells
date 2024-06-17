package pet.authservice.service;

import java.util.Optional;
import pet.authservice.exception.UserAlreadyExistException;
import pet.authservice.exception.UserNotFoundException;
import pet.authservice.model.User;

public interface UserService {
    User save(User user) throws UserAlreadyExistException;

    Optional<User> findByEmail(String email) throws UserNotFoundException;
}
