package chasseaumonstre.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;

import chasseaumonstre.controller.utils.UtilsController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.JVJView;

/*
 * Classe représentant le contrôleur du menu principal
 * 
 * @param stage : la fenêtre principale
 * @param model : le modèle
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class MHMenuController  {
    private static final String BACKGROUND_URL = "https://www.premiere.fr/sites/default/files/styles/scale_crop_border_1280x720/public/2020-02/m.jpg";

    @FXML
    private ImageView imageView;
    @FXML
    private Button jvjBtn;
    @FXML
    private Button cviBtn;
    @FXML
    private Button mviBtn;
    @FXML
    private Button iviBtn;
    @FXML
    private Button chargerLabyrinthe;

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

    /*
     * Gère le clic sur le bouton "Jouer contre joueur", lance la vue JVJ pour choisir les noms des joueurs
     */
    @FXML
    private void onPVP() {
        jvjBtn.setOnMouseClicked(e -> {
            JVJController controller = new JVJController(stage, model);
            new JVJView(stage, controller);
        });
       
    }

    /*
     * Gère le clic sur le bouton "Chasseur contre IA"
     */
    @FXML
    private void onHunterVAi() {
        
    }

    /*
     * Gère le clic sur le bouton "Monstre contre IA"
     */
    @FXML
    private void onMonsterVAi() {
        
    }

    /*
     * Gère le clic sur le bouton "IA contre IA"
     */
    @FXML
    private void onAiVAi() {
    }

    @FXML
    private void onLoadLabyrinth() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger un labyrinthe");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(this.stage);
        if (selectedFile != null) {
            System.out.println(selectedFile);
            // À implémenter...
            // this.model.importMaze(selectedFile);
        }

    }

    /*
     * Initialise le contrôleur, affiche le fond d'écran et initialise le style des boutons
     */
    public void initialize() {
        Image image = new Image(BACKGROUND_URL);
        imageView.setImage(image);
        UtilsController.hovereffect(jvjBtn);
        UtilsController.hovereffect(cviBtn);
        UtilsController.hovereffect(mviBtn);
        UtilsController.hovereffect(iviBtn);
        UtilsController.hovereffect(chargerLabyrinthe);
    }
    
}