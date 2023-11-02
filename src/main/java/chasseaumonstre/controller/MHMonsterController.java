package chasseaumonstre.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import chasseaumonstre.App;
import chasseaumonstre.controller.utils.UtilsController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHHunterView;
import chasseaumonstre.views.MHMonsterView;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    @FXML
    private ScrollPane alertHistory;

    @FXML
    private VBox contentAlerts;

    private Stage stage;
    private boolean moved;
    private MonsterHunterModel model;
    private MHMonsterView partieView;
    private MHHunterView hunterView;

    private Alert winAlert;

    private List<Label> alerts;

    public MHMonsterController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
        this.moved = false;
        this.maze = new GridPane();

        this.winAlert = new Alert(Alert.AlertType.INFORMATION);
        this.alerts = new ArrayList<>();
    }

    public void initialize() {
        this.characterName.setText("Le Monstre \n" + this.model.getMonsterName());
        this.alertHistory.setVvalue(1.0);
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

    private boolean advance(int moveX, int moveY) {
        if (model.getMonster().estAdjacente(moveX, moveY)) {
            if (model.getMonster().isVisited(moveX, moveY)) {
                visitedAlert(moveX, moveY);
                return false;
            }
            moved = true;
            skipTurn.setDisable(false);
            ICoordinate coord = model.getMonster().getCoord();
            model.getMaze()[coord.getRow()][coord.getCol()] = CellInfo.EMPTY;
            model.getMonster().setCoord(moveX, moveY, model.getTurn());
            model.getMaze()[moveX][moveY] = CellInfo.MONSTER;
        } else {
            UtilsController.playSound(WRONG_SOUND_PATH, LOW_VOLUME);
            farAlert(moveX, moveY);
        }
        partieView.update();
        return moved;
    }

    public CellInfo handleMove(int moveX, int moveY) {
        CellInfo cellValue = model.getMaze()[moveX][moveY];
        switch (cellValue) {
            case EMPTY:
                if (advance(moveX, moveY)) {
                    UtilsController.playSound(STEPS_SOUND_PATH, VOLUME);
                    pathAlert(moveX, moveY);
                    this.updateHistory();
                }
                break;

            case WALL:
                UtilsController.playSound(WRONG_SOUND_PATH, LOW_VOLUME);
                wallAlert(moveX, moveY);
                break;

            case EXIT:
                if (advance(moveX, moveY)) {
                    this.updateHistory();
                    winAlert();
                }
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
        this.alertHeader.setText("You walk on a empty case.");
        this.alertBody.setText("Coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.BLUE);
    }

    private void wallAlert(int cellX, int cellY) {
        this.alertHeader.setText("You cannot walk on a wall.");
        this.alertBody.setText("Coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.RED);
    }

    private void visitedAlert(int cellX, int cellY) {
        this.alertHeader.setText("You already walked on this case.\n Keep searching!");
        this.alertBody.setText("Coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.ORANGE);
    }

    private void farAlert(int cellX, int cellY) {
        this.alertHeader.setText("You are too far from this case!");
        this.alertBody.setText("Coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.ORANGE);
    }

    private void winAlert() {
        this.winAlert.setTitle("MONSTER Victory");
        this.winAlert.setHeaderText(null);
        this.winAlert.setContentText("The Monster leave the labyrinth. The Monster wins!");
        this.winAlert.showAndWait();

        alertOnClose();
    }

    private void alertOnClose() {
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

    public void keyPressedOnScene(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (hasMoved())
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
