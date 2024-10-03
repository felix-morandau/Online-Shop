package controller;

import model.Client;
import model.Product;
import repo.ShoppingCartRepo;
import view.ProductDetails;

import javax.swing.*;

public class ProductDetailsController {
    private ProductDetails view;

    public ProductDetailsController(Product product){
        productDetailsFunctionality(product);
    }

    private void productDetailsFunctionality(Product product){
        view = new ProductDetails(product);
        view.setVisible(true);
        view.getBackButton().addActionListener(e -> goBack());
        view.getAddToCartButton().addActionListener(e -> addToCart(product));
        view.getAddReviewButton().addActionListener(e -> changeToAddReview(product, DatabaseInfo.getCurrentClient()));
        view.getViewReviewsButton().addActionListener(e -> changeToViewReviews(product));
    }

    private void addToCart(Product product){
        ShoppingCartRepo shoppingCartRepo = new ShoppingCartRepo();
        shoppingCartRepo.addProductToCart(DatabaseInfo.getCurrentClient().getId(), product);
        JOptionPane.showMessageDialog(null, "Product added successfully.");
    }

    private void goBack(){
        view.setVisible(false);
        MainMenuController mainMenuController = new MainMenuController();
    }

    private void changeToAddReview(Product product, Client client){
        view.setVisible(false);
        AddReviewController addReviewController = new AddReviewController(product, client);
    }

    private void changeToViewReviews(Product product){
        view.setVisible(false);
        ReviewController reviewController = new ReviewController(product);
    }
}
