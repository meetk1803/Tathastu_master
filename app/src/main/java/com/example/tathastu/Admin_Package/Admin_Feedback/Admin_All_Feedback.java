package com.example.tathastu.Admin_Package.Admin_Feedback;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Admin_All_Feedback extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    private ConnectivityReceiver connectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_all_feedback);

        // Assuming you have a RecyclerView with the id "recycle_feedback" in your layout
        RecyclerView recycleFeedback = findViewById(R.id.recycle_feedback);

        // Create a LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // Set layout managers for respective RecyclerViews
        recycleFeedback.setLayoutManager(layoutManager);

        // Create a list of Admin_All_Feedback_DataModel objects (replace these with your actual data)
        List<Admin_All_Feedback_DataModel> feedbackDataModelList = generateDummyData();

        // Create an instance of Admin_All_Feedback_DataAdapter and set it to the RecyclerView
        Admin_All_Feedback_DataAdapter adapter = new Admin_All_Feedback_DataAdapter(this, feedbackDataModelList);
        recycleFeedback.setAdapter(adapter);

        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        FloatingActionButton BTN_back = findViewById(R.id.BTN_back);

        // BACK
        BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //------------------------------------------------------------------------------------------------------------------
    // Dummy data for personal info
    private List<Admin_All_Feedback_DataModel> generateDummyData() {
        // Replace this method with your actual data retrieval logic
        List<Admin_All_Feedback_DataModel> dummyData = new ArrayList<>();

        // Add dummy data with alternating banner images
        for (int i = 0; i < 10; i++) {
            String userName = "Fname Lname";
            String userEmail = "tathastu052threesofficial@gmail.com";
            String msg = "Embrace growth, conquer challenges, and radiate positivity. In the journey of life, resilience is your greatest ally. Cherish every moment, learn from setbacks, and evolve into the best version of yourself. Illuminate the path with kindness and determination. #LifeJourney";

            Admin_All_Feedback_DataModel userModel = new Admin_All_Feedback_DataModel(userName, userEmail, msg);
            dummyData.add(userModel);
        }

        // Add more dummy data as needed

        return dummyData;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the receiver to avoid memory leaks
        unregisterReceiver(connectivityReceiver);
    }

    // Snackbar
    private void showSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();

        // Inflate custom layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.custom_snackbar_layout, null);

        // Set text
        TextView textView = customView.findViewById(android.R.id.text1);
        textView.setText(message);

        // Add custom view to Snackbar
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarView;
        snackbarLayout.removeAllViews(); // Remove all default views
        snackbarLayout.setPadding(1, 1, 1, 1);
        snackbarLayout.addView(customView, 0);

        snackbar.show();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            showSnackbar(findViewById(android.R.id.content), "Please check your internet connection...");
        }
    }
}
