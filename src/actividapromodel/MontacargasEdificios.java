/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionpromodel;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author hp
 */


public class MontacargasEdificios extends JFrame {

    private JPanel contentPane;
    private JLabel lblEdificio1, lblEdificio2, lblMontacargas;
    private JTextField txtTiempoEspera;
    private int tiempoEspera = 10000; // Tiempo de espera inicial en milisegundos (10 segundos)
    private boolean moverDerecha = true; // Indica la dirección del movimiento
    private int anchoPanel = 1000; // Ancho del panel
    private int altoPanel = 600; // Alto del panel

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MontacargasEdificios frame = new MontacargasEdificios();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MontacargasEdificios() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, anchoPanel, altoPanel);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelEdificios = new JPanel();
        contentPane.add(panelEdificios, BorderLayout.CENTER);
        panelEdificios.setLayout(null);

        lblEdificio1 = new JLabel("");
        lblEdificio1.setIcon(new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\SimulacionPromodel\\src\\simulacionpromodel\\edificio11.jpeg"));
        lblEdificio1.setBounds(30, 41, anchoPanel / 4, altoPanel - 22);
        panelEdificios.add(lblEdificio1);

        lblEdificio2 = new JLabel("");
        lblEdificio2.setIcon(new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\SimulacionPromodel\\src\\simulacionpromodel\\edificio22.jpg"));
        lblEdificio2.setBounds(anchoPanel - (anchoPanel / 4) - 30, 41, anchoPanel / 4, altoPanel - 22);
        panelEdificios.add(lblEdificio2);

        lblMontacargas = new JLabel("");
        lblMontacargas.setIcon(new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\SimulacionPromodel\\src\\simulacionpromodel\\montacargas333.jpg"));
        lblMontacargas.setHorizontalAlignment(SwingConstants.CENTER);
        lblMontacargas.setBounds((anchoPanel / 4) + 10, altoPanel / 2 - 50, 100, 120);
        panelEdificios.add(lblMontacargas);

        JPanel panelControl = new JPanel();
        contentPane.add(panelControl, BorderLayout.SOUTH);

        txtTiempoEspera = new JTextField();
        txtTiempoEspera.setText(String.valueOf(tiempoEspera / 1000));
        txtTiempoEspera.setPreferredSize(new Dimension(60, 24));
        panelControl.add(txtTiempoEspera);

        txtTiempoEspera.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tiempoEspera = Integer.parseInt(txtTiempoEspera.getText()) * 1000;
                } catch (NumberFormatException ex) {
                    // Manejar entrada no válida
                }
            }
        });

        Thread hiloMontacargas = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    moverMontacargas();
                    try {
                        Thread.sleep(50); // Pausa para simular la animación del movimiento
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread hiloTiempo = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(tiempoEspera);
                        moverDerecha = !moverDerecha; // Cambiar la dirección del movimiento
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        hiloMontacargas.start();
        hiloTiempo.start();
    }

    private void moverMontacargas() {
        int posicionX = lblMontacargas.getX();
        if (moverDerecha) {
            if (posicionX < anchoPanel - (anchoPanel / 4) - 60) {
                lblMontacargas.setLocation(posicionX + 10, lblMontacargas.getY());
            }
        } else {
            if (posicionX > (anchoPanel / 4) + 10) {
                lblMontacargas.setLocation(posicionX - 10, lblMontacargas.getY());
            }
        }
    }
}