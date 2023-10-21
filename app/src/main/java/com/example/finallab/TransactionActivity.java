package com.example.finallab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity implements TransactionAdapter.TransactionClickListener {
    private RecyclerView transactionRecyclerView;
    private TransactionAdapter transactionAdapter;
    private List<Transaction> transactionList;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        transactionRecyclerView = findViewById(R.id.transactionRecyclerView);
        transactionList = new ArrayList<>();


        fetchTransactionData();

        transactionAdapter = new TransactionAdapter(this, transactionList, this);
        transactionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        transactionRecyclerView.setAdapter(transactionAdapter);

        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    private void fetchTransactionData() {
        transactionList.add(new Transaction("2023-06-15", "Medicine A", 10.99, 3));
        transactionList.add(new Transaction("2023-06-14", "Medicine B", 15.99, 2));
        transactionList.add(new Transaction("2023-06-13", "Medicine C", 8.99, 5));
    }

    @Override
    public void onUpdateButtonClick(int position) {

        Toast.makeText(this, "Update button clicked for position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteButtonClick(int position) {
        Toast.makeText(this, "Delete button clicked for position: " + position, Toast.LENGTH_SHORT).show();
    }

    private void logoutUser() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
