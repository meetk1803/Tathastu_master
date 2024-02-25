package com.example.tathastu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class About_us_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_screen);

        ExtendedFloatingActionButton BTN_about_tc=findViewById(R.id.BTN_about_tc);
        FloatingActionButton BTN_about_insta=findViewById(R.id.BTN_about_insta);
        FloatingActionButton BTN_about_fb=findViewById(R.id.BTN_about_fb);
        FloatingActionButton BTN_about_twitter=findViewById(R.id.BTN_about_twitter);


        BTN_about_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instagramLink="https://www.instagram.com/tathastu.g052?igsh=OGQ5ZDc2ODk2ZA==\n";
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLink));
                startActivity(i);
            }
        });
        BTN_about_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instagramLink="https://www.facebook.com/tathastu.g052?mibextid=ZbWKwL\n";
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLink));
                startActivity(i);
            }
        });
        BTN_about_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instagramLink="https://twitter.com/tathastu_g052?t=MrAtvaTuvcF1hbSMDqd5_A&s=09\n";
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLink));
                startActivity(i);
            }
        });

        BTN_about_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(About_us_Screen.this,Terms_C_activity.class);
                startActivity(i);
            }
        });
    }
}