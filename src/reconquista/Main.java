package reconquista;

import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		Mision mision = new Mision();
		try {
			mision.cargarDeArchivo("TP-Tierra-Fantasia/resources/Mision_1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		mision.mostrarMision();

		Dijkstra dij = new Dijkstra(mision.getPueblos());
		List<Integer> camino = dij.obtenerCaminoMasCorto(mision.getPuebloInicial(), mision.getPuebloFinal());

		System.out.println();
		System.out.println("Camino mas corto");
		System.out.println("----------------");
		for (int i = 1 ; i < camino.size() ; i++) {
			System.out.println((i) + " -> " + (camino.get(i)));
		}

/*		Pueblo puebloPropio = mision.getPueblo(0);
		Ejercito propio = new Ejercito(puebloPropio.getHabitantes(), puebloPropio.getRaza());

		Pueblo puebloActual = mision.getPueblo(2);
		
		switch (puebloActual.getRelacion()) {
			case "aliado":
				System.out.println("\nEstas en un pueblo aliado, tu ejercito descansa y se incorporan " + puebloActual.getHabitantes()/2 + " de raza " + puebloActual.getRaza());
				propio.descansarEjercito();
				propio.incorporarEjercito(puebloActual.getHabitantes()/2, puebloActual.getRaza());
				break;
			case "enemigo":
				System.out.println("\nEstas en un pueblo enemigo, tu ejercito entra en combate con la tropas " + puebloActual.getRaza() + " con un ejercito de " + puebloActual.getHabitantes());
				Ejercito enemigo = new Ejercito(puebloActual.getHabitantes(), puebloActual.getRaza());
				combate(propio, enemigo);
				break;
		}*/
	}

}
