package com.example.tathastu;

import static com.example.tathastu.R.style.CustomDatePickerStyle;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Profile_Screen extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    //ALL
    private TextInputEditText txt_Profile_Fname, txt_Profile_Lname, txt_Profile_email, txt_Profile_mno, txt_Profile_dob;
    //PROFILE IMAGE
    int SELECT_PICTURE = 200;
    ShapeableImageView img_profile_photo;
    Uri selectedImageUri;

    //EDIT PROFILE
    private ExtendedFloatingActionButton BTN_Profile_edit;
    //DATE OF BIRTH
    private int minYear, maxYear;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        initializeViews();
        setOnClickListeners();
    }

    private void initializeViews() {
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        img_profile_photo = findViewById(R.id.img_profile_photo);
        txt_Profile_Fname = findViewById(R.id.txt_Profile_Fname);
        txt_Profile_Lname = findViewById(R.id.txt_Profile_Lname);
        txt_Profile_email = findViewById(R.id.txt_Profile_email);
        txt_Profile_mno = findViewById(R.id.txt_Profile_mno);
        txt_Profile_dob = findViewById(R.id.txt_Profile_dob);
        BTN_Profile_edit = findViewById(R.id.BTN_Profile_edit);
    }

    private void setOnClickListeners() {
        txt_Profile_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        BTN_Profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleEditTextsEditable();
            }
        });

        findViewById(R.id.txt_profile_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
    }

    private void toggleEditTextsEditable() {
        // Toggle the editable state of EditTexts
        setEditableState(txt_Profile_Fname, !txt_Profile_Fname.isEnabled());
        setEditableState(txt_Profile_Lname, !txt_Profile_Lname.isEnabled());
        setEditableState(txt_Profile_email, !txt_Profile_email.isEnabled());
        setEditableState(txt_Profile_mno, !txt_Profile_mno.isEnabled());
        setEditableState(txt_Profile_dob, !txt_Profile_dob.isEnabled());

        // Change the text of the button based on the current state
        if (txt_Profile_Fname.isEnabled()) {
            BTN_Profile_edit.setText("Done");
        } else {
            BTN_Profile_edit.setText("Edit Profile Details");
        }
    }

    private void setEditableState(TextInputEditText editText, boolean editable) {
        editText.setEnabled(editable);
        editText.setFocusable(editable);
        editText.setFocusableInTouchMode(editable);
    }

    public void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    img_profile_photo.setImageURI(selectedImageUri);
                }
            }
        }
    }

    private void showDatePicker() {
        Calendar newCalendar = Calendar.getInstance();
        int year = newCalendar.get(Calendar.YEAR);
        int month = newCalendar.get(Calendar.MONTH);
        int dayOfMonth = newCalendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Profile_Screen.this,
                CustomDatePickerStyle,
                Profile_Screen.this,
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
        txt_Profile_dob.setText(dateFormatter.format(newDate.getTime()));
    }
}
