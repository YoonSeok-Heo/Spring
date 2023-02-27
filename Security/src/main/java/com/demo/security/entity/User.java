package com.demo.security.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Data
public class User {

    @Id
    private String email;
    private String password;
    private String name;
    private String role;

    public void encryptPassword(PasswordEncoder passwordEncoder){
        password = passwordEncoder.encode(password);
    }
}
