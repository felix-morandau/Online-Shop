package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddReview extends JFrame {
    private JComboBox<String> ratingComboBox;
    private JButton addButton;

    private JLabel ratingLabel;
    private JLabel descriptionLabel;
    private JTextField descriptionTextField;

    public AddReview() {
        setTitle("Add Review");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(181, 215, 255));  // Set background color

        // Initialize components
        ratingLabel = new JLabel("Rating:");
        ratingComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        descriptionLabel = new JLabel("Description:");
        descriptionTextField = new JTextField();
        addButton = new JButton("Add");

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Add components to the frame with GridBagConstraints
        add(ratingLabel, gbc);
        gbc.gridx = 1;
        add(ratingComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(descriptionLabel, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;  // Make the description field longer
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(descriptionTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Reset gridwidth
        gbc.anchor = GridBagConstraints.SOUTHEAST;  // Align to the bottom right
        gbc.fill = GridBagConstraints.NONE;
        add(addButton, gbc);
        setVisible(true);
    }

    public JComboBox<String> getRatingComboBox() {
        return ratingComboBox;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JTextField getDescriptionTextField() {
        return descriptionTextField;
    }
}
