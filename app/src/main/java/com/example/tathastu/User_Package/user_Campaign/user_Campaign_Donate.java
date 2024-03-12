package com.example.tathastu.User_Package.user_Campaign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class user_Campaign_Donate extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    private ConnectivityReceiver connectivityReceiver;

    DatabaseReference reference,reference1;
    FirebaseStorage storage;
    StorageReference storageReference;
    ShapeableImageView campaignimage;
    TextInputEditText txt_amount;
    SwitchCompat hidename;

    CardView card_pay_100, card_pay_200, card_pay_500, card_pay_1000, card_pay_1500, card_pay_2000;
    ExtendedFloatingActionButton btn_donate;
    String username,email,contactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_campaign_donate);


        txt_amount =  findViewById(R.id.editText_amount);
        campaignimage = findViewById(R.id.eventimage);
        btn_donate =  findViewById(R.id.btn_donate);
        card_pay_100 = findViewById(R.id.card_pay_100);
        card_pay_200 = findViewById(R.id.card_pay_200);
        card_pay_500 = findViewById(R.id.card_pay_500);
        card_pay_1000 = findViewById(R.id.card_pay_1000);
        card_pay_1500 = findViewById(R.id.card_pay_1500);
        card_pay_2000 = findViewById(R.id.card_pay_2000);
        hidename = (SwitchCompat) findViewById(R.id.hidename);

        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));



        Intent intent = this.getIntent();
        String key = intent.getStringExtra("key");
        int imageid = intent.getIntExtra("cimage",R.drawable.bg);

        reference = FirebaseDatabase.getInstance().getReference().child("campaigns").child(key);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                campaigns campaign = snapshot.getValue(campaigns.class);
                if (campaign != null) {
                    String description = campaign.getDescription();
                    String ioname = campaign.getOrganizer_name();
                    String iocontact = campaign.getOrganizer_contact();
                    String icdonated = campaign.getDonation_received();
                    String imageUrl = campaign.getImageUrl();

                    Glide.with(user_Campaign_Donate.this).load(imageUrl).into(campaignimage);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // checking if campaign is closed

//                if(total_amount > received_amount){

                // fetch the current time and date

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String date = dtf.format(now).toString();

                if(hidename.isChecked()){
                    username = "Anonymous";
                    email = "-";
                    contactNo = "-";
                }else {
                    // Fetch current user Details

                    username = "user";
                    email = "email";
                    contactNo = "8484844856";
                }

                // coding to store donations data

                if(txt_amount.length() != 0){
                    String amount = txt_amount.getText().toString();
                    Random r = new Random();
                    Integer transaction_id = r.nextInt(100000);
                    String tid  = Integer.toString(transaction_id);

                    reference.child("Donations").child(transaction_id.toString()).child("name").setValue(username);
                    reference.child("Donations").child(transaction_id.toString()).child("email").setValue(email);
                    reference.child("Donations").child(transaction_id.toString()).child("contact_no").setValue(contactNo);
                    reference.child("Donations").child(transaction_id.toString()).child("amount").setValue(amount);
                    reference.child("Donations").child(transaction_id.toString()).child("date").setValue(date);
                    reference.child("Donations").child(transaction_id.toString()).child("transaction_id").setValue(transaction_id.toString());

                    // adding the user donation to the total received donation

                    reference1 = FirebaseDatabase.getInstance().getReference().child("campaigns").child(key).child("Donations");
                    reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int total = 0;

                            for(DataSnapshot donationSnapshot : snapshot.getChildren()){
                                if(donationSnapshot.hasChild("amount")){
                                    String amount = donationSnapshot.child("amount").getValue(String.class);
                                    total+=Integer.parseInt(amount);
                                }
                            }
                            reference.child("donation_received").setValue(String.valueOf(total));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(user_Campaign_Donate.this,"Error....",Toast.LENGTH_SHORT).show();
                        }
                    });

                    Toast.makeText(user_Campaign_Donate.this,"donation successfull",Toast.LENGTH_SHORT).show();

                }else {
                    txt_amount.setError("Enter Amount");
                }

//                }else{
//                    Toast.makeText(user_campaign_donate.this,"Campaign Completed",Toast.LENGTH_SHORT).show();
//                }

            }
        });

        txt_amount = findViewById(R.id.editText_amount);

// Assuming card_pay_100, card_pay_200, ..., card_pay_2000 are your card views
        int[] cardIds = {R.id.card_pay_100, R.id.card_pay_200, R.id.card_pay_500, R.id.card_pay_1000, R.id.card_pay_1500, R.id.card_pay_2000};
        int[] amounts = {100, 200, 500, 1000, 1500, 2000};

        for (int i = 0; i < cardIds.length; i++) {
            final int amount = amounts[i];

            findViewById(cardIds[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Clear previous selection
                    txt_amount.setText("");

                    // Set the text for the clicked card
                    txt_amount.setText(String.valueOf(amount));
                }
            });
        }

        FloatingActionButton BTN_back=findViewById(R.id.BTN_event_back);
        //BACK
        BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i =new Intent(Event_Notifications_Screen.this, DashBoard_Screen.class);
//                startActivity(i);
                finish();
            }
        });
    }

//------------------------------------------------------------------------------------------------

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