package com.company.PresentationLayer;

import com.company.BusinessLayer.DeliveryService;
import com.company.BusinessLayer.MenuItem;
import com.company.BusinessLayer.Order;
import com.company.DataLayer.BusinessFileWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class EmployeeGUI extends JFrame implements Observer{

    public JButton backButton;
    public JButton takeOrderButton;
    public JList<Order> orderJList;
    ArrayList<Order> ordersToBeTaken;

    private DeliveryService deliveryService;

    public EmployeeGUI(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        ordersToBeTaken = new ArrayList<>();
        ordersToBeTaken.addAll(this.deliveryService.getOrders());
        this.setTitle("Employee");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 750, 500);
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().setBackground(new Color(220,220,250));

        takeOrderButton = new JButton("TAKE ORDER");

        orderJList = new JList<Order>(ordersToBeTaken.toArray(new Order[0]));
        orderJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);   //trebuie apasat CTRL sa selectezi mai multe
        orderJList.setBounds(300, 30, 900, 700);
        JScrollPane scrollPane = new JScrollPane(orderJList);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        backButton = new JButton("BACK");

        this.getContentPane().add(backButton);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(takeOrderButton);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.goBack();
            }
        });

        takeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusinessFileWriter.generateBill(orderJList.getSelectedValuesList(), deliveryService);
                for (Order order : orderJList.getSelectedValuesList())
                    ordersToBeTaken.remove(order);
                orderJList.setListData(ordersToBeTaken.toArray(new Order[0]));
            }
        });
    }

    /**
     * Update method
     * @param o
     * @param arg the argument passed
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(((Order) arg).getOrderID());
        ordersToBeTaken.add((Order) arg);
        orderJList.setListData(ordersToBeTaken.toArray(new Order[0]));
    }
}
