package reconquista;

public class Combate {

    Ejercito propio;
    Ejercito enemigo;

    public Combate(Ejercito propio, Ejercito enemigo) {
        this.propio = propio;
        this.enemigo = enemigo;
    }

    public void combate() {
        if(propio.informarUnidades() == 0 || enemigo.informarUnidades() == 0) {
            return;
        }

        Raza combatientePropio = this.propio.obtenerCombatiente();
        Raza combatienteEnemigo = this.enemigo.obtenerCombatiente();

        while (this.propio.informarUnidades() != 0 && this.enemigo.informarUnidades() != 0) {

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
                this.propio.reportarBaja();
                if ( this.propio.informarUnidades() > 0) {
                    combatientePropio = this.propio.obtenerCombatiente();
                } else {
                    break;
                }

            }

            if (!combatienteEnemigo.estaVivo()) {
                System.out.println("La quedo un enemigo");
                this.enemigo.reportarBaja();
                if (this.enemigo.informarUnidades() > 0) {
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
