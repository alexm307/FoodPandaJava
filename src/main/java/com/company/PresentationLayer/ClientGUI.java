package com.company.PresentationLayer;

import com.company.BusinessLayer.Account;
import com.company.BusinessLayer.DeliveryService;
import com.company.BusinessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientGUI extends JFrame {

    JButton makeOrderButton;
    JButton searchButton;
    JButton readMenuButton;
    JButton backButton;

    JComboBox<String> selectionCriteria;

    JLabel nameLabel;
    JTextField nameField;

    JList<MenuItem> menuItemJList;

    private DeliveryService deliveryService;
    private Account clientAccount;

    public ClientGUI (DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        this.setTitle("Client");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 1000, 910);
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().setBackground(new Color(220,220,250));

        selectionCriteria = new JComboBox<>();
        selectionCriteria.addItem("Name");
        selectionCriteria.addItem("Rating");
        selectionCriteria.addItem("Calories");
        selectionCriteria.addItem("Protein");
        selectionCriteria.addItem("Fat");
        selectionCriteria.addItem("Sodium");
        selectionCriteria.addItem("Price");

        nameLabel = new JLabel("Category:");
        nameLabel.setBounds(10, 20, 50, 20);
        nameField = new JTextField(20);

        searchButton = new JButton("Search");
        searchButton.setBounds(55, 450, 100, 40);

        makeOrderButton = new JButton("Place Order");
        makeOrderButton.setBounds(10, 800, 200, 40);

        readMenuButton = new JButton("Read Menu");

        menuItemJList = new JList<MenuItem>(deliveryService.getFullMenu().toArray(MenuItem[]::new));
        menuItemJList.setBounds(300, 30, 900, 700);
        menuItemJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);   //trebuie apasat CTRL sa selectezi mai multe
        JPanel panel = new JPanel();
        panel.setBounds(300, 100, 200, 200);
        panel.setBackground(Color.blue);
        JScrollPane scrollPane = new JScrollPane(menuItemJList);
        scrollPane.setPreferredSize(new Dimension(900, 800));

        backButton = new JButton("BACK");

        readMenuButton = new JButton("Read Menu");

        this.getContentPane().add(selectionCriteria);
        this.getContentPane().add(nameLabel);
        this.getContentPane().add(nameField);
        this.getContentPane().add(searchButton);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(readMenuButton);
        this.getContentPane().add(makeOrderButton);
        this.getContentPane().add(backButton);

        this.addActionListeners();
    }

    /**
     * Method for setting the current client using the application
     * @param clientAccount the account
     */
    public void setClientAccount (Account clientAccount) {
        this.clientAccount = clientAccount;
    }

    /**
     * Method for action listeners
     */
    public void addActionListeners() {
        makeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.createOrder(menuItemJList.getSelectedValuesList(), clientAccount.getID());
                deliveryService.save();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<MenuItem> filteredItems = deliveryService.searchByCriteria(selectionCriteria.getSelectedIndex(), nameField.getText());
                if (filteredItems != null && filteredItems.size() > 0)
                    menuItemJList.setListData(filteredItems.toArray(new MenuItem[0]));
                else
                    JOptionPane.showMessageDialog(null, "PRODUCTS NOT FOUND!");
            }
        });

        readMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemJList.setListData(deliveryService.getFullMenu().toArray(new MenuItem[0]));
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.goBack();
            }
        });
    }
}
