package fr.univlille.info.J3.chasseaumonstre.controller;

import fr.univlille.info.J3.chasseaumonstre.controller.utils.UtilsController;
import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;
import fr.univlille.info.J3.chasseaumonstre.views.GameEndView;
import fr.univlille.info.J3.chasseaumonstre.views.MHHunterView;
import fr.univlille.info.J3.chasseaumonstre.views.MHMenuView;
import fr.univlille.info.J3.chasseaumonstre.views.MHMonsterView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class GameEndController {
    @FXML
    private VBox contentHunterMaze, contentMonsterMaze;

    @FXML
    private GridPane hunterMaze, monsterMaze;

    @FXML
    private VBox hunterHistory, monsterHistory;

    @FXML
    private Button btnMainMenu;

    private Stage stage;
    private MonsterHunterModel model;
    private GameEndView gameEndView;
    private MHMonsterView monsterView;
    private MHHunterView hunterView;

    public GameEndController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
        this.hunterMaze = new GridPane();

        this.model.initialize();
    }

    public void setGameEndView(GameEndView gameEndView) {
        this.gameEndView = gameEndView;
    }

    public void setMonsterView(MHMonsterView monsterView) {
        this.monsterView = monsterView;
    }

    public void setHunterView(MHHunterView hunterView) {
        this.hunterView = hunterView;
    }

    public MonsterHunterModel getModel() {
        return model;
    }

    public VBox getContentHunterMaze() {
        return contentHunterMaze;
    }

    public VBox getContentMonsterMaze() {
        return contentMonsterMaze;
    }

    public VBox getHunterHistory() {
        return hunterHistory;
    }

    public VBox getMonsterHistory() {
        return monsterHistory;
    }

    @FXML
    private void onBtnMainMenu() {
        new MHMenuView(stage, new MHMenuController(stage, model));
    }

    public void initialize() {
        UtilsController.hovereffect(btnMainMenu);
    }
}
