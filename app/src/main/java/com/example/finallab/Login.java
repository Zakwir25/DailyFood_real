package com.example.finallab;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Login extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;

    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        registerButton = findViewById(R.id.buttonRegister);

        // Create the database helper
        dbHelper = new MyDatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                } else {
                    if (validateUser(email, password)) {
                        if (isUserVerified(email)) {
                            redirectToHomePage();
                        } else {
                            generateAndSendOTP(email);
                            redirectToOTPPage();
                        }
                    } else {
                        Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateUser(String email, String password) {
        database = dbHelper.getReadableDatabase();

        String[] projection = {
                MyDatabaseContract.UserEntry.COLUMN_NAME_EMAIL
        };

        String selection = MyDatabaseContract.UserEntry.COLUMN_NAME_EMAIL + " = ? AND " +
                MyDatabaseContract.UserEntry.COLUMN_NAME_PASSWORD + " = ?";
        String[] selectionArgs = { email, password };

        Cursor cursor = database.query(
                MyDatabaseContract.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean userExists = cursor.moveToFirst();
        cursor.close();
        database.close();

        return userExists;
    }

    private boolean isUserVerified(String email) {
        database = dbHelper.getReadableDatabase();

        String[] projection = {
                MyDatabaseContract.UserEntry.COLUMN_NAME_VERIFIED
        };

        String selection = MyDatabaseContract.UserEntry.COLUMN_NAME_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = database.query(
                MyDatabaseContract.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean verified = false;
        if (cursor.moveToFirst()) {
            verified = (cursor.getInt(cursor.getCount()) == 1);
        }
        cursor.close();
        database.close();

        return verified;
    }

    private void generateAndSendOTP(String email) {
        // Generate a random 6-digit OTP code
        Random random = new Random();
        int otpCode = random.nextInt(900000) + 100000;

        database = dbHelper.getWritableDatabase();

        String selection = MyDatabaseContract.UserEntry.COLUMN_NAME_EMAIL + " = ?";
        String[] selectionArgs = { email };

        ContentValues values = new ContentValues();
        values.put(MyDatabaseContract.UserEntry.COLUMN_NAME_OTP_CODE, otpCode);

        database.update(
                MyDatabaseContract.UserEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        database.close();
    }

    private void redirectToHomePage() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void redirectToOTPPage() {
        Intent intent = new Intent(Login.this, OTPActivity.class);
        startActivity(intent);
        finish();
    }
}



