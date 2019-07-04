package Controller;

import Common.Message;
import Common.MessageType;
import Model.Main;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

import static Controller.setIP.currentUser;
import static Controller.setIP.objectOut;

import static java.lang.Thread.sleep;

public class Setting {
    public Button BackButton;
    public Button SaveChangesButton;
    public TextField PassTextField;
    public TextField FirstNameTextField;
    public TextField LastNameTextField;
    public Button ChangeImageButton;
    public ImageView ImgeView;
    public TextField PhoneNoTextField;


    public void ChangeImage(ActionEvent actionEvent) {
//file chooser
        //berize too ye addressi
//        age save ro zad berize too currrent
    }


    public void SaveChanges(ActionEvent actionEvent) throws IOException, InterruptedException {

        if (PassTextField.getText() != null && !PassTextField.getText().isEmpty()) {
            currentUser.setPassword(PassTextField.getText());
        }
        if (FirstNameTextField.getText() != null && !FirstNameTextField.getText().isEmpty()) {
            currentUser.setName(FirstNameTextField.getText());
        }
        if (LastNameTextField.getText() != null && !LastNameTextField.getText().isEmpty()) {
            currentUser.setLastName(LastNameTextField.getText());
        }
        if (PhoneNoTextField.getText() != null && !PhoneNoTextField.getText().isEmpty()) {
            currentUser.setPhoneNumber(Integer.parseInt(PhoneNoTextField.getText()));
        }
        System.out.println(currentUser);


        objectOut.writeObject(new Message(MessageType.Change , currentUser));
        objectOut.flush();
        sleep(1000);
        new PageLoader().load("../View/main.fxml");

    }


    public void Back(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("../View/main.fxml");
    }
}
