package com.everis.d4i.tutorial.repositories;

import com.everis.d4i.tutorial.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable> {
	public abstract User findByUsername(String username);
	public abstract User findById(int id);
}