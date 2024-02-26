package com.example.tathastu.User_Package.user_Event;

// EventModel.java

public class UserModel_Event_Notify {
    private int ngoImageResId; // Resource ID for the NGO image
    private String ngoName;
    private String ngoDescription;
    private String ngoAddress;
    private String eventDate;

    public UserModel_Event_Notify( int ngoImageResId, String ngoName, String ngoDescription, String ngoAddress, String eventDate) {
        this.ngoImageResId = ngoImageResId;
        this.ngoName = ngoName;
        this.ngoDescription = ngoDescription;
        this.ngoAddress = ngoAddress;
        this.eventDate = eventDate;
    }


    public int getNgoImageResId() {
        return ngoImageResId;
    }

    public String getNgoName() {
        return ngoName;
    }

    public String getNgoDescription() {
        return ngoDescription;
    }

    public String getNgoAddress() {
        return ngoAddress;
    }

    public String getEventDate() {
        return eventDate;
    }
}

