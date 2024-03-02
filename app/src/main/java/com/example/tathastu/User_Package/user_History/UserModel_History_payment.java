package com.example.tathastu.User_Package.user_History;

// EventModel.java

public class UserModel_History_payment {
    private String moneyPayment;
    private String moneyDate;

    public UserModel_History_payment(String moneyPayment, String moneyDate) {
        this.moneyPayment = moneyPayment;
        this.moneyDate = moneyDate;
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

