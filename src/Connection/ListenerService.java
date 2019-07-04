package Connection;

import Model.Main;

import java.io.IOException;
import java.io.ObjectInputStream;

import static Controller.setIP.objectIn;

public class ListenerService implements Runnable {
    public static Object result = null;
    private ObjectInputStream objectInputStream;

    public ListenerService(){
        this.objectInputStream =objectIn;
    }


    @Override
    public void run(){
        while(true){
            Object message = new Object();
            try {
                message = objectInputStream.readObject();
            } catch (IOException e) {

            } catch (ClassNotFoundException e) {

            }
            if(message != null)
               result = ClientMessageHandler.handle(message);
        }
    }
}
