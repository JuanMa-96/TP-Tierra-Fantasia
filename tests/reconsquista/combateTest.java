package reconsquista;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reconquista.*;

import static org.junit.jupiter.api.Assertions.*;

public class combateTest {

    private Ejercito ejercitoPropio;
    private Ejercito ejercitoEnemigo;

    @BeforeEach
    public void setUp() {
        ejercitoPropio = new Ejercito(0, "Raza");
        ejercitoEnemigo = new Ejercito(0, "Raza");
    }

    @Test
    public void testCombateEntreWrivesYReralopes() {
        ejercitoPropio.incorporarEjercito(1, "Wrives");
        ejercitoEnemigo.incorporarEjercito(1,  "Reralopes");

        Combate combate = new Combate(ejercitoPropio, ejercitoEnemigo);
        combate.combate();

        assertTrue(ejercitoPropio.informarUnidades() == 0 || ejercitoEnemigo.informarUnidades() == 0,
                "El combate debe finalizar cuando un ejército se queda sin unidades");

        if (ejercitoPropio.informarUnidades() > 0) {
            System.out.println("El ejército propio (Wrives) ganó");
        } else if (ejercitoEnemigo.informarUnidades() > 0) {
            System.out.println("El ejército enemigo (Reralopes) ganó");
        }
    }

    @Test
    public void testCombateConMultiplesUnidades() {
        ejercitoPropio.incorporarEjercito(1, "Wrives");
        ejercitoPropio.incorporarEjercito(1, "Radaiteran");
        ejercitoPropio.incorporarEjercito(1, "Nortaichian");

        ejercitoEnemigo.incorporarEjercito(1, "Reralopes");
        ejercitoEnemigo.incorporarEjercito(1, "Wrives");
        ejercitoEnemigo.incorporarEjercito(1, "Nortaichian");

        Combate combate = new Combate(ejercitoPropio, ejercitoEnemigo);
        combate.combate();

        assertTrue(ejercitoPropio.informarUnidades() == 0 || ejercitoEnemigo.informarUnidades() == 0,
                "El combate debe finalizar cuando un ejército se queda sin unidades");

        if (ejercitoPropio.informarUnidades() > 0) {
            System.out.println("El ejército propio ganó");
        } else if (ejercitoEnemigo.informarUnidades() > 0) {
            System.out.println("El ejército enemigo ganó");
        }
    }

    @Test
    public void testCombateEntreGuerrerosDeAltaSaludContraGuerrerosDeBajaSalud() {
        Raza guerreroAltaSalud = new Wrives();
        guerreroAltaSalud.setSalud(200);
        ejercitoPropio.incorporarEjercito(guerreroAltaSalud);

        Raza guerreroBajaSalud = new Radaiteran();
        guerreroBajaSalud.setSalud(20);
        ejercitoEnemigo.incorporarEjercito(guerreroBajaSalud);

        Combate combate = new Combate(ejercitoPropio, ejercitoEnemigo);
        combate.combate();

        assertTrue(ejercitoPropio.informarUnidades() > 0 && ejercitoEnemigo.informarUnidades() == 0,
                "El ejército con alta salud debería ganar rápidamente");
    }

    @Test
    public void testCombateSinUnidades() {
        Combate combate = new Combate(ejercitoPropio, ejercitoEnemigo);
        combate.combate();

        assertEquals(0, ejercitoPropio.informarUnidades(), "No debería haber unidades en el ejército propio");
        assertEquals(0, ejercitoEnemigo.informarUnidades(), "No debería haber unidades en el ejército enemigo");
    }
}
