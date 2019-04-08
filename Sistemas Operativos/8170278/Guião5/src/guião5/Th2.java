/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guião5;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author ricar
 */
public class Th2 implements Runnable {
    final private int MAX=10;
    private Semaphore sem;

    public Th2(Semaphore sem) {
        this.sem=sem;
    }
    public void run(){
        try{
            sem.acquire();
            
            Random gerador=new Random();
            String myname=Thread.currentThread().getName();
            
            System.out.println("["+myname+"]"+gerador.nextInt(MAX));
        }catch(InterruptedException iex){}
    }
    
}
