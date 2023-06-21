package com.cvqs.usermanagementservice.dto;

import com.cvqs.usermanagementservice.model.Role;
import com.cvqs.usermanagementservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDto {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserIdDto id;

    private String eMail;

    private String password;

    private Boolean isDeleted;

    private Role role;

    public UserDto(UserIdDto id, String eMail, String password, Boolean isDeleted) {
        logger.info("UserIdDto: ctor entered.");
        this.id = id;
        this.eMail = eMail;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    public UserDto(UserIdDto id, String eMail, String password, Boolean isDeleted,Role role) {
        logger.info("UserIdDto: ctor entered.");
        this.id = id;
        this.eMail = eMail;
        this.password = password;
        this.isDeleted = isDeleted;
        this.role = role;
    }

    public UserDto() {
    }

    public static UserDto convert(User user){
        UserIdDto userIdDto = UserIdDto.convert(user.getUserId(), user.getUserName());
        return new UserDto(
                userIdDto,
                user.getEMail(),
                user.getPassword(),
                user.getDeleted(),
                user.getRole()
        );
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserIdDto getId() {
        logger.info("UserDto: getId entered.");
        return id;
    }

    public void setId(UserIdDto id) {
        logger.info("UserDto: setId entered.");

        this.id = id;
    }

    public String geteMail() {
        logger.info("UserDto: geteMail entered.");
        return eMail;
    }

    public void seteMail(String eMail) {
        logger.info("UserDto: seteMail entered.");
        this.eMail = eMail;
    }

    public String getPassword() {
        logger.info("UserDto: getPassword entered.");
        return password;
    }

    public void setPassword(String password) {
        logger.info("UserDto: setPassword entered.");
        this.password = password;
    }

    public Boolean getDeleted() {
        logger.info("UserDto: getDeleted entered.");
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        logger.info("UserDto: setDeleted entered.");
        isDeleted = deleted;
    }
}
