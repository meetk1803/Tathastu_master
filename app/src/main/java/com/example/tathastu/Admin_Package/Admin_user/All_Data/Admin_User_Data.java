package com.example.tathastu.Admin_Package.Admin_user.All_Data;

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
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class Admin_User_Data extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    private ConnectivityReceiver connectivityReceiver;

    MaterialTextView txt_user_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_data);

        // Assuming you have a RecyclerView with the id "userdata" in your layout
        RecyclerView recycle_userData_model = findViewById(R.id.recycle_userData_model);

        // Create a LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycle_userData_model.setLayoutManager(layoutManager);

        // Create a list of UserModel_Event_Notify objects (replace these with your actual data)
        List<Admin_User_DataModel> userDataModelList = generateDummyData();

        // Create an instance of UserAdapter_Event_Notify and set it to the RecyclerView
        Admin_User_DataAdapter adapter = new Admin_User_DataAdapter(this, userDataModelList);
        recycle_userData_model.setAdapter(adapter);

        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        FloatingActionButton BTN_back=findViewById(R.id.BTN_back);
        txt_user_data=findViewById(R.id.txt_user_data);

        // BACK
        BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // TRANSACTION HISTORY
        txt_user_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleRecyclerViewVisibility(recycle_userData_model);
            }
        });

    }
//--------------------------------------------------------------------------------------------------

    // Function to toggle visibility of RecyclerView
    private void toggleRecyclerViewVisibility(RecyclerView recyclerView) {
        if (recyclerView.getVisibility() == View.VISIBLE) {
            recyclerView.setVisibility(View.GONE);
            txt_user_data.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.round_keyboard_arrow_down_24, 0);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            txt_user_data.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.round_keyboard_arrow_up_24, 0);
        }
    }


    private List<Admin_User_DataModel> generateDummyData() {
        List<Admin_User_DataModel> dummyData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String userName = "Fname Lname"+i;
            String userNumber = "+91 1234567890";
            String userEmail = "tathastu052threesofficial@gmail.com";
            Admin_User_DataModel userModel = new Admin_User_DataModel(userName, userNumber, userEmail);
            dummyData.add(userModel);
        }
        return dummyData;
    }

    private void showSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.custom_snackbar_layout, null);
        TextView textView = customView.findViewById(android.R.id.text1);
        textView.setText(message);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarView;
        snackbarLayout.removeAllViews();
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
