package Controller;

import Common.*;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static Controller.setIP.objectOut;

public class Reply {
    public Label username;
    public TextField subejct;
    public TextArea textArea;
    public Button BackButton;
    public Button sendButton;
    public Button AttachButton;


    public void initialize() {
        username.setText(!showMail.selectedMail.getGmail().getSender().equals(setIP.currentUser) ? showMail.selectedMail.getGmail().getSender().getUsername() : showMail.selectedMail.getGmail().getReceiver().getUsername());

    }


    public void Back(ActionEvent actionEvent) throws IOException {

        new PageLoader().load("../View/main.fxml");
    }


    public void send(ActionEvent actionEvent) throws IOException {

        Gmail gmail = new Gmail();
        gmail.setSender(setIP.currentUser);
        gmail.setReceiver(!showMail.selectedMail.getGmail().getSender().equals(setIP.currentUser) ? showMail.selectedMail.getGmail().getSender() : showMail.selectedMail.getGmail().getReceiver());
        gmail.setMessage(textArea.getText());
        gmail.setSubject(subejct.getText());
        gmail.setLocalDateTime(LocalDateTime.now());
        Set<MailType> mailTypeSet = new HashSet<>();
        mailTypeSet.add(MailType.OutBox);
        UserMail userMail = new UserMail(gmail, mailTypeSet);
        showMail.selectedMail.setNext(userMail);
        userMail.setPrevious(showMail.selectedMail);
        setIP.OutBox.add(gmail);
        objectOut.writeObject(new Message(MessageType.Replied, userMail));
        objectOut.flush();
        new PageLoader().load("../View/main.fxml");
    }


    public void Attach(ActionEvent actionEvent) {

    }
}
