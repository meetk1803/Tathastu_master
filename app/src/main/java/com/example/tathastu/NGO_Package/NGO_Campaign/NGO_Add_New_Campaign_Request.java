package com.example.tathastu.NGO_Package.NGO_Campaign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class NGO_Add_New_Campaign_Request extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    private ConnectivityReceiver connectivityReceiver;

    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseStorage storage;
    StorageReference storageReference;

    ExtendedFloatingActionButton btn_addimage;
    Button btn_add_campaign;
    TextInputEditText txt_campaignname,txt_description,txt_organizer,txt_organizermno;
    ImageView cimage;
    int Select_Picture = 200;
    Uri selectedImageUri;
    Boolean validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_add_new_campaign_request);


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


        btn_addimage =  findViewById(R.id.btn_addimage);
        btn_add_campaign =  findViewById(R.id.btn_add_campaign);
        cimage =  findViewById(R.id.cimage);
        txt_campaignname = findViewById(R.id.txt_campaignname);
        txt_description = findViewById(R.id.txt_description);
        txt_organizer =  findViewById(R.id.txt_organizer);
        txt_organizermno = findViewById(R.id.txt_organizermno);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference().child("campaigns");


        btn_addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i,"Select Picture"),Select_Picture);
                //imageChooser();
            }
        });

        btn_add_campaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate = CheckValidation();
                if(validate){
                    if (cimage.getDrawable() == null){
                        Toast.makeText(NGO_Add_New_Campaign_Request.this," Select Campaign Image ",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String cname = txt_campaignname.getText().toString();
                        String message = "Are you sure, you want to launch " + cname +  " Campaign.";
                        AlertDialog.Builder builder= new AlertDialog.Builder(NGO_Add_New_Campaign_Request.this);
                        builder.setMessage(message);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                // coding to store data in database

                                String key= String.valueOf(System.currentTimeMillis());

                                String cname = txt_campaignname.getText().toString();
                                String cdesc = txt_description.getText().toString();
                                String coname = txt_organizer.getText().toString();
                                String cocontact = txt_organizermno.getText().toString();
                                //Integer ctotal = Integer.parseInt(txt_targetamount.getText().toString());

                                //Integer cid = new Random().nextInt();
                                String cdonated = "0";

                                // showing Progress
                                final ProgressDialog p = new ProgressDialog(NGO_Add_New_Campaign_Request.this);
                                p.setTitle("Uploading.....");
                                p.show();


                                reference.child(key).child("name").setValue(cname);
                                //reference.child("running").child(cname).child("cid").setValue(cid);
                                reference.child(key).child("description").setValue(cdesc);
                                reference.child(key).child("organizer_name").setValue(coname);
                                reference.child(key).child("organizer_contact").setValue(cocontact);
                                reference.child(key).child("donation_received").setValue(cdonated);
                                reference.child(key).child("key").setValue(key);

                                //storing Image in Firebase Storage

                                if(selectedImageUri != null){
                                    StorageReference reference1 = storageReference.child("campaign/" + key);
                                    reference1.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            p.dismiss();

                                            // storing image url in realtime database

                                            reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    reference.child(key).child("imageUrl").setValue(uri.toString());
                                                }
                                            });


                                            AlertDialog.Builder br = new AlertDialog.Builder(NGO_Add_New_Campaign_Request.this);
                                            br.setMessage(cname + " Campaign is successfully launched.");
                                            br.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    finish();
                                                }
                                            });
                                            AlertDialog alertConfirm = br.create();
                                            alertConfirm.show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(NGO_Add_New_Campaign_Request.this,"Image uploading fail",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }else {
                                    Toast.makeText(NGO_Add_New_Campaign_Request.this,"empty",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                return;
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }

                }
            }
        });
    }

//-------------------------------------------------------------------------------------------------------------------

    private boolean CheckValidation(){
        //txt_campaignname,txt_description,txt_organizer,txt_organizermno,txt_targetamount;

        if(txt_campaignname.length() < 3){
            txt_campaignname.setError("Atleast add 3 characters");
            return false;
        }

        if(txt_description.length() < 10){
            txt_description.setError("Atleast add 10 characters");
            return false;
        }

        if(txt_organizer.length() < 3){
            txt_organizer.setError("Atleast add 3 characters");
            return false;
        }

        if(txt_organizermno.length() == 0){
            txt_organizermno.setError("Field Required");
            return false;
        }

        if(txt_organizermno.length() != 10){
            txt_organizermno.setError("Invalid Number");
            return false;
        }

        return true;
    }

    // taking image
    void imageChooser(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"Select Picture"),Select_Picture);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == Select_Picture){
                selectedImageUri= data.getData();

                if(null != selectedImageUri){
                    cimage.setImageURI(selectedImageUri);
                }
            }
        }
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