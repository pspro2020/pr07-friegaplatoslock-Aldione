import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;

public class Organizador implements Runnable{


    private final Bandeja bandeja;

    public Organizador(Bandeja bandeja) {
        this.bandeja = bandeja;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                alacena(bandeja.cogerPlatoSeco());
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private Plato alacena(Plato plato) throws InterruptedException {
        Thread.sleep((new Random().nextInt(2-1)+1) * 1000);
        LocalTime hora = LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        System.out.printf("%s - El %s ha colocado el plato %d en la alacena\n", formato.format(hora), this.getClass().getSimpleName(), plato.getPlatoID());
        return plato;
    }
}
