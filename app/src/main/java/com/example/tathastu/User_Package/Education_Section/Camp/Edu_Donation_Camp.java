package com.example.tathastu.User_Package.Education_Section.Camp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.Education_Section.Camp.Edu_Camp_Adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Edu_Donation_Camp extends AppCompatActivity {
    FloatingActionButton btn_back;
    RecyclerView recyclerView;
    Edu_Camp_Adapter adapter;
    ArrayList<String> item1, item2, item3, item4, item5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_donation_camp);

        btn_back = findViewById(R.id.BTN_edu_user_back);
        recyclerView = findViewById(R.id.recycle_edu_camp);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        item1 = new ArrayList<>();
        item1.add("123Shree Vallabhipur Taluka Patidar Pragati Mandal");
        item1.add("123Jivan Jyot Trust");
        item1.add("123Smt P.J Koshiya Charitable Trust");
        item1.add("123Bruhad Dhandhuka Taluka Pragati Mandal");

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
        item5.add("KATARGAM1");
        item5.add("VARACHHA2");
        item5.add("UTRAN3");
        item5.add("ADAJAN4");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Edu_Camp_Adapter(this,item1,item2,item3,item4,item5);
        recyclerView.setAdapter(adapter);

    }
}