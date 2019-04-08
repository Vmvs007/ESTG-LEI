import java.util.concurrent.*;

public class Current implements Runnable {
	Semaphore s;
	int n;
	public Current (Semaphore sem,int i) {s=sem;n=i;}	
    public void run() {
		String myname = Thread.currentThread().getName();
		System.out.println("["+myname+"] Inicio da thread");
		try{
			Thread.sleep(n*1000);
		} catch (InterruptedException iex){}	
		s.release();
		System.out.println("["+myname+"] Fim da thread");
	}
    public static void main(String args[]) {
		Semaphore sem = new Semaphore(5);
		for (int i=0; i<10; i++) {
			try {
				sem.acquire();
			} catch (InterruptedException iex){}	
			Thread th = new Thread(new Current(sem,i),"Th"+i);
			th.start();
		}		
    }
}
