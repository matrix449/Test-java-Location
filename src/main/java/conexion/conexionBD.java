package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {

    // Corregido de jdbc:mysql a jdbc:mariadb
    private static final String URL ="jdbc:mariadb://localhost:3305/casosdeprueba";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConexion() {
        Connection con = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }

        return con;
    }
}