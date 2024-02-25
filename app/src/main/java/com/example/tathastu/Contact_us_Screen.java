package com.example.tathastu;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
public class Contact_us_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_screen);

        FloatingActionButton BTN_contact_insta=findViewById(R.id.BTN_contact_insta);
        FloatingActionButton BTN_contact_fb=findViewById(R.id.BTN_contact_fb);
        FloatingActionButton BTN_contact_twitter=findViewById(R.id.BTN_contact_twitter);

        MaterialTextView txt_contact_mno = findViewById(R.id.txt_contact_mno);
        MaterialTextView txt_contact_email = findViewById(R.id.txt_contact_email);
        MaterialTextView txt_contact_address = findViewById(R.id.txt_contact_address);

        setCopyToClipboardListener(txt_contact_mno, "+910123456789");
        setCopyToClipboardListener(txt_contact_email, "tathastu052threesofficial@gmail.com");
        setCopyToClipboardListener(txt_contact_address, "123 ABC Street, City Light Area, Surat 395007, Gujarat, India.");


        //FOR FOLLOW US ON
        BTN_contact_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instagramLink="https://www.instagram.com/tathastu.g052?igsh=OGQ5ZDc2ODk2ZA==\n";
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLink));
                startActivity(i);
            }
        });
        BTN_contact_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instagramLink="https://www.facebook.com/tathastu.g052?mibextid=ZbWKwL\n";
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLink));
                startActivity(i);
            }
        });
        BTN_contact_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instagramLink="https://twitter.com/tathastu_g052?t=MrAtvaTuvcF1hbSMDqd5_A&s=09\n";
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLink));
                startActivity(i);
            }
        });

    }

    private void setCopyToClipboardListener(MaterialTextView textView, final String text) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Copy the text to the clipboard
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Contact Info", text);
                if (clipboard != null) {
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(Contact_us_Screen.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}