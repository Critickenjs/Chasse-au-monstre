package fr.univlille.info.J3.chasseaumonstre.controller;

import fr.univlille.info.J3.chasseaumonstre.App;
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

/**
 * Le contrôleur associé à l'écran de fin de jeu
 * 
 * @see GameEndView
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
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

    /**
     *
     * @param stage La fenêtre de l'application.
     * @param model Le modèle.
     */
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

    /**
     * Gère l'action effectuée lors du clic sur le bouton "Menu principal".
     */
    @FXML
    private void onBtnMainMenu() {
        try {
            this.stage.close();
            new App().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        UtilsController.hovereffect(btnMainMenu);
    }
}
