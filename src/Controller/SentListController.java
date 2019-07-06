package Controller;

import Common.Gmail;
import Common.Message;
import Common.MessageType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Connection.ListenerService.result;
import static Controller.setIP.currentUser;
import static java.lang.Thread.sleep;

public class SentListController {
    public ListView listView;
    public Button backButton;
    public Label typeLabel;

    public static Gmail selected;
    public static List<Gmail> listToShow = new ArrayList<>();

    public void initialize() throws IOException, InterruptedException {

        setIP.objectOut.writeObject(new Message(MessageType.Sent , currentUser));

        listToShow = (ArrayList<Gmail>) result;
        listView.setItems(FXCollections.observableArrayList(listToShow));
        listView.setCellFactory(sentListView -> new mailItem());
    }

    public void back(ActionEvent actionEvent) {

    }


    public void OpenMail(MouseEvent mouseEvent) {

    }
}
