package pl.skrzypkowski.shop.service;

import org.springframework.context.annotation.Primary;
import pl.skrzypkowski.shop.domain.web.CustomUserDetails;
import pl.skrzypkowski.shop.domain.web.Role;
import pl.skrzypkowski.shop.domain.web.User;
import pl.skrzypkowski.shop.domain.web.UserRole;
import pl.skrzypkowski.shop.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skrzypkowski.shop.repository.UserRoleRepository;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Override
    @Transactional(readOnly = true)
    public CustomUserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Get roles of user
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roles = userRoleRepository.findAllByUserId(user.getId()).stream().map(UserRole::getRole).collect(Collectors.toSet());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);
        customUserDetails.setAuthorities(authorities);
        
        return customUserDetails;
    }

}