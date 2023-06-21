package com.cvqs.usermanagementservice.dto;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserIdDto {

Logger logger = LoggerFactory.getLogger(this.getClass());
    private Integer userId;

    private String userName;

    public UserIdDto(Integer userId, String userName) {
        logger.info("UserIdDto ctor entered.");
        this.userId = userId;
        this.userName = userName;
    }

    public UserIdDto() {
    }

    public static UserIdDto convert(Integer userId, String userName){
        return new UserIdDto(
                userId,
                userName
        );
    }

    public Integer getUserId() {
        logger.info("UserIdDto: getUserId entered.");
        return userId;
    }

    public void setUserId(Integer userId) {
        logger.info("UserIdDto: setUserId entered.");
        this.userId = userId;
    }

    public String getUserName() {
        logger.info("UserIdDto: getUserName entered.");
        return userName;
    }

    public void setUserName(String userName) {
        logger.info("UserIdDto: setUserName entered.");
        this.userName = userName;
    }
}
