package com.example.tathastu.NGO_Package.NGO_History;

// EventModel.java

public class NGOModel_History_payment {
    private String moneyPayment;
    private String moneyDate;

    public NGOModel_History_payment(String moneyPayment, String moneyDate) {
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

