package com.example.finallab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder>{

    private Context context;
    private List<Medicine> medicineList;

    public MedicineAdapter(Context context, List<Medicine> medicineList) {
        this.context = context;
        this.medicineList = medicineList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_medicine, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medicine medicine = medicineList.get(position);

        holder.medicineNameTextView.setText(medicine.getName());
        holder.medicineManufacturerTextView.setText(medicine.getManufacturer());
        holder.medicinePriceTextView.setText(String.valueOf(medicine.getPrice()));

        Picasso.get()
                .load(medicine.getImageUrl())
                .placeholder(R.drawable.b2)
                .into(holder.medicineImageView);
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView medicineImageView;
        public TextView medicineNameTextView;
        public TextView medicineManufacturerTextView;
        public TextView medicinePriceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medicineImageView = itemView.findViewById(R.id.medicineImageView);
            medicineNameTextView = itemView.findViewById(R.id.medicineNameTextView);
            medicineManufacturerTextView = itemView.findViewById(R.id.medicineManufacturerTextView);
            medicinePriceTextView = itemView.findViewById(R.id.medicinePriceTextView);
        }
    }
}



