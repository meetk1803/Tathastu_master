package com.example.tathastu.User_Package.blood_section;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class O_add_details extends AppCompatActivity {

    FloatingActionButton btn_back;
    TextView txt_name,txt_sdate,txt_edate,txt_location,txt_note,txt_mno;
    Button btn_submit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oadd_details);

        btn_back = findViewById(R.id.BTN_back);
        btn_submit = findViewById(R.id.BTN_add);
        txt_name = findViewById(R.id.txt_name);
        txt_sdate = findViewById(R.id.txt_sdate);
        txt_edate = findViewById(R.id.txt_edate);
        txt_location = findViewById(R.id.txt_location);
        txt_note = findViewById(R.id.txt_note);
        txt_mno = findViewById(R.id.txt_mno);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(O_add_details.this, "Request Created Successfuly", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(O_add_details.this, B_Request_page.class);
                startActivity(i);
            }
        });
    }
}