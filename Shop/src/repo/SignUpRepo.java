package repo;

import model.Admin;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpRepo {

    public static boolean saveAdmin(Admin admin) {
        try (Connection connection = DatabaseConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO user (username, password, email) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getEmail());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveClient(Client client) {
        try (Connection connection = DatabaseConnection.connection()) {
            // Insert into 'user' table and retrieve the generated ID
            String insertUserQuery = "INSERT INTO \"user\" (username, password, email) VALUES (?, ?, ?) RETURNING user_id";
            try (PreparedStatement userStatement = connection.prepareStatement(insertUserQuery)) {
                userStatement.setString(1, client.getUsername());
                userStatement.setString(2, client.getPassword());
                userStatement.setString(3, client.getEmail());

                ResultSet resultSet = userStatement.executeQuery();

                int userId = -1; // Default value indicating failure
                if (resultSet.next()) {
                    userId = resultSet.getInt("user_id"); // Retrieve the generated ID
                }

                if (userId != -1) {
                    // Insert the retrieved ID along with other data into the 'client' table
                    String insertClientQuery = "INSERT INTO client (user_id, name, age, phone_nr) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement clientStatement = connection.prepareStatement(insertClientQuery)) {
                        clientStatement.setInt(1, userId);
                        clientStatement.setString(2, client.getName());
                        clientStatement.setInt(3, client.getAge());
                        clientStatement.setString(4, client.getPhoneNr());

                        int clientRowsAffected = clientStatement.executeUpdate();

                        // Return true if both inserts were successful
                        return clientRowsAffected > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
