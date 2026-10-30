package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {

    private static final String URL ="jdbc:mariadb://ies9021.edu.ar:3306/ies9021_SCS";
    private static final String USER = "ies9021_userdb";
    private static final String PASSWORD = "Xsw23edc.2025";

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