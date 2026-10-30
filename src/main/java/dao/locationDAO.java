package dao;

import conexion.conexionBD;
import modelo.Location;
import java.sql.*;
import java.util.ArrayList;

public class locationDAO {

    // LISTAR
    public ArrayList<Location> listar() {

        ArrayList<Location> lista = new ArrayList<>();

        String sql = "SELECT * FROM location";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Location loc = new Location();

                loc.setIdLocation(rs.getInt("id_location"));
                loc.setIdZone(rs.getInt("id_zone"));
                loc.setIdAddress(rs.getInt("id_address"));
                loc.setLatitude(rs.getDouble("latitude"));
                loc.setLongitude(rs.getDouble("longitude"));
                loc.setIdUserCreate(rs.getInt("id_user_create"));
                loc.setIdUserUpdate(rs.getInt("id_user_update"));

                Timestamp tc = rs.getTimestamp("date_create");
                if (tc != null) {
                    loc.setDateCreate(tc.toLocalDateTime());
                }

                Timestamp tu = rs.getTimestamp("date_update");
                if (tu != null) {
                    loc.setDateUpdate(tu.toLocalDateTime());
                }

                lista.add(loc);
            }

        } catch (SQLException e) {
            System.out.println("Error listar: " + e.getMessage());
        }

        return lista;
    }

    // BUSCAR POR ID
    public Location buscarPorId(int id) {

        String sql = "SELECT * FROM location WHERE id_location = ?";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Location loc = new Location();

                loc.setIdLocation(rs.getInt("id_location"));
                loc.setIdZone(rs.getInt("id_zone"));
                loc.setIdAddress(rs.getInt("id_address"));
                loc.setLatitude(rs.getDouble("latitude"));
                loc.setLongitude(rs.getDouble("longitude"));
                loc.setIdUserCreate(rs.getInt("id_user_create"));
                loc.setIdUserUpdate(rs.getInt("id_user_update"));

                Timestamp tc = rs.getTimestamp("date_create");
                if (tc != null) {
                    loc.setDateCreate(tc.toLocalDateTime());
                }

                Timestamp tu = rs.getTimestamp("date_update");
                if (tu != null) {
                    loc.setDateUpdate(tu.toLocalDateTime());
                }

                return loc;
            }

        } catch (SQLException e) {
            System.out.println("Error buscarPorId: " + e.getMessage());
        }

        return null;
    }

    // INSERTAR
    public boolean insertar(Location loc) {

        String sql = "INSERT INTO location "
                + "(id_zone, id_address, latitude, longitude, id_user_create, id_user_update, date_create, date_update) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, loc.getIdZone());
            ps.setObject(2, loc.getIdAddress());
            ps.setDouble(3, loc.getLatitude());
            ps.setDouble(4, loc.getLongitude());
            ps.setObject(5, loc.getIdUserCreate());
            ps.setObject(6, loc.getIdUserUpdate());
            ps.setObject(7, loc.getDateCreate());
            ps.setObject(8, loc.getDateUpdate());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insertar: " + e.getMessage());
            return false;
        }
    }

    // MODIFICAR
    public boolean modificar(Location loc) {

        String sql = "UPDATE location SET "
                + "id_zone = ?, "
                + "id_address = ?, "
                + "latitude = ?, "
                + "longitude = ?, "
                + "id_user_update = ?, "
                + "date_update = ? "
                + "WHERE id_location = ?";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, loc.getIdZone());
            ps.setObject(2, loc.getIdAddress());
            ps.setDouble(3, loc.getLatitude());
            ps.setDouble(4, loc.getLongitude());
            ps.setObject(5, loc.getIdUserUpdate());
            ps.setObject(6, loc.getDateUpdate());
            ps.setInt(7, loc.getIdLocation());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error modificar: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int id) {

        String sql = "DELETE FROM location WHERE id_location = ?";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar: " + e.getMessage());
            return false;
        }
    }
    public ArrayList<Location> buscarPorZona(int idZona) {

    ArrayList<Location> lista = new ArrayList<>();

    String sql = "SELECT * FROM location WHERE id_zone = ?";

    try (Connection con = conexionBD.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idZona);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Location loc = new Location();

            loc.setIdLocation(rs.getInt("id_location"));
            loc.setIdZone(rs.getInt("id_zone"));
            loc.setIdAddress(rs.getInt("id_address"));
            loc.setLatitude(rs.getDouble("latitude"));
            loc.setLongitude(rs.getDouble("longitude"));
            loc.setIdUserCreate(rs.getInt("id_user_create"));
            loc.setIdUserUpdate(rs.getInt("id_user_update"));

            Timestamp tc = rs.getTimestamp("date_create");
            if (tc != null) {
                loc.setDateCreate(tc.toLocalDateTime());
            }

            Timestamp tu = rs.getTimestamp("date_update");
            if (tu != null) {
                loc.setDateUpdate(tu.toLocalDateTime());
            }

            lista.add(loc);
        }

    } catch (SQLException e) {
        System.out.println("Error buscarPorZona: " + e.getMessage());
    }

    return lista;
}

}