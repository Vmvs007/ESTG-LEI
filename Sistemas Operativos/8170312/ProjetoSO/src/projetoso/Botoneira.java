/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author *Abílio - 8170054* | *Ricardo - 8170278* | *Vitor - 8170312*
 */
public class Botoneira implements Runnable {

    private int pisoS;
    private JButton[] aux;
    private SharedOBJ sh1;
    private Motor mt;

    public Botoneira(SharedOBJ sh) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        sh1 = sh;
        FileManager fm = new FileManager();
        mt = new Motor(sh1);
        int i = 0, piso, gridHelper;
        ProjetoSO act = new ProjetoSO(sh1, mt);

        //Ler ficheiro com o número de andares e peso máximo
        fm.readFile("elevador.txt");
        sh1.setMaxCarga(fm.getCarga());
        JPanel panela = new JPanel();
        Dimension panelDimension = new Dimension(300, 300);
        if (fm.getPisos() % 2 == 0) {
            gridHelper = fm.getPisos() / 2;
        } else {
            gridHelper = (fm.getPisos() + 1) / 2;
        }
        panela.setLayout(new GridLayout(3 + gridHelper, 2));

        panela.setSize(panelDimension);
        panela.setMaximumSize(panelDimension);
        Dimension frameDimension = new Dimension(400, 300);

        JFrame elevador = new JFrame("Elevador");

        //Definir tamanho da janela e localização inicial
        elevador.setSize(frameDimension);
        elevador.setMaximumSize(frameDimension);
        elevador.setLocation(width / 2 - 200, height / 2 - 150);

        // Construtor do label que apresenta o andar atual
        JLabel elevadorSign = new JLabel("");
        elevadorSign.setHorizontalAlignment(SwingConstants.LEFT);
        panela.add(elevadorSign);
        elevadorSign.setSize(30, 70);
        JLabel andarMostrar = new JLabel("Bem-vindo ao elevador pressione um botão com o andar que pretende, e de seguida, feche a porta");
        panela.add(andarMostrar);
        JButton[] bt = new JButton[fm.getPisos()];
        JButton fechar = new JButton("Fechar");
        fechar.setActionCommand("F");
        fechar.addActionListener(act);
        JButton abrir = new JButton("Abrir");
        JButton entrar = new JButton("Entrar");
        JButton sair = new JButton("Sair");
        entrar.setActionCommand("EN");
        sair.setActionCommand("SA");
        entrar.addActionListener(act);
        sair.addActionListener(act);
        abrir.setActionCommand("A");
        abrir.addActionListener(act);
        Dimension buttonDimension = new Dimension(10, 10);
        JLabel clear = new JLabel(" ");
        JLabel clear2 = new JLabel(" ");
        JLabel clear3 = new JLabel(" ");

        //Ciclo que acrecenta botões dependendo do número de andares definido 
        while (i < fm.getPisos()) {
            piso = i;
            bt[i] = new JButton("" + piso);
            bt[i].setSize(10, 10);
            bt[i].setMaximumSize(buttonDimension);
            bt[i].setActionCommand("" + i);
            bt[i].addActionListener(act);
            panela.add(bt[i]);
            i++;
        }
        if (fm.getPisos() % 2 == 0) {
            panela.add(clear);
            panela.add(clear2);
        } else {
            panela.add(clear);
            panela.add(clear2);
            panela.add(clear3);
        }
        
        panela.add(entrar);
        panela.add(sair);
        panela.add(fechar);
        panela.add(abrir);
        elevador.add(panela);
        elevador.setBackground(Color.GREEN);
        elevador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        elevador.setVisible(true);

        System.out.println(fm.getCarga());
        System.out.println(fm.getPisos());
        aux = bt;

    }

    public int getPisoS() {
        return this.pisoS;
    }

    @Override
    public void run() {
        System.out.println("Running");
    }

}
