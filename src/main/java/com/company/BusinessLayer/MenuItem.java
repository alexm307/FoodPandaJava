package com.company.BusinessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable{
    private String name;
    private int price;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;

    public abstract int computePrice();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium;
    }
}
