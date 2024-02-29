package com.example.tathastu.User_Package.user_NGO_list;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_DashBoard.DashBoard_Screen;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class direct_contact_to_NGO extends AppCompatActivity implements NGODataAdapter.ItemClickListener {

    private static final String API_URL = "https://meetk1803.github.io/tathastu_quotes_api/ngo_list.json";

    private RecyclerView recyclerView;
    private NGODataAdapter adapter;
    private List<NGOData> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_contact_to_ngo);

        FloatingActionButton BTN_back = findViewById(R.id.BTN_back);
        recyclerView = findViewById(R.id.recycle_ngo_detail);

        // BACK
        BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(direct_contact_to_NGO.this, DashBoard_Screen.class);
                startActivity(i);
            }
        });

        // Initialize RecyclerView and Adapter
        dataList = new ArrayList<>();
        adapter = new NGODataAdapter(this, dataList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch data from API
        new FetchDataTask().execute(API_URL);
    }

    private class FetchDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String apiUrl = strings[0];
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                bufferedReader.close();
                inputStream.close();

                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                parseJsonData(result);
            }
        }
    }

    private void parseJsonData(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                NGOData ngoData = new NGOData();
                ngoData.setNgoName(jsonObject.getString("ngo_name"));
                ngoData.setNgoMno(jsonObject.getString("mob"));
                ngoData.setNgoCategory(jsonObject.getString("category"));
                ngoData.setNgoAddress(jsonObject.getString("address"));
                ngoData.setNgoWebsite(jsonObject.getString("website"));
                ngoData.setNgoEmail(jsonObject.getString("email"));
                ngoData.setNgoInstagram(jsonObject.getString("instagram"));
                ngoData.setNgoLinkedIn(jsonObject.getString("linkedin"));
                ngoData.setNgoFacebook(jsonObject.getString("facebook"));
                ngoData.setNgoTwitter(jsonObject.getString("twitter"));
                ngoData.setNgoYoutube(jsonObject.getString("youtube"));

                dataList.add(ngoData);
            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onItemClick(NGOData ngoData) {
        Intent intent = new Intent(this, item_NGO_Detail.class);

        // Pass data to the intent
        intent.putExtra("NGO_NAME", ngoData.getNgoName());
        intent.putExtra("NGO_ADDRESS", ngoData.getNgoAddress());
        intent.putExtra("NGO_CATEGORY", ngoData.getNgoCategory());
        intent.putExtra("NGO_MNO", ngoData.getNgoMno());
        intent.putExtra("NGO_WEBSITE", ngoData.getNgoWebsite());
        intent.putExtra("NGO_EMAIL", ngoData.getNgoEmail());
        intent.putExtra("NGO_INSTAGRAM", ngoData.getNgoInstagram());
        intent.putExtra("NGO_LINKEDIN", ngoData.getNgoLinkedIn());
        intent.putExtra("NGO_FACEBOOK", ngoData.getNgoFacebook());
        intent.putExtra("NGO_TWITTER", ngoData.getNgoTwitter());
        intent.putExtra("NGO_YOUTUBE", ngoData.getNgoYoutube());

        // Start the details activity
        startActivity(intent);

        Toast.makeText(this, "See Details Clicked for: " + ngoData.getNgoName(), Toast.LENGTH_SHORT).show();
    }
}
