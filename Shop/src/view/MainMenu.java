package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainMenu extends JFrame {
    private JPanel rightPanel;
    private JButton adminMenu = new JButton("Admin Menu");
    private JButton viewCart = new JButton("View Cart");
    private JButton refreshButton = new JButton("Refresh");  // New Refresh Button
    private JButton button1 = new JButton("Jewellery"); // Placeholder for buttons
    private JButton button2 = new JButton("Flowers");
    private JButton button3 = new JButton("Fluffs");
    private JButton button4 = new JButton("Dresses");
    private List<Map<JButton, Product>> viewButtons = new ArrayList<>();
    private JTextField searchField = new JTextField(20);
    private JButton searchButton = new JButton("Search");
    private JComboBox<String> orderByComboBox = new JComboBox<>(new String[]{"Name", "Price"});

    public MainMenu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // Top Panel for buttons with light blue background
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(173, 216, 230)); // Light blue background color

        // Order By Panel (new panel)
        JPanel orderByPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        orderByPanel.setBackground(new Color(173, 216, 230)); // Light blue background color

        // Order By Label
        JLabel orderByLabel = new JLabel("Order by:");
        orderByPanel.add(orderByLabel);

        // Order By ComboBox
        orderByPanel.add(orderByComboBox);

        // Search Bar Panel
        JPanel searchBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchBarPanel.setBackground(new Color(173, 216, 230)); // Light blue background color

        // Add Order By Panel and Search Bar Panel to the top panel
        topPanel.add(orderByPanel, BorderLayout.WEST);
        topPanel.add(searchBarPanel, BorderLayout.CENTER);

        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);

        // View Cart and Refresh Buttons Panel on the right
        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtonsPanel.setBackground(new Color(173, 216, 230)); // Light blue background color
        rightButtonsPanel.add(refreshButton);  // New Refresh Button
        rightButtonsPanel.add(viewCart);

        // Add rightButtonsPanel to the top panel
        topPanel.add(rightButtonsPanel, BorderLayout.EAST);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Left Panel (your existing code)
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(500, 600));
        leftPanel.setBackground(new Color(204, 255, 204)); // Light green background color
        leftPanel.setBorder(new EmptyBorder(50, 20, 50, 20)); // Padding for buttons

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel labelTitle = new JLabel("MyShop"); // Placeholder for title label
        labelTitle.setFont(new Font("Arial", Font.BOLD, 25)); // Larger font for title

        ImageIcon img = new ImageIcon("src/Images/cart.png");
        JLabel photoLabel = new JLabel(img); // Placeholder for photo label

        leftPanel.add(labelTitle, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.1;
        leftPanel.add(button1, gbc);

        gbc.gridy = 2;
        leftPanel.add(button2, gbc);

        gbc.gridy = 3;
        leftPanel.add(button3, gbc);

        gbc.gridy = 4;
        leftPanel.add(button4, gbc);

        gbc.gridy = 5;
        gbc.weighty = 1.0;
        leftPanel.add(photoLabel, gbc);

        // Right Panel with light blue background (your existing code)
        rightPanel = new JPanel(new GridLayout(0, 1));
        rightPanel.setBackground(new Color(173, 216, 230)); // Light blue background color
        JScrollPane scrollPane = new JScrollPane(rightPanel);
        scrollPane.setPreferredSize(new Dimension(500, 600));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Add topPanel to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    public void loadProducts(List<Product> products, Category category) {
        rightPanel.removeAll();
        if (products != null) {
            for (Product product : products) {
                if(product.getCategory() == category) {
                    JPanel productPanel = new JPanel(new BorderLayout());
                    productPanel.setPreferredSize(new Dimension(480, 250));
                    productPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding for each product panel
                    productPanel.setBackground(Color.WHITE);

                    ImageIcon productImage = new ImageIcon(product.getImageURL()); // Assuming getProductImage() returns image path
                    JLabel photoLabel = new JLabel(productImage);
                    photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    productPanel.add(photoLabel, BorderLayout.CENTER);

                    JButton viewButton = new JButton("View");
                    productPanel.add(viewButton, BorderLayout.SOUTH);

                    Map<JButton, Product> buttonProduct = new HashMap<>();
                    buttonProduct.put(viewButton, product);
                    viewButtons.add(buttonProduct);

                    JLabel productNameLabel = new JLabel(product.getName());
                    productNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    productPanel.add(productNameLabel, BorderLayout.NORTH);

                    rightPanel.add(productPanel);
                }
            }
        }
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public JButton getAdminMenu() {
        return adminMenu;
    }

    public JButton getViewCart() {
        return viewCart;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JButton getButton4() {
        return button4;
    }

    public List<Map<JButton, Product>> getViewButtons() {
        return viewButtons;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JComboBox<String> getOrderByComboBox() {
        return orderByComboBox;
    }
}
