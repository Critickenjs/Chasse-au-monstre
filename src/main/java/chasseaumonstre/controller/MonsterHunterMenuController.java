package chasseaumonstre.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
// import javafx.scene.layout.Background;
// import javafx.scene.layout.BackgroundFill;
// import javafx.scene.layout.CornerRadii;
// import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MonsterHunterMenuVue;

public class MonsterHunterMenuController {
    @FXML
    private Text titre;
    @FXML
    private Button jvj;
    @FXML
    private Button cvi;
    @FXML
    private Button mvi;
    @FXML
    private Button ivi;

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

    public void initialize() {
        this.titre.setText("Chasse au Monstre");
        this.jvj.setText("Joueur vs Joueur");
        this.cvi.setText("Chasseur vs IA");
        this.mvi.setText("Monstre vs IA");
        this.ivi.setText("IA vs IA");
    }

    @FXML
    public void onPVP() {
        System.out.println("onPVP");
    }

    @FXML
    public void onHunterVAi() {
        System.out.println("onHunterVAi");
    }

    @FXML
    public void onMonsterVAi() {
        System.out.println("onMonsterVAi");
    }

    @FXML
    public void onAiVAi() {
        System.out.println("onAiVAi");
    }

    // @Override
    // public void initialize(URL url, ResourceBundle resourceBundle) {
    //     button.setOnMouseEntered(event -> {
    //         button.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, null)));
    //         text.setFill(Color.WHITE);
    //     });
    //     button.setOnMouseExited(event -> {
    //         button.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
    //         text.setFill(Color.BLACK);
    //     });
    //     button.setOnMousePressed(event -> {
    //         button.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, null)));
    //     });
    //     button.setOnMouseReleased(event -> {
    //         button.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, null)));
    //     });
    // }
}
