package chasseaumonstre.controller;

import java.io.File;

import chasseaumonstre.controller.utils.UtilsController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHMonsterView;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MHHunterController {
    private final String GUN_SHOT_SOUND_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "main"
            + File.separator + File.separator + "resources" + File.separator + "audio" + File.separator
            + "gun-shot.mp3";

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

    private Stage stage;
    private MonsterHunterModel model;
    private MHMonsterView monsterView;

    public MHHunterController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
    }

    public void initialize() {
        this.characterName.setText("Le Chasseur \n" + this.model.getHunterName());
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
        model.getHunter().shoot(shotX, shotY);
        CellInfo cellValue = model.getMaze()[shotX][shotY];

        switch (cellValue) {
            case EMPTY:
                pathAlert(shotX, shotY);
                break;

            case WALL:
                wallAlert(shotX, shotY);
                break;

            case MONSTER:
                break;

            default:
                break;
        }

        return cellValue;
    }

    @FXML
    public void onSkipTurn() {
        this.monsterView.render();
    }

    public void setMonsterView(MHMonsterView monsterView) {
        this.monsterView = monsterView;
    }

    private void pathAlert(int cellX, int cellY) {
        UtilsController.playSound(GUN_SHOT_SOUND_PATH, VOLUME);
        this.alertHeader.setText("You shot a path cell.\n Keep searching!");
        this.alertBody.setText("Coordinates:\n (" + cellX + ", " + cellY + ")");
        alertHeader.setStyle("-fx-text-fill: red;");
    }

    private void wallAlert(int cellX, int cellY) {
        UtilsController.playSound(GUN_SHOT_SOUND_PATH, VOLUME);
        this.alertHeader.setText("You shot a wall.\n Keep searching!");
        this.alertBody.setText("Coordinates:\n (" + cellX + ", " + cellY + ")");
        alertHeader.setStyle("-fx-text-fill: red;");
    }
}
