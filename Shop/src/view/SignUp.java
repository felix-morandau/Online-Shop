package view;

import javax.swing.*;
import java.awt.*;

public class SignUp extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeDropdown = new JComboBox<>(new String[]{"Client", "Admin"});
    private JButton signUpButtonRight = new JButton("Sign Up");

    public SignUp() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBackground(new Color(204, 255, 204)); // Light green background color

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(204, 255, 204)); // Light green background color

        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        gbcLeft.anchor = GridBagConstraints.WEST;
        gbcLeft.insets = new Insets(20, 0, 5, 0); // Padding for top label and between components

        ImageIcon img = new ImageIcon("src/Images/bagImg.png");
        JLabel photoLabelLeft = new JLabel(img);
        photoLabelLeft.setFont(new Font("Arial", Font.BOLD, 16));
        leftPanel.add(photoLabelLeft, gbcLeft);

        gbcLeft.gridy++;
        gbcLeft.insets = new Insets(5, 0, 5, 0); // Padding between labels

        JLabel welcomeLabel = new JLabel("Welcome to MyShop");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        leftPanel.add(welcomeLabel, gbcLeft);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(173, 216, 230)); // Light blue background color

        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        gbcRight.anchor = GridBagConstraints.WEST;
        gbcRight.insets = new Insets(20, 0, 5, 0); // Padding for top label

        gbcRight.gridy++;
        gbcRight.insets = new Insets(5, 0, 5, 0); // Padding between fields

        gbcRight.gridy++;
        gbcRight.insets = new Insets(5, 0, 5, 0); // Padding between fields

        JLabel[] labels = {
                new JLabel("Name:"),
                new JLabel("Age:"),
                new JLabel("Phone Number:"),
                new JLabel("Email:"),
                new JLabel("Username:"),
                new JLabel("Password:")
        };

        nameField = new JTextField(15);
        ageField = new JTextField(15);
        phoneNumberField = new JTextField(15);
        emailField = new JTextField(15);
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JTextField[] fields = {
                nameField,
                ageField,
                phoneNumberField,
                emailField,
                usernameField,
                passwordField
        };

        for (int i = 0; i < labels.length; i++) {
            gbcRight.gridy++;
            rightPanel.add(labels[i], gbcRight);
            gbcRight.gridy++;
            rightPanel.add(fields[i], gbcRight);
        }

        userTypeDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbcRight.gridy++;
        gbcRight.insets = new Insets(20, 0, 20, 0); // Padding for dropdown
        rightPanel.add(userTypeDropdown, gbcRight);

        signUpButtonRight.setFont(new Font("Arial", Font.BOLD, 16));
        signUpButtonRight.setAlignmentX(Component.CENTER_ALIGNMENT);

        gbcRight.gridy++;
        gbcRight.insets = new Insets(0, 0, 20, 0); // Padding for bottom button
        gbcRight.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(signUpButtonRight, gbcRight);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JComboBox<String> getUserTypeDropdown() {
        return userTypeDropdown;
    }

    public JButton getSignUpButtonRight() {
        return signUpButtonRight;
    }
}
