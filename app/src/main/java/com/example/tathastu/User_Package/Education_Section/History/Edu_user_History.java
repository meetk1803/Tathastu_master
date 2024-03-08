package com.example.tathastu.User_Package.Education_Section.History;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.Education_Section.Edu_User_Request;
import com.example.tathastu.User_Package.Food_Section.Food_User_Request;
import com.example.tathastu.User_Package.Food_Section.History.Food_user_history_adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Edu_user_History extends AppCompatActivity {
    FloatingActionButton btn_back, btn_addreqquest;
    RecyclerView recyclerView;
    Edu_user_history_adapter eduUserHistoryAdapter;
    ArrayList<String> items, items2, items3, items4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_user_history);

        recyclerView = findViewById(R.id.recycle_edu_donate_request);
        btn_back = findViewById(R.id.BTN_user_edu_history_back);
        btn_addreqquest = findViewById(R.id.BTN_edu_user_Add_request);

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
        items2.add("Grocery Details");
        items2.add("Grocery Details");

        items3 = new ArrayList<>();
        items3.add("+1-539-9594");
        items3.add("+7-071-0887");

        items4 = new ArrayList<>();
        items4.add("KATARGAM");
        items4.add("ADAJAN");


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eduUserHistoryAdapter = new Edu_user_history_adapter(this,items,items2,items3,items4);
        recyclerView.setAdapter(eduUserHistoryAdapter);


        btn_addreqquest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Edu_user_History.this, Edu_User_Request.class);
                startActivity(i);
            }
        });

    }
}