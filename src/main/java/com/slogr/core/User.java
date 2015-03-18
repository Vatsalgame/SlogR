package com.slogr.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing a user
 *
 * @author vatsalgame
 */
public class User {

    private Long id;
    private String email;
    private String password;

    public User() {
        // Jackson deserialization
    }

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
}
