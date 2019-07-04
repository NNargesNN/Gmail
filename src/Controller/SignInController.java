package Controller;

import Common.Message;
import Common.MessageType;
import Common.User;
import Model.Main;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static Connection.ListenerService.result;
import static Controller.setIP.currentUser;
import static Controller.setIP.objectOut;

import static java.lang.Thread.sleep;


public class SignInController {

    @FXML
    public TextField usernameTextField , passwordTextField;
    @FXML
    public PasswordField  passwordField;
    @FXML
    public Label wrongInformation , emptyLabel;


    public void visibleAction(MouseEvent mouseEvent) {
        if (passwordField.isVisible()){
            passwordTextField.setText(passwordField.getText());
            passwordField.setVisible(false);
            passwordTextField.setVisible(true);
        }
        else if(passwordTextField.isVisible()){
            passwordField.setText(passwordTextField.getText());
            passwordTextField.setVisible(false);
            passwordField.setVisible(true);
        }

    }

    public void NextAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException, InterruptedException {
        if(usernameTextField.getText().isEmpty() || passwordField.getText().isEmpty()){
            wrongInformation.setVisible(false);
            emptyLabel.setVisible(true);
        }
        else{
            currentUser = new User(usernameTextField.getText() , passwordField.getText());
            objectOut.writeObject(new Message(MessageType.SignIn , currentUser));
            objectOut.flush();
            sleep(1000);
            if(result instanceof User){
                currentUser = (User) result;
                new PageLoader().load("../View/main.fxml");
                System.out.println("Hello");
            }

            else if(!(boolean) result)
                wrongInformation.setVisible(true);
        }

    }

    public void createAccountAction(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("../View/SignUp.fxml");
    }
}
