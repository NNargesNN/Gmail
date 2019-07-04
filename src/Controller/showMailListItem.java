package Controller;

import Common.Gmail;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class showMailListItem extends ListCell<Gmail>{

    @Override
    public void updateItem(Gmail gmail,boolean empty){
        super.updateItem(gmail,empty);
        if (gmail != null) {
            setStyle("-fx-background-color: pink");
            try {
                setGraphic
                        (new ShowMailListItemController(gmail).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
