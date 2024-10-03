package controller;

import model.Category;
import model.Product;
import repo.MainMenuRepo;
import view.MainMenu;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class MainMenuController {
    private MainMenu view = new MainMenu();
    private MainMenuRepo repo = new MainMenuRepo();

    public MainMenuController(){
        mainMenuFunctionality();
    }

    private void mainMenuFunctionality() {
        DatabaseInfo.products = repo.getAllProducts(String.valueOf(view.getOrderByComboBox().getSelectedItem()));
        view.setVisible(true);
        view.getViewCart().addActionListener(e -> changeToShoppingCart());
        view.getSearchButton().addActionListener(e -> search());
        view.getButton1().addActionListener(e -> {
            view.loadProducts(DatabaseInfo.getProducts(), Category.JEWELLERY);
            viewButtonsFunctionality();  // Call this after loading products
        });
        view.getButton2().addActionListener(e -> {
            view.loadProducts(DatabaseInfo.getProducts(), Category.FLOWERS);
            viewButtonsFunctionality();  // Call this after loading products
        });
        view.getButton3().addActionListener(e -> {
            view.loadProducts(DatabaseInfo.getProducts(), Category.FLUFFS);
            viewButtonsFunctionality();  // Call this after loading products
        });
        view.getButton4().addActionListener(e -> {
            view.loadProducts(DatabaseInfo.getProducts(), Category.DRESSES);
            viewButtonsFunctionality();  // Call this after loading products
        });
        view.getRefreshButton().addActionListener(e -> refresh());
    }


    private void viewButtonsFunctionality(){
        List<Map<JButton, Product>> viewButtons = view.getViewButtons();

        for (Map<JButton, Product> buttonProductMap : viewButtons) {
            for (Map.Entry<JButton, Product> entry : buttonProductMap.entrySet()) {
                JButton viewButton = entry.getKey();
                Product product = entry.getValue();

                viewButton.addActionListener(e -> changeToProductDetails(product));
            }
        }
    }

    private void changeToProductDetails(Product product){
        view.setVisible(false);
        ProductDetailsController productDetailsController = new ProductDetailsController(product);
    }

    private void changeToShoppingCart(){
        view.setVisible(false);
        ShoppingCartController shoppingCartController = new ShoppingCartController();
    }

    private void search(){
        Product product = repo.getProductBySearch(view.getSearchField().getText());

        if(product == null){
            JOptionPane.showMessageDialog(null, "Product not available.");
        }
        else{
            changeToProductDetails(product);
        }
    }

    private void refresh(){
        removeRefreshActionListener();
        view.setVisible(false);
        mainMenuFunctionality();
    }

    private void removeRefreshActionListener() {
        ActionListener[] listeners = view.getRefreshButton().getActionListeners();
        for (ActionListener listener : listeners) {
            view.getRefreshButton().removeActionListener(listener);
        }
    }

}
