package prueba;

import dao.LocationDAO;
import modelo.Location;
import org.junit.jupiter.api.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Suite de Pruebas de Integración Adicionales sobre el LocationDAO original.
 * Evalúa el comportamiento de los métodos implementados por el autor sin alterar su código fuente.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LocationServicioTest {

    private LocationDAO dao;

    @BeforeEach
    void setUp() {
        dao = new LocationDAO();
    }

    @Test
    @Order(1)
    @DisplayName("CP-LOC-04 [READ]: El método listar() retorna una estructura no nula")
    void listar_devuelveListaNoNula() {
        ArrayList<Location> lista = dao.listar();
        // Valida que el método maneje la colección correctamente sin retornar null en caso de error o tabla vacía
        assertNotNull(lista, "El método listar() no debe retornar null.");
    }

    @Test
    @Order(2)
    @DisplayName("CP-LOC-05 [READ]: Buscar por una zona inexistente retorna una lista vacía")
    void buscarPorZona_zonaInexistente_retornaListaVacia() {
        // Zona con ID arbitrario muy alto que no debería existir
        ArrayList<Location> lista = dao.buscarPorZona(99999);
        
        assertNotNull(lista, "La lista retornada no debe ser nula.");
        assertTrue(lista.isEmpty(), "Debería retornar una lista vacía si la zona no tiene ubicaciones.");
    }

    @Test
    @Order(3)
    @DisplayName("CP-LOC-06 [UPDATE]: Intentar modificar un ID inexistente retorna false")
    void modificar_idInexistente_retornaFalse() {
        Location loc = new Location();
        loc.setIdLocation(-9999); // ID falso que no existe en la BD
        loc.setIdZone(1);
        loc.setIdAddress(1);
        loc.setLatitude(-33.0);
        loc.setLongitude(-68.0);
        loc.setIdUserUpdate(1);
        loc.setDateUpdate(LocalDateTime.now());

        boolean resultado = dao.modificar(loc);
        
        // Como no afecta ninguna fila, el executeUpdate() debe retornar 0 y el DAO devolver false
        assertFalse(resultado, "Modificar un registro inexistente debe retornar false.");
    }

    @Test
    @Order(4)
    @DisplayName("CP-LOC-07[DELETE]: Intentar eliminar un ID inexistente retorna false")
    void eliminar_idInexistente_retornaFalse() {
        boolean resultado = dao.eliminar(-9999); // ID falso
        
        assertFalse(resultado, "Eliminar un registro que no existe debe retornar false.");
    }
}