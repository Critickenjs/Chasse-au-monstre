package chasseaumonstre.controller;

import java.util.ArrayList;
import java.util.List;

import chasseaumonstre.model.MonsterHunterModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class MHPlayerController {
    @FXML
    protected VBox contentV;

    @FXML
    protected GridPane maze;

    @FXML
    protected Label characterName;

    @FXML
    protected Label alertHeader;

    @FXML
    protected Label alertBody;

    @FXML
    protected Button skipTurn;

    @FXML
    protected ScrollPane alertHistory;

    @FXML
    protected VBox contentAlerts;

    protected Stage stage;
    protected MonsterHunterModel model;
    protected Alert winAlert;
    protected List<Label> alerts;

    public MHPlayerController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
        this.maze = new GridPane();

        this.winAlert = new Alert(Alert.AlertType.INFORMATION);
        this.alerts = new ArrayList<>();
    }

    public abstract void initialize();

    public MonsterHunterModel getModel() {
        return this.model;
    }

    public VBox getContentV() {
        return this.contentV;
    }

    protected void updateHistory() {
        Label action = new Label("Turn : " + model.getTurn() + "\n" + alertHeader.getText() + "\n" + alertBody.getText());
        alerts.add(action);

        showHistory();
    }

    public void showHistory() {
        contentAlerts.getChildren().clear();

        for (Label action : alerts) {
            contentAlerts.getChildren().addAll(action, new Separator());
        }
    }
    
    @FXML
    public abstract void onSkipTurn();

    protected abstract void pathAlert(int cellX, int cellY);

    protected abstract void wallAlert(int cellX, int cellY);

    protected abstract void winAlert();

    protected abstract void alertOnClose();
}
