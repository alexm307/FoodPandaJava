package com.company.DataLayer;

import com.company.BusinessLayer.Account;
import com.company.BusinessLayer.DeliveryService;
import com.company.BusinessLayer.MenuItem;
import com.company.BusinessLayer.Order;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BusinessFileWriter {

    /**
     * Method for first report.
     * @param list of Orders
     */
    public static void generateFirst(List <Order> list) {
        try {
            FileWriter file = new FileWriter( "report1.txt");
            for (Order order : list){
                try {
                    file.write(order.toString() + "\n");
                } catch (IOException ignored) {
                }
            }
            file.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Method for second report.
     * @param list of MenuItems
     */
    public static void generateSecond(List<MenuItem> list) {
        try {
            FileWriter file = new FileWriter( "report2.txt");
            for (MenuItem menuItem : list){
                try {
                    file.write(menuItem.toString() + "\n");
                } catch (IOException ignored) {
                }
            }
            file.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Method for third report.
     * @param accounts of Accounts
     */
    public static void generateThird(List<Account> accounts) {
        try {
            FileWriter file = new FileWriter( "report3.txt");
            for (Account account : accounts){
                try {
                    file.write(account.toString() + "\n");
                } catch (IOException ignored) {
                }
            }
            file.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Method for fourth report.
     * @param strings list of strings to be added to the report
     */
    public static void generateFourth(List<String> strings) {
        try {
            FileWriter file = new FileWriter( "report4.txt");
            for (String string : strings){
                try {
                    file.write(string + "\n");
                } catch (IOException ignored) {
                }
            }
            file.close();
        } catch (IOException ignored) {
        }

    }


    /**
     * Method for generating the bills
     * @param orders list of orders on the bill
     * @param deliveryService the deliveryService class to be used
     */
    public static void generateBill(List <Order> orders, DeliveryService deliveryService) {
        try {
            FileWriter fileWriter = new FileWriter("bills.txt");

            for (Order order : orders) {
                ArrayList<MenuItem> itemsOnBill = deliveryService.getHashMap().get(order);
                fileWriter.append(order.toString());
                fileWriter.append("\n");
                for (MenuItem menuItem : itemsOnBill)
                    fileWriter.append(menuItem.toString());
                fileWriter.append("\n");
                fileWriter.append("Total price was " + deliveryService.calculateOrderPrice(order) + "\n");
            }
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
