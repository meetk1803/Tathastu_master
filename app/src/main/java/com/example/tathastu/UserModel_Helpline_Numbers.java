package com.example.tathastu;

public class UserModel_Helpline_Numbers {
    private String name;
    private String number;
    private String category;

    public UserModel_Helpline_Numbers(String name, String number, String category) {
        this.name = name;
        this.number = number;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getCategory() {
        return category;
    }
}