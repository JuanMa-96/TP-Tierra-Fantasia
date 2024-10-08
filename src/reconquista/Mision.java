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

	public Mision() {
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

	public void cargarDeArchivo(String rutaArchivo) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
		cantPueblos = Integer.parseInt(reader.readLine().trim());

		this.pueblos = new ArrayList<>();
		this.pueblos.add(null); // El primero es nulo para tomar de indice el nro del pueblo
		for (int i = 0; i < cantPueblos; i++) {
			String[] datosPueblo = reader.readLine().split(" ");
			int id = Integer.parseInt(datosPueblo[0]);
			int habitantes = Integer.parseInt(datosPueblo[1]);
			String raza = datosPueblo[2];
			String relacion = datosPueblo[3];
			pueblos.add(new Pueblo(raza, habitantes, id, relacion));
		}
		
		// Leer pueblo inicial y final
		String[] inicialFinal = reader.readLine().split(" -> ");
		this.puebloInicial = Integer.parseInt(inicialFinal[0]);
		this.puebloFinal = Integer.parseInt(inicialFinal[1]);
		
		/* Meter en grafo */

		String linea;
		while ((linea = reader.readLine()) != null) {
			String[] datosDistancia = linea.split(" ");
			int origen = Integer.parseInt(datosDistancia[0]);
			int destino = Integer.parseInt(datosDistancia[1]);
			int kilometros = Integer.parseInt(datosDistancia[2]);
			
			for(int i = 1; i < cantPueblos ; i++) {
				if(pueblos.get(i).getNroPueblo() == origen) {
					pueblos.get(i).agregarDistancias(destino, kilometros);
				}
			}
		}
		return;
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
}
