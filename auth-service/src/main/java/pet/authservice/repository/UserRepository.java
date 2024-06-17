package pet.authservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.authservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT User FROM Users u WHERE u.email=:email")
    Optional<User> findByEmail(String email);
}
