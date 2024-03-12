package com.example.tathastu.Admin_Package.Admin_Feedback;

public class Admin_All_Feedback_DataModel {
    private String name;
    private String email;
    private String feedback_msg;


    public Admin_All_Feedback_DataModel() {
    }

    public Admin_All_Feedback_DataModel(String name, String email, String feedback_msg) {
        this.name = name;
        this.email = email;
        this.feedback_msg = feedback_msg;
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

    public String getFeedback_msg() {
        return feedback_msg;
    }

    public void setFeedback_msg(String feedback_msg) {
        this.feedback_msg = feedback_msg;
    }
}