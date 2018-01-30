package com.MyApp.util;

public class Validator {
    private String name;
    private String email;
    private String password;
    private String repassword;

    public Validator(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Validator(String name, String email, String password, String repassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepassword() {
        return repassword;
    }

    public boolean getValidateAll() {
        return Check.validateAll(getName(), getEmail(), getPassword(), getRepassword());
    }

    public boolean getSuccessLogin() {
        return Check.validateForLogin(getEmail(), getPassword());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
