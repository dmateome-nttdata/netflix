package com.everis.d4i.tutorial.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    private String name, lastname, email;
    private long phone;
    private boolean enabled;
    private String role;
    private String token;
}
