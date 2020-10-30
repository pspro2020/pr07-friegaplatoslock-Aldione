public class Main {

    public static void main(String[] args) {
        Thread thread1, thread2, thread3;
        Bandeja bandeja = new Bandeja();
        Fregador fregador = new Fregador(bandeja);
        Secador secador = new Secador(bandeja);
        Organizador organizador = new Organizador(bandeja);

        thread1 = new Thread(fregador);
        thread2 = new Thread(secador);
        thread3 = new Thread(organizador);

        threadStarts(thread1, thread2, thread3);
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            System.out.println("Los hilos han sido interrumpidos!");
        }
        threadInterrupted(thread1, thread2, thread3);
        System.out.println("Cumpleaños feliz");


    }

    private static void threadInterrupted(Thread t1, Thread t2, Thread t3) {

        t1.interrupt();
        t2.interrupt();
        t3.interrupt();

    }

    private static void threadStarts(Thread t1, Thread t2, Thread t3) {

        t1.start();
        t2.start();
        t3.start();

    }


}