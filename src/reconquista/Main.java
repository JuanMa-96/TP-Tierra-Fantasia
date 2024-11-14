package reconquista;

import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		Archivo archivo = new Archivo();
		try {
			Mision mision = archivo.cargarDeArchivo("TP-Tierra-Fantasia/resources/Mision_1.txt");
			mision.realizarMision();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
