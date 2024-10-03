package controller;

import model.*;
import repo.ReviewRepo;
import view.AddReview;

import javax.swing.*;

public class AddReviewController {
    private final AddReview view = new AddReview();

    public AddReviewController(Product product, Client client){
        addReviewFunctionality(product, client);
    }

    private void addReviewFunctionality(Product product, Client client){
        view.setVisible(true);
        view.getAddButton().addActionListener(e -> addReviewButton(product, client));
    }

    private void addReviewButton(Product product, Client client){
        boolean hasReviewed = ReviewRepo.addReview(product, client, view.getDescriptionTextField().getText(), Integer.parseInt(view.getRatingComboBox().getSelectedItem().toString()));

        if(hasReviewed) {
            JOptionPane.showMessageDialog(null, "Couldn't add review.");
        }
        changeToProductDetails(product);

    }

    private void changeToProductDetails(Product product){
        view.setVisible(false);
        ProductDetailsController productDetailsController = new ProductDetailsController(product);
    }
}
