package com.example.tathastu.NGO_Package.NGO_Entry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tathastu.Common_Screens.Selection_Screen;
import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_Common_Screens.About_us_Screen;
import com.example.tathastu.User_Package.user_DashBoard.DashBoard_Screen;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.example.tathastu.User_Package.user_Common_Screens.Terms_C_activity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import static com.example.tathastu.R.style.CustomDatePickerStyle;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
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

public class NGO_Signin_Screen extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, ConnectivityReceiver.ConnectivityReceiverListener {

    private ConnectivityReceiver connectivityReceiver;
    public TextInputEditText dob_ngo;
    private SimpleDateFormat dateFormatter;

    private LinearLayout signin_LinearLayout_ngo;
    public ExtendedFloatingActionButton  BTN_sigin_ngo;
    public TextView BTN_signin_login_ngo,BTN_signin_TC_ngo;
    public TextInputEditText edtfname_ngo, edtlname_ngo, edtemail_ngo, edtmob_ngo, edtpwd_ngo, edtcpwd_ngo;
    public TextInputLayout txtlayout_Signin_fname_ngo, txtlayout_Signin_lname_ngo, txtlayout_Signin_email_ngo, txtlayout_Signin_mno_ngo, txtlayout_Signin_dob_ngo;
    public CheckBox ckbox_ngo;
    public int minYear;
    public int maxYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_signin_screen);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dob_ngo = findViewById(R.id.txt_Signindob_ngo);
        BTN_signin_TC_ngo = findViewById(R.id.BTN_signin_TC_ngo);
        BTN_sigin_ngo = findViewById(R.id.BTN_signin_ngo);
        BTN_signin_login_ngo = findViewById(R.id.BTN_signin_login_ngo);
        edtfname_ngo = findViewById(R.id.txt_Signin_Fname_ngo);
        edtlname_ngo = findViewById(R.id.txt_Signin_Lname_ngo);
        edtemail_ngo = findViewById(R.id.txt_Signinemail_ngo);
        edtmob_ngo = findViewById(R.id.txt_Signinmno_ngo);
        edtpwd_ngo = findViewById(R.id.txt_Signinpwd_ngo);
        edtcpwd_ngo = findViewById(R.id.txt_Signincpwd_ngo);
        signin_LinearLayout_ngo=findViewById(R.id.signin_parentlayout_ngo);

        ckbox_ngo = findViewById(R.id.chk_signintc_ngo);
        txtlayout_Signin_fname_ngo = findViewById(R.id.txtlayout_Signin_fname_ngo);
        txtlayout_Signin_lname_ngo = findViewById(R.id.txtlayout_Signin_lname_ngo);
        txtlayout_Signin_email_ngo = findViewById(R.id.txtlayout_Signin_email_ngo);
        txtlayout_Signin_mno_ngo = findViewById(R.id.txtlayout_Signin_mno_ngo);
        txtlayout_Signin_dob_ngo = findViewById(R.id.txtlayout_Signin_dob_ngo);

        // Set up touch listener for the parent layout
        signin_LinearLayout_ngo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Clear focus from EditText when touched outside
                edtfname_ngo.clearFocus();
                edtlname_ngo.clearFocus();
                edtemail_ngo.clearFocus();
                edtmob_ngo.clearFocus();
                edtpwd_ngo.clearFocus();
                edtcpwd_ngo.clearFocus();
                dob_ngo.clearFocus();


                hideSoftKeyboard(signin_LinearLayout_ngo);
                return false;
            }
        });

        FloatingActionButton BTN_back_ngo=findViewById(R.id.BTN_back_ngo);
        //BACK
        BTN_back_ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(NGO_Signin_Screen.this, NGO_Login_Screen.class);
                startActivity(i);
            }
        });

        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        //Terms & Conditions
        BTN_signin_TC_ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isInternetAvailable()) {
                    showSnackbar(findViewById(android.R.id.content), "Please check your internet connection...");
                    return;
                } else {
                    Intent i = new Intent(NGO_Signin_Screen.this, Terms_C_activity.class);
                    i.putExtra("source","signin");
                    startActivity(i);
                }
            }
        });

        //Signin
        BTN_sigin_ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = edtfname_ngo.getText().toString();
                String lname = edtlname_ngo.getText().toString();
                String email = edtemail_ngo.getText().toString();
                String mob = edtmob_ngo.getText().toString();
                String dob1 = dob_ngo.getText().toString();
                String pwd = edtpwd_ngo.getText().toString();
                String cpwd = edtcpwd_ngo.getText().toString();
                if (!isInternetAvailable()) {
                    showSnackbar(findViewById(android.R.id.content), "Please check your internet connection...");
                    return;
                } else {
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
                    } else if (ckbox_ngo.isChecked()) {
                        verification(fname, lname, email, mob, dob1);
                    } else {
                        showSnackbar(findViewById(android.R.id.content), "Please accept the Terms & Conditions...");
                    }
                }
            }
        });


        //Signin_Login
        BTN_signin_login_ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isInternetAvailable()) {
                    showSnackbar(findViewById(android.R.id.content), "Please check your internet connection...");
                    return;
                } else {
                    Intent i = new Intent(NGO_Signin_Screen.this, NGO_Login_Screen.class);
                    startActivity(i);
                }
            }
        });

        //DOB
        dob_ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard(dob_ngo);
                showDatePicker();

            }
        });
    }
//----------------------------------------------------------------------------

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(NGO_Signin_Screen.this,NGO_Login_Screen.class);
        startActivity(i);
        finish();
    }

    //HIDE THE KEYBOARD
    private void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

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
                NGO_Signin_Screen.this,
                CustomDatePickerStyle,
                //AlertDialog.THEME_HOLO_LIGHT,
                NGO_Signin_Screen.this,
                year, month, dayOfMonth
        );
        minYear = 1950;
        maxYear = Calendar.getInstance().get(Calendar.YEAR) - 18;

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
        dob_ngo.setText(dateFormatter.format(newDate.getTime()));
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
                        Intent i = new Intent(NGO_Signin_Screen.this, NGO_OTP_Screen.class);
                        i.putExtra("source", "signin");
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

    // Helper method to check if the internet connection is available
    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
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
