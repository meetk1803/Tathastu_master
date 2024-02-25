// Event_Notifications_Screen.java

package com.example.tathastu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Event_Notifications_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_notifications_screen);

        // Assuming you have a RecyclerView with the id "userdata" in your layout
        RecyclerView recycle_Event_Usermodel = findViewById(R.id.recycle_Event_Usermodel);

        // Create a LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycle_Event_Usermodel.setLayoutManager(layoutManager);

        // Create a list of UserModel_Event_Notify objects (replace these with your actual data)
        List<UserModel_Event_Notify> eventList = generateDummyData();

        // Create an instance of UserAdapter_Event_Notify and set it to the RecyclerView
        UserAdapter_Event_Notify adapter = new UserAdapter_Event_Notify(eventList);
        recycle_Event_Usermodel.setAdapter(adapter);
    }

    private List<UserModel_Event_Notify> generateDummyData() {
        // Replace this method with your actual data retrieval logic
        List<UserModel_Event_Notify> dummyData = new ArrayList<>();

        // Add dummy data with alternating banner images
        for (int i = 0; i < 10; i++) { // Change 5 to the number of items you want
            int ngoImageResId = (i % 2 == 0) ?R.drawable.donate_food : R.drawable.donate_blood; // Change this to the correct image resource for NGO
            String ngoName = "SURACHANA EDUCATION AND CHARITABLE TRUST";
            String ngoDescription = "For donation of blood and food. Also, there is a facility for volunteering. Are you interested? Please join us.";
            String ngoAddress = "Surachana Education And Charitable Trust, B-2, City Light Apartment, Parle Point, Surat 395 007, Gujarat, India";
            String eventDate = "12/12/2024";

            UserModel_Event_Notify userModel = new UserModel_Event_Notify(ngoImageResId, ngoName, ngoDescription, ngoAddress, eventDate);
            dummyData.add(userModel);
        }

        // Add more dummy data as needed

        return dummyData;
    }
}
