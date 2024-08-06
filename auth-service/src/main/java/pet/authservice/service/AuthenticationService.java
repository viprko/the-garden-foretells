package pet.authservice.service;

import pet.authservice.model.User;

public interface AuthenticationService {
    User register(User user);

    User login(User user);
}
