package com.example.tathastu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DashBoard_Screen extends AppCompatActivity {
    ImageButton BTN_dash_food,BTN_dash_blood,BTN_dash_cloth,BTN_dash_edu,BTN_dash_aboutus,BTN_dash_contactus,BTN_dash_history,BTN_dash_helpline;
    TextView seeall;
    RecyclerView recycle_Dash_event;
    CardView card_dash_event;
    private AppCompatTextView dash_quote;
    private int currentQuoteId = 1; // Initial quote ID
    private boolean isToastDisplayed = false; // Variable to track whether the toast has been displayed
    private static final int DAILY_QUOTE_REQUEST_CODE = 1001;
    private static final int MAX_REPEAT_COUNT = 500;
    private int repeatCount = 0;
    private Handler quoteHandler = new Handler(Looper.getMainLooper());
    private final int QUOTE_UPDATE_INTERVAL = 20 * 1000; // 1 minute in milliseconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_screen);

        BTN_dash_food=findViewById(R.id.BTN_dash_food);
        BTN_dash_blood=findViewById(R.id.BTN_dash_blood);
        BTN_dash_cloth=findViewById(R.id.BTN_dash_cloth);
        BTN_dash_edu=findViewById(R.id.BTN_dash_edu);
        BTN_dash_aboutus=findViewById(R.id.BTN_dash_aboutus);
        BTN_dash_contactus=findViewById(R.id.BTN_dash_contactus);
        BTN_dash_history=findViewById(R.id.BTN_dash_history);
        BTN_dash_helpline=findViewById(R.id.BTN_dash_helpline);

        ExtendedFloatingActionButton BTN_dash_logout = findViewById(R.id.BTN_dash_logout);
        ImageButton BTN_dash_food = findViewById(R.id.BTN_dash_food);
        seeall = findViewById(R.id.txt_dash_seeall);
        card_dash_event = findViewById(R.id.card_dash_event);

        dash_quote = findViewById(R.id.dash_quote);
        dash_quote.setText("");



        //FOR CONTACT US>>>
        BTN_dash_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(DashBoard_Screen.this,Contact_us_Screen.class);
                startActivity(i);
            }
        });

        //FOR ABOUT US >>>>
        BTN_dash_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(DashBoard_Screen.this,About_us_Screen.class);
                startActivity(i);
            }
        });

        //FOR HELPLINE NUMBERS
        BTN_dash_helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(DashBoard_Screen.this,Helpline_numbers_Screen.class);
                startActivity(i);
            }
        });

//FOR ADS OR EVENT BANNERS ON SCREEN
//        recycle_Dash_event = findViewById(R.id.recycle_Dash_event);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recycle_Dash_event.setLayoutManager(layoutManager);
//
//        List<UserModel_Event_Notify> eventList = generateDummyData();
//
//        UserAdapter_Event_Notify adapter = new UserAdapter_Event_Notify(eventList);
//        recycle_Dash_event.setAdapter(adapter);

//        profile_icon = findViewById(R.id.profile_icon);
//        String selectedImageUriString = getIntent().getStringExtra(Profile_Screen.SELECTED_IMAGE_URI);
//        if (selectedImageUriString != null) {
//            Uri selectedImageUri = Uri.parse(selectedImageUriString);
//            profile_icon.setImageURI(selectedImageUri);
//        }

        //CARDVIEW ----- EVENT NOTIFICATIONS
        card_dash_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoard_Screen.this, Event_Notifications_Screen.class);
                startActivity(i);
            }
        });

        //SEE ALL NOTIFICATIONS
        seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoard_Screen.this, Event_Notifications_Screen.class);
                startActivity(i);

            }
        });
        // LOGOUT BUTTON
        BTN_dash_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent logout = new Intent(DashBoard_Screen.this, Login_Screen.class);
                startActivity(logout);
            }
        });

        // Initially fetch the quote when the activity starts
        new FetchQuoteTask().execute(currentQuoteId);

        // Schedule the task to fetch a new quote every 1 minute
        scheduleQuoteUpdate();


    }
//-------------------------------------------------------------------------------------------------------------------------

    //SEE ALL FOR ADS OR EVENT BANNERS ON SCREEN
    private List<UserModel_Event_Notify> generateDummyData() {
        // Replace this method with your actual data retrieval logic
        List<UserModel_Event_Notify> dummyData = new ArrayList<>();

        // Add dummy data with alternating banner images
        for (int i = 0; i < 10; i++) { // Change 5 to the number of items you want
            int ngoImageResId = (i % 2 == 0) ? R.drawable.donate_food : R.drawable.donate_blood; // Change this to the correct image resource for NGO
            String ngoName = "SURACHANA EDUCATION AND CHARITABLE TRUST";
            String ngoDescription = "For donation of blood and food. Also, there is a facility for volunteering. Are you interested? Please join us.";
            String ngoAddress = "Surachana Education And Charitable Trust, B-2, City Light Apartment, Parle Point, Surat 395 007, Gujarat, India";
            String eventDate = "12/12/2024";

            UserModel_Event_Notify userModel = new UserModel_Event_Notify(ngoImageResId, ngoName, ngoDescription, ngoAddress, eventDate);
            dummyData.add(userModel);
        }

        // Add more dummy data as needed

        return dummyData;
    }

    //PROFILE METHOD
    public void onProfileClick(View view) {
        // Handle click on the profile icon

        Intent intent = new Intent(this, Profile_Screen.class);
        startActivity(intent);
    }

    //SCHEDULE THE QUOTES
    private void scheduleQuoteUpdate() {
        quoteHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Increment repeat count and reset to 1 if it reaches the maximum
                repeatCount = (repeatCount % MAX_REPEAT_COUNT) + 1;

                // Update the quote
                new FetchQuoteTask().execute(currentQuoteId);

                // Schedule the task to run again after 1 minute
                quoteHandler.postDelayed(this, QUOTE_UPDATE_INTERVAL);
            }
        }, QUOTE_UPDATE_INTERVAL);
    }




    private class FetchQuoteTask extends AsyncTask<Integer, Void, String> {
        @Override
        protected String doInBackground(Integer... quoteIds) {
            // Adjust quoteId based on repeat count
            if (isOnline()) {
                // If there is an active internet connection, make the API request
                int adjustedQuoteId = (currentQuoteId + repeatCount - 1) % MAX_REPEAT_COUNT + 1;
                return fetchQuote(adjustedQuoteId);
            } else {
                // If there is no internet connection, return an error message
                return "No internet connection";
            }

        }

        @Override
        protected void onPostExecute(String result) {
            // Update the UI with the new quote or handle the error message
            if (result != null) {
                if (result.equals("No internet connection") && !isToastDisplayed) {
                    // Handle the case when there is no internet connection
                    // Display a Toast message to the user
                    showToast("Please check internet connection!");
                    isToastDisplayed = true; // Set the flag to true to indicate the Toast has been displayed
                } else if (!result.equals("No internet connection")) {
                    // Apply slide-out animation to hide the current quote
                    Animation slideOutAnimation = AnimationUtils.loadAnimation(DashBoard_Screen.this, R.anim.slide_out_left);
                    slideOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            // This method is called when the slide out animation starts
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            // This method is called when the slide out animation ends
                            // Update the UI with the new quote
                            dash_quote.setText(result);

                            // Apply slide-in animation to show the new quote
                            Animation slideInAnimation = AnimationUtils.loadAnimation(DashBoard_Screen.this, R.anim.slide_in_right);
                            dash_quote.startAnimation(slideInAnimation);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            // This method is called if the animation repeats
                        }
                    });

                    dash_quote.startAnimation(slideOutAnimation);
                    isToastDisplayed = false; // Reset the flag when a new quote is displayed
                }
            }
        }

        // Method to check if the device is online
        private boolean isOnline() {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }

    // ON BACK PRESSED
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    // Show exit confirmation dialog
    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.exit_dialog, null);
        builder.setView(dialogView);

        ExtendedFloatingActionButton btnExitYes = dialogView.findViewById(R.id.BTN_exit_yes);
        ExtendedFloatingActionButton btnExitNo = dialogView.findViewById(R.id.BTN_exit_no);


        final AlertDialog dialog = builder.create();

        btnExitYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle 'Yes' button click
                finishAffinity(); // Finish this activity and all activities immediately below it
                System.exit(0); // Exit the app entirely
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

    // FETCH QUOTES

    private String fetchQuote(int quoteId) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String quoteJsonString = null;

        try {
            // Specify the URL of your API with the quote ID
            URL url = new URL("https://meetk1803.github.io/tathastu_quotes_api/tathastu_quotes.json");

            // Open the connection to the URL
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            quoteJsonString = builder.toString();
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
        } finally {
            // Close the HttpURLConnection and BufferedReader
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Check if the JSON response is null or empty
        if (quoteJsonString != null && !quoteJsonString.isEmpty()) {
            try {
                // Parse the JSON array and extract the quote
                JSONArray quotesArray = new JSONArray(quoteJsonString);
                if (quoteId <= quotesArray.length()) {
                    JSONObject quoteObject = quotesArray.getJSONObject(quoteId - 1);
                    return quoteObject.getString("quote");
                }
            } catch (JSONException e) {
                // Handle JSONException
                e.printStackTrace();
            }
        }

        return null;
    }


    private void showToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DashBoard_Screen.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
