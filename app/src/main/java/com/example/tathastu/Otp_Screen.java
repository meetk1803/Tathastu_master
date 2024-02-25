package com.example.tathastu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Otp_Screen extends AppCompatActivity {

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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);

        tvOtpTime = findViewById(R.id.tv_OTPtime);
        BTN_otp=findViewById(R.id.BTN_otp);
        mAuth = FirebaseAuth.getInstance();

        edtotp = findViewById(R.id.txt_OTP);

        phonenumber = getIntent().getStringExtra("mobile").toString();

        Toast.makeText(this, phonenumber, Toast.LENGTH_SHORT).show();

        BTN_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enteredotp = edtotp.getText().toString();
                
                if (enteredotp.isEmpty()) {

                    Toast.makeText(Otp_Screen.this, "Blank Field can not be Processed", Toast.LENGTH_SHORT).show();

                } else {
                    
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid, enteredotp);
                    signInWithPhoneAuthCredential(credential);

                }
            }
        });

        startOtpTimer();

        initiateotp();
    }

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

                            Intent i=new Intent(Otp_Screen.this,DashBoard_Screen.class);
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
    }
}
