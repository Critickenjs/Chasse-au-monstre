package chasseaumonstre.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import chasseaumonstre.controller.utils.UtilsController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHHunterView;
import chasseaumonstre.views.MHMonsterView;

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

    private Stage stage;
    private MonsterHunterModel model;
    private MHHunterView hunterView;
    private MHMonsterView monsterView;

    public JVJController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
        this.startGameButton = new Button();
    }

    /*
     * Configure la partie, les vues/contrôleurs et lance la partie en affichant la vue du chasseur.
     * 
     * @see chasseaumonstre.views.MHHunterView
     * @see chasseaumonstre.views.MHMonsterView
     * @see chasseaumonstre.controller.MHHunterController
     * @see chasseaumonstre.controller.MHMonsterController
     */
    private void startGame() {
        model.setHunterName(j1.getText());
        model.setMonsterName(j2.getText());
        MHMonsterController mc = new MHMonsterController(stage, model);
        this.monsterView = new MHMonsterView(stage, mc);
        MHHunterController hc = new MHHunterController(stage, model);
        this.hunterView = new MHHunterView(stage, hc);
        mc.setHunterView(hunterView);
        hc.setMonsterView(monsterView);
        model.initialize();
        this.hunterView.render();
    }

    /*
     * Lors d'un clic souris sur le bouton lancer,
     * vérifie que les noms des joueurs sont renseignés et lance la partie.
     */
    @FXML
    private void startGameButton() {
        startGameButton.setOnMouseClicked(e -> {
                if (j1.getText().isEmpty()) {
                    j1.setStyle("-fx-border-color: red");
                    return;
                }
                j1.setStyle("-fx-border-color: none");
                if (j2.getText().isEmpty()) {
                    j2.setStyle("-fx-border-color: red");
                    return;
                }
                j2.setStyle("-fx-border-color: none");
                startGame();
    });

    }

    /*
     * Lors d'un appui sur la touche entrée,
     * Vérifie que les noms des joueurs sont renseignés et lance la partie.
     */
    @FXML
    private void startGame(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            startGame();
        }
    }
    public void initialize() {
        UtilsController.hovereffect(startGameButton);
        
    }
}
