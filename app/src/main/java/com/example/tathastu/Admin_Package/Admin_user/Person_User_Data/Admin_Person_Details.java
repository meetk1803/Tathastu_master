package com.example.tathastu.Admin_Package.Admin_user.Person_User_Data;

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

public class Admin_Person_Details extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    private ConnectivityReceiver connectivityReceiver;

    MaterialTextView txt_userPersonal_data;
    MaterialTextView txt_userHistory_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_person_details);


        // Assuming you have a RecyclerView with the id "userdata" in your layout
        RecyclerView recycle_userPersonal_model = findViewById(R.id.recycle_userPersonal_model);
        RecyclerView recycle_userHistory_model = findViewById(R.id.recycle_userHistory_model);

        // Create a LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManagerPersonal = new LinearLayoutManager(this);
        LinearLayoutManager layoutManagerHistory = new LinearLayoutManager(this);

        // Set layout managers for respective RecyclerViews
        recycle_userPersonal_model.setLayoutManager(layoutManagerPersonal);
        recycle_userHistory_model.setLayoutManager(layoutManagerHistory);

        // Create a list of UserModel_Event_Notify objects (replace these with your actual data)
        List<Admin_person_DataModel> userDataModelList = generateDummyData();

        // Create an instance of UserAdapter_Event_Notify and set it to the RecyclerView
        Admin_person_DataAdapter adapter = new Admin_person_DataAdapter(userDataModelList);
        recycle_userPersonal_model.setAdapter(adapter);

        List<Admin_person_History_DataModel> userHistoryDataModelList = generateDummyDataHistory();

        // Create an instance of UserAdapter_Event_Notify and set it to the RecyclerView
        Admin_person_History_DataAdapter historyadapter = new Admin_person_History_DataAdapter(userHistoryDataModelList);
        recycle_userHistory_model.setAdapter(historyadapter);

        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        FloatingActionButton BTN_back=findViewById(R.id.BTN_back);
        txt_userPersonal_data=findViewById(R.id.txt_userPersonal_data);
        txt_userHistory_data=findViewById(R.id.txt_userHistory_data);


        //BACK
        BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i =new Intent(History_Screen.this, DashBoard_Screen.class);
//                startActivity(i);
                finish();
            }
        });

        //PERSONAL INFO
        txt_userPersonal_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleRecyclerViewVisibility(recycle_userPersonal_model);
            }
        });

//TRANSACTION HISTORY
        txt_userHistory_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleRecyclerHistoryViewVisibility(recycle_userHistory_model);
            }
        });


    }
//--------------------------------------------------------------------------------------------------

    // Function to toggle visibility of RecyclerView
    private void toggleRecyclerViewVisibility(RecyclerView recyclerView) {
        if (recyclerView.getVisibility() == View.VISIBLE) {
            recyclerView.setVisibility(View.GONE);
            txt_userPersonal_data.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.round_keyboard_arrow_down_24, 0);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            txt_userPersonal_data.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.round_keyboard_arrow_up_24, 0);
        }
    }
    // Function to toggle visibility of RecyclerView
    private void toggleRecyclerHistoryViewVisibility(RecyclerView recyclerView) {
        if (recyclerView.getVisibility() == View.VISIBLE) {
            recyclerView.setVisibility(View.GONE);
            txt_userHistory_data.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.round_keyboard_arrow_down_24, 0);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            txt_userHistory_data.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.round_keyboard_arrow_up_24, 0);
        }
    }

    //dummy data FOR PERSONAL INFO
    private List<Admin_person_DataModel> generateDummyData() {
        // Replace this method with your actual data retrieval logic
        List<Admin_person_DataModel> dummyData = new ArrayList<>();

        // Add dummy data with alternating banner images
        for (int i = 0; i < 1; i++) { // Change 5 to the number of items you want
            String userName="Fname Lname";
            String userNumber="+91 1234567890";
            String userEmail="tathastu052threesofficial@gmail.com";
            String userDob="12/12/2003";

            Admin_person_DataModel userModel = new Admin_person_DataModel(userName,userNumber,userEmail,userDob);
            dummyData.add(userModel);
        }

        // Add more dummy data as needed

        return dummyData;
    }

    //dummy data FOR HISTORY
    private List<Admin_person_History_DataModel> generateDummyDataHistory() {
        // Replace this method with your actual data retrieval logic
        List<Admin_person_History_DataModel> dummyHistoryData = new ArrayList<>();

        // Add dummy data with alternating banner images
        for (int i = 0; i < 10; i++) { // Change 5 to the number of items you want
            String campName="donated to,\nBlood Donation Camp";
            String moneyPayment="12000";
            String Dob="12/12/2003";

            Admin_person_History_DataModel userHistoryModel = new Admin_person_History_DataModel(campName,moneyPayment,Dob);
            dummyHistoryData.add(userHistoryModel);
        }

        // Add more dummy data as needed

        return dummyHistoryData;
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
