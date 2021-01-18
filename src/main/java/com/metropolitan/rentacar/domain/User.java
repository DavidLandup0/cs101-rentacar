package com.metropolitan.rentacar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The type User.
 */
@Document(collection = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("email")
    private String email;

    @Field("password")
    @JsonIgnore
    private String password;

    @JsonIgnore
    private Set<Authority> authorities = new HashSet<>();


    /**
     * Instantiates a new User.
     *
     * @param id       the id
     * @param email    the email
     * @param password the password
     */
    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    /**
     * Instantiates a new User.
     *
     * @param id          the id
     * @param email       the email
     * @param password    the password
     * @param authorities the authorities
     */
    public User(String id, String email, String password, Set<Authority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Instantiates a new User.
     */
    public User(){}

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets authorities.
     *
     * @return the authorities
     */
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    /**
     * Sets authorities.
     *
     * @param authorities the authorities
     */
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
