package com.example.tathastu.User_Package.Blood_Section;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class B_Request_page extends AppCompatActivity {

    FloatingActionButton btn_back, btn_addreqquest;
    RecyclerView recyclerView;
    adapter3 adapter3;
    ArrayList<String> items, items2, items3, items4, items5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brequest_page);

        recyclerView = findViewById(R.id.recycle_blood_request);
        btn_back = findViewById(R.id.BTN_back);
        btn_addreqquest = findViewById(R.id.BTN_Add_request);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        items = new ArrayList<>();
        items.add("Vrund D Savani");
        items.add("Dhruvi K Vanani");

        items2 = new ArrayList<>();
        items2.add("Age : 24");
        items2.add("Age : 38");

        items3 = new ArrayList<>();
        items3.add("+1-539-9594");
        items3.add("+7-071-0887");

        items4 = new ArrayList<>();
        items4.add("KATARGAM");
        items4.add("ADAJAN");

        items5 = new ArrayList<>();
        items5.add("B+");
        items5.add("AB-");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter3 = new adapter3(this,items,items2,items3,items4,items5);
        recyclerView.setAdapter(adapter3);


        btn_addreqquest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(B_Request_page.this, B_add_details.class);
                startActivity(i);
            }
        });

    }
}