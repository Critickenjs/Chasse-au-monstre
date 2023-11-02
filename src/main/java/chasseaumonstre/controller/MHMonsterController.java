package chasseaumonstre.controller;

import java.io.File;

import chasseaumonstre.controller.utils.UtilsController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHHunterView;
import chasseaumonstre.views.MHMonsterView;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MHMonsterController {
    private final String STEPS_SOUND_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "main"
            + File.separator + File.separator + "resources" + File.separator + "audio" + File.separator
            + "steps.wav";

    private final String WRONG_SOUND_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "main"
            + File.separator + File.separator + "resources" + File.separator + "audio" + File.separator
            + "error.mp3";

    private final double VOLUME = 100;
    private final double LOW_VOLUME = 0.05;

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
    private boolean moved;
    private MonsterHunterModel model;
    private MHMonsterView partieView;
    private MHHunterView hunterView;
    
    public MHMonsterController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
        this.moved = false;
        this.maze = new GridPane();
    }

    public void initialize() {
        this.characterName.setText("Le Monstre \n" + this.model.getMonsterName());
    }
    
    public MonsterHunterModel getModel() {
        return this.model;
    }
    
    public VBox getContentV() {
        return this.contentV;
    }
    
    public void setVue(MHMonsterView partieView) {
        this.partieView = partieView;
    }

    @FXML
    public void onSkipTurn() {
        moved = false;
        this.model.nextTurn();
        this.hunterView.render();
    }
    
    public CellInfo handleMove(int moveX, int moveY) {
        CellInfo cellValue = model.getMaze()[moveX][moveY];
        switch (cellValue) {
            case EMPTY:
            if (model.getMonster().estAdjacente(moveX, moveY)) {
                if (model.getMonster().isVisited(moveX, moveY)) {
                    visitedAlert(moveX, moveY);
                    break;
                }
                UtilsController.playSound(STEPS_SOUND_PATH, VOLUME);
                moved = true;
                ICoordinate coord = model.getMonster().getCoord();
                model.getMaze()[coord.getRow()][coord.getCol()] = CellInfo.EMPTY;
                model.getMonster().setCoord(moveX, moveY, model.getTurn());
                model.getMaze()[moveX][moveY] = CellInfo.MONSTER;
                pathAlert(moveX, moveY);
                partieView.update();
            } else {
                UtilsController.playSound(WRONG_SOUND_PATH, LOW_VOLUME);
                farAlert(moveX, moveY);
            }
            break;
            
            case WALL:
            UtilsController.playSound(WRONG_SOUND_PATH, LOW_VOLUME);
            wallAlert(moveX, moveY);
            break;
            
            default:
            break;
        }
        return cellValue;
    }
    
    public void setHunterView(MHHunterView hunterView) {
        this.hunterView = hunterView;
    }
    
    public boolean hasMoved() {
        return moved;
    }

    private void pathAlert(int cellX, int cellY) {
        this.alertHeader.setText("You walk on a empty case.\n Keep walking!");
        this.alertBody.setText("Coordinates:\n (" + cellX + ", " + cellY + ")");
        alertHeader.setStyle("-fx-text-fill: blue;");
    }
    
    private void wallAlert(int cellX, int cellY) {
        this.alertHeader.setText("you can't walk on a wall.\n Keep searching!");
        this.alertBody.setText("Coordinates:\n (" + cellX + ", " + cellY + ")");
        alertHeader.setStyle("-fx-text-fill: red;");
    }
    
    private void visitedAlert(int cellX, int cellY) {
        this.alertHeader.setText("You already walked on this case.\n Keep searching!");
        this.alertBody.setText("Coordinates:\n (" + cellX + ", " + cellY + ")");
        alertHeader.setStyle("-fx-text-fill: orange;");
    }

    private void farAlert(int cellX, int cellY) {
        this.alertHeader.setText("You are too far from this case.\n Keep searching!");
        this.alertBody.setText("Coordinates:\n (" + cellX + ", " + cellY + ")");
        alertHeader.setStyle("-fx-text-fill: orange;");
    }

    public void keyPressedOnScene(Scene scene) {
        scene.setOnKeyPressed(event -> {
                if(hasMoved())
                    return;

                int x = model.getMonster().getCoord().getRow();
                int y = model.getMonster().getCoord().getCol();

                KeyCode keyCode = event.getCode();

                switch (keyCode) {
                    case Z:
                        y--;
                        break;
                    case S:
                        y++;
                        break;
                    case Q:
                        x--;
                        break;
                    case D:
                        x++;
                        break;
                    default:
                        break;
                }

                handleMove(x, y);
            });
    }
}


