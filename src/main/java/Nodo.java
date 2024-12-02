import java.io.Serializable;
import java.util.ArrayList;

public class Nodo<T> implements Serializable {
    private T info; // Información del nodo

    // ArrayList que almacena los nodos siguientes y anteriores
    private ArrayList<Nodo<T>> nodosSiguientes = new ArrayList<>();
    private ArrayList<Nodo<T>> nodosAnteriores = new ArrayList<>();

    // Grado que se toma en cuenta en el ordenamiento topológico
    private int gradoDeEntrada = 0;
    private boolean esChar;

    // Constructor del nodo
    public Nodo(T info) {
        this.info = info;
    }

    // Getters y setters de info
    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    // Getters y setters de gradoDeEntrada
    public int getGradoDeEntrada() {
        return gradoDeEntrada;
    }

    public void setGradoDeEntrada(int gradoDeEntrada) {
        this.gradoDeEntrada = gradoDeEntrada;
    }

    public void agregarNodo(Nodo<T> nodo) {
        nodosSiguientes.add(nodo);
        gradoDeEntrada++;
    }

    // Getter de la lista de nodos siguientes
    public ArrayList<Nodo<T>> getNodosSiguientes() {
        return nodosSiguientes;
    }

    @Override
    public String toString() {
        return info.toString();
    }
}
