public class Main {
    public static void main(String[] args) {
        GrafoDirigidoAciclico<Integer> grafo = new GrafoDirigidoAciclico<>();

        Nodo<Integer> nodo1 = new Nodo<>(0);
        Nodo<Integer> nodo2 = new Nodo<>(1);
        Nodo<Integer> nodo3 = new Nodo<>(2);
        Nodo<Integer> nodo4 = new Nodo<>(3);
        Nodo<Integer> nodo5 = new Nodo<>(4);
        Nodo<Integer> nodo6 = new Nodo<>(5);
        Nodo<Integer> nodo7 = new Nodo<>(6);
        Nodo<Integer> nodo8 = new Nodo<>(7);


        grafo.agregarNodo(nodo1);
        grafo.agregarNodo(nodo2);
        grafo.agregarNodo(nodo3);
        grafo.agregarNodo(nodo4);
        grafo.agregarNodo(nodo5);
        grafo.agregarNodo(nodo6);
        grafo.agregarNodo(nodo7);
        grafo.agregarNodo(nodo8);

        grafo.insertarArista(0,2);
        grafo.insertarArista(1,2);
        grafo.insertarArista(3,5);
        grafo.insertarArista(5,6);
        grafo.insertarArista(6,7);

        grafo.hacerMatrizDeAdyacencia();

        int[][] matriz = grafo.getMatrizDeAdyacencia();
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }

        System.out.println("Numero de aristas que entran en el vertice 1: "+ grafo.gradoDeEntrada(0));
        System.out.println("Numero de aristas que salen en el vertice 1: "+ grafo.gradoDeSalida(0));
        System.out.println("El nuemro de aristas del grafo son: "+ grafo.cuantasAristasHay());

        System.out.println("Hay una arista entre el nodo 2 y el nodo 3: " + grafo.adyacente(1, 2));

        System.out.println("Hay un camino entre el nodo 1 y el nodo 3: " + grafo.conectados(0, 2));

        grafo.mostrarListaDeAdyacencia();

        System.out.println(grafo.topologicalSort());
    }
}