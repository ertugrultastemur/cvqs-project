package com.cvqs.usermanagementservice.model;

import com.cvqs.usermanagementservice.model.token.Token;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

/**
 * Model class representing a user.
 */
@Table(name="users")
@Entity
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE id=id")
@Where(clause = "is_deleted=false")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
    private List<Token> tokens;

    /**
     * Returns the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the roles assigned to the user.
     *
     * @return the roles assigned to the user
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles assigned to the user.
     *
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Returns the tokens associated with the user.
     *
     * @return the tokens associated with the user
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * Sets the tokens associated with the user.
     *
     * @param tokens the tokens to set
     */
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the deletion status of the user.
     *
     * @return the deletion status of the user
     */
    public Boolean getDeleted() {
        return isDeleted;
    }

    /**
     * Sets the deletion status of the user.
     *
     * @param deleted the deletion status to set
     */
    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * Constructs a new User object.
     *
     * @param id       the ID of the user
     * @param email    the email of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param isDeleted the deletion status of the user
     * @param roles    the roles assigned to the user
     * @param tokens   the tokens associated with the user
     */
    public User(Integer id, String email, String username, String password, Boolean isDeleted, List<Role> roles, List<Token> tokens) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isDeleted = isDeleted;
        this.roles = roles;
        this.tokens = tokens;
    }

    /**
     * Constructs a new User object.
     */
    public User() {
    }
}
