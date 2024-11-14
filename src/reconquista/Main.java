package reconquista;

import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		Mision mision = new Mision();
		try {
			mision.cargarDeArchivo("./resources/Mision_1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
