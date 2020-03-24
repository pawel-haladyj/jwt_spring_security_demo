package pl.haladyj.jwtspringsecuritydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.haladyj.jwtspringsecuritydemo.model.Role;
import pl.haladyj.jwtspringsecuritydemo.model.RoleName;
import pl.haladyj.jwtspringsecuritydemo.model.User;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName (RoleName roleName);
}
