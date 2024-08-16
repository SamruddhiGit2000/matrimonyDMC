package com.vivah.app.model;

import javax.persistence.*;

@Entity
@Table(name = "admin_users")
public class AdminUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",nullable = false, unique = true)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;


    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}


