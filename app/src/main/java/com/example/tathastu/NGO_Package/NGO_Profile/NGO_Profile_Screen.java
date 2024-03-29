package com.example.tathastu.NGO_Package.NGO_Profile;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NGO_Profile_Screen extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    TextInputEditText txt_Profile_Fname_ngo, txt_Profile_email_ngo, txt_Profile_mno_ngo, txt_Profile_address_ngo, txt_Profile_cat_ngo;

    MaterialTextView txt_btn_NGO_add_social;
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

        fetchProfileData();

        img_profile_photo = findViewById(R.id.img_profile_photo);
        txt_Profile_Fname_ngo = findViewById(R.id.txt_Profile_Fname_ngo);
        txt_Profile_email_ngo = findViewById(R.id.txt_Profile_email_ngo);
        txt_Profile_mno_ngo = findViewById(R.id.txt_Profile_mno_ngo);
        txt_Profile_address_ngo = findViewById(R.id.txt_Profile_address_ngo);
        txt_Profile_cat_ngo = findViewById(R.id.txt_Profile_cat_ngo);
        BTN_Profile_edit = findViewById(R.id.BTN_Profile_edit);
        txt_btn_NGO_add_social =findViewById(R.id.txt_btn_NGO_add_social);

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


        txt_btn_NGO_add_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NGO_Profile_Screen.this,NGO_Social_Media.class));
            }
        });
    }

    private void fetchProfileData() {
        SharedPreferences sharedPreferences1 = getSharedPreferences("USER",MODE_PRIVATE);
        String userId = sharedPreferences1.getString("userId","");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ngo").child(userId);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    NGO_Profile_Model data = snapshot.getValue(NGO_Profile_Model.class);
                    String fname = data.getFname();
                    String email = data.getEmail();
                    String mno=data.getMobile();
                    String address=data.getAddress();
                    String type=data.getType();
                    String photo=data.getPhoto();

                    txt_Profile_Fname_ngo.setText(fname);
                    txt_Profile_email_ngo.setText(email);
                    txt_Profile_mno_ngo.setText(mno);
                    txt_Profile_address_ngo.setText(address);
                    txt_Profile_cat_ngo.setText(type);

                    Glide.with(NGO_Profile_Screen.this)
                            .load(photo)
                            .centerCrop()
                            .into(img_profile_photo);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

