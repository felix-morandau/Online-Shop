package repo;

import controller.DatabaseInfo;
import model.Client;
import model.Product;
import model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepo {

    public static List<Review> getAllReviews(Product product) {
        List<Review> reviews = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connection()) {
            String query = "SELECT * FROM reviews WHERE product_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, product.getId()); // Assuming there's a method getId() in Product
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int rating = resultSet.getInt("rating");
                    String description = resultSet.getString("description");
                    int clientId = resultSet.getInt("client_id");

                    Review review = new Review(description, rating, getClientById(clientId));
                    reviews.add(review);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }

    private static Client getClientById(int id) {
        Client client = null;

        try (Connection connection = DatabaseConnection.connection()) {
            String query = "SELECT username, client.user_id as u_id FROM client JOIN \"user\" ON client.user_id = \"user\".user_id WHERE client_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int userId = resultSet.getInt("u_id");
                    String username = resultSet.getString("username");

                    client = DatabaseInfo.retrieveClientByUsername(username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }

    public static boolean addReview(Product product, Client client, String description, int rating) {
        if (!reviewExists(product, client)) {
            try (Connection connection = DatabaseConnection.connection()) {
                String insertQuery = "INSERT INTO reviews (product_id, client_id, description, rating) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    insertStatement.setInt(1, product.getId());
                    insertStatement.setInt(2, client.getId());
                    insertStatement.setString(3, description);
                    insertStatement.setInt(4, rating);

                    // Execute the insert query
                    insertStatement.executeUpdate();
                    return true; // Successfully added the review
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // Review already exists
    }

    private static boolean reviewExists(Product product, Client client) {
        try (Connection connection = DatabaseConnection.connection()) {
            String query = "SELECT COUNT(*) FROM reviews WHERE product_id = ? AND client_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, product.getId()); // Assuming there's a method getId() in Product
                preparedStatement.setInt(2, client.getId()); // Assuming there's a method getId() in Client

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
