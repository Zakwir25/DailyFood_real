package com.example.finallab;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private Context context;
    private List<Transaction> transactionList;
    private TransactionClickListener listener;

    public interface TransactionClickListener {
        void onUpdateButtonClick(int position);
        void onDeleteButtonClick(int position);
    }

    public TransactionAdapter(Context context, List<Transaction> transactionList, TransactionClickListener listener) {
        this.context = context;
        this.transactionList = transactionList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactionList.get(holder.getAdapterPosition());

        holder.transactionDateTextView.setText(transaction.getDate());
        holder.medicineNameTextView.setText(transaction.getMedicineName());
        holder.medicinePriceTextView.setText(String.valueOf(transaction.getMedicinePrice()));
        holder.transactionQuantityTextView.setText(String.valueOf(transaction.getQuantity()));

        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUpdateButtonClick(holder.getAdapterPosition());
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteButtonClick(holder.getAdapterPosition());
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteButtonClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView transactionDateTextView;
        public TextView medicineNameTextView;
        public TextView medicinePriceTextView;
        public TextView transactionQuantityTextView;
        public Button updateButton;
        public Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            transactionDateTextView = itemView.findViewById(R.id.transactionDateTextView);
            medicineNameTextView = itemView.findViewById(R.id.medicineNameTextView);
            medicinePriceTextView = itemView.findViewById(R.id.medicinePriceTextView);
            transactionQuantityTextView = itemView.findViewById(R.id.transactionQuantityTextView);
            updateButton = itemView.findViewById(R.id.updateButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}


