package com.example.tathastu.User_Package.Blood_Section;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

public class B_Edit_detail_page extends AppCompatActivity {

    FloatingActionButton btn_back;
    TextInputEditText txt_name,txt_age,txt_weight,txt_location,txt_note,txt_mno;
    MaterialAutoCompleteTextView txt_type;
    Button btn_save;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedit_detail_page);


        btn_back = findViewById(R.id.BTN_back);
        btn_save = findViewById(R.id.BTN_save);
        txt_name = findViewById(R.id.txt_name);
        txt_age = findViewById(R.id.txt_age);
        txt_weight = findViewById(R.id.txt_weight);
        txt_type = findViewById(R.id.txt_type);
        txt_location = findViewById(R.id.txt_location);
        txt_note = findViewById(R.id.txt_note);
        txt_mno = findViewById(R.id.txt_mno);

        // Define the blood groups array within the same class
        String[] bloodGroups = new String[]{
                "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        };

        // Create an ArrayAdapter using the blood groups array and a default layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                bloodGroups
        );

        // Set the adapter to the AutoCompleteTextView
        txt_type.setAdapter(adapter);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(B_Edit_detail_page.this, "Request Saved.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(B_Edit_detail_page.this, B_Request_page.class);
                startActivity(i);
            }
        });
    }
}