package com.example.admin.modules;

public class User {
    private String username;
    private String password;
    private char isEnable;
    private char isAccountNonExpired;
    private char isAccountNonLocked;
    private char isCredentialsNonExpired;

    public User() {
    }

    public User(String username, String password, char isEnable, char isAccountNonExpired, char isAccountNonLocked, char isCredentialsNonExpired) {
        this.username = username;
        this.password = password;
        this.isEnable = isEnable;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
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

    public char getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(char isEnable) {
        this.isEnable = isEnable;
    }

    public char getIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setIsAccountNonExpired(char isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public char getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(char isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public char getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setIsCredentialsNonExpired(char isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isEnable='" + isEnable + '\'' +
                ", isAccountNonExpired='" + isAccountNonExpired + '\'' +
                ", isAccountNonLocked='" + isAccountNonLocked + '\'' +
                ", isCredentialsNonExpired='" + isCredentialsNonExpired + '\'' +
                '}';
    }
}
