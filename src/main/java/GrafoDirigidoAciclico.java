import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class GrafoDirigidoAciclico<T> implements Serializable {
    private ArrayList<Nodo<T>> nodos = new ArrayList<>();
    private int[][] matrizDeAdyacencia;
    private String listaCadena;
    private String matrizCadena;
    public GrafoDirigidoAciclico() {
        
    }

    public GrafoDirigidoAciclico(int x){
        for (int i = 0; i < x; i++) {
            Nodo<Integer> nodo = new Nodo<>(i);
            nodos.add((Nodo<T>) nodo);
        }
    }

    public GrafoDirigidoAciclico(char endChar) {
        if (!Character.isLetter(endChar)) {
            throw new IllegalArgumentException("El carácter ingresado debe ser una letra.");
        }
        endChar = Character.toUpperCase(endChar); // Convertir a mayúscula
        for (char c = 'A'; c <= endChar; c++) {
            Nodo<Character> nodo = new Nodo<>(c);
            nodos.add((Nodo<T>) nodo);
        }
    }


    public GrafoDirigidoAciclico( boolean valoresAleatorios) {
        if (valoresAleatorios) {
            Random random = new Random();
            for (int i = 0; i < 4; i++) {
                Nodo<Integer> nodo = new Nodo<>(random.nextInt(100));
                nodos.add((Nodo<T>) nodo);
            }
        }
    }

    public ArrayList<Nodo<T>> getNodos() {
        return nodos;
    }

    public void agregarNodo(Nodo<T> nodo) {
        nodos.add(nodo);
    }



    public String getStringMatrizDeAdyacencia () {
        StringBuilder builder = new StringBuilder();
        matrizDeAdyacencia = getMatrizDeAdyacencia();
        for (int[] fila : matrizDeAdyacencia) {
            for (int valor : fila) {
                builder.append(valor).append(" ");
            }
            builder.append("\n");
        }
        matrizCadena = builder.toString();
        return builder.toString();
    }

    public String listaString() {
        return listaCadena;
    }

    public String matrizString() {
        return matrizCadena;
    }
    public String getListaDeAdyacencia() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nodos.size(); i++) {
            Nodo<T> nodo = nodos.get(i);
            builder.append("Nodo ").append(nodo.getInfo()).append(" -> ");
            ArrayList<Nodo<T>> nodosSiguientes = nodo.getNodosSiguientes();
            if (nodosSiguientes.isEmpty()) {
                builder.append("No tiene nodos adyacentes.\n");
            } else {
                for (Nodo<T> nodoSiguiente : nodosSiguientes) {
                    builder.append(nodoSiguiente.getInfo()).append(" ");
                }
                builder.append("\n");
            }
        }
        listaCadena = builder.toString();
        return builder.toString();
    }

    public void hacerMatrizDeAdyacencia() {
        //Tomamos el size de la lista de nodos
        int n = nodos.size();
        //Creamos la matriz de la lista de nodos
        matrizDeAdyacencia = new int[n][n];
        
        //Recorremos la la lista de los nodos
        for (int i = 0; i < nodos.size(); i++) {
            //Tomamos un nodo i
            Nodo<T> nodo = nodos.get(i);
            //Recorremos la lista de los nodos siguientes del nodo
            for (Nodo<T> nodoSiguiente : nodo.getNodosSiguientes()) {
                //Recorremos nuevamente la lista de los nodos
                for (int j = 0; j < nodos.size(); j++) {
                    //Si la info del nodo siguiente es igual al nodo es que hay una conexion
                    if (nodoSiguiente.getInfo().equals(nodos.get(j).getInfo())) {
                        matrizDeAdyacencia[i][j] = 1;
                    }
                }
            }
        }
    }

    public int[][] getMatrizDeAdyacencia() {
        return matrizDeAdyacencia;
    }

    public int gradoDeEntrada(int x){
        int gradoDeEntrada = -1;
        int numNodos = nodos.size();

        if (x < 0 || x >= nodos.size()) {
            System.out.println("Indice fuera de rango");
        } else {
            gradoDeEntrada = 0;
            for (int i = 0; i < numNodos; i++) {
                Nodo<T> nodo = nodos.get(i);
                for (int j = 0; j < nodo.getNodosSiguientes().size(); j++) {
                    Nodo<T> nodoAux = nodo.getNodosSiguientes().get(j);
                    if (nodoAux.getInfo().equals(nodos.get(x).getInfo())){
                        gradoDeEntrada++;
                    }
                }
            }
        }
        return gradoDeEntrada;
    }

    public int gradoDeSalida(int x){
        return nodos.get(x).getNodosSiguientes().size();
    }

    public int cuantasAristasHay(){
        int numeroDeAristas = 0;
        for (int i = 0; i < nodos.size(); i++) {
            numeroDeAristas += nodos.get(i).getNodosSiguientes().size();
        }
        return numeroDeAristas;
    }

    public boolean adyacente(int x, int y){
        Nodo<T> nodoX = nodos.get(x);

        for (int i = 0; i < nodoX.getNodosSiguientes().size(); i++) {
            Nodo<T> nodoAux = nodoX.getNodosSiguientes().get(i);
            if (nodoAux.getInfo().equals(nodos.get(y).getInfo())){
                return true;
            }
        }

        return false;
    }

    //Codigo visto en clase
    public boolean conectados(int i, int j) {
        if (i < 0 || i >= nodos.size() || j < 0 || j >= nodos.size()) {
            System.out.println("Indices no permitidos");
        }
        boolean[] visitados = new boolean[nodos.size()];
        return dfs(i, j, visitados);
    }
    
    private boolean dfs(int actual, int destino, boolean[] visitados) {
        if (actual == destino) {
            return true;
        }
        visitados[actual] = true;
        Nodo<T> nodoActual = nodos.get(actual);
        for (Nodo<T> nodoSiguiente : nodoActual.getNodosSiguientes()) {
            int siguienteIndex = nodos.indexOf(nodoSiguiente);
            if (!visitados[siguienteIndex]) {
                if (dfs(siguienteIndex, destino, visitados)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void mostrarListaDeAdyacencia() {
        for (int i = 0; i < nodos.size(); i++) {
            Nodo<T> nodo = nodos.get(i);
            System.out.print("Nodo " + nodo.getInfo() + " -> ");
            ArrayList<Nodo<T>> nodosSiguientes = nodo.getNodosSiguientes();
            if (nodosSiguientes.isEmpty()) {
                System.out.println("No tiene nodos adyacentes.");
            } else {
                for (Nodo<T> nodoSiguiente : nodosSiguientes) {
                    System.out.print(nodoSiguiente.getInfo() + " ");
                }
                System.out.println(); 
            }
        }
    }  

    public void eliminarAristas(){
        for (Nodo<T> nodo : nodos) {
            nodo.getNodosSiguientes().clear();
        }
        matrizDeAdyacencia = null;
    }

    public boolean insertarArista(int x, int y) {
        // Validar si los nodos existen
        if (x < 0 || x >= nodos.size() || y < 0 || y >= nodos.size()) {
            System.out.println("Esos nodos no existen");
            return false;
        }

        Nodo<T> nodoX = nodos.get(x);
        Nodo<T> nodoY = nodos.get(y);

        // Evitar auto-conexiones
        if (x == y) {
            System.out.println("No se puede conectar un nodo consigo mismo.");
            return false;
        }

        // Verificar si la conexión ya existe
        if (nodoX.getNodosSiguientes().contains(nodoY)) {
            System.out.println("Ya existe una conexión entre estos nodos.");
            return false;
        }

        // Agregar la arista temporalmente para verificar ciclos
        nodoX.getNodosSiguientes().add(nodoY);

        if (tieneCiclos()) {
            nodoX.getNodosSiguientes().remove(nodoY);
            System.out.println("No se puede agregar la arista porque genera un ciclo.");
            return false;
        }

        return true;
    }


    public boolean tieneCiclos() {
        int numNodos = nodos.size();
        boolean[] visitado = new boolean[numNodos];
        boolean[] enPila = new boolean[numNodos]; 

        for (int i = 0; i < numNodos; i++) {
            if (!visitado[i]) {
                if (tieneCicloDFS(i, visitado, enPila)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean tieneCicloDFS(int i, boolean[] visitado, boolean[] enPila) {

        Nodo<T> nodo = nodos.get(i);
        if (enPila[i]) {
            return true;
        }
        if (visitado[i]) {
            return false;
        }

        visitado[i] = true;
        enPila[i] = true;


        for (Nodo<T> vecino : nodo.getNodosSiguientes()) {
            int indexVecino = nodos.indexOf(vecino);
            if (tieneCicloDFS(indexVecino, visitado, enPila)) {
                return true;
            }
        }
        enPila[i] = false;
        return false;
    }
    
    public String topologicalSort() {
        // Verificamos si hay ciclos antes de proceder
        if (tieneCiclos()) {
            return "Ciclo detectado";
        }

        // Variables para el ordenamiento topológico
        int numNodos = nodos.size();
        boolean[] visitado = new boolean[numNodos];
        Stack<Nodo<T>> stack = new Stack<>();

        // Realizamos un DFS para llenar la pila
        for (int i = 0; i < numNodos; i++) {
            if (!visitado[i]) {
                topologicalSortDFS(i, visitado, stack);
            }
        }

        // Convertimos la pila a un string
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop().getInfo());
            if (!stack.isEmpty()) {
                sb.append(" - ");
            }
        }
        return sb.toString();
    }

    private void topologicalSortDFS(int i, boolean[] visitado, Stack<Nodo<T>> stack) {
        visitado[i] = true;
        Nodo<T> nodo = nodos.get(i);
        for (Nodo<T> vecino : nodo.getNodosSiguientes()) {
            int indexVecino = nodos.indexOf(vecino);
            if (!visitado[indexVecino]) {
                topologicalSortDFS(indexVecino, visitado, stack);
            }
        }
        stack.push(nodo);
    }

    public String toString () {
        return (
                "Grafo de tamaño: "+ getNodos().size()
                );
    }
}