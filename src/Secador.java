import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;

public class Secador implements Runnable {


    private final Bandeja bandeja;

    public Secador(Bandeja bandeja) {
        this.bandeja = bandeja;

    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                bandeja.sueltaPlatoSeco(secarPlato(bandeja.cogerPlatoMojado()));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private Plato secarPlato(Plato plato) throws InterruptedException {
        Thread.sleep((new Random().nextInt(3-1)+1) * 1000);
        LocalTime hora = LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        System.out.printf("%s - El %s ha secado el plato %d\n", formato.format(hora), this.getClass().getSimpleName(), plato.getPlatoID());
        return plato;
    }
}