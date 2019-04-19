package pl.skrzypkowski.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import pl.skrzypkowski.shop.domain.web.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByName(String name);

}
