package so_f3;

public class HelloThread extends Thread {

    public void run() {
        System.out.println("Sou uma alface do Lidl!");
    }

    public static void main(String args[]) {
        (new HelloThread()).start();
    }

}
