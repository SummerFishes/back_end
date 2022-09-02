package com.example.stocktrade.entity;

public class User {
    private int userId;
    private String username;
    private String password;
    private int flag;
    private int authority;
    private String telephone;

    public User(int userId, String username, String password, int flag, int authority,
                String telephone) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.flag = flag;
        this.authority = authority;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", flag=" + flag +
                ", authority=" + authority +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
