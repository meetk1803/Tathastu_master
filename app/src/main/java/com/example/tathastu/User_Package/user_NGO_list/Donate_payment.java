package com.example.tathastu.User_Package.user_NGO_list;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Donate_payment extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener, PaymentResultListener {

    CardView card_pay_100, card_pay_200, card_pay_500, card_pay_1000, card_pay_1500, card_pay_2000;

    private String donationAmount;

    private ConnectivityReceiver connectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_payment);

        FloatingActionButton BTN_back = findViewById(R.id.BTN_back_pay);
        ExtendedFloatingActionButton BTN_pay_donate = findViewById(R.id.BTN_pay_donate);
        TextInputLayout txtlayout_payment_user = findViewById(R.id.txtlayout_payment_user);
        TextInputEditText txt_payment_user = findViewById(R.id.txt_payment_user);
        TextView txt_user_ngo_name =findViewById(R.id.txt_user_ngo_name);
        LinearLayout layout_payment = findViewById(R.id.layout_payment);
        card_pay_100 = findViewById(R.id.card_pay_100);
        card_pay_200 = findViewById(R.id.card_pay_200);
        card_pay_500 = findViewById(R.id.card_pay_500);
        card_pay_1000 = findViewById(R.id.card_pay_1000);
        card_pay_1500 = findViewById(R.id.card_pay_1500);
        card_pay_2000 = findViewById(R.id.card_pay_2000);

//        layout_payment.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // Clear focus from EditText when touched outside
//                txt_payment_user.clearFocus();
//
//
//                hideSoftKeyboard(layout_payment);
//                return false;
//            }
//        });

        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(direct_contact_to_NGO.this, DashBoard_Screen.class);
//                startActivity(i);
                finish();
            }
        });

        // Assuming card_pay_100, card_pay_200, ..., card_pay_2000 are your card views
        int[] cardIds = {R.id.card_pay_100, R.id.card_pay_200, R.id.card_pay_500, R.id.card_pay_1000, R.id.card_pay_1500, R.id.card_pay_2000};
        int[] amounts = {100, 200, 500, 1000, 1500, 2000};

        for (int i = 0; i < cardIds.length; i++) {
            final int amount = amounts[i];
            final TextView txtPaymentUser = findViewById(R.id.txt_payment_user);

            findViewById(cardIds[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Clear previous selection
                    txtPaymentUser.setText("");

                    // Set the text for the clicked card
                    txtPaymentUser.setText(String.valueOf(amount));
                }
            });
        }
        Intent intent = getIntent();
        String ngoName = intent.getStringExtra("ngoName");
        String ngoEmail = intent.getStringExtra("ngoEmail");
        String ngoMno = intent.getStringExtra("ngoMno");

        Toast.makeText(this, ngoEmail, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ngoMno, Toast.LENGTH_SHORT).show();
        txt_user_ngo_name.setText(ngoName);

//DONATE BTN
        BTN_pay_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amt = txt_payment_user.getText().toString();
                if (!isInternetAvailable()) {
                    showSnackbar(findViewById(android.R.id.content), "Please check your internet connection...");
                    return;
                } else {
                    if (amt.isEmpty()) {
                        // Set an error message
                        showSnackbar(findViewById(android.R.id.content), "Please enter amount...");
                    } else if (amt.length() < 2) {
                        showSnackbar(findViewById(android.R.id.content), "At least donate 10 Rs. ...");
                    } else if (!isValidAmount(amt)) {
                        showSnackbar(findViewById(android.R.id.content), "We can't suggest you to donate this big amount...");
                    } else {
                        // Set the donation amount for Razorpay
                        donationAmount = amt;

                        // Process the donation
                        initiateDonation(ngoName,ngoMno,ngoEmail,amt);
                    }
                }
            }
        });



    }

    //-------------------------------------------------------------------------------------------------------
//    private void initiateDonation() {
//        Intent intent = getIntent();
//        String ngoName = intent.getStringExtra("ngoName");
//        String ngoEmail = intent.getStringExtra("ngoEmail");
//        String ngoMno = intent.getStringExtra("ngoMno");
//
//        // Check if the donation amount is set
//        if (donationAmount != null && !donationAmount.isEmpty()) {
//            List<NGOData> dataList = fetchData(); // Replace with your data-fetching logic
//
//            // Call the initiateDonation method in the adapter
//            NGODataAdapter adapter = new NGODataAdapter(dataList, Donate_payment.this);
//            adapter.initiateDonation(ngoName, ngoMno, ngoEmail, donationAmount);
//        }
//    }

    public void initiateDonation(String ngoName, String ngoMno, String ngoEmail, String donationAmount) {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_XhlHct9tqCA3jm"); // Replace with your actual Razorpay key

        try {
            JSONObject options = new JSONObject();
            options.put("name", ngoName);
            Toast.makeText(this, ngoEmail, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, ngoMno, Toast.LENGTH_SHORT).show();
            options.put("description", "Donation for " + ngoName);
            options.put("currency", "INR");
            options.put("amount", Integer.parseInt(donationAmount) * 100);

            JSONObject preFill = new JSONObject();
            preFill.put("contact", ngoMno);
            preFill.put("email", ngoEmail); // Confirm the key used here
            options.put("prefill", preFill);
            options.put("environment", "sandbox"); // "sandbox" or "production"
            Toast.makeText(this, ngoEmail, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, ngoMno, Toast.LENGTH_SHORT).show();
            options.put("theme.color", "#2e80df");
            options.put("method", new JSONObject().put("upi", true));
            checkout.open((Activity)  Donate_payment.this, options);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onPaymentSuccess(String s) {
        // Handle payment success
        showToast("Payment Successful");
    }

    @Override
    public void onPaymentError(int i, String s) {
        // Handle payment failure
        showToast("Payment Declined");
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


    // Helper method to validate Amount
    private boolean isValidAmount(String number) {
        // Validate if the amount is less than or equal to 5 digits
        String numberPattern = "\\b\\d{1,5}\\b";
        return number.matches(numberPattern);
    }

//    //HIDE THE KEYBOARD
//    private void hideSoftKeyboard(View view) {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }

    private void showToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Donate_payment.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the receiver to avoid memory leaks
        unregisterReceiver(connectivityReceiver);
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
        snackbarLayout.setPadding(1, 1, 1, 1);
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