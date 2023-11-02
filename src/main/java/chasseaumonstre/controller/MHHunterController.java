package chasseaumonstre.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import chasseaumonstre.App;
import chasseaumonstre.controller.utils.UtilsController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHMonsterView;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MHHunterController {
    private final String GUN_SHOT_SOUND_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "main"
            + File.separator + File.separator + "resources" + File.separator + "audio" + File.separator
            + "gun-shot.mp3";
    private final String METAL_SOUND_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "main"
            + File.separator + File.separator + "resources" + File.separator + "audio" + File.separator
            + "metal.wav";
    private final String MONSTERKILL_SOUND_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "main"
            + File.separator + File.separator + "resources" + File.separator + "audio" + File.separator
            + "monsterkill.mp3";

    private final double VOLUME = 0.05;

    @FXML
    private VBox contentV;

    @FXML
    private GridPane maze;

    @FXML
    private Label characterName;

    @FXML
    private Label alertHeader;

    @FXML
    private Label alertBody;

    @FXML
    private Button skipTurn;

    @FXML
    private ScrollPane alertHistory;

    @FXML
    private VBox contentAlerts;

    private Alert winAlert;
    private boolean shot;

    private MonsterHunterModel model;
    private Stage stage;
    private MHMonsterView monsterView;

    private List<Label> alerts;

    public MHHunterController(Stage stage, MonsterHunterModel model) {
        this.model = model;
        this.stage = stage;

        this.winAlert = new Alert(AlertType.INFORMATION);
        this.alerts = new ArrayList<>();
    }

    public void initialize() {
        this.characterName.setText("Le Chasseur \n" + this.model.getHunterName());
        this.alertHistory.setVvalue(1.0);
    }

    public MonsterHunterModel getModel() {
        return this.model;
    }

    public VBox getContentV() {
        return contentV;
    }

    public void setMaze(GridPane maze) {
        this.maze = maze;
    }

    public CellInfo handleShot(int shotX, int shotY) {
        this.model.getHunter().shoot(shotX, shotY);
        shot = true;
        skipTurn.setDisable(false);
        CellInfo cellValue = model.getMaze()[shotX][shotY];

        switch (cellValue) {
            case EMPTY:
                pathAlert(shotX, shotY);
                this.updateHistory();
                break;

            case WALL:
                wallAlert(shotX, shotY);
                this.updateHistory();
                break;

            case MONSTER:
                monsterAlert(shotX, shotY);
                this.updateHistory();
                winAlert();
                break;

            default:
                break;
        }

        return cellValue;
    }

    @FXML
    public void onSkipTurn() {
        shot = false;
        this.monsterView.render();
    }

    public boolean hasShot() {
        return shot;
    }

    public void setMonsterView(MHMonsterView monsterView) {
        this.monsterView = monsterView;
    }

    private void pathAlert(int cellX, int cellY) {
        UtilsController.playSound(GUN_SHOT_SOUND_PATH, VOLUME);
        this.alertHeader.setText("You shot a path cell.\n Keep searching!");
        this.alertBody.setText("Coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.RED);
    }

    private void wallAlert(int cellX, int cellY) {
        UtilsController.playSound(METAL_SOUND_PATH, VOLUME);
        this.alertHeader.setText("You shot a wall.\n Keep searching!");
        this.alertBody.setText("Coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.RED);
    }

    private void monsterAlert(int cellX, int cellY) {
        UtilsController.playSound(GUN_SHOT_SOUND_PATH, VOLUME);
        this.alertHeader.setText("YOU WON!");
        this.alertBody.setText("You found the Monster at coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.GREEN);
    }
    
    private void winAlert() {
        UtilsController.playSound(MONSTERKILL_SOUND_PATH, VOLUME);
        this.winAlert.setTitle("HUNTER Victory");
        this.winAlert.setHeaderText(null);
        this.winAlert.setContentText("The Hunter has shot the Monster. The Hunter wins!");
        this.winAlert.showAndWait();

        alertOnClose();
    }

    private void alertOnClose() {
        System.out.println("alert closed");
        Platform.runLater(() -> {
            try {
                new App().start(new Stage());
                this.stage.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        });
    }

    private void updateHistory() {
        Label action = new Label(alertHeader.getText() + "\n" + alertBody.getText());
        alerts.add(action);

        showHistory();
    }

    public void showHistory() {
        contentAlerts.getChildren().clear();

        for (Label action : alerts) {
            contentAlerts.getChildren().addAll(action, new Separator());
        }
    }
}
