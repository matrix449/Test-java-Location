package prueba;

import dao.LocationDAO;
import modelo.Location;
import org.junit.jupiter.api.*;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Suite de Auditoría QA sobre el código original del autor.
 * Demuestra la ausencia de validaciones de negocio (DEF-LOC-05) y manejo silencioso (DEF-LOC-06).
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuditoriaLocationDAOTest {

    private LocationDAO daoOriginal;

    @BeforeEach
    void setUp() {
        // Instanciamos la clase concreta original que entregó el autor
        daoOriginal = new LocationDAO();
    }

    @Test
    @Order(1)
    @DisplayName("CP-LOC-01 [CREATE]: Validar bloqueo de Latitud fuera de rango (Evidencia DEF-LOC-05)")
    void insertar_latitudInvalida_deberiaSerRechazado() {
        Location loc = new Location();
        loc.setIdZone(1);
        loc.setIdAddress(101);
        loc.setLatitude(500.0); // VALOR INVÁLIDO (> 90)
        loc.setLongitude(-68.4832);
        loc.setDateCreate(LocalDateTime.now());

        // Esperamos que el sistema rechace la inserción retornando false o lanzando excepción.
        boolean resultado = daoOriginal.insertar(loc);
        
        // Esta aserción FALLARÁ en JUnit (Rojo) porque su código devuelve true e inserta los 500 grados.
        assertFalse(resultado, "DEFECTO CRÍTICO: El DAO permitió insertar una latitud de 500.0 grados sin validarlo.");
    }

    @Test
    @Order(2)
    @DisplayName("CP-LOC-02 [CREATE]: Validar bloqueo de Longitud fuera de rango (Evidencia DEF-LOC-05)")
    void insertar_longitudInvalida_deberiaSerRechazado() {
        Location loc = new Location();
        loc.setIdZone(1);
        loc.setIdAddress(101);
        loc.setLatitude(-33.1415);
        loc.setLongitude(-999.0); // VALOR INVÁLIDO (< -180)
        loc.setDateCreate(LocalDateTime.now());

        boolean resultado = daoOriginal.insertar(loc);
        
        // Esta aserción FALLARÁ (Rojo) evidenciando la falta de capa de servicio.
        assertFalse(resultado, "DEFECTO CRÍTICO: El DAO permitió insertar una longitud de -999.0 grados.");
    }

    @Test
    @Order(3)
    @DisplayName("CP-LOC-03 [READ]: Búsqueda con ID negativo lanza excepción (Evidencia DEF-LOC-06)")
    void buscarPorId_idNegativo_deberiaLanzarExcepcion() {
        // En un sistema robusto, buscar un ID negativo debería lanzar una excepción de negocio.
        // Su código captura la anomalía y solo devuelve 'null' ocultando el error.
        Location loc = daoOriginal.buscarPorId(-10);
        
        assertNotNull(loc, "DEFECTO: El sistema falla silenciosamente devolviendo null en lugar de notificar el error de ID inválido.");
    }
}