package Controller;

import Common.Gmail;
import Model.Main;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static Controller.setIP.currentUser;

public class ShowMailListItemController {

    @FXML
    public Label MailTextLabel;
    @FXML
    public Label mailTimeInfo;
    @FXML
    public Label MailUserLabel;
    @FXML
    public ImageView UserImage;
    @FXML
    public Button downloadButton;
    @FXML
    public Label metaData;
    @FXML
    public AnchorPane pane;
    private Gmail gmail;


    public ShowMailListItemController(Gmail gmail) throws IOException {

        this.gmail = gmail;
        new PageLoader().load("/View/showMailListItem.fxml", this);

    }

    public AnchorPane init() {
        MailTextLabel.setText(gmail.getMessage());
        mailTimeInfo.setText(gmail.getLocalDateTime().toString());
        MailUserLabel.setText(!gmail.getSender().getUsername().equals(currentUser.getUsername())? gmail.getSender().getUsername():gmail.getReceiver().getUsername());
//      String info=attach info
//       metaData

//        usernameLabel.setText(user.getUsername());
//        String url = user.getImageUrl();
//        if (!Files.exists(Paths.get(url)))
//            url = UNDEFINED_PROFILE_PHOTO_URL;
//
//        userProfileImageView.setImage(new Image
//                (Paths.get(url).toUri().toString()));
//        userProfileImageView.setClip(new Circle(25, 25, 25));
        return pane;
    }

    public void download(ActionEvent actionEvent) {

    }
}
