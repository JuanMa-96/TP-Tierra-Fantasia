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

    public int[] dijkstra(int verticeInicial) {
        int[] distancias = new int[vertices]; // Array para almacenar las distancias minimas
        boolean[] caminoMasCorto = new boolean[vertices]; // Array para saber si el vértice ya tiene su camino mas corto determinado
        int[] predecesores = new int[vertices]; // Array para almacenar los predecesores de cada nodo

        // Tip para los pibes: Arrays.fill rellena el vector que le pasamos con el valor del segundo parametro
        Arrays.fill(distancias, Integer.MAX_VALUE); // Inicializa todas las distancias con un valor grande
        Arrays.fill(predecesores, -1); // Inicializamos los predecesores como -1 (sin predecesor)
        distancias[verticeInicial] = 0; // La distancia desde el vértice de inicio a sí mismo es 0

        // Usamos una cola de prioridad para obtener el vertice con la menor distancia en cada ocasion
        // Lo que le pasamos como parametro es la capacidad inicial de esa cola de prioridad (cantidad de pueblos) y
        //  despues un comparador de enteros que usa el peso de cada arista para comparar
        PriorityQueue<Arista> priorityQueue = new PriorityQueue<>(vertices, Comparator.comparingInt(Arista -> Arista.peso));

        // Agregamos el primer vertice (pueblo inicial)
        priorityQueue.add(new Arista(verticeInicial, 0));

        while (!priorityQueue.isEmpty()) {
            // Extraer el vertice con la menor distancia (aca es donde usa el comparator que pasamos arriba para calcular
            //  el menor de los vertices que va a extraer)
            int u = priorityQueue.poll().destino;

            // Si no fue procesado (aun no se encontro el camino mas corto)
            if (!caminoMasCorto[u]) {
                // Marcar el vertice como procesado
                caminoMasCorto[u] = true;

                // Procesar los vecinos del vertice extraido
                for (Arista Arista : listaAdyacencias[u]) {
                    int v = Arista.destino;
                    int peso = Arista.peso;

                    // Si encontramos una ruta más corta al vertice v, actualizamos su distancia y el predecesor
                    if (!caminoMasCorto[v] && distancias[u] + peso < distancias[v]) {
                        distancias[v] = distancias[u] + peso;
                        predecesores[v] = u;
                        priorityQueue.add(new Arista(v, distancias[v]));
                    }
                }
            }
        }
        return predecesores;
    }

    // Metodo para devolver el camino mas corto desde el nodo inicial hasta el nodo final
    public List<Integer> obtenerCaminoMasCorto(int verticeInicial, int verticeFinal) {
        int[] predecesores = dijkstra(verticeInicial);
        List<Integer> path = new ArrayList<>();
        int verticeActual = verticeFinal;

        // Recorrer hacia atras desde el nodo final usando los predecesores
        // Los valores con -1 no los va a contemplar para el camino
        while (verticeActual != -1) {
            path.add(verticeActual);
            verticeActual = predecesores[verticeActual];
        }

        // Si el nodo final no tiene predecesor (esta descolgado), no hay camino
        if (path.get(path.size() - 1) != verticeInicial) {
            return new ArrayList<>();
        }

        // Invertir el camino para que sea de inicio a fin
        Collections.reverse(path);
        return path;
    }
}
