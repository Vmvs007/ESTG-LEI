/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author *Abílio - 8170054* | *Ricardo - 8170278* | *Vitor - 8170312*
 */
public class Motor implements Runnable {

    private int moving = 0; // 1 = mover para cima || -1 = mover para baixo
    private SharedOBJ sh1;
    Semaphore sem;
    private int pisoS;
    PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

    public Motor(SharedOBJ sh) {
        this.sh1 = sh;
        this.sem = new Semaphore(1, true);
    }

    @Override
    public synchronized void run() {

        try {
            sem.acquire();
            sh1.setPisoS(queue.poll());
            System.out.println("Entrei:" + sh1.getPisoS());

        } catch (InterruptedException ex) {
            Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (sh1.isDoorsOpen() || sh1.getCarga() > sh1.getMaxCarga()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        sh1.setMoving(true);
        if (sh1.getPisoS() > sh1.getCurrentFloor()) {
            movingUp();
        } else if (sh1.getPisoS() < sh1.getCurrentFloor()) {
            movingDown();
        } else {
            sem.release();

        }

    }

    public synchronized void movingUp() {
        while (sh1.getPisoS() != sh1.getCurrentFloor()) {
            System.out.println(sh1.getCurrentFloor());
            sh1.setCurrentFloor(sh1.getCurrentFloor() + 1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sh1.semP.release();
        sh1.setMoving(false);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, null, ex);
        }

        sem.release();

    }

    public void sendPisoS(PriorityQueue pisoS) {
        this.queue = pisoS;
    }

    public synchronized void movingDown() {
        while (sh1.getPisoS() != sh1.getCurrentFloor()) {
            System.out.println(sh1.getCurrentFloor());
            sh1.setCurrentFloor(sh1.getCurrentFloor() - 1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sh1.setMoving(false);
        sh1.semP.release();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, null, ex);
        }

        sem.release();

    }

}
