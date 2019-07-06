package Controller;

import Common.*;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

import static Connection.ListenerService.result;
import static Controller.setIP.*;
import static java.lang.Thread.sleep;

public class SignInController {
    @FXML
    public TextField usernameTextField, passwordTextField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Label wrongInformation, emptyLabel;


    public void visibleAction(MouseEvent mouseEvent) {

        if (passwordField.isVisible()) {
            passwordTextField.setText(passwordField.getText());
            passwordField.setVisible(false);
            passwordTextField.setVisible(true);
        } else if (passwordTextField.isVisible()) {
            passwordField.setText(passwordTextField.getText());
            passwordTextField.setVisible(false);
            passwordField.setVisible(true);
        }

    }


    public void NextAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException, InterruptedException {
        OutBox=new ArrayList<>();
        if (usernameTextField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            wrongInformation.setVisible(false);
            emptyLabel.setVisible(true);
        } else {
            currentUser = new User(usernameTextField.getText(), passwordField.getText());
            // System.out.println("current "+/*currentUser*/user);
            objectOut.writeObject(new Message(MessageType.SignIn, currentUser));
            objectOut.flush();
           // sleep(1000);
            synchronized (Model.Main.WAIT){
                Model.Main.WAIT.wait();
            }
            System.out.println("after wait");
            if (result instanceof User) {
                currentUser = (User) result;
                Main.mailType = MailType.Inbox;
                System.out.println("here1");
                //added
                objectOut.writeObject(new Message(MessageType.Refresh, currentUser));
                synchronized (Model.Main.WAIT) {
                    Model.Main.WAIT.wait();
                }
                currentUser.mails = (ArrayList<UserMail>) result;
                /////////
                showMail.userMails=currentUser.mails;
                new PageLoader().load("../View/main.fxml");
                System.out.println("signed in :) ");
            } else if (!((boolean) result))
                wrongInformation.setVisible(true);
            System.out.println("here2");
        }
    }

    public void createAccountAction(MouseEvent mouseEvent) throws IOException {

        new PageLoader().load("../View/SignUp.fxml");
    }
}
