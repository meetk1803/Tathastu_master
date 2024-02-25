package com.example.tathastu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Terms_C_activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_c_activity);
        FloatingActionButton BTN_tc_insta=findViewById(R.id.BTN_tc_insta);
        FloatingActionButton BTN_tc_fb=findViewById(R.id.BTN_tc_fb);
        FloatingActionButton BTN_tc_twitter=findViewById(R.id.BTN_tc_twitter);

        BTN_tc_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instagramLink="https://www.instagram.com/tathastu.g052?igsh=OGQ5ZDc2ODk2ZA==\n";
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLink));
                startActivity(i);
            }
        });
        BTN_tc_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instagramLink="https://www.facebook.com/tathastu.g052?mibextid=ZbWKwL\n";
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLink));
                startActivity(i);
            }
        });
        BTN_tc_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instagramLink="https://twitter.com/tathastu_g052?t=MrAtvaTuvcF1hbSMDqd5_A&s=09\n";
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLink));
                startActivity(i);
            }
        });
    }
}