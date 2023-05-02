package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.entities.User;
import com.everis.d4i.tutorial.services.impl.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public User login(@RequestParam("user") String username,
			@RequestParam("password") String pwd) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, pwd));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = userService.findUser(username);
		String token = getJWTToken(username);
		user.setToken(token);
		return user;

	}

	@PostMapping("/register")
	private User saveUser(@RequestBody User user) {
		return userService.register(user);
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		User u=userService.findUser(username);
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(u.getRole());

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
