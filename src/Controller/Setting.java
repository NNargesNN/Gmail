package Controller;

import Common.Message;
import Common.MessageType;
import Common.User;
import Connection.ListenerService;
import Model.Main;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

import static Controller.setIP.currentUser;
import static Controller.setIP.objectOut;
import static Model.Main.WAIT;
import static java.lang.Thread.sleep;

public class Setting {
    public Button BackButton;
    public Button SaveChangesButton;
    public TextField PassTextField;
    public TextField FirstNameTextField;
    public TextField LastNameTextField;
    public Button ChangeImageButton;
    public ImageView ImageView;
    public TextField PhoneNoTextField;

public void initialize(){
    FirstNameTextField.setText(setIP.currentUser.getName());
    LastNameTextField.setText(setIP.currentUser.getLastName());
    PassTextField.setText(setIP.currentUser.getPassword());
    PhoneNoTextField.setText(String.valueOf(currentUser.getPhoneNumber()));
}
    public void ChangeImage(ActionEvent actionEvent) {
//file chooser
        //berize too ye addressi
//        age save ro zad berize too currrent
    }


    public void SaveChanges(ActionEvent actionEvent) throws IOException, InterruptedException {


        currentUser.setName(FirstNameTextField.getText());
        currentUser.setLastName(LastNameTextField.getText());
        currentUser.setPassword(PassTextField.getText());
        User newUser = new User(currentUser.getUsername() , currentUser.getPassword());
       // newUser.setImage(currentUser.getImage());
        newUser.setName(currentUser.getName());
        newUser.setLastName(currentUser.getLastName());
        newUser.setAge(currentUser.getAge());
        newUser.setPhoneNumber(currentUser.getPhoneNumber());
        newUser.setGender(currentUser.getGender());
        newUser.setMails(currentUser.getMails());
        objectOut.writeObject(new Message(MessageType.Change , newUser));
        objectOut.flush();

        synchronized (WAIT){
            WAIT.notifyAll();
        }
        currentUser=(User) ListenerService.result;
        new PageLoader().load("../View/main.fxml");

    }


    public void Back(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("../View/main.fxml");
    }
}
