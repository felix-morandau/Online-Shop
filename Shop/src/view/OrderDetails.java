package view;

import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderDetails extends JFrame {
    private JPanel mainPanel;
    private JPanel infoPanel;
    private JPanel bottomPanel;
    private JLabel totalLabel;
    private JButton placeOrderButton;

    public OrderDetails(List<Product> cartProducts) {
        setTitle("Order Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        // Info Panel
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        // Display product information
        for (Product product : cartProducts) {
            JLabel productLabel = new JLabel(product.getName() + " - $" + product.getPrice());
            infoPanel.add(productLabel);
        }

        // Total Label
        totalLabel = new JLabel("Total: $" + calculateTotal(cartProducts));
        totalLabel.setHorizontalAlignment(SwingConstants.LEFT);
        infoPanel.add(totalLabel);

        mainPanel.add(infoPanel, BorderLayout.CENTER);

        // Bottom Panel
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        placeOrderButton = new JButton("Place Order");
        bottomPanel.add(placeOrderButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private double calculateTotal(List<Product> cartProducts) {
        double total = 0;
        for (Product product : cartProducts) {
            total += product.getPrice();
        }
        return total;
    }

    public JButton getPlaceOrderButton() {
        return placeOrderButton;
    }
}
