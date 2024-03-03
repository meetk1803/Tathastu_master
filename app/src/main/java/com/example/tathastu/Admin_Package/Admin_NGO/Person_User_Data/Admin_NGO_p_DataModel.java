package com.example.tathastu.Admin_Package.Admin_NGO.Person_User_Data;

public class Admin_NGO_p_DataModel {
    private String name;
    private String number;
    private String email;
    private String dob;

    public Admin_NGO_p_DataModel(String name, String number, String email, String dob) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}


