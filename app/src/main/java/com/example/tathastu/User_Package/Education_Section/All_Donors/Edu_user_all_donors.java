package com.example.tathastu.User_Package.Education_Section.All_Donors;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.Food_Section.All_Donors.Food_user_alldonorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Edu_user_all_donors extends AppCompatActivity {
    FloatingActionButton btn_back;
    RecyclerView recyclerView;
    Edu_user_alldonorAdapter adapter;  // Change the adapter type to NGO_food_donorAdapter
    ArrayList<String> item1, item2, item3, item4, item5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_user_all_donors);

        btn_back = findViewById(R.id.BTN_user_edu_history_back);
        recyclerView = findViewById(R.id.recycle_user_alledudonor_request);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        item1 = new ArrayList<>();
        item1.add("1 ABC");
        item1.add("2 BCD");
        item1.add("3 EFD");

        item2 = new ArrayList<>();
        item2.add("+9193277-18627");
        item2.add("+1-539-9594");
        item2.add("+7-071-0887");

        item3 = new ArrayList<>();
        item3.add("hsahfgajg@gmail.com");
        item3.add("jhagsfhgsagfuys@gmail.com");
        item3.add("ygiytrenbwhoi@gmail.com");

        item4 = new ArrayList<>();
        item4.add("KATARGAM1");
        item4.add("VARACHHA2");
        item4.add("UTRAN3");

        item5 = new ArrayList<>();
        item5.add("100 sets of essential stationery items, including notebooks, pens, and pencils");
        item5.add("100 sets of essential stationery items, including notebooks, pens, and pencils");
        item5.add("100 sets of essential stationery items, including notebooks, pens, and pencils");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Edu_user_alldonorAdapter(this, item1, item2, item3, item4, item5);
        recyclerView.setAdapter(adapter);
    }
}
