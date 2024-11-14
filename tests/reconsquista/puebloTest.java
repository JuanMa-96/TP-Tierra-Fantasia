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
        // Configuración inicial de un pueblo de prueba
        pueblo = new Pueblo("Wrives", 100, 1, "aliado");
    }

    @Test
    void testConstructor() {
        // Verificar que los valores iniciales son correctos
        assertEquals(1, pueblo.getNroPueblo());
        assertEquals("Wrives", pueblo.getRaza());
        assertEquals(100, pueblo.getHabitantes());
        assertEquals("aliado", pueblo.getRelacion());
        assertTrue(pueblo.getDistancias().isEmpty(), "El pueblo debería comenzar sin distancias.");
    }

    @Test
    void testAgregarDistancias() {
        // Agregar distancias y verificar que se han añadido correctamente
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
        // Añadir una distancia y probar getDestino
        pueblo.agregarDistancias(2, 15);
        assertEquals(2, pueblo.getDestino(0), "El destino en la primera posición debería ser 2.");
    }

    @Test
    void testGetDistancia() {
        // Añadir una distancia y probar getDistancia
        pueblo.agregarDistancias(2, 15);
        assertEquals(15, pueblo.getDistancia(0), "La distancia en la primera posición debería ser 15.");
    }

    @Test
    void testPrintDistancias() {
        // Redirigir la salida para verificar el resultado de printDistancias
        pueblo.agregarDistancias(2, 10);
        pueblo.agregarDistancias(3, 20);

        // Aquí puedes simular la salida si es necesario, o simplemente validar manualmente
        // System.out redirigiría en un entorno más avanzado para validar
        pueblo.printDistancias(); // Esto imprimirá en la consola
    }
}
