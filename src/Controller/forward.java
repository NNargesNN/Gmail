package Controller;

import Common.*;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static Controller.setIP.currentUser;
import static Controller.setIP.objectOut;

public class forward {
    public TextField RecipientTextField;
    public TextArea messageTextField;
    public TextField subjectTextField;
    public ImageView AttachButton;
    public Button sendButton;
    public Button closeButton;
    public StackPane progresspane;
    public static Gmail forwarded;


    public void initialize() {

        subjectTextField.setText(forwarded.getSubject());
        messageTextField.setText(forwarded.getMessage());

    }


    public void send(ActionEvent actionEvent) throws IOException {

        Gmail gmail = new Gmail();
        gmail.setSender(currentUser);
        gmail.setReceiver(new User(RecipientTextField.getText().split("@")[0]));
        gmail.setSubject(subjectTextField.getText());
        gmail.setMessage(messageTextField.getText());
        gmail.setLocalDateTime(LocalDateTime.now());
        //set attachment
        Set<MailType> mailTypeSet = new HashSet<>();
        mailTypeSet.add(MailType.OutBox);
        UserMail mail = new UserMail(gmail, mailTypeSet);
        currentUser.mails.add(mail);
        setIP.OutBox.add(gmail);
        objectOut.writeObject(new Message(MessageType.Compose, mail));
        objectOut.flush();
        new PageLoader().load("../View/main.fxml");
    }


    public void Attach(MouseEvent mouseEvent) {

    }


    public void close(ActionEvent actionEvent) throws IOException {

        new PageLoader().load("../View/main.fxml");
    }
}
