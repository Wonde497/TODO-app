package com.example.myapplication6;

public class UserModel {
   public String email;



    public String  name;
    public String password;
    public String confirm_password;
    public String number ;
    public UserModel(String email, String name, String password, String confirm_password, String number) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.confirm_password = confirm_password;
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
