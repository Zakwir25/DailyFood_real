package com.example.finallab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OTPActivity extends AppCompatActivity {

    private EditText otpEditText;
    private Button verifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);

        otpEditText = findViewById(R.id.otpEditText);
        verifyButton = findViewById(R.id.verifyButton);

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOTP = otpEditText.getText().toString().trim();
                String generatedOTP = ""; // Get the generated OTP from SMS

                if (enteredOTP.isEmpty()) {
                    Toast.makeText(OTPActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else if (!enteredOTP.equals(generatedOTP)) {
                    Toast.makeText(OTPActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                } else {
                    redirectToHomePage();
                }
            }
        });
    }

    private void redirectToHomePage() {
        Intent intent = new Intent(OTPActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
