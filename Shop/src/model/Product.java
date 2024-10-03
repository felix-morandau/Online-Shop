package model;

import controller.DatabaseInfo;
import repo.ClientProductRepo;
import repo.ShoppingCartRepo;

import java.util.ArrayList;
import java.util.List;

public class Product {
    protected String name;
    protected double price;
    protected String description;
    protected Category category;
    protected String imageURL;
    private List<Review> reviews = new ArrayList<>();


    public Product(String name, double price, String description, Category category, String imageURL) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getId(){
        return ClientProductRepo.getProductId(this.getName());
    }

    public int getCartQuantity(){
        ShoppingCartRepo shoppingCartRepo = new ShoppingCartRepo();
        return shoppingCartRepo.getQuantity(DatabaseInfo.getCurrentClient(), this);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
