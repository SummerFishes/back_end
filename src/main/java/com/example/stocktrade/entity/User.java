package com.example.stocktrade.entity;

import java.math.BigInteger;

public class User {
    private int user_id;
    private String username;
    private String password;
    private int flag;
    private int authority;
    private String telephone;

    public User(int user_id, String username, String password, int flag, int authority, String telephone) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.flag = flag;
        this.authority = authority;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", flag=" + flag +
                ", authority=" + authority +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
