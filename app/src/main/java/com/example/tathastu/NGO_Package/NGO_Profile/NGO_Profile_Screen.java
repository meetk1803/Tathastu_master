package com.example.tathastu.NGO_Package.NGO_Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_DashBoard.Profile_Screen;
import com.example.tathastu.User_Package.user_DashBoard.Update_Profile_Screen;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class NGO_Profile_Screen extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    TextInputEditText txt_Profile_Fname_ngo, txt_Profile_email_ngo, txt_Profile_mno_ngo, txt_Profile_address_ngo, txt_Profile_cat_ngo;

    // INTERNET
    private ConnectivityReceiver connectivityReceiver;

    //PROFILE IMAGE
    int SELECT_PICTURE = 200;
    ShapeableImageView img_profile_photo;
    Uri selectedImageUri;

    //EDIT PROFILE
    ExtendedFloatingActionButton BTN_Profile_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_profile_screen);

        img_profile_photo = findViewById(R.id.img_profile_photo);
        txt_Profile_Fname_ngo = findViewById(R.id.txt_Profile_Fname_ngo);
        txt_Profile_email_ngo = findViewById(R.id.txt_Profile_email_ngo);
        txt_Profile_mno_ngo = findViewById(R.id.txt_Profile_mno_ngo);
        txt_Profile_address_ngo = findViewById(R.id.txt_Profile_address_ngo);
        txt_Profile_cat_ngo = findViewById(R.id.txt_Profile_cat_ngo);
        BTN_Profile_edit = findViewById(R.id.BTN_Profile_edit);

        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        FloatingActionButton BTN_back = findViewById(R.id.BTN_back);
        //BACK
        BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BTN_Profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NGO_Profile_Screen.this, NGO_Update_Profile.class);
                startActivity(i);
            }
        });
    }

    //----------------------------------------------------------------------------------------------------

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    img_profile_photo.setImageURI(selectedImageUri);
                }
            }
        }
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
