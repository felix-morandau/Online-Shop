package repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_NAME = "shop";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "51362912a";

    public static Connection connection() {
        Connection connection = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/" + DB_NAME;
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
