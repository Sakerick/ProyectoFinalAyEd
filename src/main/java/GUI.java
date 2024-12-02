import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.io.*;
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
    private JButton botonTopologicalSort;
    private JButton botonEliminarGrafo;
    private JButton botonGuardarGrafo;
    private OutputStream os;
    private GrafoDirigidoAciclico grafoPrincipal;
    private int nDeGrafo;
    private mxGraph graph;
    private mxGraphComponent graphComponent;
    private Object parent;
    private List<Object> nodosGraficos; // Lista para almacenar los nodos gráficos
    private ArrayList<GrafoDirigidoAciclico> grafosAnteriores;
    private JTextArea textoResultadoOrdenado;;
    private JTextArea textoMatrizDeAdyacencia;
    private JTextArea textoListaDeAdyacencia;
    private JTextArea textoNDeAristas;
    private JLabel labelSort;
    private JLabel labelMatriz;
    private JLabel labelLista;
    private JLabel labelAristas;
    private JLabel labelGrafosGuardados;
    private ScrollPane scrollMatriz;
    private ScrollPane scrollListaAdyacencia;
    private ScrollPane scrollSort;
    private JComboBox<String> comboBoxGrafosGuardados;


    private GUI() {
        botonAddArista = new JButton("Añadir Arista");
        botonAddGrafo = new JButton("Añadir grafo");
        botonTopologicalSort = new JButton("Topological Sort");
        botonGenerarGrafoRandom = new JButton("Generar Grafo");
        botonGuardarGrafo = new JButton("Guardar Grafo");
        botonEliminarGrafo = new JButton("Eliminar Grafo");

        textoResultadoOrdenado = new JTextArea();
        textoMatrizDeAdyacencia = new JTextArea();
        textoListaDeAdyacencia = new JTextArea();
        textoNDeAristas = new JTextArea();

        comboBoxGrafosGuardados = new JComboBox<>();

        scrollMatriz = new ScrollPane();
        scrollListaAdyacencia = new ScrollPane();
        scrollSort = new ScrollPane();

        labelAristas = new JLabel("No. de Aristas");
        labelSort = new JLabel("Grafo ordenado");
        labelMatriz = new JLabel("Matriz de adyacencia");
        labelLista = new JLabel("Lista adyacencia");
        labelGrafosGuardados = new JLabel("Grafos guardados");

        graph = new mxGraph();
        graphComponent = new mxGraphComponent(graph);
        nDeGrafo = 0;
        nodosGraficos = new ArrayList<>();
        grafosAnteriores = new ArrayList<>();

        grafosAnteriores = cargarGrafosDesdeArchivo("grafos.txt");
        for (int i = 0 ; i< grafosAnteriores.size();i++) {
            comboBoxGrafosGuardados.addItem(grafosAnteriores.get(i).toString());
        }
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
        panelTextos.add(comboBoxGrafosGuardados);

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
        panelBotones.add(botonEliminarGrafo);
        panelBotones.add(botonGuardarGrafo);

        panelBotones.setBounds(150,800,1000,50);
        panelBotones.setBackground(new Color(241, 224, 181));




        botonAddGrafo.setBounds(5, 15, 140, 20);
        botonAddGrafo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        botonAddGrafo.setBackground(Color.white);
        botonAddGrafo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarGrafoAnterior();
                grafoPrincipal = new GrafoDirigidoAciclico<>();
                nDeGrafo = 0;
                int alturaFila = 150;

                String valorGrafo = JOptionPane.showInputDialog("Ingrese el valor del grafo");
                if (valorGrafo != null) {
                    char inputChar = valorGrafo.charAt(0);
                    if (Character.isLetter(inputChar)) {
                        inputChar = Character.toUpperCase(inputChar);
                        grafoPrincipal = new GrafoDirigidoAciclico<>(inputChar);
                    } else {
                        try {
                            int valorNumerico = Integer.parseInt(valorGrafo);
                            grafoPrincipal = new GrafoDirigidoAciclico<>(valorNumerico);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Entrada inválida. Ingrese un número o una letra.");

                        }
                    }
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
                                nodosGraficos.add(nodoGrafico);

                        }
                    } finally {
                        graph.getModel().endUpdate();
                    }

                    textoNDeAristas.setText(String.valueOf(grafoPrincipal.cuantasAristasHay()));
                }
            }
        });

        // Añadir aristas button
        botonAddArista.setBounds(botonAddGrafo.getX() + 160, 15, botonAddGrafo.getWidth(), 20);
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
                    graph.getModel().beginUpdate();
                    try {
                        Object source = nodosGraficos.get(nodoXNum);
                        Object target = nodosGraficos.get(nodoYNum);

                        String edgeStyle = "edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;exitX=0.5;exitY=0.5;exitDx=0;exitDy=0;entryX=0.5;entryY=0.5;entryDx=0;entryDy=0;";
                        graph.insertEdge(parent, null, "", source, target, edgeStyle);

                    } finally {
                        graph.getModel().endUpdate();
                    }

                    grafoPrincipal.hacerMatrizDeAdyacencia();
                    textoListaDeAdyacencia.setText(grafoPrincipal.getListaDeAdyacencia());
                    textoMatrizDeAdyacencia.setText(grafoPrincipal.getStringMatrizDeAdyacencia());
                    textoNDeAristas.setText(String.valueOf(grafoPrincipal.cuantasAristasHay()));

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
                            nodosGraficos.add(nodoGrafico);

                        }
                    } finally {
                        graph.getModel().endUpdate();
                    }
            }
        });

        botonEliminarGrafo.setBounds(botonGenerarGrafoRandom.getX()+170,15,150,20);
        botonEliminarGrafo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        botonEliminarGrafo.setBackground(Color.white);
        botonEliminarGrafo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarGrafoAnterior();
                textoResultadoOrdenado.setText("");
                textoListaDeAdyacencia.setText("");
                textoMatrizDeAdyacencia.setText("");
                textoNDeAristas.setText("");
            }
        });


        comboBoxGrafosGuardados.setBounds(textoNDeAristas.getX(),textoNDeAristas.getY()+textoNDeAristas.getHeight()+40,250,20);
        comboBoxGrafosGuardados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grafoPrincipal = grafosAnteriores.get(comboBoxGrafosGuardados.getSelectedIndex());
                textoNDeAristas.setText(String.valueOf(grafoPrincipal.cuantasAristasHay()));
                textoListaDeAdyacencia.setText(grafoPrincipal.listaString());
                textoMatrizDeAdyacencia.setText(grafoPrincipal.matrizString());


                nDeGrafo = 0;
                int alturaFila = 150;
                eliminarGrafoAnterior();
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
                            nodosGraficos.add(nodoGrafico);

                        }
                    } finally {
                        graph.getModel().endUpdate();
                    }

                }
        });



        panelTextos.add(labelGrafosGuardados);
        labelGrafosGuardados.setBounds(comboBoxGrafosGuardados.getX(),comboBoxGrafosGuardados.getY()-25,150,20);
        labelGrafosGuardados.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        labelGrafosGuardados.setForeground(Color.black);

        botonGuardarGrafo.setBounds(botonEliminarGrafo.getX()+170,15,150,20);
        botonGuardarGrafo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        botonGuardarGrafo.setBackground(Color.white);
        botonGuardarGrafo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean hayIguales = false;
                if (grafoPrincipal != null) {
                    for (int i = 0 ;i < grafosAnteriores.size();i++) {
                        if (grafosAnteriores.get(i) == grafoPrincipal) {
                            hayIguales = true;
                        }
                    }
                    if (!hayIguales) {
                        grafosAnteriores.add(grafoPrincipal);
                        comboBoxGrafosGuardados.addItem(grafosAnteriores.getLast().toString());
                        guardarGrafosEnArchivo(grafosAnteriores,"grafos.txt");
                    }
                }

            }
        });
}


    public <T> void guardarGrafosEnArchivo(ArrayList<GrafoDirigidoAciclico> grafos, String nombreArchivo) {
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(nombreArchivo);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            oos.writeObject(grafos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Grafos guardados en " + nombreArchivo);
    }

    public <T> ArrayList<GrafoDirigidoAciclico> cargarGrafosDesdeArchivo(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (ArrayList<GrafoDirigidoAciclico>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los grafos: " + e.getMessage());
            return new ArrayList<>();
        }

    }

    public void eliminarGrafoAnterior() {
        if (graphComponent != null) {
            panelGrafos.remove(graphComponent);
            graphComponent = null;
        }

        if(grafoPrincipal != null) {
            grafoPrincipal.eliminarAristas();
        }

        graph = new mxGraph();

        graphComponent = new mxGraphComponent(graph);
        parent = graph.getDefaultParent();

        graphComponent.setBounds(0, 0, 1400, 750);
        graphComponent.getViewport().setBackground(new Color(243, 255, 207));
        panelGrafos.add(graphComponent);

        panelGrafos.revalidate();
        panelGrafos.repaint();

        parent = graph.getDefaultParent();
    }



}
