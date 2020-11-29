package com.eic.springchatapp.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.eic.springchatapp.api.repository.UserRepository;
import com.eic.springchatapp.model.User;



@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
	
	
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
	
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
     
        User user = userRepository.findByName(name);
        
        
        if ( userRepository.findByName(name) == null    ||   !user.getPassword().equals(password) ) {
        	
            throw new BadCredentialsException("Bad Credentials");

        }else  {
        	

        	List<GrantedAuthority> rolesList = new ArrayList<GrantedAuthority>();
            rolesList.add(new SimpleGrantedAuthority("USER"));

        	UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                    name, password,
                    rolesList);
        	
        	
        	
        		return result;
        }
        
		
		
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
