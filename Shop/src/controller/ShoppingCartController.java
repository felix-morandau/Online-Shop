package controller;

import model.Product;
import repo.ShoppingCartRepo;
import view.ShoppingCart;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class ShoppingCartController {
    private ShoppingCart shoppingCart;
    private final ShoppingCartRepo shoppingCartRepo = new ShoppingCartRepo();

    public ShoppingCartController() {
        cartFunctionality();
        removeButtonsFunctionality();
    }

    private void cartFunctionality() {
        DatabaseInfo.setCartProducts(shoppingCartRepo.getCartProducts());
        shoppingCart = new ShoppingCart(DatabaseInfo.getCurrentClient(), DatabaseInfo.getCartProducts());
        shoppingCart.setVisible(true);
        shoppingCart.getContinueShoppingButton().addActionListener(e -> changeToMainMenu());
    }

    private void removeButtonsFunctionality() {
        List<Map<JButton, Map<Product, JPanel>>> buttonProductPanelEntries = shoppingCart.getRemoveButtons();

        for (Map<JButton, Map<Product, JPanel>> entry : buttonProductPanelEntries) {
            JButton removeButton = entry.keySet().iterator().next();
            Map<Product, JPanel> productPanelMap = entry.get(removeButton);

            Product product = productPanelMap.keySet().iterator().next();
            JPanel productPanel = productPanelMap.get(product);

            removeButton.addActionListener(e -> removeButtonFunctionality(product, productPanel));
        }
    }

    private void removeButtonFunctionality(Product product, JPanel productPanel) {
        shoppingCartRepo.removeFromCart(DatabaseInfo.getCurrentClient(), product);
        if (shoppingCartRepo.getQuantity(DatabaseInfo.getCurrentClient(), product) == 0) {
            shoppingCart.removeProduct(productPanel);
        }
        refresh();
    }

    private void refresh(){
        shoppingCart.setVisible(false);
        cartFunctionality();
    }

    private void changeToMainMenu(){
        shoppingCart.setVisible(false);
        MainMenuController mainMenuController = new MainMenuController();
    }
}
