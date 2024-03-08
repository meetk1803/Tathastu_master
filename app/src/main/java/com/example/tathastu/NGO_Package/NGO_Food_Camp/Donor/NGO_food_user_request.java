package com.example.tathastu.NGO_Package.NGO_Food_Camp.Donor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NGO_food_user_request extends AppCompatActivity {
    FloatingActionButton btn_back;
    RecyclerView recyclerView;
    NGO_food_donorAdapter adapter;  // Change the adapter type to NGO_food_donorAdapter
    ArrayList<String> item1, item2, item3, item4, item5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_food_user_request);

        btn_back = findViewById(R.id.BTN_back);
        recyclerView = findViewById(R.id.recycle_food_user_request);
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
        item5.add("Grocery - 1kg dal,1kg rice 1kg dal,1kg rice 1kg dal,1kg rice 1kg dal,1kg rice 1kg dal,1kg rice etc");
        item5.add("Grocery - 1kg dal,1kg rice 1kg dal,1kg rice 1kg dal,1kg rice 1kg dal,1kg rice 1kg dal,1kg rice etc");
        item5.add("Grocery - 1kg dal,1kg rice 1kg dal,1kg rice 1kg dal,1kg rice 1kg dal,1kg rice 1kg dal,1kg rice etc");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NGO_food_donorAdapter(this, item1, item2, item3, item4, item5);
        recyclerView.setAdapter(adapter);
    }
}
