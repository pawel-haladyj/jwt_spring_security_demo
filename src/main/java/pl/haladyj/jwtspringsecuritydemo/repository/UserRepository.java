package pl.haladyj.jwtspringsecuritydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.haladyj.jwtspringsecuritydemo.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserNameOrEmail(String UserName, String email);
    Optional<User> findByUserName(String username);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
}
