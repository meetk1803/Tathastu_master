package com.example.tathastu.User_Package.user_DashBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.Blood_Section.B_Home_Screen;
import com.example.tathastu.User_Package.Blood_Section.B_Home_Screen_2;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Food_Selection_Screen extends AppCompatActivity  implements ConnectivityReceiver.ConnectivityReceiverListener{
    private ConnectivityReceiver connectivityReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection_screen);
        CardView BTN_food_person=findViewById(R.id.BTN_food_person);
        CardView BTN_food_camp=findViewById(R.id.BTN_food_camp);

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
                finish();
            }
        });

        //PERSON REQUIREMENT
        BTN_food_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Food_Selection_Screen.this, B_Home_Screen.class);
                startActivity(i);
            }
        });

        //CAMP REQUIREMENT
        BTN_food_camp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Food_Selection_Screen.this, B_Home_Screen_2.class);
                startActivity(i);
            }
        });

    }

    //----------------------------------------------------------------------------------------------------

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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