package reconquista;

public class Wrives extends Raza {
	private boolean haDescansado;
	private int contadorAtaque = 0;

	public Wrives() {
		this.salud = 108;
		this.saludMaxima = 108;
		this.daño = 113;
		this.haDescansado = false;
	}

	@Override
	public void atacar(Raza objetivo) {
		if (haDescansado) {
			System.out.println("Wrives no quiere atacar porque considera la violencia algo malo.");
			return;
		}

		if (this.contadorAtaque < 2) {
			System.out.println("Wrives ataca al enemigo con Magia.");
			objetivo.recibirAtaque(this.daño);
			this.contadorAtaque++;
		} else {
			System.out.println("Wrives ataca con el doble de su daño con Magia superior.");
			objetivo.recibirAtaque(2 * this.daño);
			this.contadorAtaque = 0;
		}

		return;
	}

	@Override
	public void recibirAtaque(int daño) {
		this.haDescansado = false;
		System.out.println("Wrives recibe el doble de daño porque no tiene armadura.");
		this.salud -= 2 * daño;
	}

	@Override
	public void descansar() {
		System.out.println("Wrives medita, por lo que aumenta su salud y salud máxima en 50.");
		this.saludMaxima += 50;
		this.salud += 50;
		this.haDescansado = true;
	}
}