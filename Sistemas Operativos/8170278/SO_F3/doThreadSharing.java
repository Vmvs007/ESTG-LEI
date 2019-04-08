public class doThreadSharing {
		
    public static void main(String args[]) {
		SharedObj share = new SharedObj();
		Thread tarefa = new Thread(new Run(share),"Printer_thread");
		tarefa.start();		
		for(int i=0; i<10; i++){
			Thread tarefa2 = new Thread(new Run2(share,i));
			tarefa2.setName("Numbered_thread_"+i);
			tarefa2.start();
			}
		try{
		Thread.sleep(10000);
		tarefa.interrupt();
		} catch (InterruptedException e) {}
	}
}
