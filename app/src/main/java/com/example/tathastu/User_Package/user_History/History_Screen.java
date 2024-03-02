// Event_Notifications_Screen.java

package com.example.tathastu.User_Package.user_History;

import android.content.Intent;
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
import com.example.tathastu.User_Package.user_Common_Screens.About_us_Screen;
import com.example.tathastu.User_Package.user_DashBoard.DashBoard_Screen;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class History_Screen extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    private ConnectivityReceiver connectivityReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_screen);

        // Assuming you have a RecyclerView with the id "userdata" in your layout
        RecyclerView recycle_history_Usermodel = findViewById(R.id.recycle_history_Usermodel);

        // Create a LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycle_history_Usermodel.setLayoutManager(layoutManager);

        // Create a list of UserModel_Event_Notify objects (replace these with your actual data)
        List<UserModel_History_payment> paymentList = generateDummyData();

        // Create an instance of UserAdapter_Event_Notify and set it to the RecyclerView
        UserAdapter_History_payment adapter = new UserAdapter_History_payment(paymentList);
        recycle_history_Usermodel.setAdapter(adapter);

        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        FloatingActionButton BTN_back=findViewById(R.id.BTN_back);
        //BACK
        BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(History_Screen.this, DashBoard_Screen.class);
                startActivity(i);
            }
        });


    }

    private List<UserModel_History_payment> generateDummyData() {
        // Replace this method with your actual data retrieval logic
        List<UserModel_History_payment> dummyData = new ArrayList<>();

        // Add dummy data with alternating banner images
        for (int i = 0; i < 10; i++) { // Change 5 to the number of items you want
            String moneyPayment = "₹ 12,000";
            String moneyDate = "Wednesday, 12 Feb 2023";

            UserModel_History_payment userModel = new UserModel_History_payment(moneyPayment, moneyDate);
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

    //SNACKBAR
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
