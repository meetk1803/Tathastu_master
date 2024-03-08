package com.example.tathastu.User_Package.Food_Section.History;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tathastu.R;

import com.example.tathastu.User_Package.Food_Section.Food_User_Request;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Food_user_History extends AppCompatActivity {
    FloatingActionButton btn_back, btn_addreqquest;
    RecyclerView recyclerView;
    Food_user_history_adapter foodUserHistoryAdapter;
    ArrayList<String> items, items2, items3, items4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_user_history);

        recyclerView = findViewById(R.id.recycle_food_donate_request);
        btn_back = findViewById(R.id.BTN_user_food_history_back);
        btn_addreqquest = findViewById(R.id.BTN_food_user_Add_request);

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
        foodUserHistoryAdapter = new Food_user_history_adapter(this,items,items2,items3,items4);
        recyclerView.setAdapter(foodUserHistoryAdapter);


        btn_addreqquest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Food_user_History.this, Food_User_Request.class);
                startActivity(i);
            }
        });

    }
}