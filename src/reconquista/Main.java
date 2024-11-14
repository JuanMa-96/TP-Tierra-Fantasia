package reconquista;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		Mision mision = null;
		try {
			Archivo archivo = new Archivo("TP-Tierra-Fantasia/resources/mision_perdida.txt");
			mision = archivo.getMision();
			mision.realizarMision();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
