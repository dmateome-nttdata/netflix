package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.User;
import com.everis.d4i.tutorial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public User registerManager(User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("ROLE_MANAGER");
		
		return userRepository.save(user);
	}
	
	public User register(User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("ROLE_USER");
		
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = userRepository.findByUsername(username);

		UserBuilder builder = null;
		if (usuario != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.disabled(false);
			builder.password(usuario.getPassword());
			builder.authorities(new SimpleGrantedAuthority(usuario.getRole()));

		} else
			throw new UsernameNotFoundException("Usuario no encontrado");
		return builder.build();
	}

	public User findUser(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User findUserId(int id) {
		return userRepository.findById(id);
	}
	
	public int activate(String username) {
		int a=0;
		System.out.println(username);
		User u=userRepository.findByUsername(username);
		User user=new User();
		System.out.println(u);
		user.setId(u.getId());
		user.setUsername(u.getUsername());
		user.setPassword(passwordEncoder().encode(u.getPassword()));
		user.setName(u.getName());
		user.setLastname(u.getLastname());
		user.setRole(u.getRole());
		
		if(u.isEnabled()==false) {
			user.setEnabled(true);
			a=1;
		}else {
			user.setEnabled(false);
			a=0;
		}
		
		userRepository.save(user);
		return a;
	}
	
	public void deleteUser(String username) throws Exception {
		User u = userRepository.findByUsername(username);
		userRepository.delete(u);
	}
	
	public List<User> listAllUsuarios() {
		return userRepository.findAll().stream().collect(Collectors.toList());
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
}
