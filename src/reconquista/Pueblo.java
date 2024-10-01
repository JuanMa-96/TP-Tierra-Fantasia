package reconquista;

import java.util.ArrayList;
import java.util.List;

public class Pueblo {
	private String raza;
	private int habitantes;
	private int nroPueblo;
	private String relacion;
	public List <int[]> distancias;
	
	
	public Pueblo(String nombreRaza, int cantidad, int id, String relacion) {
		this.habitantes = cantidad;
		this.raza = nombreRaza;
		this.nroPueblo = id;
		this.relacion = relacion;
		this.distancias = new ArrayList<>();
	}
	
	public int getNroPueblo() {
		return this.nroPueblo;
	}
	
	public String getRaza() {
		return this.raza;
	}
	
	public void printDistancias() {
		for (int [] par: distancias) {
			System.out.println("Destino: " + par[0] + ", " + "distancia: " + par[1] + " km.");
		}
	}
	
	public String getRelacion() {
		return this.relacion;
	}
	
	public int getHabitantes() {
		return this.habitantes;
	}
	
	public void agregarDistancias(int destino, int km) {
		this.distancias.add(new int [] {destino, km});
	}

	public List<int[]> getDistancias() {
		return distancias;
	}

	public int getDestino(int pos) {
		return this.getDistancias().get(pos)[0];
	}

	public int getDistancia(int pos) {
		return this.getDistancias().get(pos)[1];
	}
}
