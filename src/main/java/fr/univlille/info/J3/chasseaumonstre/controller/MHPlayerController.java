package fr.univlille.info.J3.chasseaumonstre.controller;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;
import fr.univlille.info.J3.chasseaumonstre.views.GameEndView;
import fr.univlille.info.J3.chasseaumonstre.views.MHHunterView;
import fr.univlille.info.J3.chasseaumonstre.views.MHMenuView;
import fr.univlille.info.J3.chasseaumonstre.views.MHMonsterView;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * Classe abstraite représentant un contrôleur de joueur 
 * 
 * @param stage : la fenêtre principale
 * @param model : le modèle
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public abstract class MHPlayerController {
    @FXML
    protected HBox contentMain;

    @FXML
    protected VBox contentV;

    @FXML
    protected GridPane maze;

    @FXML
    protected Label characterName;

    @FXML
    protected Label alertHeader;

    @FXML
    protected Label alertBody;

    @FXML
    protected Button skipTurn;

    @FXML
    protected ScrollPane alertHistory;

    @FXML
    protected VBox contentAlerts;

    protected Stage stage;
    protected MonsterHunterModel model;
    protected Alert winAlert;
    protected List<Label> alerts;
    protected MHMonsterView monsterView;
    protected MHHunterView hunterView;
    protected GameEndView gameEndView;

    public MHPlayerController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
        this.maze = new GridPane();

        this.winAlert = new Alert(Alert.AlertType.INFORMATION);
        this.alerts = new ArrayList<>();
        this.attachControllersToModel();
    }

    /*
     * Attache les contrôleurs au modèle
     */
    private void attachControllersToModel() {
        if (this.monsterView != null)
            this.model.attach(this.monsterView);
        if (this.hunterView != null)
            this.model.attach(this.hunterView);
    }

    /*
     * Initialise le contrôleur
     */
    public abstract void initialize();

    public MonsterHunterModel getModel() {
        return this.model;
    }

    public VBox getContentV() {
        return this.contentV;
    }

    public VBox getContentAlerts() {
        return this.contentAlerts;
    }

    public GameEndView getGameEndView() {
        return this.gameEndView;
    }
    
    @FXML
    private void onKeyPressedContentMain(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            pauseMenu();
        }
    }

    private void pauseMenu() {
        Stage stagePauseMenu = new Stage();
        stagePauseMenu.initStyle(StageStyle.UTILITY);

        VBox vbox = new VBox();

        Label title = new Label("Pause");

        Button resume = new Button("Resume");
        Button backtoMenu = new Button("Back to menu");

        resume.setOnAction(e -> {
            stagePauseMenu.close();
        });

        backtoMenu.setOnAction(e -> {
            stagePauseMenu.close();
            new MHMenuView(stage, new MHMenuController(stage, model));
        });

        vbox.getChildren().addAll(title, resume, backtoMenu);

        Scene scene = new Scene(vbox, 120, 100);
        stagePauseMenu.setScene(scene);
        stagePauseMenu.setResizable(false);
        stagePauseMenu.initOwner(this.stage);
        stagePauseMenu.initModality(javafx.stage.Modality.WINDOW_MODAL);

        stagePauseMenu.show();
    }

    /*
     * Met à jour l'historique des actions
     */
    protected void updateHistory() {
        Label action = new Label(
                "Tour : " + model.getTurn() + "\n" + alertHeader.getText() + "\n" + alertBody.getText());
        action.setTextFill(alertHeader.getTextFill());
        alerts.add(action);
        if(this instanceof MHMonsterController)
            gameEndView.setMonsterHistory(alerts);
        if(this instanceof MHHunterController)
            gameEndView.setHunterHistory(alerts);

        showHistory();
    }

    /*
     * Définit la vue du monstre
     * 
     * @param monsterView : la vue
     */
    public void setMonsterView(MHMonsterView monsterView) {
        this.monsterView = monsterView;
    }

    /*
     * Définit la vue du chasseur
     * 
     * @param hunterView : la vue
     */
    public void setHunterView(MHHunterView hunterView) {
        this.hunterView = hunterView;
    }

    /*
     * Affiche l'historique des actions
     */
    public void showHistory() {
        contentAlerts.getChildren().clear();

        for (Label action : alerts) {
            contentAlerts.getChildren().addAll(action, new Separator());
        }
    }

    public void setGameEndView(GameEndView gameEndView) {
        this.gameEndView = gameEndView;
    }
    
    /*
     * Gère le clic sur le bouton "Passer le tour"
     */
    @FXML
    public abstract void onSkipTurn();

    /*
     * Alerte le joueur touchant une case vide
     */
    protected abstract void pathAlert(int cellX, int cellY);

    /*
     * Alerte le joueur touchant un mûr
     */
    protected abstract void wallAlert(int cellX, int cellY);

    /*
     * Retourne au menu principal
     */
    protected abstract void alertOnClose();
}