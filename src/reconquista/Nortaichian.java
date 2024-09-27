package reconquista;

public class Nortaichian extends Raza {
	private int turnosEnfurecido;
	private int turnosDePiedra;

	public Nortaichian() {
		this.salud = 66;
		this.saludMaxima = 66;
		this.daño = 18;
		this.turnosEnfurecido = 0;
		this.turnosDePiedra = 0;
	}

	@Override
	public void atacar(Raza objetivo) {
		if (this.turnosDePiedra > 0) {
			System.out.println("Nortaichian está de piedra y no puede atacar.");
			this.turnosDePiedra--;
			return;
		}

		if (turnosEnfurecido > 0) {
			System.out.println("Nortaichian ataca con su arco infligiendo el doble de su daño por estar enfurecido.");
			objetivo.recibirAtaque(2 * this.daño);
			turnosEnfurecido--;
		} else {
			System.out.println("Nortaichian ataca con su arco y realiza un daño básico.");
			objetivo.recibirAtaque(this.daño);
		} // Se cura un 4% de su salud máxima en cada ataque
		this.salud += this.saludMaxima * 0.04;

		if (this.salud > this.saludMaxima) {
			this.salud = this.saludMaxima;
		}
	}

	@Override
	public void recibirAtaque(int daño) {
		if (this.turnosDePiedra > 0) {
			System.out.println("Nortaichian recibe la mitad del daño por estar de piedra.");
			daño /= 2;
		}

		this.salud -= daño;
		// Se enfurece por 2 turnos
		System.out.println("Nortaichian se enfurece y sus próximos ataques serán el doble de poderosos.");
		this.turnosEnfurecido = 2;
	}

	@Override
	public void descansar() {
		System.out.println("Nortaichian descansa, recupera toda su salud, pero se convierte en piedra por 2 turnos.");
		this.salud = this.saludMaxima;
		this.turnosDePiedra = 2;
	}

}
