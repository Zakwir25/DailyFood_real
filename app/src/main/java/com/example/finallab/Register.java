package com.example.finallab;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText phoneEditText;
    private Button registerButton;
    private Button loginButton;

    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize views
        nameEditText = findViewById(R.id.editTextName);
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword);
        phoneEditText = findViewById(R.id.editTextPhone);
        registerButton = findViewById(R.id.buttonRegister);
        loginButton = findViewById(R.id.buttonGoToLogin);

        // Create the database helper
        dbHelper = new MyDatabaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() ||
                        confirmPassword.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(Register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (name.length() < 5) {
                    Toast.makeText(Register.this, "Name should be at least 5 characters", Toast.LENGTH_SHORT).show();
                } else if (!email.endsWith(".com")) {
                    Toast.makeText(Register.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                } else if (!password.matches("[a-zA-Z0-9]+")) {
                    Toast.makeText(Register.this, "Password should be alphanumeric", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(name, email, password, phone);
                    redirectToOTPPage();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void registerUser(String name, String email, String password, String phone) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyDatabaseContract.UserRegis.COLUMN_NAME_NAME, name);
        values.put(MyDatabaseContract.UserRegis.COLUMN_NAME_EMAIL, email);
        values.put(MyDatabaseContract.UserRegis.COLUMN_NAME_PASSWORD, password);
        values.put(MyDatabaseContract.UserRegis.COLUMN_NAME_PHONED, phone);

        database.insert(MyDatabaseContract.UserRegis.TABLE_NAME, null, values);

        database.close();
    }

    private void redirectToOTPPage() {
        Intent intent = new Intent(Register.this, OTPActivity.class);
        startActivity(intent);
        finish();
    }
}


