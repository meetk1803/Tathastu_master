package com.example.tathastu.User_Package.Food_Section.History;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class Food_user_history_edit_request extends AppCompatActivity {
    FloatingActionButton btn_back;
    TextInputEditText txt_name,txt_location,txt_note,txt_mno,txt_email;
    Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_user_history_edit_request);

        btn_back = findViewById(R.id.BTN_back);
        btn_save = findViewById(R.id.BTN_food_user_update);
        txt_name = findViewById(R.id.txt_name);
        txt_location = findViewById(R.id.txt_location);
        txt_note = findViewById(R.id.txt_note);
        txt_mno = findViewById(R.id.txt_mno);
        txt_email = findViewById(R.id.txt_email);

btn_back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Food_user_history_edit_request.this, "Request Saved.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Food_user_history_edit_request.this, Food_user_History.class);
                startActivity(i);
            }
        });
    }
}