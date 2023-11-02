package chasseaumonstre.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHHunterView;
import chasseaumonstre.views.MHMonsterView;

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
    }

    private void startGame() {
        model.setHunterName(j1.getText());
        model.setMonsterName(j2.getText());
        MHMonsterController mc = new MHMonsterController(stage, model);
        this.monsterView = new MHMonsterView(stage, mc);
        MHHunterController hc = new MHHunterController(stage, model);
        this.hunterView = new MHHunterView(stage, hc);
        mc.setHunterView(hunterView);
        hc.setMonsterView(monsterView);
        this.hunterView.render();
    }

    @FXML
    private void startGameButton() {
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
    }

    @FXML
    private void startGame(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            startGame();
        }
    }
}
