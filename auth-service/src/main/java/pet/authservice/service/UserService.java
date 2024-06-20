package pet.authservice.service;

import pet.authservice.exception.UserAlreadyExistException;
import pet.authservice.exception.UserNotFoundException;
import pet.authservice.model.User;

public interface UserService {
    User save(User user) throws UserAlreadyExistException;

    User findByEmail(String email) throws UserNotFoundException;

    boolean isPresentByEmail(String email);
}
