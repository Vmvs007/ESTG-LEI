/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package so_f3;

/**
 *
 * @author vmvs0
 */
public class SO_F3 implements Runnable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int count,i;
       count = Integer.parseInt(args[0]);
        Thread[] th =new Thread [count];
    
        for(i=0;i<count;i++){
            SO_F3 ex1= new SO_F3();
            th[i] = new Thread (ex1);
            th[i].start();
        }
}
   
    @Override
    public void run(){
        System.out.println("Eu sou uma thread");   
    }
}
