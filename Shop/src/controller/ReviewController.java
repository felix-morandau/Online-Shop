package controller;

import model.Product;
import repo.ReviewRepo;
import view.Reviews;

public class ReviewController {
    private Reviews view;

    public ReviewController(Product product){
        viewReviewsFunctionality(product);
    }

    private void viewReviewsFunctionality(Product product){
        product.setReviews(ReviewRepo.getAllReviews(product));
        view = new Reviews(product.getReviews());
        view.setVisible(true);
        view.getBackButton().addActionListener(e -> changeToProductDetails(product));
        view.getAddReviewButton().addActionListener(e -> changeToAddReview(product));
    }

    private void changeToProductDetails(Product product){
        view.setVisible(false);
        ProductDetailsController productDetailsController = new ProductDetailsController(product);
    }

    private void changeToAddReview(Product product){
        view.setVisible(false);
        AddReviewController addReviewController = new AddReviewController(product, DatabaseInfo.getCurrentClient());
    }
}
