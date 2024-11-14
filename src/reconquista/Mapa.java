package reconquista;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Mapa {
    private static Mapa instanciaUnica;
    private Map<Integer, Pueblo> pueblos;
    private Map<Integer, List<Conexion>> adyacencias;

    private Mapa() {
        pueblos = new HashMap<>();
        adyacencias = new HashMap<>();
    }

    public static Mapa getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Mapa();
        }
        return instanciaUnica;
    }

    public void agregarPueblo(Pueblo pueblo) {
        pueblos.put(pueblo.getNroPueblo(), pueblo);
        adyacencias.putIfAbsent(pueblo.getNroPueblo(), new ArrayList<>());
    }

    public void agregarDistancia(int origen, int destino, int kilometros) {
        adyacencias.putIfAbsent(origen, new ArrayList<>());
        adyacencias.get(origen).add(new Conexion(destino, kilometros));
    }

    public Pueblo obtenerPueblo(int id) {
        return pueblos.get(id);
    }

    public List<Conexion> obtenerConexiones(int id) {
        return adyacencias.get(id);
    }

    public Map<Integer, Pueblo> getPueblos() {
        return pueblos;
    }

    public Map<Integer, List<Conexion>> getAdyacencias() {
        return adyacencias;
    }
}
