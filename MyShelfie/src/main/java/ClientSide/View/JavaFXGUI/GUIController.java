package ClientSide.View.JavaFXGUI;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;

public class GUIController {

    @FXML
    Button exit;

    @FXML
    public void startGame(javafx.event.ActionEvent actionEvent) {
        System.out.println("Game started");
    }

    @FXML
    public void exitGame(javafx.event.ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
