package Controller;

import Common.*;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class showSingleMail {
    public Button forwardButton;
    public Button deleteButton;
    public Button backButton;
    public Label MailText;
    public static Gmail gmail;


    public void initialize() {

        MailText.setText(gmail.getMessage());
    }


    public void delete(ActionEvent actionEvent) throws IOException {

        setIP.currentUser.mails.get(setIP.currentUser.mails.indexOf(new UserMail(gmail, null))).mailTypeSet.add(MailType.delete);
        setIP.objectOut.writeObject(new Message(MessageType.deleteMail,setIP.currentUser,new UserMail(gmail)));
        setIP.objectOut.flush();
        new PageLoader().load("../View/main.fxml");
    }


    public void forward(ActionEvent actionEvent) throws IOException {

        forward.forwarded = gmail;
        new PageLoader().load("../View/forward.fxml");
    }


    public void back(ActionEvent actionEvent) throws IOException {

        new PageLoader().load("../View/main.fxml");

    }
}
