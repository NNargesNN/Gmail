package Controller;

import Common.Gmail;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class mailItemController {
    @FXML
    public AnchorPane root;
    @FXML
    public ImageView senderImageView;
    @FXML
    public CheckBox mailCheckBox;
    @FXML
    public Label subjectLabel;
    @FXML
    public Label nameLabel;
    @FXML
    public Label textLabel;
    @FXML
    private Gmail mail;
    private static final String UNDEFINED_PROFILE_PHOTO_URL = "address!";


    public mailItemController(Gmail mail) throws IOException {

        this.mail = mail;
        new PageLoader().load("../View/MailItem.fxml", this);
    }


    public void showEmail(MouseEvent mouseEvent) throws IOException {

    }


    public void chechBox(ActionEvent actionEvent) {
//        set
    }


    public AnchorPane init() {

        subjectLabel.setText(mail.getSubject());
        nameLabel.setText(mail.getReceiver().getUsername());
        if (mail.getMessage().length() > 20)
            textLabel.setText(mail.getMessage().substring(0, 20));
        else
            textLabel.setText(mail.getMessage());
//        //String url=mail.getSender().get();
//        if(!Files.exists(Paths.get(url)))
//            url=UNDEFINED_PROFILE_PHOTO_URL;
//        senderImageView.setImage(new Image(Paths.get(url).toUri().toString()));
        //      senderImageView.setClip(new Circle(15,15,15));
        return root;
    }
}
