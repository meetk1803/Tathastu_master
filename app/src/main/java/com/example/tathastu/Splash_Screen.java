package com.example.tathastu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this,Intro_Starting_Screen.class);

                    startActivity(intent);
//                SharedPreferences sharedPreferences = getSharedPreferences(Login_activity.PREFS_NAME,0);
//                boolean hasloggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);
//                boolean hasloggedIn_ad = sharedPreferences.getBoolean("hasLoggedIn_ad",false);


//                if (hasloggedIn) {
//                    Intent intent = new Intent(Splash_Screen.this,Home_page.class);
//                    startActivity(intent);
//                    finish();
//                } else if (hasloggedIn_ad){
//                    Intent intent = new Intent(Splash_Screen.this,Home_Page_Admin.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Intent i=new Intent(Splash_Screen.this,Intro_Starting.class);
//                    startActivity(i);
//                    finish();
//                }

            }
        },3000);
    }
}