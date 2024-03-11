package com.example.tathastu.Common_Screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.tathastu.Common_Screens.Intro.Intro_Starting_Screen;
import com.example.tathastu.R;
import com.google.android.material.imageview.ShapeableImageView;

public class Splash_Screen extends AppCompatActivity {
    ShapeableImageView app_word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ShapeableImageView app_word=findViewById(R.id.app_word);

        YoYo.with(Techniques.FadeIn)
                .duration(3000)
                .repeat(5)
                .playOn(findViewById(R.id.app_word));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this, Intro_Starting_Screen.class);

                    startActivity(intent);
                Animatoo.INSTANCE.animateZoom(Splash_Screen.this);
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