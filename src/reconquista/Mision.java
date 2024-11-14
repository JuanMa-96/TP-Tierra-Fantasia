package reconquista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Mision {
	private List<Pueblo> pueblos;
	private int puebloInicial;
	private int puebloFinal;
	private int cantPueblos;

	public Mision(List<Pueblo> pueblos, int puebloInicial, int puebloFinal, int cantPueblos ) {
		this.pueblos = pueblos;
		this.puebloInicial = puebloInicial;
		this.puebloFinal = puebloFinal;
		this.cantPueblos = cantPueblos;
	}
	
	public Mision(List<Pueblo> pueblos, int puebloInicial, int puebloFinal) {
		this.pueblos = pueblos;
		this.puebloInicial = puebloInicial;
		this.puebloFinal = puebloFinal;
	}
	
	public void mostrarMision() {
		System.out.println("Se ejecuta la mision:" + this.puebloInicial + "->" + this.puebloFinal);
		System.out.println("Los pueblos registrados son: ");
		for(Pueblo pueblo : this.pueblos) {
			if (pueblo != null) {
				System.out.println(pueblo.getNroPueblo() + " " + pueblo.getRaza()+ " " +pueblo.getHabitantes() + " " + pueblo.getRelacion());
				pueblo.printDistancias();
			}
		}
	}
	
	public Pueblo getPueblo(int id) {
		return pueblos.get(id);
	}

	public List<Pueblo> getPueblos() {
		return pueblos;
	}

	public int getPuebloInicial() {
		return puebloInicial;
	}

	public int getPuebloFinal() {
		return puebloFinal;
	}

	public int getCantPueblos() {
		return cantPueblos;
	}

	public void realizarMision() {
		Dijkstra dij = new Dijkstra(getPueblos());
		List<Integer> camino = dij.obtenerCaminoMasCorto(getPuebloInicial(), getPuebloFinal());

		if (camino.isEmpty()) {
			System.out.println("No existe un camino entre " + getPuebloInicial() + " y " + getPuebloFinal());
			return;
		}

		System.out.println("\nCamino mas corto");
		System.out.println("----------------");


		int puebloInicial = getPuebloInicial();
		for (int numPueblo : camino) {
			System.out.println(puebloInicial + " -> " + numPueblo);

			Pueblo puebloPropio = getPueblo(puebloInicial);
			Ejercito propio = new Ejercito(puebloPropio.getHabitantes(), puebloPropio.getRaza());

			Pueblo puebloActual = getPueblo(numPueblo);

			switch (puebloActual.getRelacion()) {
				case "aliado":
					System.out.println("\nEstas en un pueblo aliado, tu ejercito descansa y se incorporan "
							+ (puebloActual.getHabitantes() / 2) + " de raza " + puebloActual.getRaza());
					propio.descansarEjercito();
					propio.incorporarEjercito(puebloActual.getHabitantes() / 2, puebloActual.getRaza());
					break;
				case "enemigo":
					System.out.println("\nEstas en un pueblo enemigo, tu ejercito entra en combate con las tropas "
							+ puebloActual.getRaza() + " con un ejercito de " + puebloActual.getHabitantes());
					Ejercito enemigo = new Ejercito(puebloActual.getHabitantes(), puebloActual.getRaza());
					Combate combate = new Combate(propio, enemigo);
					combate.combate();
					break;
			}

			// Actualizar el punto de partida al siguiente nodo en el camino
			puebloInicial = numPueblo;
		}
	}
}
