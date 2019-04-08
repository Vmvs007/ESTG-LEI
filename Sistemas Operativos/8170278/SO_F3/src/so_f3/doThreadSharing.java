package so_f3;

public class doThreadSharing {

    public static void main(String args[]) {
        SharedObj share = new SharedObj();
        Thread tarefa = new Thread(new Run(share), "Impressora de alfaces");
        tarefa.start();
        for (int i = 0; i < 10; i++) {
            Thread tarefa2 = new Thread(new Run2(share, i));
            tarefa2.setName("[Th" + i + "] " + "Eu sou uma alface do Lidl");
            tarefa2.start();
        }
        try {
            Thread.sleep(10000);
            tarefa.interrupt();
        } catch (InterruptedException e) {
        }
    }
}
