package com.example.tathastu.User_Package.user_DashBoard;

public class profile_getset {

    String fname,lname,mobile,birth_of_date,email,password,profile_image;

    public profile_getset() {

    }

    public profile_getset(String fname, String lname, String mobile, String birth_of_date, String email, String password, String profile_image) {
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.birth_of_date = birth_of_date;
        this.email = email;
        this.password = password;
        this.profile_image = profile_image;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirth_of_date() {
        return birth_of_date;
    }

    public void setBirth_of_date(String birth_of_date) {
        this.birth_of_date = birth_of_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

}
