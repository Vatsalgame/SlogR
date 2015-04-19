package com.slogr.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing a user
 *
 * @author vatsalgame
 */
public class User {

    private String email;
    private String password;

    public User() {
        // Jackson deserialization
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }
}
