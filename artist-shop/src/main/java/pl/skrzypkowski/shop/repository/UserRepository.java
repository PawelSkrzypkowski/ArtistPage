package pl.skrzypkowski.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.transaction.annotation.Transactional;
import pl.skrzypkowski.shop.domain.web.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u")
	List<User> findAll();
	
	@Query("select u from User u where u.email = ?1")
	User findByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.active=false where u.id=?1")
	void delete(Integer id);
}
