package reconquista;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Archivo {
    private Mision mision;

    public Archivo(String rutaArchivo) throws IOException {
        cargarDeArchivo(rutaArchivo);
    }

    public void cargarDeArchivo(String rutaArchivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
        int cantPueblos = Integer.parseInt(reader.readLine().trim());

        // Obtener la instancia de Mapa
        Mapa mapa = Mapa.getInstance();

        // Limpiar el mapa actual (opcional, en caso de que se quiera reiniciar cada carga)
        mapa.getPueblos().clear();
        mapa.getAdyacencias().clear();

        // Cargar los pueblos en el mapa
        for (int i = 0; i < cantPueblos; i++) {
            String[] datosPueblo = reader.readLine().split(" ");
            int id = Integer.parseInt(datosPueblo[0]);
            int habitantes = Integer.parseInt(datosPueblo[1]);
            String raza = datosPueblo[2];
            String relacion = datosPueblo[3];

            Pueblo pueblo = new Pueblo(raza, habitantes, id, relacion);
            mapa.agregarPueblo(pueblo);
        }

        // Leer pueblo inicial y final
        String[] inicialFinal = reader.readLine().split(" -> ");
        int puebloInicial = Integer.parseInt(inicialFinal[0]);
        int puebloFinal = Integer.parseInt(inicialFinal[1]);

        // Leer distancias y agregarlas al mapa
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] datosDistancia = linea.split(" ");
            int origen = Integer.parseInt(datosDistancia[0]);
            int destino = Integer.parseInt(datosDistancia[1]);
            int kilometros = Integer.parseInt(datosDistancia[2]);

            mapa.agregarDistancia(origen, destino, kilometros);
        }

        reader.close();

        // Crear la misión con los datos cargados en el mapa
        mision = new Mision(puebloInicial, puebloFinal, cantPueblos);
    }

    public Mision getMision() { return mision; }
}

