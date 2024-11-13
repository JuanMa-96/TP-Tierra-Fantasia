package reconsquista;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reconquista.*;

import static org.junit.jupiter.api.Assertions.*;

public class CombateTest {

    private Ejercito ejercitoPropio;
    private Ejercito ejercitoEnemigo;

    @BeforeEach
    public void setUp() {
        // Inicializar los ejércitos antes de cada prueba
        ejercitoPropio = new Ejercito(0, "Raza");
        ejercitoEnemigo = new Ejercito(0, "Raza");
    }

    @Test
    public void testCombateEntreWrivesYReralopes() {
        // Configurar unidades de cada raza
        ejercitoPropio.incorporarEjercito(1, "Wrives");
        ejercitoEnemigo.incorporarEjercito(1,  "Reralopes");

        Combate combate = new Combate(ejercitoPropio, ejercitoEnemigo);
        combate.combate();

        // Verificar que el combate ha terminado con un ganador
        assertTrue(ejercitoPropio.informarUnidades() == 0 || ejercitoEnemigo.informarUnidades() == 0,
                "El combate debe finalizar cuando un ejército se queda sin unidades");

        // Comprobaciones adicionales de acuerdo a la raza específica
        if (ejercitoPropio.informarUnidades() > 0) {
            System.out.println("El ejército propio (Wrives) ganó");
        } else if (ejercitoEnemigo.informarUnidades() > 0) {
            System.out.println("El ejército enemigo (Reralopes) ganó");
        }
    }

    @Test
    public void testCombateConMultiplesUnidades() {
        // Configurar un ejército con múltiples unidades de distintas razas
        ejercitoPropio.incorporarEjercito(1, "Wrives");
        ejercitoPropio.incorporarEjercito(1, "Radaiteran");
        ejercitoPropio.incorporarEjercito(1, "Nortaichian");

        ejercitoEnemigo.incorporarEjercito(1, "Reralopes");
        ejercitoEnemigo.incorporarEjercito(1, "Wrives");
        ejercitoEnemigo.incorporarEjercito(1, "Nortaichian");

        Combate combate = new Combate(ejercitoPropio, ejercitoEnemigo);
        combate.combate();

        // Verificar que el combate ha terminado con un ejército vencedor
        assertTrue(ejercitoPropio.informarUnidades() == 0 || ejercitoEnemigo.informarUnidades() == 0,
                "El combate debe finalizar cuando un ejército se queda sin unidades");

        // Comprobación de las bajas reportadas y del ganador
        if (ejercitoPropio.informarUnidades() > 0) {
            System.out.println("El ejército propio ganó");
        } else if (ejercitoEnemigo.informarUnidades() > 0) {
            System.out.println("El ejército enemigo ganó");
        }
    }

    @Test
    public void testCombateEntreGuerrerosDeAltaSaludContraGuerrerosDeBajaSalud() {
        // Configurar un ejército con unidades de alta salud
        Raza guerreroAltaSalud = new Wrives(); // Ejemplo de un guerrero de alta salud
        guerreroAltaSalud.setSalud(200); // Aumenta la salud para este test
        ejercitoPropio.incorporarEjercito(guerreroAltaSalud);

        // Configurar un ejército enemigo con guerreros de baja salud
        Raza guerreroBajaSalud = new Radaiteran(); // Ejemplo de un guerrero de baja salud
        guerreroBajaSalud.setSalud(20); // Baja salud para este test
        ejercitoEnemigo.incorporarEjercito(guerreroBajaSalud);

        Combate combate = new Combate(ejercitoPropio, ejercitoEnemigo);
        combate.combate();

        // Verificar que el combate termina rápidamente y con una clara victoria
        assertTrue(ejercitoPropio.informarUnidades() > 0 && ejercitoEnemigo.informarUnidades() == 0,
                "El ejército con alta salud debería ganar rápidamente");
    }

    @Test
    public void testCombateSinUnidades() {
        // Prueba de un caso donde uno de los ejércitos está vacío
        Combate combate = new Combate(ejercitoPropio, ejercitoEnemigo); // Ambos ejércitos vacíos
        combate.combate();

        // Verificar que el combate termina sin unidades
        assertEquals(0, ejercitoPropio.informarUnidades(), "No debería haber unidades en el ejército propio");
        assertEquals(0, ejercitoEnemigo.informarUnidades(), "No debería haber unidades en el ejército enemigo");
    }
}
