package reconquista;

public abstract class Raza {
	protected int salud;
	protected int saludMaxima;
	protected int daño;

	public abstract void atacar(Raza objetivo);

	public abstract void recibirAtaque(int daño);

	public abstract void descansar();
	
	public int informarSalud() {
		return this.salud;
	}
	public boolean estaVivo() {
		return salud > 0;
	}

}
