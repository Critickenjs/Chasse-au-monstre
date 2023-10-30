package chasseaumonstre.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button button;
    @FXML
    private Text text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button.setOnMouseEntered(event -> {
            button.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, null)));
            text.setFill(Color.WHITE);
        });
        button.setOnMouseExited(event -> {
            button.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
            text.setFill(Color.BLACK);
        });
        button.setOnMousePressed(event -> {
            button.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, null)));
        });
        button.setOnMouseReleased(event -> {
            button.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, null)));
        });
    }
}
