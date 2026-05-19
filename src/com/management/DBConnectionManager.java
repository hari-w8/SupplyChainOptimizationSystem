
<<<<<<< HEAD
    public static DBConnectionManager getInstance() {
        if (instance == null) {
            synchronized (DBConnectionManager.class) {
                if (instance == null) {
                    instance = new DBConnectionManager();
                }
            }
        }
        return instance;
    }

    /**
     * Provides the active database connection. 
     * Re-establishes connection if it was closed or dropped.
     */
    public java.sql.Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                System.out.println("LOG: Connection was closed. Re-opening...");
                this.connection = java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (java.sql.SQLException e) {
            System.err.println("ERROR: Could not reconnect to database.");
            e.printStackTrace();
        }
        return this.connection;
    }

    /**
     * Gracefully closes the database connection when the application shuts down.
     */
    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
                System.out.println("LOG: Database connection closed cleanly.");
            } catch (java.sql.SQLException e) {
                System.err.println("ERROR: Failed to close database connection safely.");
                e.printStackTrace();
            }
        }
    }
}
=======
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Properties properties = new Properties();

            FileInputStream file = new FileInputStream("src/database.properties");
            properties.load(file);

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String driver = properties.getProperty("db.driver");

            Class.forName(driver);

            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Database Connected Successfully!");

        } catch (IOException e) {
            System.out.println("database.properties file not found!");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }

        return connection;
    }

}


>>>>>>> branch 'master' of https://github.com/hari-w8/SupplyChainOptimizationSystem.git
