package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Profile extends JFrame {
    private JLabel profilePhotoLabel;
    private JTextArea detailsTextArea;

    public Profile() {
        setTitle("Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));
        setLayout(new BorderLayout());

        // Top Panel for Profile Photo (Light Green)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setPreferredSize(new Dimension(600, 300)); // Half the frame height
        topPanel.setBackground(Color.decode("#90EE90")); // Light Green color

        // Replace the string path with the path to your profile photo
        ImageIcon profilePhoto = new ImageIcon("path_to_profile_photo.jpg");
        profilePhotoLabel = new JLabel(profilePhoto);
        topPanel.add(profilePhotoLabel);

        // Second Panel for Details (Champagne Pink)
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBackground(Color.decode("#F1DDCF")); // Champagne Pink color

        // Example details (replace this with actual details)
        String userDetails = "Name: John Doe\nAge: 30\nOccupation: Developer";
        JLabel detailsLabel = new JLabel(userDetails);
        detailsLabel.setPreferredSize(new Dimension(400, 300)); // Set preferred size for the label

        JScrollPane scrollPane = new JScrollPane(detailsLabel);
        middlePanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBackground(Color.decode("#FFE4E1"));

        // Third Panel for Buttons (Light Green)
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2)); // 2 columns for 2 buttons
        bottomPanel.setPreferredSize(new Dimension(600, 50)); // Small panel at the bottom
        bottomPanel.setBackground(Color.decode("#90EE90")); // Light Green color

        // Back Button (Light Green)
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.decode("#90EE90")); // Light Green color

        // Change Details Button (Light Green)
        JButton changeDetailsButton = new JButton("Change Details");
        changeDetailsButton.setBackground(Color.decode("#90EE90")); // Light Green color

        // Setting border for the buttons (Black border)
        Border buttonBorder = new LineBorder(Color.BLACK, 1);
        backButton.setBorder(buttonBorder);
        changeDetailsButton.setBorder(buttonBorder);

        // Adding buttons to the bottom panel
        bottomPanel.add(backButton);
        bottomPanel.add(changeDetailsButton);

        // Adding panels to the frame
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
