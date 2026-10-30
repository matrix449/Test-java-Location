//package prueba;

//import dao.locationDAO;
//import modelo.Location;
//import java.time.LocalDateTime;

//public class pruebaLocation {

    //public static void main(String[] args) {

        //locationDAO dao = new locationDAO();

        
        // LISTAR
        

        //System.out.println(" LISTAR ");

        //for (Location loc : dao.listar()) {
            //System.out.println(loc);
        //}

        
        // BUSCAR POR ID
        

        //System.out.println("\n===== BUSCAR ID 1 =====");

        //Location buscado = dao.buscarPorId(1);

        //if (buscado != null) {
            //System.out.println(buscado);
        //} else {
            //System.out.println("No existe.");
        //}

        // ========================
        // INSERTAR
        // ========================

        /*
        Location nuevo = new Location();

        nuevo.setIdZone(1);
        nuevo.setIdAddress(1);
        nuevo.setLatitude(-32.88);
        nuevo.setLongitude(-68.84);
        nuevo.setIdUserCreate(1);
        nuevo.setIdUserUpdate(1);
        nuevo.setDateCreate(LocalDateTime.now());
        nuevo.setDateUpdate(LocalDateTime.now());

        if (dao.insertar(nuevo)) {
            System.out.println("Insertado correctamente");
        } else {
            System.out.println("Error al insertar");
        }
        */

        // ========================
        // MODIFICAR
        // ========================

        /*
        Location modificar = dao.buscarPorId(1);

        if (modificar != null) {

            modificar.setLatitude(-33.0000);
            modificar.setLongitude(-69.0000);
            modificar.setIdUserUpdate(1);
            modificar.setDateUpdate(LocalDateTime.now());

            if (dao.modificar(modificar)) {
                System.out.println("Registro modificado");
            } else {
                System.out.println("No se pudo modificar");
            }

        }
        */

        // ========================
        // ELIMINAR
        // ========================

        /*
        if (dao.eliminar(1)) {
            System.out.println("Registro eliminado");
        } else {
            System.out.println("No se pudo eliminar");
        }
        */

    //}
//}