package com.cvqs.usermanagementservice.dto;

public class UserIdDto {

    private Integer userId;

    private String userName;

    public UserIdDto(Integer userId, String userName) {
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
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
