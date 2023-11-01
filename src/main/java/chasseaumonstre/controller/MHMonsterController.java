package chasseaumonstre.controller;

import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHMonsterView;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MHMonsterController {

    private Stage stage;
    private MonsterHunterModel model;
    private MHMonsterView partieView;

    public MHMonsterController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
    }

    public MonsterHunterModel getModel() {
        return this.model;
    }

    public void setVue(MHMonsterView partieView) {
        this.partieView = partieView;
    }

    public void handleMove(int shotX, int shotY) {
        CellInfo cellValue = model.getMaze()[shotX][shotY];
        
        // TODO
    }

    private void pathAlert(int cellX, int cellY) {
        // TODO
    }

    private void wallAlert(int cellX, int cellY) {
        // TODO
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setTitle("Move information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
