package reconquista;

import java.util.*;

public class Dijkstra {
    private int vertices;
    private Map<Integer, List<Conexion>> listaAdyacencias;

    public Dijkstra() {
        Mapa mapa = Mapa.getInstance();
        listaAdyacencias = mapa.getAdyacencias();
        vertices = mapa.getPueblos().size();
    }

    static class Arista {
        int destino, peso;

        public Arista(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    public ResultadoDijkstra dijkstra(int verticeInicial) {
        int[] distancias = new int[vertices + 1];
        boolean[] caminoMasCorto = new boolean[vertices + 1];
        int[] predecesores = new int[vertices + 1];

        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arrays.fill(predecesores, -1);
        distancias[verticeInicial] = 0;

        PriorityQueue<Arista> priorityQueue = new PriorityQueue<>(vertices, Comparator.comparingInt(arista -> arista.peso));

        priorityQueue.add(new Arista(verticeInicial, 0));

        while (!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll().destino;

            if (!caminoMasCorto[u]) {
                caminoMasCorto[u] = true;

                List<Conexion> conexiones = listaAdyacencias.get(u);
                if (conexiones != null) {
                    for (Conexion conexion : conexiones) {
                        int v = conexion.getDestino();
                        int peso = conexion.getDistancia();

                        if (!caminoMasCorto[v] && distancias[u] + peso < distancias[v]) {
                            distancias[v] = distancias[u] + peso;
                            predecesores[v] = u;
                            priorityQueue.add(new Arista(v, distancias[v]));
                        }
                    }
                }
            }
        }

        return new ResultadoDijkstra(distancias, predecesores);
    }
}
