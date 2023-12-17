package fr.univlille.info.J3.chasseaumonstre.controller;

import fr.univlille.info.J3.chasseaumonstre.controller.utils.UtilsController;
import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;
import fr.univlille.info.J3.chasseaumonstre.views.MHHunterView;
import fr.univlille.info.J3.chasseaumonstre.views.MHMenuView;
import fr.univlille.info.J3.chasseaumonstre.views.MHMonsterView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/*
 * Contrôleur de la vue JvJ, menu permettant de choisir les noms des joueurs
 * 
 * @see chasseaumonstre.views.JVJView
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class JVJController {

    @FXML
    private Button startGameButton;

    @FXML
    private TextField j1;
    @FXML
    private TextField j2;

    @FXML
    private Button btnReturn;

    private Stage stage;
    private MonsterHunterModel model;
    private MHHunterView hunterView;
    private MHMonsterView monsterView;
    private boolean hunterAI, monsterAI;

    public JVJController(Stage stage, MonsterHunterModel model) {
        this(stage, model, false, false);
    }

    public JVJController(Stage stage, MonsterHunterModel model, boolean hunterAI, boolean monsterAI) {
        this.stage = stage;
        this.model = model;
        this.startGameButton = new Button();
        this.hunterAI = hunterAI;
        this.monsterAI = monsterAI;
    }

    /*
     * Configure la partie, les vues/contrôleurs et lance la partie en affichant la
     * vue du chasseur.
     * 
     * @see chasseaumonstre.views.MHHunterView
     * 
     * @see chasseaumonstre.views.MHMonsterView
     * 
     * @see chasseaumonstre.controller.MHHunterController
     * 
     * @see chasseaumonstre.controller.MHMonsterController
     */
    private void startGame() {
        model.setHunterName(j1.getText());
        model.setMonsterName(j2.getText());
        MHMonsterController mc = new MHMonsterController(stage, model);
        this.monsterView = new MHMonsterView(stage, mc);
        mc.setMonsterView(monsterView);
        MHHunterController hc = new MHHunterController(stage, model);
        this.hunterView = new MHHunterView(stage, hc);
        hc.setHunterView(this.hunterView);
        mc.setHunterView(hunterView);
        hc.setMonsterView(monsterView);
        mc.setMonsterView(monsterView);
        model.initialize();
        if (monsterAI){
            model.getMonster().setAi(monsterAI);
            this.hunterView.render();
        } else if (hunterAI){
            model.getHunter().setAi(hunterAI);
            model.getHunter().play();
            this.monsterView.render();
        } else {
            this.hunterView.render();
        }
    }

    /*
     * Lors d'un clic souris sur le bouton lancer,
     * vérifie que les noms des joueurs sont renseignés et lance la partie.
     */
    @FXML
    private void startGameButton() {
        startGameButton.setOnMouseClicked(e -> {
                startGame();
            
        });
    }

    /*
     * Vérifie que les noms des joueurs sont renseignés
     * 
     * @return true si les noms sont renseignés, false sinon
     */
    /* 
     private boolean arePlayerNamesSet() {
        if (j1.getText().isEmpty()) {
            j1.setStyle("-fx-border-color: red");
            return false;
        }
        j1.setStyle("-fx-border-color: none");

        if (j2.getText().isEmpty()) {
            j2.setStyle("-fx-border-color: red");
            return false;
        }
        j2.setStyle("-fx-border-color: none");

        return true;
    }
    */

    /*
     * Lors d'un appui sur la touche entrée,
     * Vérifie que les noms des joueurs sont renseignés et lance la partie.
     */
    @FXML
    private void startGame(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                    startGame();
                
                break;
            case BACK_SPACE:
                returnToMenu();
            default:
                break;
        }
    }

    @FXML
    private void returnToMenu() {
        new MHMenuView(stage, new MHMenuController(stage, model));
    }

    /*
     * Initialise les effets de survol des boutons
     */
    public void initialize() {
        UtilsController.hovereffect(startGameButton);
        UtilsController.hovereffect(btnReturn);
    }
}
