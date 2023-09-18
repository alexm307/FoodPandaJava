package com.company.BusinessLayer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * The class implmenting the orders in the system.
 */
public class Order implements Serializable {
    private int orderID;
    private int clientID;
    private LocalDateTime orderDate;

    public Order() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Classic methods used for the map data structure
     * @param o is the object passed to the equals method
     * @return whether there is equality
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderID == order.orderID && clientID == order.clientID && Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, clientID, orderDate);
    }

    public String toString () {
        String newStr = new String("client id: " + this.getClientID() + " order id: " + this.getOrderID() + " order date: " + this.getOrderDate());
        return newStr;
    }
}
