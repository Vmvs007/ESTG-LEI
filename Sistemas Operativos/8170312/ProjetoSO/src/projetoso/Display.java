/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

/**
 *
 * @author Ricardo
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display implements Runnable {

    private SharedOBJ sh1;

    public Display(SharedOBJ sh) {
        this.sh1 = sh;
    }

    @Override
    public void run() {

        BufferedImage auxEleO = null;
        BufferedImage auxEleC = null;
        JLabel img = new JLabel();
        JFrame teste = new JFrame();
        JFrame portas = new JFrame();
        teste.setSize(250, 100);
        portas.setSize(250, 300);
        
        try {
            auxEleO = ImageIO.read(new File("ElevatorOpen.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            auxEleC = ImageIO.read(new File("ElevatorClosed.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image auxEleO2 = auxEleO.getScaledInstance(portas.getWidth(), portas.getHeight(),
                Image.SCALE_SMOOTH);
        Image auxEleC2 = auxEleC.getScaledInstance(portas.getWidth(), portas.getHeight(),
                Image.SCALE_SMOOTH);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        teste.getContentPane().setBackground(Color.black);
        JPanel panel = new JPanel();
        GridLayout grid = new GridLayout(2, 2);
        panel.setLayout(grid);
        teste.add(panel);
        JLabel maxCarga = new JLabel(" Carga Máxima: " + sh1.getMaxCarga());
        JLabel carga = new JLabel("" + sh1.getCarga());
        JLabel dis = new JLabel("" + sh1.getCurrentFloor());
        // JLabel door = new JLabel("Teste");
        
        panel.add(dis);
        panel.add(carga);
        panel.add(maxCarga);
        // panel.add(door);
        portas.add(img);
        teste.setLocation(width / 2 + 185, height / 2 - 240);
        portas.setLocation(width / 2 + 185, height / 2 - 150);
        dis.setBounds(0, 0, 50, 50);
        dis.setForeground(Color.green);
        //  door.setBounds(100, 100, 50, 50);
        portas.setVisible(true);
        teste.setVisible(true);

        ImageIcon elevatorOpen = new ImageIcon(auxEleO2);
        ImageIcon elevatorClosed = new ImageIcon(auxEleC2);

        while (true) {
            
            carga.setText(" Carga: " + sh1.getCarga());
            if(sh1.getCarga() > sh1.getMaxCarga()){
                carga.setForeground(Color.red);
            }else{
                carga.setForeground(Color.green);
            }
            dis.setText(" Andar Atual: " + sh1.getCurrentFloor());
            // door.setText("  Estado da Porta: " + sh1.isDoorsOpen());
            if (sh1.isDoorsOpen() == true) {
                img.setIcon(elevatorOpen);
            } else {
                img.setIcon(elevatorClosed);
            }

            if (Thread.interrupted()) {
                return;
            }
        }

    }

}
