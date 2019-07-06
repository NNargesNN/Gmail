package Controller;

import Common.Gmail;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class mailItem extends ListCell<Gmail> {
    @Override
    public void updateItem(Gmail mail, boolean empty) {

        super.updateItem(mail, empty);
        if (mail != null) {
            if (mail.isRead())
                setStyle("-fx-background-color: #cceeff");
            else if (!mail.isRead() && !mail.isImportant()) {
                setStyle("-fx-background-color: #66ccff");
            }
            if (mail.isImportant()) {
                setStyle("-fx-background-color: #ff6699");
            }
            try {
                setGraphic(new mailItemController(mail).init());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
