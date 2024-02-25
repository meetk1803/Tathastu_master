package com.example.tathastu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import static com.example.tathastu.R.style.CustomDatePickerStyle;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialTextInputPicker;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Sigin_Screen extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public TextInputEditText dob;
    private SimpleDateFormat dateFormatter;
    public ExtendedFloatingActionButton BTN_signin_login,BTN_sigin,BTN_signin_TC;
    public EditText edtname,edtemail,edtmob;
    public CheckBox ckbox;
    public int minYear;
    public int maxYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin_screen);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dob = findViewById(R.id.txt_Sigindob);
        BTN_signin_TC = findViewById(R.id.BTN_signin_TC);
        BTN_sigin = findViewById(R.id.BTN_signin);
        BTN_signin_login = findViewById(R.id.BTN_signin_login);
        edtname = findViewById(R.id.txt_Siginname);
        edtemail = findViewById(R.id.txt_Siginemail);
        edtmob = findViewById(R.id.txt_Siginmobileno);
        ckbox = findViewById(R.id.chk_signintc);

        //Terms & Conditions
        BTN_signin_TC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Sigin_Screen.this, Terms_C_activity.class);
                startActivity(i);
            }
        });

      //Signin
        BTN_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtname.getText().toString();
                String email = edtemail.getText().toString();
                String mob = edtmob.getText().toString();
                String dob1 = dob.getText().toString();

                if (ckbox.isChecked()) {

                    verification(name, email, mob, dob1);

                } else {

                    Toast.makeText(Sigin_Screen.this, "Please Accept the Terms And Conditions.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        //Signin_Login
        BTN_signin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sigin_Screen.this, Login_Screen.class);
                startActivity(i);
            }
        });

        //DOB
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });


    }
    private void showDatePicker() {
        Calendar newCalendar = Calendar.getInstance();
        int year = newCalendar.get(Calendar.YEAR);
        int month = newCalendar.get(Calendar.MONTH);
        int dayOfMonth = newCalendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Sigin_Screen.this,
                CustomDatePickerStyle,
                //AlertDialog.THEME_HOLO_LIGHT,
                Sigin_Screen.this,
                year, month, dayOfMonth
        );
        minYear = 1990;
        maxYear = Calendar.getInstance().get(Calendar.YEAR)-21;

        DatePicker datePicker = datePickerDialog.getDatePicker();
        if (datePicker != null) {
            datePicker.setMinDate(getMinDate());
            datePicker.setMaxDate(getMaxDate());
        }
        datePickerDialog.show();
    }
    public long getMinDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(minYear, 0, 1);
        return calendar.getTimeInMillis();
    }

    public long getMaxDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(maxYear, 11, 31);
        return calendar.getTimeInMillis();
    }
    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Calendar newDate = Calendar.getInstance();
        newDate.set(year, monthOfYear, dayOfMonth);
        dob.setText(dateFormatter.format(newDate.getTime()));
    }
    
    private void verification(String name,String email,String mob,String dob1){
        
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name.toString());
                    map.put("email", email.toString());
                    map.put("birth_of_date", dob1.toString());
                    map.put("mobile",mob.toString());

                    FirebaseDatabase.getInstance().getReference().child("user").push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Sigin_Screen.this, "Data Inserted Successfully.", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(Sigin_Screen.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Sigin_Screen.this, Otp_Screen.class);
                                    i.putExtra("mobile","+91"+mob);
                                    startActivity(i);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Sigin_Screen.this, "While Inserted Data.", Toast.LENGTH_SHORT).show();
                                }
                            });
        
    }

}
