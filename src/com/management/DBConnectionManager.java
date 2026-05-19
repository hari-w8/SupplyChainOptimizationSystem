
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