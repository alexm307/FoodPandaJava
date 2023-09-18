package com.company.DataLayer;

import com.company.BusinessLayer.DeliveryService;

import java.io.*;

/**
 * The class for serialization.
 */
public class Serializator {

    /**
     * serialization method
     * @param deliveryService the class to be serialized
     */
    public static void serialize(DeliveryService deliveryService) {
        FileOutputStream file;
        try {
            file = new FileOutputStream("deliveryServiceSer.txt");
            ObjectOutputStream out = new ObjectOutputStream (file);
            out.writeObject(deliveryService);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * the deserialization method
     * @return the class to be deserialized
     */
    public static DeliveryService deSerialize() {
        try {
            FileInputStream file = new FileInputStream("deliveryServiceSer.txt");
            ObjectInputStream in;
            in = new ObjectInputStream(file);
            DeliveryService deliveryService = (DeliveryService) in.readObject(); // Method for deserialization of object
            in.close();
            file.close();
            return deliveryService;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
