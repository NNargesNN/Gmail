package Model;

import Common.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends Application {
    public static final Object WAIT=new Object();


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
