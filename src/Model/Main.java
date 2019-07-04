package Model;

import Common.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends Application {

//    public static User currentUser;
//    public static final int requestPort = 1379;
//    public static final String serverIP = "localhost";
//    public static Socket client;
//    public static ObjectOutputStream objectOut;
//    public static ObjectInputStream objectIn;
//
//    static {
//        try {
//            client = new Socket(serverIP , requestPort);
//            objectOut = new ObjectOutputStream(client.getOutputStream());
//            objectIn = new ObjectInputStream(client.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//

//    public static void initializeServices() {
//        Thread listenerThread = new Thread(new Connection.ListenerService(), "Listener Thread");
//        listenerThread.setDaemon(true);
//        listenerThread.start();
//    }
//
//
//    @Override
//    public void init(){
//        initializeServices();
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        new PageLoader().load("../View/ip.fxml");
    }

    @Override
    public void stop(){

    }


    public static void main(String[] args) {
        launch(args);
    }
}
