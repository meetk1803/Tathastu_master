package com.example.tathastu.User_Package.Blood_Section;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class B_Home_Screen_2 extends AppCompatActivity {

    FloatingActionButton btn_back, btn_request;
    RecyclerView recyclerView;
    adapter2 adapter2;
    ArrayList<String> item1, item2, item3, item4, item5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhome_screen2);

        btn_back = findViewById(R.id.BTN_back);
        recyclerView = findViewById(R.id.recycle_camp_requirements);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new adapter2(this,item1,item2,item3,item4,item5);
        recyclerView.setAdapter(adapter2);

    }
}