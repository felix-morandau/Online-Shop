package repo;

import model.Client;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientProductRepo {
    private static final Connection connection = DatabaseConnection.connection();
    public static int getProductId(String productName) {

        int productId = -1; // Default value if not found

        try {
            String query = "SELECT product_id FROM products WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, productName);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    productId = resultSet.getInt("product_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productId;
    }

    // Retrieve the ID of a client from the database
    public static int getClientId(String username) {
        int clientId = -1; // Default value if not found

        try {
            String query = "SELECT client_id FROM client " +
                    "JOIN \"user\" ON client.user_id = \"user\".user_id " +
                    "WHERE \"user\".username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    clientId = resultSet.getInt("client_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientId;
    }

}
