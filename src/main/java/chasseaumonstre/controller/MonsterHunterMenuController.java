package chasseaumonstre.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MonsterHunterMenuVue;

public class MonsterHunterMenuController  {
    
    @FXML
    private Button jvjButton;
    @FXML
    private Button cviButton;
    @FXML
    private Button mviButton;
    @FXML
    private Button iviButton;

    private MonsterHunterModel model;
    private MonsterHunterMenuVue menuView;

    public MonsterHunterMenuController(MonsterHunterModel model) {
        this.model = model;
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
        System.out.println("test");
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
