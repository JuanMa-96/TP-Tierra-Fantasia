package reconquista;

import java.util.ArrayList;
import java.util.List;

public class Ejercito {
	private int unidades;
	private List <Raza> ejercito;

	public Ejercito(int cantidad, String raza) {
		this.unidades = cantidad;
		this.ejercito = new ArrayList <> ();

		for(int i = 0 ; i < cantidad ; i++) {
			switch(raza) {
			case "Nortaichian":
				ejercito.add(new Nortaichian());
				break;
			case "Radaiteran":
				ejercito.add(new Radaiteran());
				break;
			case "Reralopes":
				ejercito.add(new Reralopes());
				break;
			case "Wrives":
				ejercito.add(new Wrives());
				break;
			}
		}
	}

	public void incorporarEjercito(int cantidad, String raza) {
		this.unidades += cantidad;
		for(int i = 0 ; i < cantidad ; i++) {
			switch(raza) {
			case "Nortaichian":
				ejercito.add(0, new Nortaichian());
				break;
			case "Radaiteran":
				ejercito.add(0, new Radaiteran());
				break;
			case "Reralopes":
				ejercito.add(0, new Reralopes());
				break;
			case "Wrives":
				ejercito.add(0, new Wrives());
				break;
			}
		}
	}

	public void incorporarEjercito(Raza guerrero) {
		this.unidades += 1;
		ejercito.add(0, guerrero);
	}

	public void ordenarPostCombate(Raza tropa) {
		ejercito.add(tropa);
	}

	public void descansarEjercito() {
		for (Raza tropa: ejercito) {
			tropa.descansar();
		}
	}

	public int informarUnidades() {
		return this.unidades;
	}

	public Raza obtenerCombatiente() {
		return ejercito.remove(0);
	}

	public void reportarBaja() {
		this.unidades--;
	}

}
