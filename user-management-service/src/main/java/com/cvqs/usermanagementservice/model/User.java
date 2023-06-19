package com.cvqs.usermanagementservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Table(name="Users")
@Entity
@SQLDelete(sql = "UPDATE Users SET is_deleted = true WHERE id=id")
@Where(clause = "is_deleted=false")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;

    @Column(name = "eMail")
    private String eMail;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "isDeleted")
    private Boolean isDeleted;


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

    public User(Integer userId, String eMail, String userName, String password, Boolean isDeleted) {
        this.userId = userId;
        this.eMail = eMail;
        this.userName = userName;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    public User() {
    }
}
