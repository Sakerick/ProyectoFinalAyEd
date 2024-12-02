import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    private JFrame frameGrafos;
    private JPanel panelGrafos;
    private JPanel panelBotones;
    private JPanel panelTextos;
    private JButton botonAddGrafo;
    private JButton botonAddArista;
    private JButton botonGenerarGrafoRandom;
    private GrafoDirigidoAciclico grafoPrincipal;
    private int nDeGrafo;
    private mxGraph graph;
    private mxGraphComponent graphComponent;
    private Object parent;
    private List<Object> nodosGraficos; // Lista para almacenar los nodos gráficos
    private JButton botonTopologicalSort;
    private JTextArea textoResultadoOrdenado;;
    private JTextArea textoMatrizDeAdyacencia;
    private JTextArea textoListaDeAdyacencia;
    private JTextArea textoNDeAristas;
    private JLabel labelSort;
    private JLabel labelMatriz;
    private JLabel labelLista;
    private JLabel labelAristas;
    private ScrollPane scrollMatriz;
    private ScrollPane scrollListaAdyacencia;
    private ScrollPane scrollSort;


    private GUI() {
        botonAddArista = new JButton("Añadir Arista");
        botonAddGrafo = new JButton("Añadir grafo");
        botonTopologicalSort = new JButton("Topological Sort");
        botonGenerarGrafoRandom = new JButton("Generar Grafo");

        textoResultadoOrdenado = new JTextArea();
        textoMatrizDeAdyacencia = new JTextArea();
        textoListaDeAdyacencia = new JTextArea();
        textoNDeAristas = new JTextArea();

        scrollMatriz = new ScrollPane();
        scrollListaAdyacencia = new ScrollPane();
        scrollSort = new ScrollPane();

        labelAristas = new JLabel("No. de Aristas");
        labelSort = new JLabel("Grafo ordenado");
        labelMatriz = new JLabel("Matriz de adyacencia");
        labelLista = new JLabel("Lista adyacencia");

        nDeGrafo = 0;
        nodosGraficos = new ArrayList<>();

    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.crearGUI();
    }

    void crearGUI() {
        frameGrafos = new JFrame("Grafos");
        frameGrafos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGrafos.setVisible(true);
        frameGrafos.setBounds(150, 0, 1600, 1200);

        frameGrafos.setLayout(null);
        frameGrafos.getContentPane().setBackground(new Color(165, 177, 181));

        panelGrafos = new JPanel();
        panelBotones= new JPanel();
        panelTextos = new JPanel();

        panelGrafos.setLayout(null);
        panelBotones.setLayout(null);
        panelTextos.setLayout(null);

        frameGrafos.add(panelBotones);
        frameGrafos.add(panelGrafos);
        frameGrafos.add(panelTextos);

        panelGrafos.setBounds(150, 20, 1000, 750);
        panelGrafos.setBackground(new Color(243, 255, 207));

        panelTextos.setBounds(1180,30,350,700);

        panelTextos.add(scrollSort);
        panelTextos.add(labelSort);

        scrollSort.add(textoResultadoOrdenado);
        scrollSort.setBounds(20, 30, 300, 60);
        textoResultadoOrdenado.setBackground(new Color(187, 207, 248));
        textoResultadoOrdenado.setBorder(BorderFactory.createLineBorder(new Color(36, 107, 255)));
        textoResultadoOrdenado.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        textoResultadoOrdenado.setForeground(Color.black);
        textoResultadoOrdenado.setEditable(false);
        labelSort.setBounds(scrollSort.getX(),scrollSort.getY()-30,300,30);
        labelSort.setFont(new Font("Times New Roman", Font.PLAIN, 18));


        panelTextos.add(scrollMatriz);
        panelTextos.add(labelMatriz);

        scrollMatriz.add(textoMatrizDeAdyacencia);
        scrollMatriz.setBounds(20, scrollSort.getY()+scrollSort.getHeight()+40, 200, 180);
        textoMatrizDeAdyacencia.setBackground(new Color(187, 207, 248));
        textoMatrizDeAdyacencia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        textoMatrizDeAdyacencia.setForeground(Color.black);
        textoMatrizDeAdyacencia.setMargin(new Insets(10, 10, 10, 10));
        textoMatrizDeAdyacencia.setEditable(false);
        labelMatriz.setBounds(scrollMatriz.getX(),scrollMatriz.getY()-30,300,30);
        labelMatriz.setFont(new Font("Times New Roman", Font.PLAIN, 18));


        panelTextos.add(scrollListaAdyacencia);
        panelTextos.add(labelLista);

        scrollListaAdyacencia.add(textoListaDeAdyacencia);
        scrollListaAdyacencia.setBounds(20, scrollMatriz.getY()+scrollMatriz.getHeight()+30, 280, 200);
        textoListaDeAdyacencia.setBackground(new Color(187, 207, 248));
        textoListaDeAdyacencia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        textoListaDeAdyacencia.setForeground(Color.black);
        textoMatrizDeAdyacencia.setMargin(new Insets(10, 10, 10, 10));
        textoListaDeAdyacencia.setEditable(false);
        labelLista.setBounds(scrollListaAdyacencia.getX(),scrollListaAdyacencia.getY()-30,300,30);
        labelLista.setFont(new Font("Times New Roman", Font.PLAIN, 18));


        panelTextos.add(textoNDeAristas);
        panelTextos.add(labelAristas);

        textoNDeAristas.setBounds(scrollListaAdyacencia.getX(),scrollListaAdyacencia.getY()+scrollListaAdyacencia.getHeight()+30,40,20);
        textoNDeAristas.setBackground(new Color(187, 207, 248));
        textoNDeAristas.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        textoNDeAristas.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        textoNDeAristas.setForeground(Color.black);
        textoNDeAristas.setMargin(new Insets(10, 10, 10, 10));
        textoNDeAristas.setEditable(false);
        labelAristas.setBounds(textoNDeAristas.getX(),textoNDeAristas.getY()-30,300,30);
        labelAristas.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        panelBotones.add(botonAddGrafo);
        panelBotones.add(botonAddArista);
        panelBotones.add(botonTopologicalSort);
        panelBotones.add(botonGenerarGrafoRandom);

        panelBotones.setBounds(150,800,1000,50);
        panelBotones.setBackground(new Color(241, 224, 181));



        // Botón para añadir nodos

        botonAddGrafo.setBounds(80, 15, 150, 20);
        botonAddGrafo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        botonAddGrafo.setBackground(Color.white);
        botonAddGrafo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                nDeGrafo = 0;
                int alturaFila = 150;
                eliminarGrafoAnterior();
                String valorGrafo = JOptionPane.showInputDialog("Ingrese el valor del grafo");
                if (valorGrafo != null) {
                    grafoPrincipal = new GrafoDirigidoAciclico<>(Integer.parseInt(valorGrafo));
                    graph.getModel().beginUpdate();
                    try {
                        for (int i =0;i < grafoPrincipal.getNodos().size();i++) {
                            if (nDeGrafo == 5) {
                                alturaFila+=100;
                                nDeGrafo = 0;
                            }

                                Object nodoGrafico = graph.insertVertex(parent, null, "Nodo " + String.valueOf(grafoPrincipal.getNodos().get(i)),
                                        100 + nDeGrafo * 150, alturaFila, 80, 30);
                                nDeGrafo++;
                                nodosGraficos.add(nodoGrafico); // Guardar referencia al nodo gráfico

                        }
                    } finally {
                        graph.getModel().endUpdate();
                    }

                    textoNDeAristas.setText(String.valueOf(grafoPrincipal.cuantasAristasHay()));
                }
            }
        });

        // Botón para añadir aristas
        botonAddArista.setBounds(botonAddGrafo.getX() + 170, 15, 150, 20);
        botonAddArista.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        botonAddArista.setBackground(Color.white);
        botonAddArista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nodoX = "";
                int nodoXNum = 0;
                String nodoY = "";
                int nodoYNum = 0;
                do {
                    nodoX = JOptionPane.showInputDialog("Ingrese el nodo X");
                    if (nodoX != null) {
                        nodoXNum = Integer.parseInt(nodoX);
                        if (nodoXNum > grafoPrincipal.getNodos().size() - 1) {
                            JOptionPane.showMessageDialog(null, "Ese nodo no existe!");
                        }
                    }
                } while (nodoXNum > grafoPrincipal.getNodos().size() - 1);

                do {
                    nodoY = JOptionPane.showInputDialog("Ingrese el nodo Y");
                    if (nodoY != null) {
                        nodoYNum = Integer.parseInt(nodoY);
                        if (nodoYNum > grafoPrincipal.getNodos().size() - 1) {
                            JOptionPane.showMessageDialog(null, "Ese nodo no existe!");
                        }
                    }
                } while (nodoYNum > grafoPrincipal.getNodos().size() - 1);

                if (grafoPrincipal.insertarArista(nodoXNum, nodoYNum)) {
                    // Agregar arista al grafo visual
                    graph.getModel().beginUpdate();
                    try {
                        Object source = nodosGraficos.get(nodoXNum);
                        Object target = nodosGraficos.get(nodoYNum);

                        // Asegurarse de que la arista tenga el estilo de flecha
                        String edgeStyle = "edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;exitX=0.5;exitY=0.5;exitDx=0;exitDy=0;entryX=0.5;entryY=0.5;entryDx=0;entryDy=0;";
                        graph.insertEdge(parent, null, "", source, target, edgeStyle);
                    } finally {
                        graph.getModel().endUpdate();
                    }

                    // Actualizar los textos de adyacencia
                    grafoPrincipal.hacerMatrizDeAdyacencia();
                    textoListaDeAdyacencia.setText(grafoPrincipal.getListaDeAdyacencia());
                    textoMatrizDeAdyacencia.setText(grafoPrincipal.getStringMatrizDeAdyacencia());
                    textoNDeAristas.setText(String.valueOf(grafoPrincipal.cuantasAristasHay()));

                    // Revalidar y repintar el panel para asegurarse de que se vean los cambios
                    panelGrafos.revalidate();
                    panelGrafos.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Nodo cíclico no permitido");
                }
            }
        });
        
        botonTopologicalSort.setBounds(botonAddArista.getX()+170,15,150,20);
        botonTopologicalSort.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        botonTopologicalSort.setBackground(Color.white);
        botonTopologicalSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textoResultadoOrdenado.setText(grafoPrincipal.topologicalSort());
            }
        });

        botonGenerarGrafoRandom.setBounds(botonTopologicalSort.getX()+170,15,150,20);
        botonGenerarGrafoRandom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        botonGenerarGrafoRandom.setBackground(Color.white);
        botonGenerarGrafoRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int alturaFila = 150;
                eliminarGrafoAnterior();
                    grafoPrincipal = new GrafoDirigidoAciclico<>(true);
                    graph.getModel().beginUpdate();
                    try {
                        for (int i =0;i < grafoPrincipal.getNodos().size();i++) {
                            if (nDeGrafo == 5) {
                                alturaFila+=100;
                                nDeGrafo = 0;
                            }

                            Object nodoGrafico = graph.insertVertex(parent, null, "Nodo " + String.valueOf(grafoPrincipal.getNodos().get(i)),
                                    100 + nDeGrafo * 150, alturaFila, 80, 30);
                            nDeGrafo++;
                            nodosGraficos.add(nodoGrafico); // Guardar referencia al nodo gráfico

                        }
                    } finally {
                        graph.getModel().endUpdate();
                    }
            }
        });
    }

    public void eliminarGrafoAnterior() {
        // Verificar si ya hay un graphComponent existente en el panel y eliminarlo
        if (graphComponent != null) {
            panelGrafos.remove(graphComponent);  // Elimina el grafo anterior
            graphComponent = null;  // Asegúrate de que graphComponent se reinicie a null
        }

        // Crear un nuevo grafo y graphComponent
        graph = new mxGraph();

        // Crear un nuevo graphComponent y asignar el nuevo grafo
        graphComponent = new mxGraphComponent(graph);
        parent = graph.getDefaultParent();

        // Configurar el tamaño y agregar el nuevo graphComponent al panel
        graphComponent.setBounds(0, 0, 1400, 750);
        panelGrafos.add(graphComponent);

        // Asegúrate de que el panel sea actualizado para reflejar el nuevo grafo
        panelGrafos.revalidate();
        panelGrafos.repaint();

        // Asignar el parent para el nuevo grafo
        parent = graph.getDefaultParent();
    }


}
