package reconsquista;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import reconquista.*;

import java.util.List;

public class puebloTest {
    private Pueblo pueblo;

    @BeforeEach
    void setUp() {
        pueblo = new Pueblo("Wrives", 100, 1, "aliado");
    }

    @Test
    void testConstructor() {
        assertEquals(1, pueblo.getNroPueblo());
        assertEquals("Wrives", pueblo.getRaza());
        assertEquals(100, pueblo.getHabitantes());
        assertEquals("aliado", pueblo.getRelacion());
        assertTrue(pueblo.getDistancias().isEmpty(), "El pueblo debería comenzar sin distancias.");
    }

    @Test
    void testAgregarDistancias() {
        pueblo.agregarDistancias(2, 10);
        pueblo.agregarDistancias(3, 20);

        List<int[]> distancias = pueblo.getDistancias();
        assertEquals(2, distancias.size(), "Debería haber dos distancias añadidas.");

        assertEquals(2, distancias.get(0)[0]);
        assertEquals(10, distancias.get(0)[1]);
        assertEquals(3, distancias.get(1)[0]);
        assertEquals(20, distancias.get(1)[1]);
    }

    @Test
    void testGetDestino() {
        pueblo.agregarDistancias(2, 15);
        assertEquals(2, pueblo.getDestino(0), "El destino en la primera posición debería ser 2.");
    }

    @Test
    void testGetDistancia() {
        pueblo.agregarDistancias(2, 15);
        assertEquals(15, pueblo.getDistancia(0), "La distancia en la primera posición debería ser 15.");
    }

    @Test
    void testPrintDistancias() {
        pueblo.agregarDistancias(2, 10);
        pueblo.agregarDistancias(3, 20);

        pueblo.printDistancias();
    }
}
