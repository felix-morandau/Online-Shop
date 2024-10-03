package view;

import controller.LoginController;
import controller.SignUpController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel welcomeLabel;
    private JLabel leftImageLabel;
    private JLabel rightImageLabel;
    private JLabel emailLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel notMemberLabel;
    private JButton signUpButton;

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));
        setLayout(new GridLayout(1, 2));

        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(184, 255, 181)); // Light Green
        leftPanel.setPreferredSize(new Dimension(500, 600));

        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(181, 215, 255)); // Light Blue
        rightPanel.setPreferredSize(new Dimension(500, 600));

        // Left Panel
        welcomeLabel = new JLabel("Welcome to MyShop");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        leftImageLabel = new JLabel(new ImageIcon("src/Images/bagImg.png")); // Replace "left_image.jpg" with your image file path
        leftImageLabel.setHorizontalAlignment(JLabel.CENTER);

        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        gbcLeft.insets = new Insets(20, 0, 20, 0); // Top, Left, Bottom, Right
        leftPanel.add(welcomeLabel, gbcLeft);

        gbcLeft.gridy = 1;
        leftPanel.add(leftImageLabel, gbcLeft);

        // Right Panel
        rightImageLabel = new JLabel(new ImageIcon("src/Images/blackIcon.png")); // Replace "right_image.jpg" with your image file path
        rightImageLabel.setHorizontalAlignment(JLabel.CENTER);

        emailLabel = new JLabel("Email:");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        emailField = new JTextField(15);
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle login button click
            }
        });

        notMemberLabel = new JLabel("Not a member?");
        signUpButton = new JButton("Sign Up");

        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        gbcRight.insets = new Insets(20, 0, 5, 0); // Top, Left, Bottom, Right
        gbcRight.anchor = GridBagConstraints.CENTER;

        rightPanel.add(rightImageLabel, gbcRight);

        gbcRight.gridy = 1;
        gbcRight.anchor = GridBagConstraints.LINE_START;
        gbcRight.fill = GridBagConstraints.HORIZONTAL;
        gbcRight.ipadx = 10; // Add some padding between labels and fields

        rightPanel.add(emailLabel, gbcRight);
        gbcRight.gridy++;
        gbcRight.insets = new Insets(0, 0, 5, 0); // Adjust insets for emailField
        rightPanel.add(emailField, gbcRight);
        gbcRight.gridy++;
        rightPanel.add(usernameLabel, gbcRight);
        gbcRight.gridy++;
        gbcRight.insets = new Insets(0, 0, 5, 0); // Adjust insets for usernameField
        rightPanel.add(usernameField, gbcRight);
        gbcRight.gridy++;
        rightPanel.add(passwordLabel, gbcRight);
        gbcRight.gridy++;
        gbcRight.insets = new Insets(0, 0, 5, 0); // Adjust insets for passwordField
        rightPanel.add(passwordField, gbcRight);

        // Button components at the bottom
        gbcRight.gridy++;
        gbcRight.anchor = GridBagConstraints.CENTER; // Center the components
        gbcRight.insets = new Insets(5, 0, 5, 0); // Adjust insets for the loginButton
        rightPanel.add(loginButton, gbcRight);

        gbcRight.gridy++;
        gbcRight.insets = new Insets(30, 0, 5, 0); // Adjust insets for the "Not a member?" label
        rightPanel.add(notMemberLabel, gbcRight);

        gbcRight.gridy++;
        gbcRight.insets = new Insets(0, 0, 20, 0); // Adjust insets for the "Sign Up" button
        rightPanel.add(signUpButton, gbcRight);

        add(leftPanel);
        add(rightPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel getLeftPanel() {
        return leftPanel;
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

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getSignUpButton() {
        return signUpButton;
    }
}
