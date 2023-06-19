package com.cvqs.usermanagementservice.dto;

public class CreateUserRequestDto {

    private Integer userId;

    private String eMail;

    private String userName;

    private String password;

    private Boolean isDeleted;

    public CreateUserRequestDto(Integer userId, String eMail, String userName, String password, Boolean isDeleted) {
        this.userId = userId;
        this.eMail = eMail;
        this.userName = userName;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    public CreateUserRequestDto() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
