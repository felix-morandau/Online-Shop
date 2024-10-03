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

public class LoginRepo {
    public static Client validateClient(String username, String password) {
        try (Connection connection = DatabaseConnection.connection()) {
            // Query to retrieve user based on email/username and password
            String query = "SELECT * FROM \"user\" WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    for(Client client : DatabaseInfo.getClients()){
                        if(Objects.equals(client.getUsername(), username)){
                            System.out.println("lalsal");
                            return client;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DatabaseConnection.connection()) {
            String query = "SELECT *"
                    + "FROM \"user\" "
                    + "JOIN client ON \"user\".user_id = client.user_id";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    // Additional fields from the client table
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String phoneNr = resultSet.getString("phone_nr");

                    // Create a new Client object with the retrieved data and add it to the list
                    Client client = new Client(username, password, email, name, age, phoneNr);
                    clients.add(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
