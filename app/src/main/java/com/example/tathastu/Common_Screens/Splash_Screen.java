package com.example.tathastu.Common_Screens;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.tathastu.Common_Screens.Intro.Intro_Starting_Screen;
import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_DashBoard.DashBoard_Screen;
import com.example.tathastu.User_Package.user_Entry.Login_Screen;
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
                Animatoo.INSTANCE.animateZoom(Splash_Screen.this);



                if (isFirstTimeLogin()) {

                    Intent i=new Intent(Splash_Screen.this, Intro_Starting_Screen.class);
                    startActivity(i);
                    finish();

                } else {

                    Intent intent1 = new Intent(Splash_Screen.this, DashBoard_Screen.class);
                    startActivity(intent1);
                    finish();

                }
            }
        },3000);
    }

    private boolean isFirstTimeLogin(){
        SharedPreferences preferences = getSharedPreferences(Login_Screen.PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(Login_Screen.KEY_FIRST_TIME_LOGIN, true);
    }

}