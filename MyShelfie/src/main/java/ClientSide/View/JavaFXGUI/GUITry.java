package ClientSide.View.JavaFXGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GUITry extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("try.fxml"));
            primaryStage.setTitle("Main menu");
            primaryStage.setScene(new Scene(root));
            Image icon = new Image("icon.png");
            primaryStage.getIcons().add(icon);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { launch(args); }
}
