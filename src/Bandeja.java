import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bandeja {

    private ArrayList<Plato> bandejaMojado = new ArrayList<Plato>();
    private ArrayList<Plato> bandejaSeco = new ArrayList<Plato>();
    ReentrantLock locker = new ReentrantLock();
    Condition vacio = locker.newCondition();



    public void sueltaPlatoMojado(Plato platoId) {
        locker.lock();
        try{
            bandejaMojado.add(platoId);
            vacio.signalAll();
        }finally {
            locker.unlock();
        }

    }

    public Plato cogerPlatoMojado() throws InterruptedException {
        locker.lock();
        try{
            while(bandejaMojado.isEmpty()){
                vacio.await();
            }
            return bandejaMojado.remove(0);
        }finally {
            locker.unlock();
        }

    }

    public void sueltaPlatoSeco(Plato platoId) {
        locker.lock();
        try{
            bandejaSeco.add(platoId);
            vacio.signalAll();
        }finally {
            locker.unlock();
        }

    }

    public Plato cogerPlatoSeco() throws InterruptedException {
        locker.lock();
        try{
            while(bandejaSeco.isEmpty()){
                vacio.await();
            }
            return bandejaSeco.remove(0);
        }finally {
            locker.unlock();
        }

    }


}