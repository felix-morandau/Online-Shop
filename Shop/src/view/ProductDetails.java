package view;

import model.Product;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class ProductDetails extends JFrame {
    private final JButton backButton = new JButton("Back");
    private final JButton addToCartButton = new JButton("Add to Cart");
    private final JButton viewReviewsButton = new JButton("View Reviews");
    private final JButton addReviewButton = new JButton("Add Review"); // New button

    public ProductDetails(Product product) {
        setTitle("Product Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Left Panel (Photo)
        JPanel leftPanel = new JPanel(new BorderLayout());

        // Top Left Panel (Photo)
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setPreferredSize(new Dimension(300, 250));
        topLeftPanel.setBorder(new LineBorder(Color.BLACK, 4)); // Border around the photo
        topLeftPanel.setBackground(Color.WHITE);

        ImageIcon productImage = new ImageIcon(product.getImageURL()); // Assuming a method getImgURL() in Product
        JLabel photoLabel = new JLabel(productImage);
        topLeftPanel.add(photoLabel, BorderLayout.NORTH); // Align the photo label to the top

        leftPanel.add(topLeftPanel, BorderLayout.NORTH);

        // Bottom Left Panel (Remaining space)
        JPanel bottomLeftPanel = new JPanel();
        bottomLeftPanel.setLayout(new BoxLayout(bottomLeftPanel, BoxLayout.Y_AXIS));
        bottomLeftPanel.setBackground(Color.WHITE);

        // Add any additional components to the bottomLeftPanel as needed

        leftPanel.add(bottomLeftPanel, BorderLayout.SOUTH);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(leftPanel, gbc);

        // Right Panel (Product Details)
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(173, 216, 230)); // Light blue background

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Align labels to the top-right
        gbc.weighty = 1.0;

        addLabel(rightPanel, "Name: ", product.getName(), gbc);
        addLabel(rightPanel, "Price: ", "$" + product.getPrice(), gbc);
        addLabel(rightPanel, "Description: ", product.getDescription(), gbc);

        JScrollPane scrollPane = new JScrollPane(rightPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(300, 400));

        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(scrollPane, gbc);

        // Bottom Panel with Buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(200, 200, 200)); // Light gray background

        bottomPanel.add(backButton);
        bottomPanel.add(Box.createHorizontalGlue()); // Create space between buttons
        bottomPanel.add(addToCartButton);
        bottomPanel.add(viewReviewsButton);
        bottomPanel.add(addReviewButton); // New button

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(bottomPanel, gbc);

        add(mainPanel);

        pack();
        setVisible(true);
    }

    private void addLabel(JPanel panel, String label, String value, GridBagConstraints gbc) {
        JLabel labelComponent = new JLabel(label + value);
        labelComponent.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy++;
        panel.add(labelComponent, gbc);
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getAddToCartButton() {
        return addToCartButton;
    }

    public JButton getViewReviewsButton() {
        return viewReviewsButton;
    }

    public JButton getAddReviewButton() {
        return addReviewButton;
    }
}
