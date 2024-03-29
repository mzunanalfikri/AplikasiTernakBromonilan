package com.rfid.ternakbromonilan.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Entity(name="akun")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Transient
    private Collection<String> roles = Collections.singletonList("ROLE_USER");

    public User(){

    }

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, Collection<String> roles){
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }
}
