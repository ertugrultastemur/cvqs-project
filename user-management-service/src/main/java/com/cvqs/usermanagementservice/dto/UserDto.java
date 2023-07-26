package com.cvqs.usermanagementservice.dto;

import com.cvqs.usermanagementservice.model.Role;
import com.cvqs.usermanagementservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * DTO class for a user.
 */
public class UserDto {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private int id;

    private String username;

    private String email;

    private String password;

    private Boolean isDeleted;

    private List<Role> roles;

    /**
     * Constructs a UserDto object with the specified attributes.
     *
     * @param id the user ID
     * @param username the username
     * @param email the email
     * @param password the password
     * @param isDeleted the deleted status of the user
     */
    public UserDto(int id, String username, String email, String password, Boolean isDeleted) {
        logger.info("UserIdDto: ctor entered.");
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    /**
     * Constructs a UserDto object with the specified attributes.
     *
     * @param id the user ID
     * @param username the username
     * @param email the email
     * @param password the password
     * @param isDeleted the deleted status of the user
     * @param roles the roles of the user
     */
    public UserDto(int id, String username, String email, String password, Boolean isDeleted, List<Role> roles) {
        logger.info("UserIdDto: ctor entered.");
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isDeleted = isDeleted;
        this.roles = roles;
    }

    /**
     * Default constructor for the UserDto class.
     */
    public UserDto() {
    }

    /**
     * Converts a User object to a UserDto object.
     *
     * @param user the User object to convert
     * @return the converted UserDto object
     */
    public static UserDto convert(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getDeleted(),
                user.getRoles()
        );
    }

    /**
     * Returns the roles of the user.
     *
     * @return the roles of the user
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles of the user.
     *
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
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
     * Returns the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        logger.info("UserDto: getEmail entered.");
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        logger.info("UserDto: setEmail entered.");
        this.email = email;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        logger.info("UserDto: getPassword entered.");
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        logger.info("UserDto: setPassword entered.");
        this.password = password;
    }

    /**
     * Returns the deleted status of the user.
     *
     * @return the deleted status of the user
     */
    public Boolean getDeleted() {
        logger.info("UserDto: getDeleted entered.");
        return isDeleted;
    }

    /**
     * Sets the deleted status of the user.
     *
     * @param deleted the deleted status to set
     */
    public void setDeleted(Boolean deleted) {
        logger.info("UserDto: setDeleted entered.");
        isDeleted = deleted;
    }
}
