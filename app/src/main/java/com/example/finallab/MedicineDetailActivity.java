package com.example.finallab;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class MedicineDetailActivity extends AppCompatActivity {

    private ImageView medicineImageView;
    private TextView medicineNameTextView;
    private TextView medicineManufacturerTextView;
    private TextView medicinePriceTextView;
    private TextView medicineDescriptionTextView;
    private EditText quantityEditText;
    private Button buyButton;

    private Medicine selectedMedicine;

    private MyDatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);

        // Get the selected medicine data from previous activity
        Intent intent = getIntent();
        selectedMedicine = (Medicine) intent.getSerializableExtra("medicine");

        // Initialize database helper
        databaseHelper = new MyDatabaseHelper(this);

        // Initialize views
        medicineImageView = findViewById(R.id.medicineImageView);
        medicineNameTextView = findViewById(R.id.medicineNameTextView);
        medicineManufacturerTextView = findViewById(R.id.medicineManufacturerTextView);
        medicinePriceTextView = findViewById(R.id.medicinePriceTextView);
        medicineDescriptionTextView = findViewById(R.id.medicineDescriptionTextView);
        quantityEditText = findViewById(R.id.quantityEditText);
        buyButton = findViewById(R.id.buyButton);

        // Set medicine information to views
        Glide.with(this)
                .load(selectedMedicine.getImageUrl())
                .into(medicineImageView);

        medicineNameTextView.setText(selectedMedicine.getName());
        medicineManufacturerTextView.setText(selectedMedicine.getManufacturer());
        medicinePriceTextView.setText(String.format("Price: $%.2f", selectedMedicine.getPrice()));
        medicineDescriptionTextView.setText(selectedMedicine.getDescription());

        // Set click listener for buy button
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyMedicine();
            }
        });
    }

    private void buyMedicine() {
        String quantityString = quantityEditText.getText().toString().trim();

        // Validate quantity
        if (quantityString.isEmpty()) {
            Toast.makeText(this, "Please enter the quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(quantityString);

        // Validate quantity is more than 0
        if (quantity <= 0) {
            Toast.makeText(this, "Quantity must be more than 0", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert transaction data into the application's database
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabaseContract.TransactionEntry.COLUMN_NAME_MEDICINE_NAME, selectedMedicine.getName());
        values.put(MyDatabaseContract.TransactionEntry.COLUMN_NAME_QUANTITY, quantity);

        long newRowId = db.insert(MyDatabaseContract.TransactionEntry.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Medicine bought successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to buy medicine", Toast.LENGTH_SHORT).show();
        }
    }
}


