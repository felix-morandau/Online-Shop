package repo;

import controller.DatabaseInfo;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainMenuRepo {
    // Function to get all products from the database and add them to a list
    public List<Product> getAllProducts(String orderBy) {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connection()) {
            // Query to retrieve all products
            String query;
            if (Objects.equals(orderBy, "Name")) {
                query = "SELECT * FROM products ORDER BY name";
            } else {
                query = "SELECT * FROM products ORDER BY price";
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    // Create Product objects from the retrieved data
                    int productId = resultSet.getInt("product_id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    String description = resultSet.getString("description");
                    int categoryId = resultSet.getInt("category_id");
                    String imageURL = resultSet.getString("photo_img");

                    // Create and add the Product to the list
                    Product product = new Product(name, price, description, getCategory(categoryId), imageURL);
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }


    private static Category getCategory(int category_id){
        switch (category_id){
            case 1:
                return Category.JEWELLERY;

            case 2:
                return Category.FLOWERS;

            case 3:
                return Category.FLUFFS;

            default:
                return Category.DRESSES;
        }
    }

    public Product getProductBySearch(String name) {
        Product product = null;

        try (Connection connection = DatabaseConnection.connection()) {
            // Use parameter placeholders in the LIKE condition to avoid SQL injection
            String query = "SELECT product_id FROM products WHERE name LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Set the parameter value with the %name% format
                preparedStatement.setString(1, "%" + name + "%");

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("product_id");
                    product = DatabaseInfo.getProductById(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

}
