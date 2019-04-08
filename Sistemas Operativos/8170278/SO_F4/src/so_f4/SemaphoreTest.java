import java.util.concurrent.*;
public class SemaphoreTest{
    static Semaphore s = new Semaphore(0);

    public void fun(final char c, final int r) throws Exception {
        new Thread(new Runnable(){
            public void run(){
                try{ 
                    System.out.println("acquire "+r);
                    s.acquire(r);
                    System.out.println(c+"_"+r);
                } catch(Exception e){ e.printStackTrace(); }
            }
        }).start();
        Thread.sleep(500);
    }

    public static void main(String[]args) throws Exception{
        SemaphoreTest f = new SemaphoreTest();

        f.fun('B',2);
        f.fun('F',6);
        f.fun('A',1);
        f.fun('C',3);
        f.fun('D',4);
        f.fun('E',5);

        while(s.hasQueuedThreads()){
            Thread.sleep(1000);
            System.out.println("release "+1+", available "+(s.availablePermits()+1));
            s.release(1);
        }
    }
}
