/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicOptionPaneUI;

/**
 *
 * @author *Abílio - 8170054* | *Ricardo - 8170278* | *Vitor - 8170312*
 */
public class ProjetoSO implements ActionListener {

    FileManager fm;
    private int pisoSelecionado;
    SharedOBJ sh1;
    private Motor mt;
    Portas pt;
    PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

    public ProjetoSO(SharedOBJ sh, Motor mt) {
        sh1 = sh;
        this.mt = mt;
        pt = new Portas(sh1);
        fm = new FileManager();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Semaphore sem = new Semaphore(1);
        boolean teste = true;
        SharedOBJ sh = new SharedOBJ();
        new Thread(new Display(sh)).start();
        new Thread(new Botoneira(sh)).start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int aux;

        if (e.getActionCommand() == "A") {
            pt.setCommand("A");
            new Thread(pt).start();

        } else if (e.getActionCommand() == "F") {
            pt.setCommand("F");
            new Thread(pt).start();
        } else if (e.getActionCommand() == "EN") {
            if (sh1.isMoving() == false) {
                sh1.setCarga(60);
            }

        } else if (e.getActionCommand() == "SA") {
            if (sh1.isMoving() == false) {
                sh1.setCarga(-60);
            }

        } else {

            System.out.println("Andar "
                    + ": " + e.getActionCommand());
            queue.add(Integer.parseInt(e.getActionCommand()));
            startMotor();

        }
    }

    public void startMotor() {

        pt.setCommand("AF");
        new Thread(pt).start();

        new Thread(mt).start();
        mt.sendPisoS(queue);
        fm.writeToFile(queue.peek(), sh1.getCarga());

    }

}
