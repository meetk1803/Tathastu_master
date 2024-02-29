package com.example.tathastu.User_Package.user_NGO_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_DashBoard.DashBoard_Screen;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class item_NGO_Detail extends AppCompatActivity {
    private FloatingActionButton BTN_Back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cardview_ngo_in_details);
        BTN_Back=findViewById(R.id.BTN_back);

        // BACK
        BTN_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(item_NGO_Detail.this, direct_contact_to_NGO.class);
                startActivity(i);
            }
        });

        // Retrieve data from intent
        Intent intent = getIntent();
        if (intent != null) {
            // Retrieve data using intent extras
            String ngoName = intent.getStringExtra("NGO_NAME");
            String ngoAddress = intent.getStringExtra("NGO_ADDRESS");
            String ngoCategory = intent.getStringExtra("NGO_CATEGORY");
            String ngoMno = intent.getStringExtra("NGO_MNO");
            String ngoWebsite = intent.getStringExtra("NGO_WEBSITE");
            String ngoEmail = intent.getStringExtra("NGO_EMAIL");
            String ngoInstagram = intent.getStringExtra("NGO_INSTAGRAM");
            String ngoLinkedIn = intent.getStringExtra("NGO_LINKEDIN");
            String ngoFacebook = intent.getStringExtra("NGO_FACEBOOK");
            String ngoTwitter = intent.getStringExtra("NGO_TWITTER");
            String ngoYoutube = intent.getStringExtra("NGO_YOUTUBE");

            // Now you can use these values to populate your layout elements
            // Example:
            TextView txtNgoName = findViewById(R.id.txt_NGO_name);
            TextView txtNgoAddress = findViewById(R.id.txt_NGO_address);
            TextView  txtngoCategory= findViewById(R.id.txt_NGO_category);
            TextView txtNgoMno = findViewById(R.id.txt_NGO_mno);
            TextView txtNgoWebsite = findViewById(R.id.txt_NGO_website);
            TextView txtNgoEmail = findViewById(R.id.txt_NGO_email);
            TextView txtNgoInstagram = findViewById(R.id.txt_NGO_instagram);
            TextView txtNgoLinkedIn = findViewById(R.id.txt_NGO_linkedin);
            TextView txtNgoFacebook = findViewById(R.id.txt_NGO_facebook);
            TextView txtNgoTwitter = findViewById(R.id.txt_NGO_twitter);
            TextView txtNgoYoutube = findViewById(R.id.txt_NGO_youtube);


            txtNgoName.setText(ngoName);
            txtNgoAddress.setText(ngoAddress);
            txtngoCategory.setText(ngoCategory);
            txtNgoMno.setText(ngoMno);
            txtNgoWebsite.setText(ngoWebsite);
            txtNgoEmail.setText(ngoEmail);
            txtNgoInstagram.setText(ngoInstagram);
            txtNgoLinkedIn.setText(ngoLinkedIn);
            txtNgoFacebook.setText(ngoFacebook);
            txtNgoTwitter.setText(ngoTwitter);
            txtNgoYoutube.setText(ngoYoutube);


            // Do the same for other TextViews
        }
    }
}
