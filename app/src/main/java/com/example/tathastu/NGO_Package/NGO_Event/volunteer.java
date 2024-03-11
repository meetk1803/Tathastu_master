package com.example.tathastu.NGO_Package.NGO_Event;

public class volunteer {

    private String user_id;
    private String name;
    private String email;
    private String contact_no;
    private String address;
    private String age;

    public volunteer(){
    }

    public volunteer(String user_id, String name, String email, String contact_no, String address, String age) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.contact_no = contact_no;
        this.address = address;
        this.age = age;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }


}
