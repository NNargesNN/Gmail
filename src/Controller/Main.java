
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

import static Connection.ListenerService.result;
import static Controller.setIP.*;
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
    public ArrayList<UserMail> MailsToShow;
    public List<Gmail> GmailsToShow = new ArrayList<>();
    public static Gmail selected;
    public static Gmail currentGmail;
    public static List<UserMail> Mails = new ArrayList<>();


    public void initialize() throws InterruptedException, IOException {

    }


    public void compose(MouseEvent mouseEvent) throws IOException {

        new PageLoader().load("../View/NewMessage.fxml");
    }


    public void inbox(ActionEvent actionEvent) throws IOException, InterruptedException {

        //--------
        List<Gmail> inbox = new ArrayList<>();
        objectOut.writeObject(new Message(MessageType.Inbox, currentUser));
        objectOut.flush();
        synchronized (Model.Main.WAIT) {
            Model.Main.WAIT.wait();
        }

        inbox = ((ArrayList<UserMail>) result).stream().map(a -> a.getGmail()).collect(Collectors.toList());
        GmailsToShow = inbox;
        Set<MailType> mailTypes=new HashSet<>();
        mailTypes.add(MailType.Inbox);
        for (int i = 0; i <inbox.size() ; i++) {
            if(!currentUser.mails.contains(new UserMail(inbox.get(i)))){

                currentUser.mails.add(new UserMail(inbox.get(i),mailTypes));
            }
        }
        OutBox = new ArrayList<>();
        mailsList.setItems(FXCollections.observableArrayList(inbox));
        inbox.sort((a,b)->a.getLocalDateTime().compareTo(b.getLocalDateTime()));
        mailsList.setCellFactory(new Callback<ListView<Gmail>, ListCell<Gmail>>() {
            @Override
            public ListCell<Gmail> call(ListView<Gmail> gmailListView) {

                return new mailItem();
            }
        });
    }


    public void sent(ActionEvent actionEvent) throws IOException, InterruptedException {

        List<Gmail> sent = new ArrayList<>();
        objectOut.writeObject(new Message(MessageType.Sent, currentUser));
        objectOut.flush();
        synchronized (Model.Main.WAIT) {
            Model.Main.WAIT.wait();
        }

        sent = ((ArrayList<UserMail>) result).stream().map(a -> a.getGmail()).collect(Collectors.toList());
        GmailsToShow = sent;
        Set<MailType> mailTypes=new HashSet<>();
        mailTypes.add(MailType.Sent);
        for (int i = 0; i <sent.size() ; i++) {
            if(!currentUser.mails.contains(new UserMail(sent.get(i)))){

                currentUser.mails.add(new UserMail(sent.get(i),mailTypes));
            }
        }

        OutBox = new ArrayList<>();
        sent.sort((a,b)->a.getLocalDateTime().compareTo(b.getLocalDateTime()));
        mailsList.setItems(FXCollections.observableArrayList(sent));
        mailsList.setCellFactory(new Callback<ListView<Gmail>, ListCell<Gmail>>() {
            @Override
            public ListCell<Gmail> call(ListView<Gmail> gmailListView) {

                return new mailItem();
            }
        });
    }


    public void draft(ActionEvent actionEvent) {

    }


    public void outbox(ActionEvent actionEvent) throws IOException {

        GmailsToShow = OutBox;
        OutBox.sort((a,b)->a.getLocalDateTime().compareTo(b.getLocalDateTime()));
        mailsList.setItems(FXCollections.observableArrayList(OutBox));
        mailsList.setCellFactory(new Callback<ListView<Gmail>, ListCell<Gmail>>() {
            @Override
            public ListCell<Gmail> call(ListView<Gmail> gmailListView) {

                return new mailItem();
            }
        });

    }


    public void important(ActionEvent actionEvent) throws IOException {


    }


    public void unread(ActionEvent actionEvent) throws IOException {

        mailType = MailType.unread;
        new PageLoader().load("../View/main.fxml");
    }


    public void searchText(ActionEvent actionEvent) {

    }


    public void openMail(MouseEvent mouseEvent) throws IOException {

        selected = mailsList.getSelectionModel().getSelectedItem();
        selected.setRead(true);

       // setIP.currentUser.mails.get(setIP.currentUser.mails.indexOf(new UserMail(Main.selected,null))).getGmail().setRead(true);
        new PageLoader().load("../View/showMail.fxml");

    }


    public void signOut(ActionEvent actionEvent) throws IOException, InterruptedException {
        objectOut.writeObject(new Message(MessageType.SignOut,currentUser,currentUser.mails));

        currentUser = null;
        new PageLoader().load("../View/SignIn.fxml");
    }


    public void searchBySubject(ActionEvent actionEvent) {

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


    public void searchByUsernameButton(ActionEvent actionEvent) {

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


    public void search(MouseEvent mouseEvent) {

    }


    public void setting(MouseEvent mouseEvent) throws IOException {

        new PageLoader().load("../View/setting.fxml");
    }


    public void refresh(MouseEvent mouseEvent) throws IOException, InterruptedException {

        objectOut.writeObject(new Message(MessageType.Refresh, currentUser));
        synchronized (Model.Main.WAIT) {
            Model.Main.WAIT.wait();
        }
        currentUser.mails = (ArrayList<UserMail>) result;
        showMail.userMails=currentUser.mails;
        new PageLoader().load("../View/main.fxml");

    }
}
