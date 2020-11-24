package com.example.news.Model;

public class Account {
    private String name;
    private String email;
    private String address;
    private String birthday;
    private String sex;
    private String password;

    public Account(String name, String email, String address, String birthday, String sex, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.sex = sex;
        this.password = password;
    }

    public Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
