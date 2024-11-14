package reconquista;

public class ResultadoDijkstra {
    private int[] distancias;
    private int[] predecesores;

    public ResultadoDijkstra(int[] distancias, int[] predecesores) {
        this.distancias = distancias;
        this.predecesores = predecesores;
    }

    public int[] getDistancias() {
        return distancias;
    }

    public int[] getPredecesores() {
        return predecesores;
    }
}



