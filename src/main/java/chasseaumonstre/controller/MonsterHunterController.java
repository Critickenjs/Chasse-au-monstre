package chasseaumonstre.controller;

import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MonsterHunterPartieVue;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MonsterHunterController {
    private Stage stage;
    private MonsterHunterModel model;
    private MonsterHunterPartieVue partieView;

    public MonsterHunterController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
    }

    public MonsterHunterModel getModel() {
        return this.model;
    }

    public void setVue(MonsterHunterPartieVue partieView) {
        this.partieView = partieView;
    }

    public void handleShot(int shotX, int shotY) {
        CellInfo cellValue = model.getMaze()[shotX][shotY];

        switch (cellValue) {
            case EMPTY:
                pathAlert(shotX, shotY);
                break;

            case WALL:
                wallAlert(shotX, shotY);
                break;

            default:
                break;
        }
    }

    private void pathAlert(int cellX, int cellY) {
        showAlert("You shot a path cell. Keep searching!\nCoordinates: (" + cellX + ", " + cellY + ").");
    }

    private void wallAlert(int cellX, int cellY) {
        showAlert("You shot a wall. Keep searching!");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setTitle("Shot Result");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
