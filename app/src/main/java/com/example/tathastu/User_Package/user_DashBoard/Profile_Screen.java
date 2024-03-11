package com.example.tathastu.User_Package.user_DashBoard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profile_Screen extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    //ALL
    TextInputEditText txt_Profile_Fname, txt_Profile_email, txt_Profile_mno, txt_Profile_dob;

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
        setContentView(R.layout.activity_profile_screen);

        img_profile_photo = findViewById(R.id.img_profile_photo);
        txt_Profile_Fname = findViewById(R.id.txt_Profile_Fname);
        txt_Profile_email = findViewById(R.id.txt_Profile_email);
        txt_Profile_mno = findViewById(R.id.txt_Profile_mno);
        txt_Profile_dob = findViewById(R.id.txt_Profile_dob);
        BTN_Profile_edit = findViewById(R.id.BTN_Profile_edit);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        DatabaseReference referenceprofile = FirebaseDatabase.getInstance().getReference("user");

        referenceprofile.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                profile_getset userpro = snapshot.getValue(profile_getset.class);
                if (userpro != null) {
                    Picasso.get().load(userpro.getProfile_image()).into(img_profile_photo);
                    txt_Profile_Fname.setText(userpro.getFname()+" "+userpro.getLname());
                    txt_Profile_email.setText(userpro.getEmail());
                    txt_Profile_mno.setText(userpro.getMobile());
                    txt_Profile_dob.setText(userpro.getBirth_of_date());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

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


        BTN_Profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Profile_Screen.this, Update_Profile_Screen.class);
                startActivity(i);
            }
        });
    }

//------------------------------------------------------------------

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

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
