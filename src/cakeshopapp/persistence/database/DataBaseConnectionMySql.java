package cakeshopapp.persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionMySql {

    private static DataBaseConnectionMySql instance;
    private Connection connection;

    private static final String url = "jdbc:mysql://localhost:3306/dulces_delicias";
    private static final String username = "root";
    private static final String password = "";

    // Constructor privado
    private DataBaseConnectionMySql(){
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar la base de datos", e);
        }
    }

    public static synchronized DataBaseConnectionMySql getInstance(){
        if(instance == null){
            instance = new DataBaseConnectionMySql();
        }
        return instance;
    }

    // Método con validación de estado
    public Connection getConnection() {
        try {
            // Si la conexión es nula o se cerró por timeout, la volvemos a abrir automáticamente
            if (connection == null || connection.isClosed()) {
                System.out.println("Restableciendo conexión perdida con MySQL...");
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error crítico al validar o reconectar la base de datos", e);
        }
        return connection;
    }
}