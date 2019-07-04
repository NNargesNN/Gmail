package Controller;

import Common.Gmail;
import Model.PageLoader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.LinkedList;

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
    private Gmail nextMail;
    private Gmail prevMail;
    private Gmail nowMail;
    private Gmail current;
    public ListView mailSequence;
    public LinkedList<Gmail> prevs;
    public LinkedList<Gmail> nexts;
    public LinkedList<Gmail> listsToShow;



    public void initialize() {

        nowMail = Main.selected;
      ///  System.out.println(nowMail);
        prevs = new LinkedList<>();
        nexts = new LinkedList<>();
        listsToShow=new LinkedList<>();
        current = nowMail;
      ///  System.out.println(current);
        while (current.getNext() != null) {
            current = current.getNext();
            nexts.add(current);
        }
        current = nowMail;
        while (current.getPrevious() != null) {
            current = current.getPrevious();
            prevs.add(current);
        }
        Gmail m=null;
        if (prevs != null &&prevs.size()!=0&& prevs.getLast() != null)
            m = prevs.getLast();
        while (m!=null && m.getPrevious() != null) {
            listsToShow.add(m);
            m = m.getPrevious();
        }

        listsToShow.add(nowMail);
        m = nowMail;
        while (m!=null&&m.getNext() != null) {
            m = m.getNext();
            listsToShow.add(m);
        }
        mailSequence.setItems(FXCollections.observableArrayList(listsToShow));
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
        ///???  :(
        // goto compose set text mail ghabli
        //receiver ghabli
        new PageLoader().load("../View/NewMessage.fxml");
    }


    public void forward(ActionEvent actionEvent) {

    }
}
