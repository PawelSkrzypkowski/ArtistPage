package pl.skrzypkowski.shop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.skrzypkowski.shop.domain.api.User;
import pl.skrzypkowski.shop.service.RoleService;
import pl.skrzypkowski.shop.service.UserService;

@RestController
public class ApiUserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping(value = "/api/v1/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		return new ResponseEntity<User>(userService.checkLogin(user), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/api/v1/register")
	public ResponseEntity<Boolean> register(@RequestBody User user) {
		pl.skrzypkowski.shop.domain.web.User dbUser = new pl.skrzypkowski.shop.domain.web.User();
		dbUser.setName(user.getName());
		dbUser.setEmail(user.getEmail());
		dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
		dbUser.addRole(roleService.findByName("ROLE_CUSTOMER"));
		
    	return new ResponseEntity<Boolean>(userService.register(dbUser), HttpStatus.CREATED);
	}
	
}
