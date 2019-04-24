package pl.skrzypkowski.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.skrzypkowski.shop.domain.web.UserRole;

import java.util.List;

/**
 * Created by Paweł Skrzypkowski
 * Wojskowa Akademia Techniczna im. Jarosława Dąbrowskiego, Warszawa 2018.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByUserId(Integer userId);
}
