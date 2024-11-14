package reconsquista;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import reconquista.*;

import java.util.List;
import java.util.Map;

public class mapaTest {
    private Mapa mapa;

    @BeforeEach
    void setUp() {
        mapa = Mapa.getInstance();
        mapa.getPueblos().clear();
        mapa.getAdyacencias().clear();
    }

    @Test
    void testSingletonInstance() {
        Mapa otraInstancia = Mapa.getInstance();
        assertSame(mapa, otraInstancia, "Debería ser la misma instancia de Mapa (singleton)");
    }

    @Test
    void testAgregarPueblo() {
        Pueblo pueblo = new Pueblo("Wrives", 100, 1, "propio");
        mapa.agregarPueblo(pueblo);

        assertEquals(1, mapa.getPueblos().size(), "Debería haber un pueblo en el mapa.");
        assertEquals(pueblo, mapa.obtenerPueblo(1), "El pueblo agregado debería ser accesible por su ID.");
    }

    @Test
    void testAgregarDistancia() {
        Pueblo pueblo1 = new Pueblo("Wrives", 100, 1, "propio");
        Pueblo pueblo2 = new Pueblo("Nortaichian", 50, 2, "aliado");

        mapa.agregarPueblo(pueblo1);
        mapa.agregarPueblo(pueblo2);

        mapa.agregarDistancia(1, 2, 15);

        List<Conexion> conexiones = mapa.obtenerConexiones(1);
        assertNotNull(conexiones, "Debería haber una lista de conexiones para el pueblo 1.");
        assertEquals(1, conexiones.size(), "Debería haber una conexión agregada para el pueblo 1.");
        assertEquals(2, conexiones.get(0).getDestino(), "El destino de la conexión debería ser el pueblo 2.");
        assertEquals(15, conexiones.get(0).getDistancia(), "La distancia de la conexión debería ser 15 km.");
    }

    @Test
    void testObtenerPueblo() {
        Pueblo pueblo = new Pueblo("Wrives", 100, 1, "propio");
        mapa.agregarPueblo(pueblo);

        Pueblo obtenido = mapa.obtenerPueblo(1);
        assertNotNull(obtenido, "Debería poder obtenerse el pueblo por su ID.");
        assertEquals(pueblo, obtenido, "El pueblo obtenido debería coincidir con el pueblo agregado.");
    }

    @Test
    void testObtenerConexiones() {
        Pueblo pueblo1 = new Pueblo("Wrives", 100, 1, "propio");
        Pueblo pueblo2 = new Pueblo("Nortaichian", 50, 2, "aliado");
        Pueblo pueblo3 = new Pueblo("Radaiteran", 75, 3, "enemigo");

        mapa.agregarPueblo(pueblo1);
        mapa.agregarPueblo(pueblo2);
        mapa.agregarPueblo(pueblo3);

        mapa.agregarDistancia(1, 2, 10);
        mapa.agregarDistancia(1, 3, 20);

        List<Conexion> conexiones = mapa.obtenerConexiones(1);
        assertNotNull(conexiones, "Debería haber una lista de conexiones para el pueblo 1.");
        assertEquals(2, conexiones.size(), "Debería haber dos conexiones para el pueblo 1.");

        assertEquals(2, conexiones.get(0).getDestino(), "El destino de la primera conexión debería ser el pueblo 2.");
        assertEquals(10, conexiones.get(0).getDistancia(), "La distancia de la primera conexión debería ser 10 km.");
        assertEquals(3, conexiones.get(1).getDestino(), "El destino de la segunda conexión debería ser el pueblo 3.");
        assertEquals(20, conexiones.get(1).getDistancia(), "La distancia de la segunda conexión debería ser 20 km.");
    }

    @Test
    void testGetPueblos() {
        Pueblo pueblo1 = new Pueblo("Wrives", 100, 1, "propio");
        Pueblo pueblo2 = new Pueblo("Nortaichian", 50, 2, "aliado");

        mapa.agregarPueblo(pueblo1);
        mapa.agregarPueblo(pueblo2);

        Map<Integer, Pueblo> pueblos = mapa.getPueblos();
        assertEquals(2, pueblos.size(), "Debería haber dos pueblos en el mapa.");
        assertTrue(pueblos.containsKey(1), "Debería contener el pueblo con ID 1.");
        assertTrue(pueblos.containsKey(2), "Debería contener el pueblo con ID 2.");
    }

    @Test
    void testGetAdyacencias() {
        Pueblo pueblo1 = new Pueblo("Wrives", 100, 1, "propio");
        Pueblo pueblo2 = new Pueblo("Nortaichian", 50, 2, "aliado");

        mapa.agregarPueblo(pueblo1);
        mapa.agregarPueblo(pueblo2);

        mapa.agregarDistancia(1, 2, 10);

        Map<Integer, List<Conexion>> adyacencias = mapa.getAdyacencias();
        assertTrue(adyacencias.containsKey(1), "Debería contener conexiones para el pueblo con ID 1.");
        assertEquals(1, adyacencias.get(1).size(), "Debería haber una conexión para el pueblo con ID 1.");
    }
}
