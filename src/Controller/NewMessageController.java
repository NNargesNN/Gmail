package Controller;

import Common.*;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static Controller.setIP.currentUser;
import static Controller.setIP.objectOut;

import static java.lang.Thread.sleep;

public class NewMessageController {
    public TextField RecipientTextField;
    public TextArea messageTextField;
    public Button sendButton;
    public ImageView AttackButton;
    public Button closeButton;
    public TextField subjectTextField;
    public static Gmail gmail;


    public void initialize() {

        gmail = new Gmail();
    }


    public void send(ActionEvent actionEvent) throws IOException, InterruptedException {

        if (RecipientTextField.getText().isEmpty() || subjectTextField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Recipient and Subject must be entered").showAndWait();
        } else {
            gmail.setSender(currentUser);
            gmail.setReceiver(new User(RecipientTextField.getText().split("@")[0]));
            gmail.setSubject(subjectTextField.getText());
            gmail.setMessage(messageTextField.getText());
            gmail.setLocalDateTime(LocalDateTime.now());
            //set attachment
            Set<MailType> mailTypeSet=new HashSet<>();
            mailTypeSet.add(MailType.OutBox);
            UserMail mail = new UserMail(gmail, mailTypeSet);
            currentUser.mails.add(mail);

            objectOut.writeObject(new Message(MessageType.Compose,mail));
            objectOut.flush();
            sleep(1000);
            new PageLoader().load("../View/main.fxml");
        }
    }


    public void Attach(MouseEvent mouseEvent) {
//file chooser
//        FileChooser chooser = new FileChooser();
//        chooser.setTitle("select a file");
//        File file = chooser.showOpenDialog(PageLoader.getStage());
//        if (file != null) {
//            mail.setFile(file);
//        }
    }


    public void close(ActionEvent actionEvent) throws IOException {

        new PageLoader().load("../View/main.fxml");
    }
}
