package reconquista;

public class Radaiteran extends Raza {
	private int cantidadAtaques;

	public Radaiteran() {
		this.salud = 36;
		this.saludMaxima = 36;
		this.daño = 56;
		this.cantidadAtaques = 0;
	}
	
	public void reiniciarDaño() {
		this.cantidadAtaques = 0;
	}
	
	@Override
	public void atacar(Raza objetivo) {
		cantidadAtaques++;
		System.out.println("Radaiteran ataca con su Shuriken con un aumento en su daño.");
		objetivo.recibirAtaque(this.daño +  (this.cantidadAtaques * 3));
	}

	@Override
	public void recibirAtaque(int daño) {
		this.salud -= daño;
	}

	@Override
	public void descansar() {
		System.out.println("Radaiteran descansa, pero no le sucede nada.");
	}
	
}
