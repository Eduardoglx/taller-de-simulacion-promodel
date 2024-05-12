
package actividapromodel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author hp
 */

public class SimulacionPiezasDestinos extends JFrame {
    private Timer timer;
    private int piezasProducidas = 0;
    private int numPiezas = 1000;
    private int animationDelay = 50;
//
    public JLabel piezasProducidasLabel;
    public JPanel animationPanel;
    //public JProgressBar progressBar;
    public ArrayList<Pieza> piezas;
//
    private long tiempoInicial;
    private long tiempoFinal;

    class Pieza {
        int x;
        int y;
        int destino;
        ImageIcon imagen;
        int x1;
        int x2;

        public Pieza(int x1, int x2, int y, int destino, ImageIcon imagen) {
            this.x1 = x1;
            this.x2 = x2;
            this.x = x1;
            this.y = y;
            this.destino = destino;
            this.imagen = imagen;
        }

        public void move() {
            if (x < x2) {
                x += 10;
            } else {
                x = x1;

                Random rand = new Random();
                double probabilidad = rand.nextDouble();

                if (destino == 1) {
                    if (probabilidad < 0.4) {
                        destino = 2;
                    } else {
                        destino = 4;
                    }
                } else if (destino == 2) {
                    if (probabilidad < 0.2) {
                        destino = 3;
                    } else {
                        destino = 4;
                    }
                } else if (destino == 3) {
                    if (probabilidad < 0.5) {
                        destino = 4;
                    } else {
                        destino = 2;
                    }
                }
            }
        }
    }

    public SimulacionPiezasDestinos() {
        setTitle("Ejercicio-11 Simulación de Producción de Piezas");
        setSize(1150, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        piezasProducidasLabel = new JLabel("Piezas Producidas: 0");
        //progressBar = new JProgressBar(0, numPiezas);
        piezas = new ArrayList<>();
        animationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Cola1.png").getImage(), 200, 50, null);
                g.drawImage(new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Imagen4.jpg").getImage(), 350, 50, null);
                g.drawImage(new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Cola2.png").getImage(), 500, 50, null);
                g.drawImage(new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Imagen5.jpg").getImage(), 650, 50, null);
                g.drawImage(new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Cola3.png").getImage(), 800, 50, null);
                g.drawImage(new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Imagen6.jpg").getImage(), 950, 50, null);
                g.drawImage(new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Cola4.png").getImage(), 500, 200, null);
                g.drawImage(new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Imagen7.jpg").getImage(), 650, 200, null);

                for (Pieza pieza : piezas) {
                    g.drawImage(pieza.imagen.getImage(), pieza.x, pieza.y, pieza.imagen.getIconWidth() / 2, pieza.imagen.getIconHeight() / 2, null);
                }
            }
        };
//
        /*animationPanel.setPreferredSize(new Dimension(800, 200));
        JTextField cantidadPiezasField = new JTextField(String.valueOf(numPiezas), 10);
        JButton startButton = new JButton("Iniciar Simulación");
        
        //Dimension dimension = new Dimension(150, 30);
       // startButton.setPreferredSize(dimension);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(piezasProducidasLabel);
        panel.add(progressBar);
        panel.add(new JLabel("Cantidad de Piezas a Producir:"));
        panel.add(cantidadPiezasField);
        panel.add(startButton);

        add(panel, BorderLayout.NORTH);
        add(animationPanel, BorderLayout.CENTER);*/
//
        JTextField cantidadPiezasField = new JTextField(String.valueOf(numPiezas), 10);
        JButton startButton = new JButton("Iniciar Simulación");

        // Establecer las nuevas dimensiones del botón
        Dimension buttonDimension = new Dimension(140, 30);
        startButton.setPreferredSize(buttonDimension);

        // Establecer las nuevas dimensiones del campo de texto
        Dimension textFieldDimension = new Dimension(100, 30);
        cantidadPiezasField.setPreferredSize(textFieldDimension);

        /*JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(piezasProducidasLabel);
        panel.add(progressBar);
        panel.add(new JLabel("Cantidad de Piezas a Producir:"));
        panel.add(cantidadPiezasField);
        panel.add(startButton);

        add(panel, BorderLayout.NORTH);
        add(animationPanel, BorderLayout.CENTER);*/
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Utilizar FlowLayout
        panel.add(piezasProducidasLabel);
        //panel.add(progressBar);
        panel.add(new JLabel("Cantidad de Piezas a Producir:"));
        panel.add(cantidadPiezasField);
        panel.add(startButton);

        add(panel, BorderLayout.NORTH);
        add(animationPanel, BorderLayout.CENTER);
//
        
        startButton.addActionListener(e -> {
            try {
                numPiezas = Integer.parseInt(cantidadPiezasField.getText());
                startSimulation();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para la cantidad de piezas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setupAnimation();
    }

    private void createPiezas() {
        ImageIcon imagen1 = new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Imagen1.jpg");
        ImageIcon imagen2 = new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Imagen2.jpg");
        ImageIcon imagen3 = new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Imagen3.jpg");

        piezas.add(new Pieza(200, 400, 50, 1, imagen1));
        piezas.add(new Pieza(500, 700, 50, 2, imagen2));
        piezas.add(new Pieza(800, 1000, 50, 3, imagen3));

        ImageIcon nuevaImagen1 = new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Imagen1.jpg");
        ImageIcon nuevaImagen2 = new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Imagen2.jpg");
        ImageIcon nuevaImagen3 = new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ActividaPromodel\\src\\actividapromodel\\Imagen3.jpg");

        piezas.add(new Pieza(600, 700, 200, 1, nuevaImagen1));
        piezas.add(new Pieza(550, 700, 200, 2, nuevaImagen2));
        piezas.add(new Pieza(500, 700, 200, 3, nuevaImagen3));
    }

    private void setupAnimation() {
        timer = new Timer(animationDelay, e -> {
            if (piezasProducidas < numPiezas) {
                for (Pieza pieza : piezas) {
                    pieza.move();
                }

                piezasProducidas++;
                piezasProducidasLabel.setText("Piezas Producidas: " + piezasProducidas);
                //progressBar.setValue(piezasProducidas);

                animationPanel.repaint();
            } else {
                tiempoFinal = System.currentTimeMillis();
                mostrarMetricas();
                ((Timer) e.getSource()).stop();
            }
        });

        timer.setInitialDelay(0);
    }

    private void mostrarMetricas() {
        long tiempoTotal = tiempoFinal - tiempoInicial;
        JOptionPane.showMessageDialog(null, "Tiempo total de simulación: " + tiempoTotal + " milisegundos", "Métricas", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimulacionPiezasDestinos simulacion = new SimulacionPiezasDestinos();
            simulacion.setVisible(true);
        });
    }

    public void startSimulation() {
        tiempoInicial = System.currentTimeMillis();
        piezasProducidas = 0;
        piezasProducidasLabel.setText("Piezas Producidas: 0");
        //progressBar.setValue(0);
        piezas.clear();
        createPiezas();
        timer.start();
    }
}