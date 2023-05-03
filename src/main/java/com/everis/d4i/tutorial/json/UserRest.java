package com.everis.d4i.tutorial.json;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRest {

    private int id;
    private String username;
    private String password;
    private String name, lastname, email;
    private long phone;
    private String role;
    private String token;


}
