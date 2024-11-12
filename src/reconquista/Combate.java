package reconquista;

public class Combate {

    public static void combate(Ejercito propio, Ejercito enemigo) {
        Raza combatientePropio = propio.obtenerCombatiente();
        Raza combatienteEnemigo = enemigo.obtenerCombatiente();

        while (propio.informarUnidades() != 0 && enemigo.informarUnidades() != 0) {

            while (combatientePropio.salud > 0 && combatienteEnemigo.salud > 0) {
                if (combatientePropio.estaVivo()) {
                    combatientePropio.atacar(combatienteEnemigo);
                }

                if (combatienteEnemigo.estaVivo()) {
                    combatienteEnemigo.atacar(combatientePropio);
                }
                System.out.println(
                        "Vida alida: " + combatientePropio.salud + "  Vida enemiga: " + combatienteEnemigo.salud);
            }

            if (!combatientePropio.estaVivo()) {
                System.out.println("Murio uno nuestro");
                propio.reportarBaja();
                if ( propio.informarUnidades() > 0) {
                    combatientePropio = propio.obtenerCombatiente();
                } else {
                    break;
                }

            }

            if (!combatienteEnemigo.estaVivo()) {
                System.out.println("La quedo un enemigo");
                enemigo.reportarBaja();
                if (enemigo.informarUnidades() > 0) {
                    combatienteEnemigo = enemigo.obtenerCombatiente();
                } else {
                    break;
                }
            }
        }

        if (combatientePropio.salud > 0) {
            propio.ordenarPostCombate(combatientePropio);
        }

        System.out.println("Tropas propias " + propio.informarUnidades());
        System.out.println("Tropas enemigas " + enemigo.informarUnidades());

        if (propio.informarUnidades() > 0) {
            System.out.println("Hemos ganado el combate");
        } else {
            if (enemigo.informarUnidades() > 0) {
                System.out.println("Nuestro ejercito perecio en combate");
            }
        }

    }
}
