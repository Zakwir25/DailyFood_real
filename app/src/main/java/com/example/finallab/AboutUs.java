package com.example.finallab;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class AboutUs extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker bluejackPharmacyMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        TextView headerTextView = findViewById(R.id.headerTextView);
        headerTextView.setText("About Us");

        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        descriptionTextView.setText("kita berjulan obat dikalangan Online ");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Bluejack Pharmacy location
        LatLng bluejackPharmacyLocation = new LatLng(-6.20201, 106.78113);

        // Add marker
        bluejackPharmacyMarker = mMap.addMarker(new MarkerOptions()
                .position(bluejackPharmacyLocation)
                .title("Bluejack Pharmacy"));

        // Move camera to the marker
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bluejackPharmacyLocation, 15f));
    }
}


