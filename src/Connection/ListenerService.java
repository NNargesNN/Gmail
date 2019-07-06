package Connection;

import java.io.IOException;
import java.io.ObjectInputStream;

import static Model.Main.WAIT;
import static Controller.setIP.objectIn;

public class ListenerService implements Runnable {
    public static Object result = null;
    private ObjectInputStream objectInputStream;


    public ListenerService() {

        this.objectInputStream = objectIn;
    }


    @Override
    public void run() {

        while (true) {
            Object message = new Object();
            try {
                message = objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
            }
            if (message != null) {
                result = ClientMessageHandler.handle(message);
                synchronized (WAIT){
                    WAIT.notifyAll();
                    System.out.println("notified");
                }
            }
        }
    }
}
