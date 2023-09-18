package com.company.BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeProduct extends MenuItem {
    ArrayList<MenuItem> menuItems;

    public CompositeProduct(String name, List<MenuItem> menuItems){
        this.menuItems = new ArrayList<>();
        this.setName(name);
        this.menuItems.addAll(menuItems);
        for (MenuItem menuItem: menuItems)
            System.out.println(menuItem);
    }

    /**
     * overriden method for computing the price of the composite product
     * @return
     */
    @Override
    public int computePrice() {
        int result = 0;
        for (MenuItem menuItem : menuItems) {
            result += menuItem.computePrice();
        }
        return result;
    }

    public void addBaseProduct(MenuItem baseProduct) {
        menuItems.add(baseProduct);
    }

    /**
     * Method for computing the properties of the composite product.
     */
    public void computeProperties() {
        int price = 0;
        int rating = 0;
        int calories = 0;
        int proteins = 0;
        int sodium = 0;
        int fat = 0;
        for (MenuItem menuItem : menuItems) {
            price += menuItem.getPrice();
            rating += menuItem.getRating();
            proteins += menuItem.getProtein();
            calories += menuItem.getCalories();
            sodium += menuItem.getSodium();
            fat += menuItem.getFat();
        }
        this.setPrice(price);
        rating /= menuItems.size();
        this.setRating(rating);
        this.setCalories(calories);
        this.setProtein(proteins);
        this.setFat(fat);
        this.setSodium(sodium);
    }
}
