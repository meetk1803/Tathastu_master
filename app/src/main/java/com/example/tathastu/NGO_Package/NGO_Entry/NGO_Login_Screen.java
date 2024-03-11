package com.example.tathastu.NGO_Package.NGO_Entry;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.tathastu.Common_Screens.Selection_Screen;
import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class NGO_Login_Screen extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    public ExtendedFloatingActionButton BTN_login_ngo ;

    private LinearLayout login_parentLayout_ngo;

    public MaterialTextView BTN_login_sigin_ngo;
    public TextInputEditText edtmno_ngo,edtpwd_ngo;
    TextInputLayout txtlayout_login_mno_ngo;
    private ConnectivityReceiver connectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_login_screen);

        BTN_login_ngo = findViewById(R.id.BTN_login_ngo);
        BTN_login_sigin_ngo = findViewById(R.id.BTN_login_signin_ngo);
        edtmno_ngo = findViewById(R.id.txt_Loginmno_ngo);
        edtpwd_ngo=findViewById(R.id.txt_login_pwd_ngo);
        txtlayout_login_mno_ngo = findViewById(R.id.txtlayout_login_mno_ngo);
        login_parentLayout_ngo=findViewById(R.id.login_parentlayout_ngo);

        // Set up touch listener for the parent layout
        login_parentLayout_ngo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Clear focus from EditText when touched outside
                edtmno_ngo.clearFocus();
                edtpwd_ngo.clearFocus();


                hideSoftKeyboard(login_parentLayout_ngo);
                return false;
            }
        });

        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        BTN_login_ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mob = edtmno_ngo.getText().toString();
                String pwd=edtpwd_ngo.getText().toString();
                if (!isInternetAvailable()) {
                    showSnackbar(findViewById(android.R.id.content),"Please check your internet connection...");
                    return;
                }else {
                    if (mob.isEmpty()) {
                        // Set an error message
                        showSnackbar(findViewById(android.R.id.content), "Please enter mobile number...");
                    }else if (mob.length() < 10) {
                        showSnackbar(findViewById(android.R.id.content), "Please enter a valid mobile number...");
                    }else if (pwd.isEmpty()) {
                        // Set an error message
                        showSnackbar(findViewById(android.R.id.content), "Please enter password...");
                    }
                    else if (pwd.length() < 8) {
                        showSnackbar(findViewById(android.R.id.content), "Password should be 8 characters long...");
                    } else if (!isValidPassword(pwd)) {
                        showSnackbar(findViewById(android.R.id.content), "Password must include at least one uppercase letter, one lowercase letter, one special character, and one digit...");
                    }else if (!isValidNumber(mob)) {
                        showSnackbar(findViewById(android.R.id.content),"Please enter a valid mobile number...");

                    }

                    //WHEN DATABASE FETCH THE PASSWORD AND CHECK EITHER EQUAL OR NOT
//                else if (!pwd.equals(cpwd)) {
//                    showSnackbar(findViewById(android.R.id.content), "Confirm password doesn't match");
//                }
                    else {
                        // Clear the error message
                        Intent i = new Intent(NGO_Login_Screen.this, NGO_OTP_Screen.class);
                        Toast.makeText(NGO_Login_Screen.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        i.putExtra("source", "login");
                        i.putExtra("mobile", "+91" + mob);
                        startActivity(i);
                        Animatoo.INSTANCE.animateSlideLeft(NGO_Login_Screen.this);
                    }
                }
            }
        });


        BTN_login_sigin_ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isInternetAvailable()) {
                    showSnackbar(findViewById(android.R.id.content), "Please check your internet connection...");
                    return;
                } else {
                    Intent i = new Intent(NGO_Login_Screen.this, NGO_Signin_Screen.class);
                    startActivity(i);
                    Animatoo.INSTANCE.animateSlideLeft(NGO_Login_Screen.this);
                }
            }
        });
    }

    //--------------------------------------------------------------------------------------------


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(NGO_Login_Screen.this, Selection_Screen.class);
        startActivity(i);
        Animatoo.INSTANCE.animateSlideRight(this);
        finish();
    }

    //HIDE THE KEYBOARD
    private void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
    // Helper method to validate phone
    private boolean isValidNumber(String number) {
        String numberPattern = "\\b\\d{10}\\b";
        return number.matches(numberPattern);
    }
    // Helper method to validate password
    private boolean isValidPassword(String password) {
        // Password should have minimum 8 characters, 1 uppercase, 1 special character, 1 lowercase, and 1 number
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()-=_+{};:'\\\"<>,.?/\\\\|]).{8,}$";
        return password.matches(passwordPattern);
    }

    //SNACKBAR
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
        snackbarLayout.setPadding(1,1,1,1);
        snackbarLayout.addView(customView, 0);

        snackbar.show();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            showSnackbar(findViewById(android.R.id.content), "Please check your internet connection...");
        }
    }
}
