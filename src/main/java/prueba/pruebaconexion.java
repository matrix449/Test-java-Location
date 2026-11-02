package prueba;

import conexion.conexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class pruebaconexion {

    public static void main(String[] args) {
        conexionBD.getConexion();
    }
}