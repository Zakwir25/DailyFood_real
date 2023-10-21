package com.example.finallab;

public class Transaction {
    private String date;
    private String medicineName;
    private double medicinePrice;
    private int quantity;

    public Transaction(String date, String medicineName, double medicinePrice, int quantity) {
        this.date = date;
        this.medicineName = medicineName;
        this.medicinePrice = medicinePrice;
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public double getMedicinePrice() {
        return medicinePrice;
    }

    public int getQuantity() {
        return quantity;
    }
}

