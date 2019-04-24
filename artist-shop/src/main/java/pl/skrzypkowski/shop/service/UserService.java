package pl.skrzypkowski.shop.service;

import java.util.List;

import pl.skrzypkowski.shop.domain.web.Role;
import pl.skrzypkowski.shop.domain.web.User;

public interface UserService {

	List<User> findAll();
	
	User findOne(Integer id);
	
	long countAll();
	
	void delete(Integer id);
	
	pl.skrzypkowski.shop.domain.api.User checkLogin(pl.skrzypkowski.shop.domain.api.User user);
	
	boolean register(User user, Role role);
	
}
