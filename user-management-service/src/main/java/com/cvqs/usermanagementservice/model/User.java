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
    private int userId;

    @Column(name = "eMail")
    private String eMail;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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


    public User(int userId, String eMail, String userName, String password) {
        this.userId = userId;
        this.eMail = eMail;
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }
}
