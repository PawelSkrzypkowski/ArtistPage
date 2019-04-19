package pl.skrzypkowski.shop.service;

import pl.skrzypkowski.shop.domain.web.Role;

public interface RoleService {

	Role findByName(String name);
	
}
