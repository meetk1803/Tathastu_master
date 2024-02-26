package com.example.tathastu.User_Package.user_Entry;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_DashBoard.DashBoard_Screen;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Otp_Screen extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

    MaterialTextView txt_otp_mno;
    ExtendedFloatingActionButton BTN_otp;
    private MaterialTextView tvOtpTime;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private static final long OTP_TIMER_DURATION = 300000; // 5 minutes in milliseconds
    private static final long INTERVAL = 1000; // 1 second in milliseconds
    public String phonenumber;
    FirebaseAuth mAuth;
    String otpid;
    public EditText edtotp;
    private ConnectivityReceiver connectivityReceiver;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);

        tvOtpTime = findViewById(R.id.tv_OTPtime);
        BTN_otp = findViewById(R.id.BTN_otp);
        mAuth = FirebaseAuth.getInstance();
        txt_otp_mno=findViewById(R.id.txt_otp_mno);
        edtotp = findViewById(R.id.txt_OTP);

        phonenumber = getIntent().getStringExtra("mobile").toString();
        String last_four_digits=phonenumber.substring(phonenumber.length()-4);
        txt_otp_mno.setText("+91 XXXXXX"+last_four_digits);

        Toast.makeText(this, phonenumber, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, last_four_digits, Toast.LENGTH_SHORT).show();


        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        BTN_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enteredotp = edtotp.getText().toString();
                if (!isInternetAvailable()) {
                    showSnackbar(findViewById(android.R.id.content),"Please check your internet connection...");
                    return;
                }else {
                    if (enteredotp.isEmpty()) {
                        showSnackbar(findViewById(android.R.id.content), "Please enter an OTP...");
                    } else if (enteredotp.length() < 6) {
                        showSnackbar(findViewById(android.R.id.content), "Please enter 6 digits long OTP...");
                    }
               /* else if (enteredotp == authenticated otp(VARIABLE)) {
                    showSnackbar(findViewById(android.R.id.content),"Invalid OTP - Please enter correct OTP...");
                }*/
                    else {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid, enteredotp);
                        signInWithPhoneAuthCredential(credential);
                    }
                }
            }
        });

        startOtpTimer();

        initiateotp();
    }
//---------------------------------------------------------------------------------------------------
    private void initiateotp() {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phonenumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                otpid = s; // When the sim card is in to the other device these method call

                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                signInWithPhoneAuthCredential(phoneAuthCredential); // when the sim card in current device thse method is call

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                // OTP Generate Time Show the Error These Method is called
                                Toast.makeText(Otp_Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent i = new Intent(Otp_Screen.this, DashBoard_Screen.class);
                            startActivity(i);

                        } else {

                            Toast.makeText(Otp_Screen.this, "Sign In Code Error", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void startOtpTimer() {
        timeLeftInMillis = OTP_TIMER_DURATION;

        countDownTimer = new CountDownTimer(timeLeftInMillis, INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                // The timer has finished, handle accordingly
                tvOtpTime.setText("00:00"); // Update the UI or trigger OTP resend, etc.
            }
        }.start();
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        tvOtpTime.setText(timeFormatted);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Ensure to cancel the timer to avoid memory leaks
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        // Unregister the receiver to avoid memory leaks
        unregisterReceiver(connectivityReceiver);
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
