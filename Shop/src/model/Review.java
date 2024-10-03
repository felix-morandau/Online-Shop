package model;

import java.util.ArrayList;
import java.util.List;

public class Review {
    private String text;
    private int rating;
    private Client client;

    public Review(String text, int rating, Client client){
        this.text = text;
        this.rating = rating;
        this.client = client;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }

    public Client getClient() {
        return client;
    }
}
