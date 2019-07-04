
package Controller;

import Common.*;
import Connection.ListenerService;
import Model.PageLoader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static Controller.setIP.currentUser;
import static Controller.setIP.objectOut;
import static java.lang.Thread.sleep;

public class Main {
    @FXML
    public ImageView composeButton;
    @FXML
    public Button InboxButton;
    @FXML
    public Button sentButton;
    @FXML
    public Button draftButton;
    @FXML
    public Button outboxButton;
    @FXML
    public Button importantButton;
    @FXML
    public Button unreadButton;
    @FXML
    public TextField searchTextField;
    @FXML
    public Button signOutButton;
    @FXML
    public ListView<Gmail> mailsList;
    @FXML
    public ImageView settingButton;
    @FXML
    public ImageView searchBuuton;
    @FXML
    public ToggleButton searchBySubjectButton;
    @FXML
    public ToggleGroup searchBy;
    @FXML
    public ToggleButton searchByUsernameButton;
    @FXML
    public ImageView refreshButton;
    public static MailType mailType = MailType.Inbox;
    public ArrayList<UserMail> MailsToShow ;
    public List<Gmail> GmailsToShow = new ArrayList<>();
    public static Gmail selected;


    public void initialize() {

        selected = null;
        MailsToShow = new ArrayList<>();
        //* server sends all mails to client as an arraylisti chizi!;
        //*client read them
        //*
        // send request to server
        // read  signin.currentUser.emails from db
        //alan hame ye email ha hast bayad faghat barhasbe label bashe
        //bar hasbe time e beshe kari kard importanta bashe aval

        //initialize MailToShow
       // MailsToShow=currentUser.mails;

        if (currentUser.mails != null && currentUser.mails.size() != 0) {
            if (mailType.equals(MailType.Inbox)) {
                for (UserMail userMail : currentUser.mails) {
                    if (userMail.getMailTypeSet().contains(MailType.Inbox)) {
                        MailsToShow.add(userMail);
                    }
                }
            } else if (mailType.equals(MailType.Sent)) {
                for (UserMail userMail : currentUser.mails) {
                    if (userMail.getMailTypeSet().contains(MailType.Sent)) {
                        MailsToShow.add(userMail);
                    }
                }
            } else if (mailType.equals(MailType.OutBox)) {
                for (UserMail userMail : currentUser.mails) {
                    if (userMail.getMailTypeSet().contains(MailType.OutBox)) {
                        MailsToShow.add(userMail);
                    }
                }
            } else if (mailType.equals(MailType.important)) {
                for (UserMail userMail : currentUser.mails) {
                    if (userMail.getMailTypeSet().contains(MailType.important)) {
                        MailsToShow.add(userMail);
                    }
                }
            } else if (mailType.equals(MailType.unread)) {
                for (UserMail userMail : currentUser.mails) {
                    if (userMail.getMailTypeSet().contains(MailType.Unread)) {
                        MailsToShow.add(userMail);
                    }
                }
            } else {
                for (UserMail userMail : currentUser.mails) {
                    if (userMail.getMailTypeSet().contains(MailType.Inbox)) {
                        MailsToShow.add(userMail);
                    }
                }
            }
            if(MailsToShow!=null && MailsToShow.size()!=0)
            GmailsToShow = MailsToShow.stream().map(a -> a.getGmail()).sorted(Comparator.comparing(Gmail::getLocalDateTime)).collect(Collectors.toList());
           // System.out.println(GmailsToShow);
            mailsList.setItems(FXCollections.observableList(GmailsToShow));
            mailsList.setCellFactory(mail -> new mailItem());
        }
    }


    public void compose(MouseEvent mouseEvent) throws IOException {

        new PageLoader().load("../View/NewMessage.fxml");
    }


    public void inbox(ActionEvent actionEvent) throws IOException {

        mailType = MailType.Inbox;
        new PageLoader().load("../View/main.fxml");
    }


    public void sent(ActionEvent actionEvent) throws IOException {

        mailType = MailType.Sent;
        new PageLoader().load("../View/main.fxml");
    }


    public void draft(ActionEvent actionEvent) {

    }


    public void outbox(ActionEvent actionEvent) throws IOException {

        mailType = MailType.OutBox;
        new PageLoader().load("../View/main.fxml");
    }


    public void important(ActionEvent actionEvent) throws IOException {

        mailType = MailType.important;
        new PageLoader().load("../View/main.fxml");
    }


    public void unread(ActionEvent actionEvent) throws IOException {

        mailType = MailType.unread;
        new PageLoader().load("../View/main.fxml");
    }


    public void searchText(ActionEvent actionEvent) {

    }


    public void openMail(MouseEvent mouseEvent) throws IOException {

        selected = mailsList.getSelectionModel().getSelectedItem();
        new PageLoader().load("../View/showMail.fxml");

    }


    public void signOut(ActionEvent actionEvent) throws IOException {

        new PageLoader().load("../View/SignIn.fxml");
    }


    public void searchBySubject(ActionEvent actionEvent) {

        if (GmailsToShow != null && GmailsToShow.size() != 0) {
            List<Gmail> searchResult = new ArrayList<>();
            for (Gmail gmail : GmailsToShow) {
                if (gmail.getSubject().toLowerCase().
                        startsWith(searchTextField.getText().toLowerCase()))
                    searchResult.add(gmail);
            }
            mailsList.setItems(FXCollections.observableArrayList(searchResult));
            mailsList.setCellFactory(new Callback<ListView<Gmail>, ListCell<Gmail>>() {
                @Override
                public ListCell<Gmail> call(ListView<Gmail> gmailListView) {

                    return new mailItem();
                }
            });
        }
    }


    public void searchByUsernameButton(ActionEvent actionEvent) {

        if (GmailsToShow != null && GmailsToShow.size() != 0) {
            List<Gmail> searchResult = new ArrayList<>();
            for (Gmail gmail : GmailsToShow) {
                if (gmail.getSender().getUsername().toLowerCase().
                        startsWith(searchTextField.getText().toLowerCase()) || gmail.getReceiver().getUsername().toLowerCase().
                        startsWith(searchTextField.getText().toLowerCase()))
                    searchResult.add(gmail);
            }
            mailsList.setItems(FXCollections.observableArrayList(searchResult));
            mailsList.setCellFactory(new Callback<ListView<Gmail>, ListCell<Gmail>>() {
                @Override
                public ListCell<Gmail> call(ListView<Gmail> gmailListView) {

                    return new mailItem();
                }
            });
        }
    }


    public void search(MouseEvent mouseEvent) {

    }


    public void setting(MouseEvent mouseEvent) throws IOException {

        new PageLoader().load("../View/setting.fxml");
    }


    public void refresh(MouseEvent mouseEvent) throws IOException, InterruptedException {
        //read from server again!
        objectOut.writeObject(new Message(MessageType.Refresh, currentUser));
        objectOut.flush();
        sleep(1000);
        if (ListenerService.result instanceof User) {
            currentUser = (User) ListenerService.result;
            new PageLoader().load("../View/main.fxml");

        }
    }
}
