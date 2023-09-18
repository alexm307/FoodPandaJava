package com.company;

import com.company.BusinessLayer.BaseProduct;
import com.company.BusinessLayer.DeliveryService;
import com.company.DataLayer.Serializator;

public class Main {
    public static void main(String[] args) {

        DeliveryService deliveryService = Serializator.deSerialize();
        if (deliveryService == null) {
            System.out.println("era null");
            deliveryService = new DeliveryService();
        }
        deliveryService.start();
    }
}
