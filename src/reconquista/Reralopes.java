package reconquista;

public class Reralopes extends Raza {
	private int ataquesPotenciados;
	private boolean ataqueExitoso;

	public Reralopes() {
		this.salud = 53;
		this.saludMaxima = 53;
		this.daño = 27;
		this.ataquesPotenciados = 0;
		this.ataqueExitoso = false;
	}

	@Override
	public void atacar(Raza objetivo) {
		if (ataqueExitoso) {
			System.out.println("Reralopes erra este ataque.");
			this.ataqueExitoso = false;
			return;
		} else {
			if (this.ataquesPotenciados > 0) {
				System.out.println("Reralopes ataca realizando " + 2 * this.daño +" con su catapulta y ocasiona el doble de daño.");
				objetivo.recibirAtaque(2 * this.daño);
				this.ataquesPotenciados--;
			} else {
				System.out.println("Reralopes ataca realizando " + this.daño + " con su catapulta e inflinge daño básico.");
				objetivo.recibirAtaque(this.daño);
			}
			this.ataqueExitoso = true;
		}
		
	}

	@Override
	public void recibirAtaque(int daño) {
		System.out.println("Reralopes se desconcentra y su daño vuelve a la normalidad.");
		this.ataquesPotenciados = 0;
		this.salud -= daño;
		if (this.salud < 0) {
			this.salud = 0;
		}
	}

	@Override
	public void descansar() {
		System.out.println("Reralopes se concentra, y los próximos 3 ataques harán el doble de daño.");
		this.ataquesPotenciados = 3;
	}

}
