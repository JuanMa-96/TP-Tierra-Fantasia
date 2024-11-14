package reconquista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Archivo {

    public Mision cargarDeArchivo(String rutaArchivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
        int cantPueblos = Integer.parseInt(reader.readLine().trim());

        ArrayList<Pueblo> pueblos = new ArrayList<>();
        pueblos.add(null); // El primero es nulo para tomar de indice el nro del pueblo
        for (int i = 0; i < cantPueblos; i++) {
            String[] datosPueblo = reader.readLine().split(" ");
            int id = Integer.parseInt(datosPueblo[0]);
            int habitantes = Integer.parseInt(datosPueblo[1]);
            String raza = datosPueblo[2];
            String relacion = datosPueblo[3];
            pueblos.add(new Pueblo(raza, habitantes, id, relacion));
        }

        // Leer pueblo inicial y final
        String[] inicialFinal = reader.readLine().split(" -> ");
        int puebloInicial = Integer.parseInt(inicialFinal[0]);
        int puebloFinal = Integer.parseInt(inicialFinal[1]);

        /* Meter en grafo */

        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] datosDistancia = linea.split(" ");
            int origen = Integer.parseInt(datosDistancia[0]);
            int destino = Integer.parseInt(datosDistancia[1]);
            int kilometros = Integer.parseInt(datosDistancia[2]);

            for(int i = 1; i < cantPueblos ; i++) {
                if(pueblos.get(i).getNroPueblo() == origen) {
                    pueblos.get(i).agregarDistancias(destino, kilometros);
                }
            }

        }

        return new Mision(pueblos, puebloInicial, puebloFinal, cantPueblos);
    }
}
