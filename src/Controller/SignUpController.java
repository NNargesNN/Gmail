package Controller;

import Common.Message;
import Common.MessageType;
import Common.User;
import Model.Main;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import static Connection.ListenerService.result;
import static Controller.setIP.*;
import static java.lang.Thread.sleep;

import java.io.IOException;
import java.util.Calendar;

public class SignUpController {
    @FXML
    public TextField firstname, lastname, username, confirmTextField, passwordTextField;
    @FXML
    public PasswordField password, confirm;
    @FXML
    public Label wrongUsernameLabel, notMatched, ageLimit, notFilled, passwordError, takenUsername, cantSignUpLabel;
    @FXML
    public DatePicker birthdate;


    public void nextAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException, InterruptedException {

        int age = Calendar.getInstance().get(Calendar.YEAR) - birthdate.getValue().getYear();
        //empty fields
        if (firstname.getText().isEmpty() || lastname.getText().isEmpty() || username.getText().isEmpty() || (passwordTextField.isVisible() && (passwordTextField.getText().isEmpty() || confirmTextField.getText().isEmpty())) || (password.isVisible() && (password.getText().isEmpty() || confirm.getText().isEmpty()))) {
            notFilled.setVisible(true);
            notMatched.setVisible(false);
            wrongUsernameLabel.setVisible(false);
            ageLimit.setVisible(false);
            passwordError.setVisible(false);
        }
        //password error
        else if (passwordTextField.getText().length() < 8 || !passwordTextField.getText().matches("[a-zA-Z0-9]+")) {
            passwordError.setVisible(true);
            wrongUsernameLabel.setVisible(false);
            notMatched.setVisible(false);
            ageLimit.setVisible(false);
            notFilled.setVisible(false);
            takenUsername.setVisible(false);
        }
        //username error
        else if (!username.getText().matches("[a-zA-Z0-9.]+")) {
            notMatched.setVisible(false);
            passwordError.setVisible(false);
            wrongUsernameLabel.setVisible(true);
            ageLimit.setVisible(false);
            notFilled.setVisible(false);
            takenUsername.setVisible(false);
        }
        //to see if passwordText and confirmation matches or not
        else if ((passwordTextField.isVisible() && !passwordTextField.getText().equals(confirmTextField.getText())) ||
                (password.isVisible() && !password.getText().equals(confirm.getText()))) {
            notMatched.setVisible(true);
            passwordError.setVisible(false);
            wrongUsernameLabel.setVisible(false);
            ageLimit.setVisible(false);
            notFilled.setVisible(false);
            takenUsername.setVisible(false);
        }
        //age limit check
        else if (Calendar.getInstance().get(Calendar.YEAR) - birthdate.getValue().getYear() <= 13) {
            notMatched.setVisible(false);
            passwordError.setVisible(false);
            wrongUsernameLabel.setVisible(false);
            ageLimit.setVisible(true);
            notFilled.setVisible(false);
            takenUsername.setVisible(false);
        } else {
            currentUser = new User(username.getText(), passwordTextField.getText());
            currentUser.setAge(age);
            currentUser.setName(firstname.getText());
            currentUser.setLastName(lastname.getText());
            objectOut.writeObject(new Message(MessageType.SignUp1,  currentUser));
            objectOut.flush();
            synchronized (Main.WAIT){
                Main.WAIT.wait();
            }
          //  sleep(1000);
            System.out.println("user wants to sign up1 " + result);
            if (result != null && (boolean) result)
                new PageLoader().load("../View/SignUpNext.fxml");
            else if (result != null && !(boolean) result)
                cantSignUpLabel.setVisible(true);

        }
    }


    public void visibleAction(MouseEvent mouseEvent) {

        if (password.isVisible() && confirm.isVisible()) {
            passwordTextField.setText(password.getText());
            confirmTextField.setText(confirm.getText());
            password.setVisible(false);
            confirm.setVisible(false);
            passwordTextField.setVisible(true);
            confirmTextField.setVisible(true);
        } else if (passwordTextField.isVisible() && confirmTextField.isVisible()) {
            password.setText(passwordTextField.getText());
            confirm.setText(confirmTextField.getText());
            password.setVisible(true);
            confirm.setVisible(true);
            passwordTextField.setVisible(false);
            confirmTextField.setVisible(false);
        }

    }


    public void signinAction(MouseEvent mouseEvent) throws IOException {

        new PageLoader().load("../View/SignIn.fxml");
    }
}
