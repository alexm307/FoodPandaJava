package com.company.BusinessLayer;

public class Validator {
    /**
     * Method that validates data input by the administrator when creating or updating a product
     * @param title product title
     * @param rating the rating
     * @param calories its amount of calories
     * @param protein the protein quantity
     * @param fat
     * @param sodium
     * @param price
     * @return the data as an array of int
     */
    public static int[] validateProduct(String title,String rating, String calories, String protein, String fat, String sodium, String price) {
        int []results = new int[5];
        if (calories.equals(""))
            results[0] = -1;
        else
            results[0] = Integer.parseInt(calories);
        if (protein.equals(""))
            results [1] = -1;
        else
            results[1] = Integer.parseInt(protein);
        if (fat.equals(""))
            results[2] = -1;
        else
            results[2] = Integer.parseInt(fat);
        if (sodium.equals(""))
            results[3] = -1;
        else
            results[3] = Integer.parseInt(sodium);
        if (price.equals(""))
            results[4] = -1;
        else
            results[4] = Integer.parseInt(price);
        if (rating.equals(""))
            results[0] = -1;  //if rating is null, first int is negative
        if (title.equals(""))
            results[0] = -1;
        return results;
    }
}
