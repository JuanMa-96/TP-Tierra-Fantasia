package reconsquista;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import reconquista.*;


public class ejercitoTest {
    private Ejercito ejercito;

    @BeforeEach
    void setUp() {
        ejercito = new Ejercito(5, "Wrives");
    }

    @Test
    void testConstructor() {
        assertEquals(5, ejercito.informarUnidades(), "El ejército debería tener 5 unidades iniciales.");
    }

    @Test
    void testIncorporarEjercitoCantidad() {
        ejercito.incorporarEjercito(3, "Reralopes");

        assertEquals(8, ejercito.informarUnidades(), "El ejército debería tener 8 unidades después de incorporar 3.");
    }

    @Test
    void testIncorporarEjercitoIndividual() {
        Raza nuevoGuerrero = new Nortaichian();
        ejercito.incorporarEjercito(nuevoGuerrero);

        assertEquals(6, ejercito.informarUnidades(), "El ejército debería tener 6 unidades después de incorporar un guerrero individual.");
    }

    @Test
    void testDescansarEjercito() {
        ejercito.descansarEjercito();

        assertEquals(5, ejercito.informarUnidades(), "El ejército debería seguir teniendo 5 unidades después de descansar.");
    }

    @Test
    void testReportarBaja() {
        ejercito.reportarBaja();

        assertEquals(4, ejercito.informarUnidades(), "El ejército debería tener 4 unidades después de reportar una baja.");
    }
}
