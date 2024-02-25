package com.example.tathastu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Login_Screen extends AppCompatActivity {
    public ExtendedFloatingActionButton BTN_login,BTN_login_sigin;
    public EditText edtmno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        BTN_login=findViewById(R.id.BTN_login);
        BTN_login_sigin=findViewById(R.id.BTN_login_signin);
        edtmno = findViewById(R.id.txt_Loginmno);

        BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mob = edtmno.getText().toString();

                Intent i = new Intent(Login_Screen.this, Otp_Screen.class);
                Toast.makeText(Login_Screen.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                i.putExtra("mobile","+91"+mob);
                startActivity(i);

            }
        });
        BTN_login_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_Screen.this, Sigin_Screen.class);
                startActivity(i);

            }
        });

    }


}
