package chasseaumonstre.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.JVJView;
import chasseaumonstre.views.MHHunterView;

public class JVJController {
    
    @FXML
    private Button startGameButton;
    
    @FXML
    private TextField j1; 
    @FXML
    private TextField j2; 

    private Stage stage;
    private MonsterHunterModel model;

    public JVJController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
    }

    @FXML
    private void startGame() {

        String chasseurName = j1.getText();
        String monstreName = j2.getText();

        model.setHunterName(chasseurName);
        model.setMonsterName(monstreName);
        

        MHHunterController controller = new MHHunterController(stage, model);
        new MHHunterView(stage, controller);
    }
}
