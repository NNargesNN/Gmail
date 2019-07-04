package Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Paths;

public class PageLoader {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    private static Stage stage;

    public static void initStage(Stage primaryStage){
        stage = primaryStage;
        stage.initStyle(StageStyle.DECORATED);
        stage.setHeight(HEIGHT);
        stage.setWidth(WIDTH);
        stage.setResizable(false);
        stage.setTitle("Gmail");
        stage.getIcons().add(new Image(Paths.get("C:\\\\Users\\\\ASUS\\\\Desktop\\\\Project\\\\src\\\\Repository/gmail.png").toUri().toString()));

    }

    public void load(String url) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(url));
        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }

    public void load(String url, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        fxmlLoader.setController(controller);
        fxmlLoader.load();
    }
}
