package Controller;

import Common.Gmail;

import javafx.scene.control.ListCell;

import java.io.IOException;

public class mailItem extends ListCell<Gmail> {
    @Override
    public void updateItem(Gmail mail, boolean empty) {

        super.updateItem(mail, empty);
        if (mail != null) {
            setStyle("-fx-background-color: pink");
            try {
                setGraphic(new mailItemController(mail).init());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
