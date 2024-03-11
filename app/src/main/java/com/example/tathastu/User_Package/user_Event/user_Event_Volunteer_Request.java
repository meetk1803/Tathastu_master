package com.example.tathastu.User_Package.user_Event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class user_Event_Volunteer_Request extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    private ConnectivityReceiver connectivityReceiver;

    DatabaseReference reference,reference1;
    //    FirebaseStorage storage;
//    StorageReference storageReference;
    String iename,ieparticipated,ietotal;

    EditText txt_vname,txt_vemail,txt_vcno,txt_vaddress,txt_vage;
    Button btn_volunteer;


    boolean validate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_event_volunteer_request);

        txt_vname = (EditText) findViewById(R.id.txt_vname);
        txt_vemail = (EditText) findViewById(R.id.txt_vemail);
        txt_vcno = (EditText) findViewById(R.id.txt_vcno);
        txt_vaddress = (EditText) findViewById(R.id.txt_vaddress);
        txt_vage = (EditText) findViewById(R.id.txt_vage);
        btn_volunteer = (Button) findViewById(R.id.btn_volunteer);


        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        FloatingActionButton BTN_back=findViewById(R.id.BTN_back);
        //BACK
        BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i =new Intent(Event_Notifications_Screen.this, DashBoard_Screen.class);
//                startActivity(i);
                finish();
            }
        });


        String vname = txt_vname.getText().toString();
        String vemail = txt_vemail.getText().toString();
        String vcno = txt_vcno.getText().toString();
        String vaddress = txt_vaddress.getText().toString();
        String vage = txt_vage.getText().toString();

        Intent intent = this.getIntent();
        iename = intent.getStringExtra("ename");

        reference = FirebaseDatabase.getInstance().getReference().child("events").child(iename);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                events events = snapshot.getValue(events.class);
                if (events != null) {
                    iename = events.getName();
                    ieparticipated = events.getVolunteer_get();
                    ietotal = events.getTotal_volunteer();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check validation

                validate = CheckValidation();

                if(validate){

                    // checking if event is full

                    if (Integer.parseInt(ietotal) <= Integer.parseInt(ieparticipated)){

                        Toast.makeText(user_Event_Volunteer_Request.this,"Sorry, the event is full",Toast.LENGTH_SHORT).show();
                    }else {
                        String message;
                        if(Integer.parseInt(txt_vage.getText().toString()) < 18){
                            message = "Volunteers under age of 18 are assign and overlooked by guide \n\nAre you sure, you want to participate in " + iename +  " event.";
                        }
                        else{
                            message = "Are you sure, you want to participate in " + iename +  " event.";
                        }

                        AlertDialog.Builder builder= new AlertDialog.Builder(user_Event_Volunteer_Request.this);
                        builder.setMessage(message);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                // storing data in firebase

                                // random genetate volunteer id
                                Random r = new Random();
                                Integer id = r.nextInt(100000);

                                String uid = "01";  // current user id
                                String vname = txt_vname.getText().toString();
                                String vemail = txt_vemail.getText().toString();
                                String vcontactno = txt_vcno.getText().toString();
                                String vaddress = txt_vaddress.getText().toString();
                                String vage = txt_vage.getText().toString();

                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        reference.child("Volunteers").child(id.toString()).child("user_id").setValue(uid);
                                        reference.child("Volunteers").child(id.toString()).child("name").setValue(vname);
                                        reference.child("Volunteers").child(id.toString()).child("email").setValue(vemail);
                                        reference.child("Volunteers").child(id.toString()).child("contact_no").setValue(vcontactno);
                                        reference.child("Volunteers").child(id.toString()).child("address").setValue(vaddress);
                                        reference.child("Volunteers").child(id.toString()).child("age").setValue(vage);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                                // also add volunteer id in user data


                                // changing the number of volunteer participated

                                reference1 = FirebaseDatabase.getInstance().getReference().child("events").child(iename).child("Volunteers");
                                reference1.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dsnapshot) {
                                        Integer v =(int) dsnapshot.getChildrenCount();

                                        reference.child("volunteer_get").setValue(Integer.toString(v));
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                Toast.makeText(user_Event_Volunteer_Request.this,"Participation is successfull",Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }

                }

//                String email = txt_vemail.getText().toString();
//                String message = "Your Registration for " + iename +  " is complete. Further details are sent to the " + email + ".";
//                AlertDialog.Builder builder= new AlertDialog.Builder(user_event_volunteer.this);
//                builder.setMessage(message);
//                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                finish();
//                            }
//                        });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();

            }
        });


    }
//--------------------------------------------------------------------------------------

    private boolean CheckValidation(){

        if(txt_vname.length() < 3){
            txt_vname.setError("Atleast add 3 characters");
            return false;
        }

        if(txt_vemail.length() < 10){
            txt_vemail.setError("Atleast add 10 characters");
            return false;
        }

        if(txt_vcno.length() == 0){
            txt_vcno.setError("Field Required");
            return false;
        }

        if(txt_vcno.length() != 10){
            txt_vcno.setError("Enter valid contact number");
            return false;
        }

        if(txt_vaddress.length() < 10 ){
            txt_vaddress.setError("Atleast add 10 characters");
            return false;
        }

        if(txt_vage.length() == 0){
            txt_vage.setError("Field Required");
            return false;
        }

        if(txt_vage.length() < 2){
            txt_vage.setError("Not Qualified");
            return false;
        }

        return true;
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