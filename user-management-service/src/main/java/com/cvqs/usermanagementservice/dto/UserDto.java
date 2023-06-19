package com.cvqs.usermanagementservice.dto;

import com.cvqs.usermanagementservice.model.User;

public class UserDto {

    private UserIdDto id;

    private String eMail;

    private String password;

    private Boolean isDeleted = Boolean.FALSE;

    public UserDto(UserIdDto id, String eMail, String password, Boolean isDeleted) {
        this.id = id;
        this.eMail = eMail;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    public UserDto() {
    }

    public static UserDto convert(User user){
        UserIdDto userIdDto=null;
        if (user.getUserId()!=null){
            userIdDto = UserIdDto.convert(user.getUserId(), user.getUserName());
        }
        return new UserDto(
                userIdDto,
                user.geteMail(),
                user.getPassword(),
                user.getDeleted()
        );
    }

    public UserIdDto getId() {
        return id;
    }

    public void setId(UserIdDto id) {
        this.id = id;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
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
