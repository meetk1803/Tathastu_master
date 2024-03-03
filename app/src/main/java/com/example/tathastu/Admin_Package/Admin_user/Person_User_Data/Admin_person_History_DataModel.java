package com.example.tathastu.Admin_Package.Admin_user.Person_User_Data;

// EventModel.java

public class Admin_person_History_DataModel {

    private String campName;
    private String moneyPayment;
    private String moneyDate;

    public Admin_person_History_DataModel(String campName,String moneyPayment, String moneyDate) {
        this.campName = campName;
        this.moneyPayment = moneyPayment;
        this.moneyDate = moneyDate;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getMoneyPayment() {
        return moneyPayment;
    }

    public void setMoneyPayment(String moneyPayment) {
        this.moneyPayment = moneyPayment;
    }

    public String getMoneyDate() {
        return moneyDate;
    }

    public void setMoneyDate(String moneyDate) {
        this.moneyDate = moneyDate;
    }
}

