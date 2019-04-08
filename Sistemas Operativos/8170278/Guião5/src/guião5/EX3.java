/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guião5;

import java.util.concurrent.Semaphore;

/**
 *
 * @author ricar
 */
public class EX3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Semaphore sem=new Semaphore(0);
        Thread th1=new Thread(new Th1(sem),"Th1");
        Thread th2=new Thread(new Th2(sem),"Th2");
        
        th1.start();
        th2.start();
        
        try{
            th1.join();
            th2.join();
        }catch(InterruptedException e){};
    }
    
}
