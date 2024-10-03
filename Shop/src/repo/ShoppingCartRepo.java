package repo;

import model.Category;
import model.Client;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartRepo {
    public List<Product> getCartProducts() {
        List<Product> cartProductList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connection()) {
            // Query to retrieve all products from the cart with information from the products table
            String query = "SELECT c.product_id, p.name, p.price, p.description, p.category_id, p.photo_img " +
                    "FROM cart_item c " +
                    "JOIN products p ON c.product_id = p.product_id";
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
                    cartProductList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartProductList;
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

    public void addProductToCart(int clientId, Product product) {
        try (Connection connection = DatabaseConnection.connection()) {
            // Check if the product already exists in the cart
            if (productExistsInCart(connection, clientId, product)) {
                // If it exists, update the quantity
                updateProductQuantity(connection, clientId, product);
            } else {
                // If it doesn't exist, insert a new entry with quantity = 1
                insertNewProduct(connection, clientId, product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean productExistsInCart(Connection connection, int clientId, Product product) throws SQLException {
        // Query to check if the product already exists in the cart
        String query = "SELECT * FROM cart_item WHERE client_id = ? AND product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientId);
            preparedStatement.setInt(2, product.getId()); // Assuming there's a method getId() in Product

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    private void updateProductQuantity(Connection connection, int clientId, Product product) throws SQLException {
        // Query to update the quantity of an existing product in the cart
        String updateQuery = "UPDATE cart_item SET quantity = quantity + 1 WHERE client_id = ? AND product_id = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, clientId);
            updateStatement.setInt(2, product.getId());

            // Execute the update query
            updateStatement.executeUpdate();
        }
    }

    private void insertNewProduct(Connection connection, int clientId, Product product) throws SQLException {
        // Query to insert a new product into the cart_item table with quantity = 1
        String insertQuery = "INSERT INTO cart_item (client_id, product_id, quantity) VALUES (?, ?, 1)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setInt(1, clientId);
            insertStatement.setInt(2, product.getId());

            // Execute the insert query
            insertStatement.executeUpdate();
        }
    }

    public int getQuantity(Client client, Product product) {
        int quantity = -1;

        try (Connection connection = DatabaseConnection.connection()) {
            String query = "SELECT quantity FROM cart_item WHERE client_id = ? AND product_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, client.getId());
                preparedStatement.setInt(2, product.getId());

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    quantity = resultSet.getInt("quantity");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantity;
    }

    public int removeFromCart(Client client, Product product) {
        int updatedQuantity = -1; // Default value if not found

        try (Connection connection = DatabaseConnection.connection()) {
            // Get client and product IDs
            int clientId = client.getId();
            int productId = product.getId();

            if (clientId != -1 && productId != -1) {
                // Query to retrieve the current quantity from the cart_item table
                String selectQuery = "SELECT quantity FROM cart_item WHERE client_id = ? AND product_id = ?";
                try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                    selectStatement.setInt(1, clientId);
                    selectStatement.setInt(2, productId);

                    ResultSet resultSet = selectStatement.executeQuery();
                    if (resultSet.next()) {
                        int currentQuantity = resultSet.getInt("quantity");

                        if (currentQuantity == 1) {
                            // If the quantity is 1, delete the item from the cart
                            deleteCartItem(connection, clientId, productId);
                            updatedQuantity = 0; // Quantity becomes 0 after deletion
                        } else {
                            // If the quantity is greater than 1, decrease the quantity
                            updateCartItemQuantity(connection, clientId, productId, currentQuantity - 1);
                            updatedQuantity = currentQuantity - 1; // Updated quantity
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updatedQuantity;
    }

    // Helper method to delete a product from the cart_item table
    private void deleteCartItem(Connection connection, int clientId, int productId) throws SQLException {
        String deleteQuery = "DELETE FROM cart_item WHERE client_id = ? AND product_id = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
            deleteStatement.setInt(1, clientId);
            deleteStatement.setInt(2, productId);

            // Execute the delete query
            deleteStatement.executeUpdate();
        }
    }

    // Helper method to update the quantity of a product in the cart_item table
    private void updateCartItemQuantity(Connection connection, int clientId, int productId, int newQuantity) throws SQLException {
        String updateQuery = "UPDATE cart_item SET quantity = ? WHERE client_id = ? AND product_id = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, newQuantity);
            updateStatement.setInt(2, clientId);
            updateStatement.setInt(3, productId);

            // Execute the update query
            updateStatement.executeUpdate();
        }
    }
}
