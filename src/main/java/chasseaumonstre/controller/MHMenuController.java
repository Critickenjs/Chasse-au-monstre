    package chasseaumonstre.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHHunterView;

public class MHMenuController  {
    
    @FXML
    private Button jvjBtn;
    @FXML
    private Button cviBtn;
    @FXML
    private Button mviBtn;
    @FXML
    private Button iviBtn;

    private Stage stage;
    private MonsterHunterModel model;

    public MHMenuController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
        this.jvjBtn = new Button();
    }

    public MonsterHunterModel getModel() {
        return this.model;
    }

    // Vous pouvez ajouter des méthodes pour gérer les événements ici, par exemple :

    @FXML
    private void onPVP() {
        jvjBtn.setOnMouseClicked(e -> {
            MHHunterController controller = new MHHunterController(stage, model);
            new MHHunterView(stage, controller);
        });
    }

    @FXML
    private void onHunterVAi() {
        
    }

    @FXML
    private void onMonsterVAi() {
        
    }

    @FXML
    private void onAiVAi() {
    }
}
