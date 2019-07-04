package Controller;

import Common.User;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class setIP {
    @FXML
    public TextField serverIPTextField;
    @FXML
    public Button ConnectButton;
    public static User currentUser;
    public static final int requestPort = 1379;
    public static String serverIP;
    public static Socket client;
    public static ObjectOutputStream objectOut;
    public static ObjectInputStream objectIn;


    public void Connect(ActionEvent actionEvent) throws IOException {

        if (!serverIPTextField.getText().isEmpty()) {
            serverIP = serverIPTextField.getText();
            try {
                client = new Socket(serverIP, requestPort);
                objectOut = new ObjectOutputStream(client.getOutputStream());
                objectIn = new ObjectInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            init();
            new PageLoader().load("../View/SignIn.fxml");

        }
    }


    public  void initializeServices() {

        Thread listenerThread = new Thread(new Connection.ListenerService(), "Listener Thread");
        listenerThread.setDaemon(true);
        listenerThread.start();
    }


    public void init() {

        initializeServices();
    }
}