package chasseaumonstre.controller;

import java.io.File;

import chasseaumonstre.App;
import chasseaumonstre.controller.utils.UtilsController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHMonsterView;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MHHunterController extends MHPlayerController {
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

    private boolean shot;
    private MHMonsterView monsterView;

    public MHHunterController(Stage stage, MonsterHunterModel model) {
        super(stage, model);
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

    @FXML
    public void onSkipTurn() {
        shot = false;
        this.monsterView.render();
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

    public boolean hasShot() {
        return shot;
    }

    public void setMonsterView(MHMonsterView monsterView) {
        this.monsterView = monsterView;
    }

    protected void pathAlert(int cellX, int cellY) {
        UtilsController.playSound(GUN_SHOT_SOUND_PATH, VOLUME);
        this.alertHeader.setText("You shot a path cell.\n Keep searching!");
        this.alertBody.setText("Coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.RED);
    }

    protected void wallAlert(int cellX, int cellY) {
        UtilsController.playSound(METAL_SOUND_PATH, VOLUME);
        this.alertHeader.setText("You shot a wall.\n Keep searching!");
        this.alertBody.setText("Coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.RED);
    }

    private void monsterAlert(int cellX, int cellY) {
        UtilsController.playSound(GUN_SHOT_SOUND_PATH, VOLUME);
        this.alertHeader.setText("YOU WON!");
        this.alertBody.setText("You found the Monster.\nCoordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.GREEN);
    }
    
    protected void winAlert() {
        UtilsController.playSound(MONSTERKILL_SOUND_PATH, VOLUME);
        this.winAlert.setTitle("HUNTER Victory");
        this.winAlert.setHeaderText(null);
        this.winAlert.setContentText("The Hunter has shot the Monster. The Hunter wins!");
        this.winAlert.showAndWait();

        alertOnClose();
    }

    protected void alertOnClose() {
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
}
