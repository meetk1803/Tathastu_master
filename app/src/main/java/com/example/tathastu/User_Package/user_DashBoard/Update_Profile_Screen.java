package com.example.tathastu.User_Package.user_DashBoard;

import static com.example.tathastu.R.style.CustomDatePickerStyle;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_Global_Class.ConnectivityReceiver;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Update_Profile_Screen extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, ConnectivityReceiver.ConnectivityReceiverListener {
    //ALL
    private TextInputEditText txt_Profile_Fname, txt_Profile_Lname, txt_Profile_email, txt_Profile_mno, txt_Profile_dob, txt_Profile_pwd, txt_Profile_cpwd;

    // INTERNET
    private ConnectivityReceiver connectivityReceiver;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 300;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private static final int REQUEST_IMAGE_CROP = 3;
    private LinearLayout update_parentLayout;


    //PROFILE IMAGE
    int SELECT_PICTURE = 100;
    ShapeableImageView img_profile_photo;

    MaterialTextView txt_updatep_change;

    //EDIT PROFILE
    private ExtendedFloatingActionButton BTN_Profile_update;
    //DATE OF BIRTH
    private int minYear, maxYear;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_screen);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        img_profile_photo = findViewById(R.id.img_updatep_photo);
        txt_Profile_Fname = findViewById(R.id.txt_updatep_Fname);
        txt_Profile_Lname = findViewById(R.id.txt_updatep_Lname);
        txt_Profile_email = findViewById(R.id.txt_updatep_email);
        txt_Profile_mno = findViewById(R.id.txt_updatep_mno);
        txt_Profile_dob = findViewById(R.id.txt_updatep_dob);
        txt_Profile_pwd = findViewById(R.id.txt_updatep_pwd);
        txt_Profile_cpwd = findViewById(R.id.txt_updatep_cpwd);
        BTN_Profile_update = findViewById(R.id.BTN_updatep_update);

        txt_updatep_change = findViewById(R.id.txt_updatep_change);

        update_parentLayout = findViewById(R.id.update_parentLayout);

        // Set up touch listener for the parent layout
        update_parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Clear focus from EditText when touched outside
                img_profile_photo.clearFocus();
                txt_Profile_Fname.clearFocus();
                txt_Profile_Lname.clearFocus();
                txt_Profile_email.clearFocus();
                txt_Profile_mno.clearFocus();
                txt_Profile_dob.clearFocus();
                txt_Profile_pwd.clearFocus();
                txt_Profile_cpwd.clearFocus();

                hideSoftKeyboard(update_parentLayout);
                return false;
            }
        });

        txt_updatep_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_PICTURE);
            }
        });

        setOnClickListeners();

        // Initialize the ConnectivityReceiver
        connectivityReceiver = new ConnectivityReceiver();
        ConnectivityReceiver.connectivityReceiverListener = this;

        // Register the receiver to listen for connectivity changes
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        FloatingActionButton BTN_back = findViewById(R.id.BTN_back);
        //BACK
        BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();

            }
        });

        //UPDATE BTN
        BTN_Profile_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = txt_Profile_Fname.getText().toString();
                String lname = txt_Profile_Lname.getText().toString();
                String email = txt_Profile_email.getText().toString();
                String mob = txt_Profile_mno.getText().toString();
                String dob1 = txt_Profile_dob.getText().toString();
                String pwd = txt_Profile_pwd.getText().toString();
                String cpwd = txt_Profile_cpwd.getText().toString();
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
                    } else {
                        showSnackbar(findViewById(android.R.id.content), "Please accept the Terms & Conditions...");
                    }
                }
            }
        });


    }

//-------------------------------------------------------------------------------------------------------------



//FOR IMAGE PICKER
@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK) {
        if (requestCode == SELECT_PICTURE || requestCode == REQUEST_IMAGE_CAPTURE) {
            if (data != null) {
                // Start cropping activity with the selected image
                Uri imageUri = data.getData();
                startCropActivity(imageUri);
            } else {
                Toast.makeText(this, "No Image Selected.", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_IMAGE_CROP) {
            if (data != null) {
                // Handle the cropped image result
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap croppedBitmap = extras.getParcelable("data");
                    img_profile_photo.setImageBitmap(croppedBitmap);
                } else {
                    Toast.makeText(this, "Error cropping image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

    private void startCropActivity(Uri sourceUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(sourceUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            // Start cropping activity with the original image
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(sourceUri, "image/*");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 200);
            cropIntent.putExtra("outputY", 200);
            cropIntent.putExtra("return-data", true);
            startActivityForResult(cropIntent, REQUEST_IMAGE_CROP);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //FOR DOB
    private void setOnClickListeners() {
        txt_Profile_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(txt_Profile_dob);
                showDatePicker();

            }
        });


    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        showExitDialog();

    }


    // showExitDialog
    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.exit_dialog_update, null);
        builder.setView(dialogView);

        ExtendedFloatingActionButton btnExitYes = dialogView.findViewById(R.id.BTN_exit_yes);
        ExtendedFloatingActionButton btnExitNo = dialogView.findViewById(R.id.BTN_exit_no);


        final AlertDialog dialog = builder.create();

        btnExitYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle 'Yes' button click
                finish();
                dialog.dismiss();
            }
        });

        btnExitNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle 'No' button click
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false); // Prevent dismiss on outside touch
        dialog.show();
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

    private void showDatePicker() {
        Calendar newCalendar = Calendar.getInstance();
        int year = newCalendar.get(Calendar.YEAR);
        int month = newCalendar.get(Calendar.MONTH);
        int dayOfMonth = newCalendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Update_Profile_Screen.this,
                CustomDatePickerStyle,
                Update_Profile_Screen.this,
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
        txt_Profile_dob.setText(dateFormatter.format(newDate.getTime()));
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