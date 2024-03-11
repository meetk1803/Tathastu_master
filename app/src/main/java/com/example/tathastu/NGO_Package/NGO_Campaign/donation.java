package com.example.tathastu.NGO_Package.NGO_Campaign;

public class donation {

    private String amount;
    private String contact_no;
    private String date;
    private String email;
    private String name;
    private String transaction_id;

    public donation(){
    }

    public donation(String amount, String contact_no, String date, String email, String name, String transaction_id) {
        this.amount = amount;
        this.contact_no = contact_no;
        this.date = date;
        this.email = email;
        this.name = name;
        this.transaction_id = transaction_id;
    }

    public String getAmount() {
        return amount;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
}
