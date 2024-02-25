package com.example.tathastu;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class About_us_Screen extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    private ConnectivityReceiver connectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_screen);

        ExtendedFloatingActionButton BTN_about_tc = findViewById(R.id.BTN_about_tc);
        FloatingActionButton BTN_about_insta = findViewById(R.id.BTN_about_insta);
        FloatingActionButton BTN_about_fb = findViewById(R.id.BTN_about_fb);
        FloatingActionButton BTN_about_twitter = findViewById(R.id.BTN_about_twitter);

        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();

        // Set the listener before registering the receiver
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        BTN_about_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    String instagramLink = "https://www.instagram.com/tathastu.g052?igsh=OGQ5ZDc2ODk2ZA==\n";
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLink));
                    startActivity(i);
                } else {
                    showSnackbar(findViewById(android.R.id.content), "Please check your internet connection...");
                }
            }
        });

        BTN_about_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    String facebookLink = "https://www.facebook.com/tathastu.g052?mibextid=ZbWKwL\n";
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookLink));
                    startActivity(i);
                } else {
                    showSnackbar(findViewById(android.R.id.content), "Please check your internet connection...");
                }
            }
        });

        BTN_about_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()){
                String twitterLink = "https://twitter.com/tathastu_g052?t=MrAtvaTuvcF1hbSMDqd5_A&s=09\n";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterLink));
                startActivity(i);}
                else {
                    showSnackbar(findViewById(android.R.id.content), "Please check your internet connection...");
                }
            }
        });

        BTN_about_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(About_us_Screen.this, Terms_C_activity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the receiver to avoid memory leaks
        unregisterReceiver(connectivityReceiver);
    }

    // Helper method to check if the device is connected to the internet
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    // Helper method to show Snackbar
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
