package fr.univlille.info.J3.chasseaumonstre.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.File;
import java.io.IOException;

import fr.univlille.info.J3.chasseaumonstre.controller.utils.UtilsController;
import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;
import fr.univlille.info.J3.chasseaumonstre.views.JVJView;
import fr.univlille.info.J3.chasseaumonstre.views.MHAIView;
import fr.univlille.info.J3.chasseaumonstre.views.MHHunterView;
import fr.univlille.info.J3.chasseaumonstre.views.MHMonsterView;

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
    private Button parametres;
    @FXML
    private Button quitter;

    private Stage stage;
    private MonsterHunterModel model;
    private MHHunterView hunterView;
    private MHMonsterView monsterView;

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
        cviBtn.setOnMouseClicked(e -> {
            startGame(false, true);
        });
    }

    /*
     * Gère le clic sur le bouton "Monstre contre IA"
     */
    @FXML
    private void onMonsterVAi() {
        mviBtn.setOnMouseClicked(e -> {
            startGame(true, false);
        });
    }

    /*
     * Gère le clic sur le bouton "IA contre IA"
     */
    @FXML
    private void onAiVAi() {
        iviBtn.setOnMouseClicked(e -> {
            startGame(true, true);
        });
    }

    private void loadLabyrinth() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger un labyrinthe");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(this.stage);
        if (selectedFile != null) {
            try {
                this.model.importMaze(selectedFile);
            } catch (IOException e) {
                System.out.println("Erreur IO sur le fichier '" + selectedFile + "'");
            } catch(NumberFormatException e) {
                System.out.println("Erreur de parsing d'entier sur le fichier '" + selectedFile + "'");
            }
        }
    }

    @FXML
    private void onParameter() {
        Stage stageParameter = new Stage();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);

        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setHeaderText(null);
        alert2.setTitle("Succès");
        alert2.setContentText("Changement effectué avec succès");

        VBox vbox = new VBox();
        HBox hbox = new HBox();

        Label label = new Label("Changer la taille du labyrinthe");
        label.setStyle("-fx-font-weight: bold");

        TextField width = new TextField(""+model.getWidth());
        width.setPromptText("Width");

        TextField height = new TextField(""+model.getHeight());
        height.setPromptText("Height");

        Button button = new Button("Valider");

        hbox.getChildren().addAll(width, height);

        HBox obstacleSettings = new HBox();
        Label obstacleLabel = new Label("Pourcentage d'obstacles :");
        TextField obstacle = new TextField(""+model.getObstacles());
        obstacleSettings.getChildren().addAll(obstacleLabel, obstacle);

        HBox fovSettings = new HBox();
        Label fovLabel = new Label("Champ de vision :");
        TextField fov = new TextField(""+model.getMonster().getFov());
        fovSettings.getChildren().addAll(fovLabel, fov);

        button.setOnAction(e -> {
            if(!width.getText().equals("") && height.getText().equals("")) {
                alert.setTitle("Erreur de saisie");
                alert.setContentText("Veuillez saisir les 2 champs");
                alert.showAndWait();
            } else {
               try {
                    int widthVal = Integer.parseInt(width.getText());
                    int heightVal = Integer.parseInt(height.getText());
                    try {
                        model.setWidth(widthVal);
                        model.setHeight(heightVal);
                    } catch(IllegalArgumentException e1) {
                        alert.setTitle("Erreur de saisie");
                        alert.setContentText(e1.getMessage());
                        alert.showAndWait();
                        return;
                    }
               } catch(NumberFormatException e2) {
                    alert.setContentText("Erreur de type sur l'un des 2 champs");
                    alert.showAndWait();
                    return;
               }
            }

            if(!obstacle.getText().equals("")) {
                try {
                    int obstacleVal = Integer.parseInt(obstacle.getText());
                    try {
                        model.setObstacles(obstacleVal);
                    } catch(IllegalArgumentException e1) {
                        alert.setTitle("Erreur de saisie");
                        alert.setContentText(e1.getMessage());
                        alert.showAndWait();
                        return;
                    }
                } catch(NumberFormatException e2) {
                    alert.setTitle("Erreur de saisie");
                    alert.setContentText("Erreur de type sur le champ");
                    alert.showAndWait();
                    return;
                }
            }

            if(!fov.getText().equals("")) {
                try {
                    int fovVal = Integer.parseInt(fov.getText());
                    try {
                        model.getMonster().setFov(fovVal);
                    } catch(IllegalArgumentException e1) {
                        alert.setTitle("Erreur de saisie");
                        alert.setContentText(e1.getMessage());
                        alert.showAndWait();
                        return;
                    }
                } catch(NumberFormatException e2) {
                    alert.setTitle("Erreur de saisie");
                    alert.setContentText("Erreur de type sur le champ");
                    alert.showAndWait();
                    return;
                }
            }

            alert2.showAndWait();
        });

        Button button2 = new Button("Charger un labyrinthe prédéfini");

        button2.setOnAction(e -> {
            this.loadLabyrinth();
        });

        vbox.getChildren().addAll(label, hbox, obstacleSettings, fovSettings, button, button2);

        vbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        obstacleSettings.setSpacing(10);
        obstacleSettings.setAlignment(Pos.CENTER_LEFT);
        fovSettings.setSpacing(10);
        fovSettings.setAlignment(Pos.CENTER_LEFT);
        vbox.setSpacing(10);

        Scene scene = new Scene(vbox, 450, 210);
        stageParameter.setScene(scene);
        stageParameter.setResizable(false);
        stageParameter.initOwner(this.stage);
        stageParameter.initModality(Modality.WINDOW_MODAL);

        stageParameter.show();
    }

    @FXML
    private void onQuit() {
        this.stage.close();
    }

    private void startGame(boolean hunterAI, boolean monsterAI) {
        MHMonsterController mc = null;
        MHHunterController hc = null;
    
        model.initialize();
        if (!monsterAI) {
            mc = new MHMonsterController(stage, model);
            this.monsterView = new MHMonsterView(stage, mc);
            mc.setMonsterView(this.monsterView);
        }

        if (!hunterAI) {
            hc = new MHHunterController(stage, model);
            this.hunterView = new MHHunterView(stage, hc);
            hc.setHunterView(this.hunterView);
            hc.setMonsterView(this.monsterView);
        }

        if (mc != null) {
            mc.setHunterView(this.hunterView);
        }
        
        if (monsterAI && hunterAI) {
            model.getMonster().setAi(monsterAI);
            model.getHunter().setAi(hunterAI);
    
            MHAIController aiController = new MHAIController(stage, model);
            MHAIView aiView = new MHAIView(stage, aiController);
            aiController.setView(aiView);
            aiView.render();
        } else if (monsterAI) {
            model.getMonster().setAi(monsterAI);
            this.hunterView.render();
        } else if (hunterAI) {
            model.getHunter().setAi(hunterAI);
            model.getHunter().play();
            this.monsterView.render();
        } else {
            this.hunterView.render();
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
        UtilsController.hovereffect(parametres);
        UtilsController.hovereffect(quitter);
    }
    
}