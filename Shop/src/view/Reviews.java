package view;

import model.Review;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Reviews extends JFrame {
    private JButton backButton;
    private JButton addReviewButton;

    public Reviews(List<Review> reviews) {
        setTitle("Reviews");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));

        // Second Panel - Scroll Pane for Reviews
        JPanel reviewsPanel = new JPanel();
        reviewsPanel.setBackground(new Color(181, 215, 255)); // Light Blue
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));

        for (Review review : reviews) {
            JPanel reviewPanel = new JPanel(new GridBagLayout());
            reviewPanel.setBackground(Color.WHITE); // White background for each review
            reviewPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            JLabel usernameLabel = new JLabel("Username: " + review.getClient().getUsername());
            JLabel ratingLabel = new JLabel("Rating: " + review.getRating());
            JTextArea descriptionArea = new JTextArea("Description: " + review.getText());
            descriptionArea.setWrapStyleWord(true);
            descriptionArea.setLineWrap(true);
            descriptionArea.setEditable(false);

            // Make labels as wide as the panel
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0;
            gbc.gridy = 0;
            reviewPanel.add(usernameLabel, gbc);

            gbc.gridy = 1;
            reviewPanel.add(ratingLabel, gbc);

            gbc.gridy = 2;
            reviewPanel.add(descriptionArea, gbc);

            reviewsPanel.add(reviewPanel);
        }

        JScrollPane scrollPane = new JScrollPane(reviewsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.setPreferredSize(new Dimension(600, 40));
        bottomPanel.setBackground(new Color(184, 255, 181)); // Light Green

        backButton = new JButton("Back");
        backButton.setBackground(new Color(184, 255, 181)); // Light Green
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border

        addReviewButton = new JButton("Add Review");
        addReviewButton.setBackground(new Color(184, 255, 181)); // Light Green
        addReviewButton.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border

        bottomPanel.add(backButton);
        bottomPanel.add(addReviewButton);

        // Adding components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getAddReviewButton() {
        return addReviewButton;
    }
}
