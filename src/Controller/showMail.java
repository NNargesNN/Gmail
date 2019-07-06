package Controller;

import Common.Gmail;
import Common.User;
import Common.UserMail;
import Model.PageLoader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.ietf.jgss.GSSName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class showMail {
    @FXML
    public Label mailTextLabel;
    @FXML
    public Button BackButton;
    @FXML
    public Button DeleteButton;
    @FXML
    public Button ReplyButton;
    @FXML
    public Label emailAddress;
    public Button forwardButton;
    public Button makeImportantButton;
    public Button makeUnreadButton;
    private UserMail nextMail;
    private UserMail prevMail;
    private Gmail nowMail;
    private UserMail current;
    public ListView<Gmail> mailSequence;
    public LinkedList<UserMail> prevs;
    public LinkedList<UserMail> nexts;
    public LinkedList<UserMail> listsToShow;
    public static UserMail NowMailUserMail;
    public static UserMail selectedMail;

public static ArrayList<UserMail> userMails;
    public void initialize() {


        nowMail = Main.selected;
        NowMailUserMail = userMails.get(userMails.indexOf(new UserMail(nowMail)));
        ///  System.out.println(nowMail);
        prevs = new LinkedList<>();
        nexts = new LinkedList<>();
        listsToShow = new LinkedList<>();
        current = NowMailUserMail;
        while (current.getNext() != null) {
            current = current.getNext();
            nexts.add(current);
        }
        current = NowMailUserMail;
        while (current.getPrevious() != null) {
            current = current.getPrevious();
            prevs.add(current);
        }
        UserMail m = null;
        if (prevs != null && prevs.size() != 0 && prevs.getLast() != null)
            m = prevs.getLast();
        while (m != null && m.getPrevious() != null) {
            listsToShow.add(m);
            m = m.getPrevious();
        }
        listsToShow.add(NowMailUserMail);
        m = NowMailUserMail;
        while (m != null && m.getNext() != null) {
            m = m.getNext();
            listsToShow.add(m);
        }
        mailSequence.setItems(FXCollections.observableArrayList(listsToShow.stream().map(UserMail::getGmail).collect(Collectors.toList())));
        mailSequence.setCellFactory(item -> new showMailListItem());
    }


    public void Back(ActionEvent actionEvent) throws IOException {
        //save someWhere where was the last page
        //pageloader ...
        new PageLoader().load("../View/main.fxml");
    }


    public void Delete(ActionEvent actionEvent) {
        //send to server to delete this mail
        //-->delete the folder in server side
    }


    public void Reply(ActionEvent actionEvent) throws IOException {

        selectedMail=listsToShow.getLast();
        new PageLoader().load("../View/Reply.fxml");
    }


    public void forward(ActionEvent actionEvent) throws IOException {
        forward.forwarded=Main.selected;
        new PageLoader().load("../View/forward.fxml");
    }


    public void makeImportant(ActionEvent actionEvent) {
        if(Main.selected.isImportant()){
             Main.selected.setImportant(false);
             setIP.currentUser.mails.get(setIP.currentUser.mails.indexOf(new UserMail(Main.selected,null))).getGmail().setImportant(false);
        }else{
            Main.selected.setImportant(true);
            setIP.currentUser.mails.get(setIP.currentUser.mails.indexOf(new UserMail(Main.selected,null))).getGmail().setImportant(true);
        }

    }


    public void makeUnread(ActionEvent actionEvent) {
        Main.selected.setRead(false);
        setIP.currentUser.mails.get(setIP.currentUser.mails.indexOf(new UserMail(Main.selected,null))).getGmail().setRead(false);
    }


    public void OpenSingleMail(MouseEvent mouseEvent) throws IOException {
        showSingleMail.gmail = mailSequence.getSelectionModel().getSelectedItem();
        new PageLoader().load("../View/showSingleMail.fxml");
    }
}
