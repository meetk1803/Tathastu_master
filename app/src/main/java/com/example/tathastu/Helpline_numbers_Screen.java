
package com.example.tathastu;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Helpline_numbers_Screen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter_Helpline_Numbers helplineAdapter;
    private List<UserModel_Helpline_Numbers> helplineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline_numbers_screen);

        recyclerView = findViewById(R.id.recycle_helpline_numbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        helplineList = new ArrayList<>();
        helplineAdapter = new UserAdapter_Helpline_Numbers(helplineList, this);
        recyclerView.setAdapter(helplineAdapter);

        // Fetch helpline numbers from the API
        new FetchHelplineNumbersTask().execute("https://meetk1803.github.io/tathastu_quotes_api/tathastu_helpline_numbers.json");
    }



    private void copyToClipboard(String phoneNumber) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Phone Number", phoneNumber);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
            showToast("Phone number copied to clipboard");
        }
    }
    private class FetchHelplineNumbersTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return fetchDataFromApi(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "Error fetching data from API";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null && !result.startsWith("Error")) {
                parseHelplineNumbers(result);
            } else {
                if (!isNetworkAvailable()) {
                    showToast("No internet connection");
                } else {
                    showToast(result);
                }
            }
        }

        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }


    private String fetchDataFromApi(String apiUrl) throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonData = null;

        try {
            URL url = new URL(apiUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

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
            jsonData = builder.toString();
        } finally {
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

        return jsonData;
    }

    private void parseHelplineNumbers(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String number = jsonObject.getString("number");
                String category = jsonObject.getString("category");

                UserModel_Helpline_Numbers helpline = new UserModel_Helpline_Numbers(name, number, category);
                helplineList.add(helpline);
            }

            // Notify the adapter that the data has changed
            helplineAdapter.notifyDataSetChanged();

            // Show a toast indicating successful data fetching
            showToast("Helpline numbers loaded successfully");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Helpline_numbers_Screen.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
