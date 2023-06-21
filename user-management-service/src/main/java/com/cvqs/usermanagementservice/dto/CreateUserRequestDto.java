package com.cvqs.usermanagementservice.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateUserRequestDto {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String eMail;

    private String userName;

    private String password;


    public CreateUserRequestDto(String eMail, String userName, String password) {
        logger.info("CreateRequestDto: ctor entered.");
        this.eMail = eMail;
        this.userName = userName;
        this.password = password;
    }

    public CreateUserRequestDto() {
    }


    public String getEMail() {
        logger.info("CreateUserRequestDto: getEMail entered.");
        return eMail;
    }

    public void setEMail(String eMail) {
        logger.info("CreateUserRequestDto: setEMail entered.");

        this.eMail = eMail;
    }

    public String getUserName() {
        logger.info("CreateUserRequestDto: getUserName entered.");

        return userName;
    }

    public void setUserName(String userName) {
        logger.info("CreateUserRequestDto: setUserName entered.");
        this.userName = userName;
    }

    public String getPassword() {
        logger.info("CreateUserRequestDto: getPassword entered.");
        return password;
    }

    public void setPassword(String password) {
        logger.info("CreateUserRequestDto: setPassword entered.");
        this.password = password;
    }

}
