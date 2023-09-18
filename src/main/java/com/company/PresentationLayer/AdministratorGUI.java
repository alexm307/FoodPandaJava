package com.company.PresentationLayer;

import com.company.BusinessLayer.BaseProduct;
import com.company.BusinessLayer.DeliveryService;
import com.company.BusinessLayer.MenuItem;
import com.company.BusinessLayer.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministratorGUI extends JFrame {

    private DeliveryService deliveryService;

    private JButton createProductButton;    //buttons for doing CRUD on products :)
    private JButton readProductButton;
    private JButton updateProductButton;
    private JButton deleteProductButton;
    private JButton backButton;

    private JLabel nameLabel;
    private JTextField nameField;

    private JLabel priceLabel;
    private JTextField priceField;

    private JLabel ratingLabel;
    private JTextField ratingField;

    private JLabel calorieLabel;
    private JTextField calorieField;

    private JLabel fatLabel;
    private JTextField fatField;

    private JLabel proteinLabel;
    private JTextField proteinField;

    private JLabel sodiumLabel;
    private JTextField sodiumField;

    private JButton createCompositeMenu;

    private JButton importButton;

    private JButton reportsButton;

    private JList<MenuItem> menuItemJList;

    JTextArea productList;

    public AdministratorGUI (DeliveryService deliveryService) {
        this.deliveryService = deliveryService;

        this.setTitle("Admin");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 1200, 1000);
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().setBackground(new Color(220,220,250));

        importButton = new JButton("Import Products");
        importButton.setBounds(300, 880, 200, 50);

        createCompositeMenu = new JButton("Create Menu");
        createCompositeMenu.setBounds(10, 890, 200, 50);

        createProductButton = new JButton("Create Product");
        createProductButton.setBounds(10, 430, 150, 40);

        readProductButton = new JButton("Read Menu");
        readProductButton.setBounds(10, 480, 150, 40);

        updateProductButton = new JButton("Update Product");
        updateProductButton.setBounds(10, 530, 150, 40);

        deleteProductButton = new JButton("Delete Product");
        deleteProductButton.setBounds(10, 580, 150, 40);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 50, 20);
        nameField = new JTextField(20);

        ratingLabel = new JLabel("Rating:");
        ratingLabel.setBounds(10, 80, 50, 20);
        ratingField = new JTextField(20);

        calorieLabel = new JLabel("Calorie:");
        calorieLabel.setBounds(10, 140, 50, 20);
        calorieField = new JTextField(20);

        sodiumLabel = new JLabel("Sodium:");
        sodiumLabel.setBounds(10, 200, 50, 20);
        sodiumField = new JTextField(20);

        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 260, 200, 20);
        priceField = new JTextField(20);

        proteinLabel = new JLabel("Protein:");
        proteinLabel.setBounds(10, 320, 200, 20);
        proteinField = new JTextField(20);

        fatLabel = new JLabel("Fat:");
        fatLabel.setBounds(10, 380, 200, 20);
        fatField = new JTextField(20);

        productList = new JTextArea();
        productList.setBounds(250, 10, 700, 700);
        productList.setEditable(false);

        backButton = new JButton("BACK");

        menuItemJList = new JList<MenuItem>(deliveryService.getFullMenu().toArray(MenuItem[]::new));
        menuItemJList.setBounds(300, 30, 900, 700);
        menuItemJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);   //trebuie apasat CTRL sa selectezi mai multe
        JPanel panel = new JPanel();
        panel.setBounds(300, 100, 200, 200);
        panel.setBackground(Color.blue);
        JScrollPane scrollPane = new JScrollPane(menuItemJList);
        scrollPane.setPreferredSize(new Dimension(900, 800));

        reportsButton = new JButton("Reports");

        this.getContentPane().add(createProductButton);
        this.getContentPane().add(readProductButton);
        this.getContentPane().add(deleteProductButton);
        this.getContentPane().add(updateProductButton);
        this.getContentPane().add(createCompositeMenu);
        this.getContentPane().add(nameLabel);
        this.getContentPane().add(nameField);
        this.getContentPane().add(calorieLabel);
        this.getContentPane().add(calorieField);
        this.getContentPane().add(fatLabel);
        this.getContentPane().add(fatField);
        this.getContentPane().add(proteinLabel);
        this.getContentPane().add(proteinField);
        this.getContentPane().add(sodiumLabel);
        this.getContentPane().add(sodiumField);
        this.getContentPane().add(priceLabel);
        this.getContentPane().add(priceField);
        this.getContentPane().add(ratingLabel);
        this.getContentPane().add(ratingField);
        this.getContentPane().add(importButton);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(reportsButton);
        this.getContentPane().add(backButton);

        this.addActionListeners();
    }

    public void addList(JList<MenuItem> menuItemJList) {
        this.setVisible(false);
        this.getContentPane().add(menuItemJList);
        this.setVisible(true);
    }

    /**
     * Method for action listeners.
     */
    public void addActionListeners() {
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame reportsFrame = new JFrame();
                reportsFrame.setTitle("Reports");
                //reportsFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                reportsFrame.setBounds(500, 150, 470, 300);
                reportsFrame.getContentPane().setLayout(null);
                reportsFrame.getContentPane().setBackground(new Color(220,220,250));
                reportsFrame.setVisible(true);
                JButton repo1 = new JButton("Report 1");
                repo1.setBounds(10, 200, 100, 50);
                JButton repo2 = new JButton("Report 2");
                repo2.setBounds(120, 200, 100, 50);
                JButton repo3 = new JButton("Report 3");
                repo3.setBounds(230, 200, 100 ,50);
                JButton repo4 = new JButton("Report 4");
                repo4.setBounds(340, 200, 100, 50);

                JLabel startHourLabel = new JLabel("Start hour:");
                startHourLabel.setBounds(10, 10, 100, 20);
                JTextField startHourField = new JTextField();
                startHourField.setBounds(10, 30, 100, 20);

                JLabel endHourLabel = new JLabel("End hour:");
                endHourLabel.setBounds(10, 50, 100, 20);
                JTextField endHourField = new JTextField();
                endHourField.setBounds(10, 70, 100, 20);

                JLabel timesLabel = new JLabel("Times:");
                timesLabel.setBounds(120, 10, 100, 20);
                JTextField timesField = new JTextField();
                timesField.setBounds(120, 30, 100, 20);

                JLabel minPriceLabel = new JLabel("Min Price:");
                minPriceLabel.setBounds(230, 10, 100, 20);
                JTextField minPriceField = new JTextField();
                minPriceField.setBounds(230, 30, 100, 20);

                JLabel dayLabel = new JLabel("Day of month:");
                dayLabel.setBounds(340, 10, 100, 20);
                JTextField dayField = new JTextField();
                dayField.setBounds(340, 30, 100, 20);

                reportsFrame.getContentPane().add(startHourLabel);
                reportsFrame.getContentPane().add(startHourField);
                reportsFrame.getContentPane().add(endHourLabel);
                reportsFrame.getContentPane().add(endHourField);
                reportsFrame.getContentPane().add(timesLabel);
                reportsFrame.getContentPane().add(dayLabel);
                reportsFrame.getContentPane().add(minPriceField);
                reportsFrame.getContentPane().add(minPriceLabel);
                reportsFrame.getContentPane().add(timesField);
                reportsFrame.getContentPane().add(dayField);
                reportsFrame.getContentPane().add(repo1);
                reportsFrame.getContentPane().add(repo2);
                reportsFrame.getContentPane().add(repo3);
                reportsFrame.getContentPane().add(repo4);

                repo1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deliveryService.generateTimeIntervalReport(Integer.parseInt(startHourField.getText()), Integer.parseInt(endHourField.getText()));
                    }
                });

                repo2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deliveryService.generateSpecifiedTimesReport(Integer.parseInt(timesField.getText()));
                    }
                });

                repo3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deliveryService.generateClientsReport(Integer.parseInt(timesField.getText()), Integer.parseInt(minPriceField.getText()));
                    }
                });

                repo4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deliveryService.generateDayReport(Integer.parseInt(dayField.getText()));
                    }
                });
            }
        });

        createProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int []numerical = Validator.validateProduct(nameField.getText(), ratingField.getText(), calorieField.getText(), proteinField.getText(),
                        fatField.getText(), sodiumField.getText(), priceField.getText());
                if (numerical[0] == -1)
                    JOptionPane.showMessageDialog(null, "Invalid product!");
                else {
                    double rating = Double.parseDouble(ratingField.getText());
                    int calories = numerical[0];
                    int protein = numerical[1];
                    int fat = numerical[2];
                    int sodium = numerical[3];
                    int price = numerical[4];
                    MenuItem newProduct = new BaseProduct(nameField.getText(), rating, calories, protein, fat, sodium, price);
                    deliveryService.createMenuItem(newProduct);
                    JOptionPane.showMessageDialog(null, "Product created!");
                    menuItemJList.setListData(deliveryService.getFullMenu().toArray(new MenuItem[0]));
                }
                deliveryService.save();
            }
        });

        readProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    menuItemJList = new JList<MenuItem>(deliveryService.getFullMenu().toArray(MenuItem[]::new));     //hopefully this is the list of menu items that we can select
                    menuItemJList.setBounds(250, 10, 700, 700);
                    addList(menuItemJList);
                    menuItemJList.setListData(deliveryService.getFullMenu().toArray(new MenuItem[0]));
                    System.out.println("hope it worked");
            }
        });

        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.deleteMenuItems(menuItemJList.getSelectedValuesList());
                menuItemJList.setListData(deliveryService.getFullMenu().toArray(new MenuItem[0]));
                deliveryService.save();
            }
        });

        updateProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int []numerical = Validator.validateProduct(nameField.getText(), ratingField.getText(), calorieField.getText(), proteinField.getText(),
                        fatField.getText(), sodiumField.getText(), priceField.getText());
                double rating = -1;
                if (!ratingField.getText().equals(""))
                    rating = Double.parseDouble(ratingField.getText());
                    int calories = numerical[0];
                    int protein = numerical[1];
                    int fat = numerical[2];
                    int sodium = numerical[3];
                    int price = numerical[4];
                    MenuItem updatedProduct = new BaseProduct(nameField.getText(), rating, calories, protein, fat, sodium, price);
                    if (deliveryService.modifyMenuItem(updatedProduct, menuItemJList.getSelectedValue()))
                        JOptionPane.showMessageDialog(null, "Product updated!");
                    else
                        JOptionPane.showMessageDialog(null, "Product not found!");
                    menuItemJList.setListData(deliveryService.getFullMenu().toArray(new MenuItem[0]));
                deliveryService.save();
            }
        });

        createCompositeMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuItemJList.getSelectedValuesList().size() == 0)
                    JOptionPane.showMessageDialog(null, "NO ITEMS WERE SELECTED");
                else {
                    deliveryService.createCompositeItem(menuItemJList.getSelectedValuesList(), nameField.getText());
                    menuItemJList.setListData(deliveryService.getFullMenu().toArray(new MenuItem[0]));
                    deliveryService.save();
                }
            }
        });

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.importMenuItems();
                menuItemJList.setListData(deliveryService.getFullMenu().toArray(new MenuItem[0]));
                deliveryService.save();
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
