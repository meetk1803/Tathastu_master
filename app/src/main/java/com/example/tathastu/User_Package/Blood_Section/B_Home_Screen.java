package com.example.tathastu.User_Package.Blood_Section;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.tathastu.User_Package.Blood_Section.adapter1;
import com.example.tathastu.User_Package.Blood_Section.adapter2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class B_Home_Screen extends AppCompatActivity {

    TextView txtall;
    FloatingActionButton btn_back, btn_request;
    RecyclerView recyclerView, recyclerView2;
    adapter1 adapter1;
    adapter2 adapter2;
    ArrayList<String> items, items2, items3, items4, items5;
    ArrayList<String> item1, item2, item3, item4, item5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhome_screen);

        txtall = findViewById(R.id.txt_all);
        btn_back = findViewById(R.id.BTN_back);
        btn_request = findViewById(R.id.BTN_request);
        recyclerView = findViewById(R.id.recycle_blood_requirements);
        recyclerView2 = findViewById(R.id.recycle_camp_requirements);

        txtall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(B_Home_Screen.this, B_Home_Screen_2.class);
                startActivity(i);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(B_Home_Screen.this, B_Request_page.class);
                startActivity(i);
            }
        });

        items = new ArrayList<>();
        items.add("Vrund D Savani");
        items.add("Meet J Koshiya");
        items.add("Dhruvi K Vanani");
        items.add("Rushit P Kakadiya");
        items.add("Manav R Patel");
        items.add("Ravi H Sachapara");

        items2 = new ArrayList<>();
        items2.add("Age : 24");
        items2.add("Age : 41");
        items2.add("Age : 38");
        items2.add("Age : 25");
        items2.add("Age : 20");
        items2.add("Age : 17");

        items3 = new ArrayList<>();
        items3.add("+18-930-8156");
        items3.add("+1-539-9594");
        items3.add("+7-071-0887");
        items3.add("+67-434-8448");
        items3.add("+21-915-0404");
        items3.add("+91-756-6102");

        items4 = new ArrayList<>();
        items4.add("KATARGAM");
        items4.add("VARACHHA");
        items4.add("UTRAN");
        items4.add("ADAJAN");
        items4.add("AMBATALAVDI");
        items4.add("HAJIRA");

        items5 = new ArrayList<>();
        items5.add("B+");
        items5.add("O-");
        items5.add("A+");
        items5.add("AB-");
        items5.add("B-");
        items5.add("A-");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter1 = new adapter1(this,items,items2,items3,items4,items5);
        recyclerView.setAdapter(adapter1);


        item1 = new ArrayList<>();
        item1.add("Shree Vallabhipur Taluka Patidar Pragati Mandal");
        item1.add("Jivan Jyot Trust");
        item1.add("Smt P.J Koshiya Charitable Trust");
        item1.add("Bruhad Dhandhuka Taluka Pragati Mandal");

        item2 = new ArrayList<>();
        item2.add("21-03-2024");
        item2.add("18-04-2024");
        item2.add("05-03-2024");
        item2.add("11-05-2024");

        item3 = new ArrayList<>();
        item3.add("22-03-2024");
        item3.add("21-04-2024");
        item3.add("07-03-2024");
        item3.add("15-05-2024");

        item4 = new ArrayList<>();
        item4.add("+9193277-18627");
        item4.add("+1-539-9594");
        item4.add("+7-071-0887");
        item4.add("+67-434-8448");

        item5 = new ArrayList<>();
        item5.add("KATARGAM");
        item5.add("VARACHHA");
        item5.add("UTRAN");
        item5.add("ADAJAN");

        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter2 = new adapter2(this,item1,item2,item3,item4,item5);
        recyclerView2.setAdapter(adapter2);


    }
}



