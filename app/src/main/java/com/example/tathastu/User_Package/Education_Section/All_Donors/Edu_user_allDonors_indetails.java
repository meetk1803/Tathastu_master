package com.example.tathastu.User_Package.Education_Section.All_Donors;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Edu_user_allDonors_indetails extends AppCompatActivity {
    FloatingActionButton but_back;
    Button but_call;
    AppCompatTextView name, number, email, loc, note;
    String example;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_user_all_donors_indetails);
        name = findViewById(R.id.display_edu_donor_name);
        number = findViewById(R.id.display_edu_donor_phone);
        email = findViewById(R.id.display_edu_donor_email);
        loc = findViewById(R.id.display_edu_donor_address);
        note = findViewById(R.id.display_edu_donor_note);


        Toast.makeText(this, "user", Toast.LENGTH_SHORT).show();

        but_back = findViewById(R.id.BTN_back);
        but_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        example = number.getText().toString();
        but_call = findViewById(R.id.BTN_edu_donor_call);
        but_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + example));
                startActivity(callIntent);
            }
        });

        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name");
        name.setText(name1);
        String number1 = intent.getStringExtra("number");
        number.setText(number1);
        String email1 = intent.getStringExtra("email");
        email.setText(email1);
        String loc1 = intent.getStringExtra("loc");
        loc.setText(loc1);
        String note1 = intent.getStringExtra("note");
        note.setText(note1);
    }
}