package com.company.PresentationLayer;

import com.company.BusinessLayer.DeliveryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the login interface.
 */
public class LoginGUI extends JFrame {
    private JButton loginButton;
    private JButton registerButton;

    private JLabel usernameLabel;
    private JTextField usernameField;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    DeliveryService deliveryService;

    public LoginGUI (DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        this.setTitle("Log In");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 250, 300);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(220,220,250));

        usernameLabel = new JLabel("Please enter username:");
        usernameLabel.setBounds(10, 10, 200, 20);
        usernameField = new JTextField();
        usernameField.setBounds(10, 30, 200, 20);

        passwordLabel = new JLabel("Please enter password:");
        passwordLabel.setBounds(10, 60, 200, 20);
        passwordField = new JPasswordField();
        passwordField.setBounds(10, 90, 200, 20);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(50, 120, 100, 50);

        registerButton = new JButton("REGISTER");
        registerButton.setBounds(50, 180, 100, 50);

        this.getContentPane().add(usernameField);
        this.getContentPane().add(usernameLabel);
        this.getContentPane().add(passwordField);
        this.getContentPane().add(passwordLabel);
        this.getContentPane().add(loginButton);
        this.getContentPane().add(registerButton);

        this.addLoginActionListener();
        this.addRegisterActionListener();
    }

    /**
     * Methods for adding action listeners.
     */
    public void addRegisterActionListener() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                deliveryService.register(usernameField.getText(), password);
                deliveryService.save();
            }
        });
    }

    public void addLoginActionListener() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                if (!deliveryService.login(usernameField.getText(), password))
                    JOptionPane.showMessageDialog(null, "ACCOUNT NOT EXISTENT!");
            }
        });
    }
}
