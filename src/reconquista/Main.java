package reconquista;

import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		try {
			Archivo archivo = new Archivo("TP-Tierra-Fantasia/resources/Mision_1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
