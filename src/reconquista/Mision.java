package reconquista;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Mision {
	private int puebloInicial;
	private int puebloFinal;
	private int cantPueblos;

	public Mision(int puebloInicial, int puebloFinal, int cantPueblos ) {
		this.puebloInicial = puebloInicial;
		this.puebloFinal = puebloFinal;
		this.cantPueblos = cantPueblos;
	}

	public void mostrarMision() {
		Mapa mapa = Mapa.getInstance();

		System.out.println("Se ejecuta la misión: " + this.puebloInicial + " -> " + this.puebloFinal);
		System.out.println("Los pueblos registrados son:");

		for (Pueblo pueblo : mapa.getPueblos().values()) {
			System.out.println(pueblo.getNroPueblo() + " " + pueblo.getRaza() + " " + pueblo.getHabitantes() + " " + pueblo.getRelacion());
			pueblo.printDistancias();
		}
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
		Dijkstra dijkstra = new Dijkstra();
		ResultadoDijkstra resultado = dijkstra.dijkstra(puebloInicial);

		int[] distancias = resultado.getDistancias();
		int[] predecesores = resultado.getPredecesores();

		if (distancias[puebloFinal] == Integer.MAX_VALUE) {
			System.out.println("No existe un camino entre " + puebloInicial + " y " + puebloFinal);
			return;
		}

		System.out.println("\nCamino más corto:");
		System.out.println("----------------");

		List<Integer> camino = new ArrayList<>();
		for (int at = puebloFinal; at != -1; at = predecesores[at]) {
			camino.add(at);
		}
		Collections.reverse(camino);

		int diasTotales = 0;

		Mapa mapa = Mapa.getInstance();

		Pueblo puebloPropio = mapa.obtenerPueblo(puebloInicial);
		Ejercito propio = new Ejercito(puebloPropio.getHabitantes(), puebloPropio.getRaza());

		for (int i = 0; i < camino.size() - 1; i++) {
			int actualPuebloId = camino.get(i);
			int siguientePuebloId = camino.get(i + 1);

			Pueblo puebloActual = mapa.obtenerPueblo(actualPuebloId);
			Pueblo puebloSiguiente = mapa.obtenerPueblo(siguientePuebloId);

			int distancia = obtenerDistanciaEntrePueblos(mapa, actualPuebloId, siguientePuebloId);
			int diasDeViaje = (int) Math.ceil(distancia / 10.0);
			diasTotales += diasDeViaje;

			System.out.println("\nViajando desde " + actualPuebloId + " a " + siguientePuebloId + " (Distancia: " + distancia + " km, Días de viaje: " + diasDeViaje + ")");

			switch (puebloSiguiente.getRelacion()) {
				case "aliado":
					System.out.println("\nLlegaste a un pueblo aliado, tu ejército descansa y se incorporan "
							+ (puebloSiguiente.getHabitantes() / 2) + " tropas de la raza " + puebloSiguiente.getRaza());
					propio.descansarEjercito();
					propio.incorporarEjercito(puebloSiguiente.getHabitantes() / 2, puebloSiguiente.getRaza());
					break;

				case "enemigo":
					System.out.println("\nLlegaste a un pueblo enemigo. Tu ejército entra en combate con las tropas "
							+ puebloSiguiente.getRaza() + " con un ejército de " + puebloSiguiente.getHabitantes() + " soldados.");
					Ejercito enemigo = new Ejercito(puebloSiguiente.getHabitantes(), puebloSiguiente.getRaza());
					Combate combate = new Combate(propio, enemigo);
					combate.combate();
					diasTotales++;
					break;
			}

			puebloInicial = siguientePuebloId;
		}

		System.out.println("\nMisión completada:");
		System.out.println("------------------");
		System.out.println("Días totales: " + diasTotales);
		System.out.println("Ejército restante: " + propio.informarUnidades() + " soldados.");
		System.out.println("Estado final de la misión: " + (propio.informarUnidades() > 0 ? "Éxito" : "Derrota"));
	}

	private int obtenerDistanciaEntrePueblos(Mapa mapa, int origen, int destino) {
		List<Conexion> conexiones = mapa.obtenerConexiones(origen);
		for (Conexion conexion : conexiones) {
			if (conexion.getDestino() == destino) {
				return conexion.getDistancia();
			}
		}
		return Integer.MAX_VALUE;
	}

}
