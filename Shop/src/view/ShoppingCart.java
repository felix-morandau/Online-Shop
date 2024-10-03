package view;

import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart extends JFrame {
    private JPanel rightPanel;  // Declare rightPanel at the class level
    private JScrollPane scrollPane;  // Declare scrollPane at the class level
    private JButton continueShoppingButton = new JButton("Continue Shopping");

    private List<Map<JButton, Map<Product, JPanel>>> removeButtons = new ArrayList<>();

    public ShoppingCart(Client client, List<Product> products) {
        setTitle(client.getUsername() + "'s Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Left Panel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(500, 600));
        leftPanel.setBackground(new Color(255, 200, 200)); // Light red background

        JLabel nameLabel = new JLabel(client.getUsername() + "'s Cart");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        leftPanel.add(nameLabel, BorderLayout.CENTER);

        // Right Panel (Scroll Panel)
        rightPanel = new JPanel(new GridLayout(0, 1));
        rightPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(rightPanel);
        scrollPane.setPreferredSize(new Dimension(500, 600));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        for (Product product : products) {
            JPanel productPanel = new JPanel(new BorderLayout());
            productPanel.setPreferredSize(new Dimension(400, 200));
            productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            productPanel.setBackground(Color.WHITE);

            ImageIcon productImage = new ImageIcon(product.getImageURL());
            JLabel photoLabel = new JLabel(productImage);
            productPanel.add(photoLabel, BorderLayout.WEST);

            JPanel textPanel = new JPanel(new BorderLayout());
            textPanel.setBackground(Color.WHITE);

            JLabel titleLabel = new JLabel(product.getName() + " X " + product.getCartQuantity());
            titleLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            JButton removeButton = new JButton("Remove");
            removeButton.setBackground(Color.RED);

            Dimension buttonSize = new Dimension(80, 30);
            removeButton.setPreferredSize(buttonSize);
            removeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));


            Map<Product, JPanel> productPanelMap = new HashMap<>();
            productPanelMap.put(product, productPanel);

            Map<JButton, Map<Product, JPanel>> buttonProductMap = new HashMap<>();
            buttonProductMap.put(removeButton, productPanelMap);
            removeButtons.add(buttonProductMap);

            // Add the titleLabel and removeButton to the textPanel
            textPanel.add(titleLabel, BorderLayout.CENTER);
            textPanel.add(removeButton, BorderLayout.SOUTH);

            productPanel.add(textPanel, BorderLayout.CENTER);
            rightPanel.add(productPanel);
        }

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(new Color(255, 220, 220)); // Light pink background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton checkoutButton = new JButton("Go to Checkout");

        gbc.gridx = 0;
        gbc.gridy = 0;
        bottomPanel.add(continueShoppingButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        bottomPanel.add(checkoutButton, gbc);

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void removeProduct(JPanel productPanel) {
        rightPanel.remove(productPanel);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public List<Map<JButton, Map<Product, JPanel>>> getRemoveButtons() {
        return removeButtons;
    }

    public JButton getContinueShoppingButton() {
        return continueShoppingButton;
    }
}
