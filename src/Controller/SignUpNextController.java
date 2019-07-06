package Controller;

import Common.Message;
import Common.MessageType;
import Common.User;
import Connection.ListenerService;
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

import javax.imageio.ImageIO;

import static Controller.setIP.*;
import static java.lang.Thread.sleep;

import java.awt.image.BufferedImage;
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

    public void initialize() throws IOException {
        File unknownImageFile = new File("src/User'sPictures/photo.jpg");
        Image image = new Image(unknownImageFile.toURI().toString());
        imageView.setImage(image);
//        BufferedImage bufferedImage = ImageIO.read(unknownImageFile);
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
//        setIP.currentUser.setImage(byteArrayOutputStream.toByteArray());
    }

    public void backAction(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("../View/SignUp.fxml");
    }

    public void nextAction(ActionEvent actionEvent) throws IOException, InterruptedException {
        if(!phoneNumber.getText().isEmpty()){
            currentUser.setPhoneNumber(Integer.valueOf(phoneNumber.getText()));
        }


            if(gender.getSelectionModel().getSelectedItem().toString().equals("male"))
                currentUser.setGender(Common.Gender.male);
            else /*if(gender.getSelectionModel().getSelectedItem().toString().equals("female"))*/
                currentUser.setGender(Common.Gender.female);
                User user=new User(currentUser.getUsername(),currentUser.getPassword());
                user.setGender(currentUser.getGender());
                user.setPhoneNumber(currentUser.getPhoneNumber());
                user.setAge(currentUser.getAge());
                user.setLastName(currentUser.getLastName());
                user.setName(currentUser.getName());
//                user.setImage(currentUser.getImage());

            //currentUser.setPhoneNumber(Integer.valueOf(phoneNumber.getText()));
            objectOut.writeObject(new Message(MessageType.SignUp2 , user));
            objectOut.flush();
        System.out.println("User sent in SignupNext");
            synchronized (Main.WAIT){
                Main.WAIT.wait();
            }
        System.out.println("after wait in Sign up next");
            currentUser=(User) ListenerService.result;
            new PageLoader().load("../View/SignIn.fxml");

    }


    public void addPhoto(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedImage = fileChooser.showOpenDialog(null);
        if(selectedImage != null){
            FileOutputStream userPhoto = new FileOutputStream("src/User'sPictures/"+ currentUser.getUsername()+".jpg");
            Files.copy(selectedImage.toPath() , userPhoto);
            File file = new File("src/User'sPictures/"+ currentUser.getUsername()+".jpg");
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
//            BufferedImage bufferedImage=ImageIO.read(selectedImage);
//            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage,"jpg",byteArrayOutputStream);
//            currentUser.setImage(byteArrayOutputStream.toByteArray());


        }
    }
}
