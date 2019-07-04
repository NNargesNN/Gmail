package Controller;

import Common.Message;
import Common.MessageType;
import Model.Main;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import static Controller.setIP.currentUser;
import static Controller.setIP.objectOut;


import java.io.*;
import java.nio.file.Files;

public class SignUpNextController {
    @FXML
    public TextField phoneNumber;
    @FXML
    public ImageView imageView;
    @FXML
    public ComboBox gender;
    @FXML
    public Label empty;

    public void initialize(){

    }

    public void backAction(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("../View/SignUp.fxml");
    }

    public void nextAction(ActionEvent actionEvent) throws IOException {
        if(!phoneNumber.getText().isEmpty()){
            currentUser.setPhoneNumber(Integer.valueOf(phoneNumber.getText()));
        }
//        if(gender.getSelectionModel().getSelectedItem() != null){
//            if(gender.getSelectionModel().getSelectedItem().toString().equals("male"))
//                currentUser.setGender(Common.Gender.male);
//            else if(gender.getSelectionModel().getSelectedItem().toString().equals("female"))
//                currentUser.setGender(Common.Gender.female);
//        }
//        if(phoneNumber.getText().isEmpty() || gender.getSelectionModel().getSelectedItem() == null){
//            empty.setVisible(true);
//        }

            if(gender.getSelectionModel().getSelectedItem().toString().equals("male"))
                currentUser.setGender(Common.Gender.male);
            else /*if(gender.getSelectionModel().getSelectedItem().toString().equals("female"))*/
                currentUser.setGender(Common.Gender.female);
            //currentUser.setPhoneNumber(Integer.valueOf(phoneNumber.getText()));
            objectOut.writeObject(new Message(MessageType.SignUp2 , currentUser));
            objectOut.flush();
            new PageLoader().load("../View/SignIn.fxml");

    }


    public void addPhoto(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedImage = fileChooser.showOpenDialog(null);
        if(selectedImage != null){
            FileOutputStream userPhoto = new FileOutputStream("src/User'sPictures/picture.jpg");
            Files.copy(selectedImage.toPath() , userPhoto);
            File file = new File("src/User'sPictures/picture.jpg");
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);

 //           currentUser.setImage();

        }
    }
}
