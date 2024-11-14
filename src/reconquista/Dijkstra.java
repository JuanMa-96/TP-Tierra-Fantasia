package reconquista;

import java.util.*;

public class Dijkstra {
    private int vertices;

    private LinkedList<Arista>[] listaAdyacencias;

    public Dijkstra(List<Pueblo> pueblos) {
        vertices = pueblos.size();
        listaAdyacencias = new LinkedList[vertices];
        int nroPueblo = 1;
        for(Pueblo p : pueblos) {
            if (p != null) {
                LinkedList<Arista> ll = new LinkedList<>();

                for(int i = 0 ; i < p.getDistancias().size() ; i++) {
                    Arista ar = new Arista(p.getDestino(i), p.getDistancia(i));
                    ll.add(ar);
                }
                listaAdyacencias[nroPueblo] = ll;
                nroPueblo++;
            }
        }
    }

    static class Arista {
        int destino, peso;

        public Arista(int destination, int weight) {
            this.destino = destination;
            this.peso = weight;
        }
    }

    public ResultadoDijkstra dijkstra(int verticeInicial) {
        int[] distancias = new int[vertices]; // Array para almacenar las distancias mínimas
        boolean[] caminoMasCorto = new boolean[vertices]; // Array para saber si el vértice ya tiene su camino más corto determinado
        int[] predecesores = new int[vertices]; // Array para almacenar los predecesores de cada nodo

        Arrays.fill(distancias, Integer.MAX_VALUE); // Inicializa todas las distancias con un valor grande
        Arrays.fill(predecesores, -1); // Inicializamos los predecesores como -1 (sin predecesor)
        distancias[verticeInicial] = 0; // La distancia desde el vértice de inicio a sí mismo es 0

        // Usamos una cola de prioridad para obtener el vértice con la menor distancia en cada ocasión
        PriorityQueue<Arista> priorityQueue = new PriorityQueue<>(vertices, Comparator.comparingInt(arista -> arista.peso));

        // Agregamos el primer vértice (pueblo inicial)
        priorityQueue.add(new Arista(verticeInicial, 0));

        while (!priorityQueue.isEmpty()) {
            // Extraer el vértice con la menor distancia
            int u = priorityQueue.poll().destino;

            // Si no fue procesado (aún no se encontró el camino más corto)
            if (!caminoMasCorto[u]) {
                // Marcar el vértice como procesado
                caminoMasCorto[u] = true;

                // Procesar los vecinos del vértice extraído
                for (Arista arista : listaAdyacencias[u]) {
                    int v = arista.destino;
                    int peso = arista.peso;

                    // Si encontramos una ruta más corta al vértice v, actualizamos su distancia y el predecesor
                    if (!caminoMasCorto[v] && distancias[u] + peso < distancias[v]) {
                        distancias[v] = distancias[u] + peso;
                        predecesores[v] = u;
                        priorityQueue.add(new Arista(v, distancias[v]));
                    }
                }
            }
        }

        return new ResultadoDijkstra(distancias, predecesores);
    }
}
