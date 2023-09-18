package com.company.BusinessLayer;

import java.io.Serializable;

public class BaseProduct extends MenuItem {

    public BaseProduct (String name, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.setName(name);
        this.setPrice(price);
        this.setRating(rating);
        this.setCalories(calories);
        this.setProtein(protein);
        this.setFat(fat);
        this.setSodium(sodium);
    }

    @Override
    public int computePrice() {
        return this.getPrice();
    }
}
