package com.example.finallab;

public class Medicine {
    private String name;
    private String manufacturer;
    private String imageUrl;
    private double price;

    private String description;

    public Medicine(String name, String manufacturer, String imageUrl, double price, String description) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }
    public String getManufacturer() {
        return manufacturer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }
}

