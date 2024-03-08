package com.example.tathastu.User_Package.Education_Section.Camp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Edu_Camp_Indetails extends AppCompatActivity {
    FloatingActionButton but_back;
    Button but_call;
    AppCompatTextView name, sdate, edate, address, note, phone;
    String example;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_camp_indetails);
        name = findViewById(R.id.display_edu_name);
        sdate = findViewById(R.id.display_edu_sdate);
        edate = findViewById(R.id.display_edu_edate);
        address = findViewById(R.id.display_edu_address);
        note = findViewById(R.id.display_edu_note);
        phone = findViewById(R.id.display_edu_phone);

        but_back = findViewById(R.id.BTN_edu_back);
        but_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        example = phone.getText().toString();
        but_call = findViewById(R.id.BTN_call);
        but_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + example));
                startActivity(callIntent);
            }
        });

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        name.setText(title);
        String sdate1 = intent.getStringExtra("sdate");
        sdate.setText(sdate1);
        String edate1 = intent.getStringExtra("edate");
        edate.setText(edate1);
        String loc = intent.getStringExtra("loc");
        //address.setText(loc);
        String mno = intent.getStringExtra("mno");
        phone.setText(mno);
    }
}