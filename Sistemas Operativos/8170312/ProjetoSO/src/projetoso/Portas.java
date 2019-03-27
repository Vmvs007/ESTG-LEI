/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author *Abílio - 8170054* | *Ricardo - 8170278* | *Vitor - 8170312*
 */
public class Portas implements Runnable {

    String command;
    SharedOBJ sh1;

    public Portas(SharedOBJ sh) {
        this.command = command;
        this.sh1 = sh;

    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        if (this.command == "F") {
            closeDoors();
        } else if (this.command == "A" && sh1.isMoving() == false) {
            openDoors();
        } else if (this.command == "AF") {
            closeDoors();
            System.out.println("Permits" + sh1.semP.availablePermits());
            try {

                sh1.semP.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Portas.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                sh1.semP.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Portas.class.getName()).log(Level.SEVERE, null, ex);
            }
            openDoorsAF();
        } 
    }

    public void openDoorsAF() {
        openDoors();
        sh1.semP.release();
    }

    public void openDoors() {
        sh1.setDoorsOpen(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Portas.class.getName()).log(Level.SEVERE, null, ex);
        }
        sh1.setDoorsOpen(false);

    }

    public synchronized void closeDoors() {
        sh1.setDoorsOpen(false);
        // notifyAll();
    }

}
