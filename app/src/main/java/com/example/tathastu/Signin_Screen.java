package com.example.tathastu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import static com.example.tathastu.R.style.CustomDatePickerStyle;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Signin_Screen extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,ConnectivityReceiver.ConnectivityReceiverListener {

    private ConnectivityReceiver connectivityReceiver;
    public TextInputEditText dob;
    private SimpleDateFormat dateFormatter;
    public ExtendedFloatingActionButton BTN_signin_login, BTN_sigin;
    public TextView BTN_signin_TC;
    public TextInputEditText edtfname, edtlname, edtemail, edtmob,edtpwd,edtcpwd;
    public TextInputLayout txtlayout_Signin_fname, txtlayout_Signin_lname, txtlayout_Signin_email, txtlayout_Signin_mno, txtlayout_Signin_dob;
    public CheckBox ckbox;
    public int minYear;
    public int maxYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_screen);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dob = findViewById(R.id.txt_Signindob);
        BTN_signin_TC = findViewById(R.id.BTN_signin_TC);
        BTN_sigin = findViewById(R.id.BTN_signin);
        BTN_signin_login = findViewById(R.id.BTN_signin_login);
        edtfname = findViewById(R.id.txt_Signin_Fname);
        edtlname = findViewById(R.id.txt_Signin_Lname);
        edtemail = findViewById(R.id.txt_Signinemail);
        edtmob = findViewById(R.id.txt_Signinmno);
        edtpwd = findViewById(R.id.txt_Signinpwd);
        edtcpwd = findViewById(R.id.txt_Signincpwd);

        ckbox = findViewById(R.id.chk_signintc);
        txtlayout_Signin_fname = findViewById(R.id.txtlayout_Signin_fname);
        txtlayout_Signin_lname = findViewById(R.id.txtlayout_Signin_lname);
        txtlayout_Signin_email = findViewById(R.id.txtlayout_Signin_email);
        txtlayout_Signin_mno = findViewById(R.id.txtlayout_Signin_mno);
        txtlayout_Signin_dob = findViewById(R.id.txtlayout_Signin_dob);

        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        //Terms & Conditions
        BTN_signin_TC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signin_Screen.this, Terms_C_activity.class);
                startActivity(i);
            }
        });

        //Signin
        BTN_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = edtfname.getText().toString();
                String lname = edtlname.getText().toString();
                String email = edtemail.getText().toString();
                String mob = edtmob.getText().toString();
                String dob1 = dob.getText().toString();
                String pwd = edtpwd.getText().toString();
                String cpwd = edtcpwd.getText().toString();

                if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || mob.isEmpty() || dob1.isEmpty() || pwd.isEmpty() || cpwd.isEmpty()) {
                    showSnackbar(findViewById(android.R.id.content), "Please enter required details...");
                } else if (mob.length() < 10) {
                    showSnackbar(findViewById(android.R.id.content), "Please enter a valid mobile number...");
                } else if (!isValidEmail(email)) {
                    showSnackbar(findViewById(android.R.id.content), "Please enter a valid email address (Gmail, Yahoo, or Outlook)...");
                } else if (pwd.length() < 8 || cpwd.length() < 8) {
                    showSnackbar(findViewById(android.R.id.content), "Password should be 8 characters long...");
                } else if (!isValidPassword(pwd)) {
                    showSnackbar(findViewById(android.R.id.content), "Password must include at least one uppercase letter, one lowercase letter, one special character, and one digit...");
                } else if (!pwd.equals(cpwd)) {
                    showSnackbar(findViewById(android.R.id.content), "Confirm password doesn't match...");
                } else if (ckbox.isChecked()) {
                    verification(fname, lname, email, mob, dob1);
                } else {
                    showSnackbar(findViewById(android.R.id.content), "Please accept the Terms & Conditions...");
                }
            }
        });



        //Signin_Login
        BTN_signin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Signin_Screen.this, Login_Screen.class);
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
//----------------------------------------------------------------------------

    // Helper method to validate Gmail, Yahoo, and Outlook addresses
    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-z0-9._%+-]+@(gmail\\.com|yahoo\\.com|outlook\\.com)$";
        return email.matches(emailPattern);
    }

    // Helper method to validate password
    private boolean isValidPassword(String password) {
        // Password should have minimum 8 characters, 1 uppercase, 1 special character, 1 lowercase, and 1 number
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()-=_+{};:'\\\"<>,.?/\\\\|]).{8,}$";
        return password.matches(passwordPattern);
    }

    //SHOW DATE PICKER
    private void showDatePicker() {
        Calendar newCalendar = Calendar.getInstance();
        int year = newCalendar.get(Calendar.YEAR);
        int month = newCalendar.get(Calendar.MONTH);
        int dayOfMonth = newCalendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Signin_Screen.this,
                CustomDatePickerStyle,
                //AlertDialog.THEME_HOLO_LIGHT,
                Signin_Screen.this,
                year, month, dayOfMonth
        );
        minYear = 1990;
        maxYear = Calendar.getInstance().get(Calendar.YEAR) - 21;

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

    private void showSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();

        // Inflate custom layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.custom_snackbar_layout, null);

        // Set text
        TextView textView = customView.findViewById(android.R.id.text1);
        textView.setText(message);

        // Add custom view to Snackbar
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarView;
        snackbarLayout.removeAllViews(); // Remove all default views
        snackbarLayout.setPadding(1, 1, 1, 1);
        snackbarLayout.addView(customView, 0);

        snackbar.show();
    }


    private void verification(String fname, String lname, String email, String mob, String dob1) {

        Map<String, Object> map = new HashMap<>();
        map.put("fname", fname);
        map.put("lname", lname);
        map.put("email", email);
        map.put("birth_of_date", dob1);
        map.put("mobile", mob);

        FirebaseDatabase.getInstance().getReference().child("user").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        showSnackbar(findViewById(android.R.id.content), "Data Inserted Successfully.\nSuccessfully Registered.");
                        Intent i = new Intent(Signin_Screen.this, Otp_Screen.class);
                        i.putExtra("mobile", "+91" + mob);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showSnackbar(findViewById(android.R.id.content), "Error: While Inserting Data.");
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the receiver to avoid memory leaks
        unregisterReceiver(connectivityReceiver);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            showSnackbar(findViewById(android.R.id.content), "Please check your internet connection...");
        }
    }
}
