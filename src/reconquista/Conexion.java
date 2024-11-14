package reconquista;

public class Conexion {
    private int destino;
    private int distancia;

    public Conexion(int destino, int distancia) {
        this.destino = destino;
        this.distancia = distancia;
    }

    public int getDestino() {
        return destino;
    }

    public int getDistancia() {
        return distancia;
    }
}

