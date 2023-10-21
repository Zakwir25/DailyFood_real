package com.example.finallab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    private ImageView logoImageView;
    private RecyclerView medicineRecyclerView;
    private Button logoutButton;

    private List<Medicine> medicineList;
    private MedicineAdapter medicineAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        logoImageView = findViewById(R.id.logoImageView);
        medicineRecyclerView = findViewById(R.id.medicineRecyclerView);
        logoutButton = findViewById(R.id.logoutButton);





        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        Picasso.get()
                .load(R.drawable.a1)
                .into(logoImageView);

        medicineList = new ArrayList<>();
        medicineAdapter = new MedicineAdapter(this, medicineList);
        medicineRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        medicineRecyclerView.setAdapter(medicineAdapter);

        fetchMedicineData();

    }

    private void fetchMedicineData() {
        String url = "https://mocki.io/v1/ae13b04b-13df-4023-88a5-7346d5d3c7eb";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Clear existing data
                            medicineList.clear();

                            // Parse JSON response
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                String name = jsonObject.getString("name");
                                String manufacturer = jsonObject.getString("manufacturer");
                                String imageUrl = jsonObject.getString("imageUrl");
                                double price = jsonObject.getDouble("price");
                                String description = jsonObject.getString("desciption");

                                Medicine medicine = new Medicine(name, manufacturer, imageUrl, price, description);
                                medicineList.add(medicine);
                            }

                            // Update the RecyclerView
                            medicineAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(HomePageActivity.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(HomePageActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(request);
    }

    private void logoutUser() {

        // Redirect to Login Page
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish(); // Close the current activity
    }
}

