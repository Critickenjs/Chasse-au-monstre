package chasseaumonstre.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MonsterHunterMenuVue;
import chasseaumonstre.views.MonsterHunterPartieVue;

public class MonsterHunterMenuController  {
    
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
    private MonsterHunterMenuVue menuView;

    public MonsterHunterMenuController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;

        this.jvjBtn = new Button();
    }

    public MonsterHunterModel getModel() {
        return this.model;
    }

    public void setVue(MonsterHunterMenuVue menuView) {
        this.menuView = menuView;
    }

   
    // Vous pouvez ajouter des méthodes pour gérer les événements ici, par exemple :

    @FXML
    private void onPVP() {
        jvjBtn.setOnMouseClicked(e -> {
            MonsterHunterController controller = new MonsterHunterController(stage, model);
            MonsterHunterPartieVue partieView = new MonsterHunterPartieVue(stage, controller);
            

        });
    }

    @FXML
    private void onHunterVAi() {
        // Code à exécuter lorsque l'utilisateur survole le bouton cviButton
    }

    @FXML
    private void onMonsterVAi() {
        // Code à exécuter lorsque l'utilisateur survole le bouton mviButton
    }

    @FXML
    private void onAiVAi() {
        // Code à exécuter lorsque l'utilisateur survole le bouton iviButton
    }
}
