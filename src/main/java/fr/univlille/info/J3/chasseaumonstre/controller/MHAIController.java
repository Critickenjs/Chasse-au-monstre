package fr.univlille.info.J3.chasseaumonstre.controller;

import SubjectObserver.Subject;
import fr.univlille.info.J3.chasseaumonstre.controller.utils.UtilsController;
import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;
import fr.univlille.info.J3.chasseaumonstre.views.MHAIView;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Classe représentant un contrôleur du joueur chasseur
 * 
 * @param stage : la fenêtre principale
 * @param model : le modèle
 * @see MHPlayerController
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class MHAIController extends MHPlayerController {
    private final double VOLUME = 0.05;
    private boolean finished = false;

    private MHAIView view;

    public MHAIController(Stage stage, MonsterHunterModel model) {
        super(stage, model, null);
    }
    
    /*
    * Initialise le contrôleur, affiche le nom du chasseur et initialise la zone
    */
    public void initialize() {
        this.characterName.setText("IA vs IA");
        this.alertHistory.setVvalue(1.0);
        skipTurn.setDisable(true);
        Thread t = new Thread(() -> {
            while(!this.finished) {
                handleAction(this.getModel().getHunter(), this.model.getHunter().play());
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
                handleAction(this.getModel().getMonster(), this.model.getMonster().play());
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });
        t.start();
    }

    public MHAIView getView() {
        return view;
    }

    public void setView(MHAIView view) {
        this.view = view;
    }

    public MonsterHunterModel getModel() {
        return this.model;
    }

    public VBox getContentV() {
        return contentV;
    }

    public void setMaze(GridPane maze) {
        this.maze = maze;
    }

    /*
     * Gère le clic sur le bouton "Passer le tour"
     */
    @FXML
    public void onSkipTurn() {
        if (!skipTurn.isDisabled()) {
            skipTurn.setDisable(true);
            handleAction(getModel().getHunter(), this.model.getHunter().play());
            handleAction(getModel().getMonster(), this.model.getMonster().play());
            this.model.nextTurn();
            skipTurn.setDisable(false);
        } 
    }
    
    /*
     * Alerte le joueur que le monstre a été tué et que le chasseur a gagné
     */
    public void hunterWinAlert() {
        Platform.runLater(() -> {
            UtilsController.playSound(UtilsController.MONSTERKILL_SOUND_PATH, VOLUME);
            this.winAlert.setTitle("Victoire du CHASSEUR");
            this.winAlert.setHeaderText(null);
            this.winAlert.setContentText("Le Chasseur a abattu le Monstre. Le Chasseur gagne !");
            this.winAlert.showAndWait();

            alertOnClose();
        });
    }

    /*
     * Alerte le joueur que le monstre a atteint la sortie et a gagné
     */
    public void monsterWinAlert() {
        Platform.runLater(() -> {
            this.winAlert.setTitle("Victoire du MONSTRE");
            this.winAlert.setHeaderText(null);
            this.winAlert.setContentText("Le Monstre a atteint la sortie du Labyrinthe. Le Monstre gagne !");
            this.winAlert.showAndWait();

            alertOnClose();
        });
    }

    /*
     * Alerte lorsque la case touchée est un mur (vide car IA)
     */
    public void wallAlert(int x, int y) {

    }

    /*
     * Alerte lorsque la case touchée est un chemin (vide car IA)
     */
    public void pathAlert(int x, int y) {
        
    }

    /*
     * Gère l'action des joueurs
     * 
     * @param subj : le joueur qui a joué
     * @param coordinate : la case touchée
     */
    public void handleAction(Subject subj, ICoordinate coordinate) {
        if (subj.equals(this.model.getMonster())) {
            Platform.runLater(() -> {view.update();});
            if (coordinate.equals(model.getExit())) {
                this.finished = true;
                monsterWinAlert();
            }
        } else {
            Platform.runLater(() -> {view.update();});
            if (coordinate.equals(model.getMonster().getCoord())) {
                this.finished = true;
                hunterWinAlert();
            }
        }
    }

    /*
     * Affiche l'écran de fin de partie lorsque l'alerte est fermée
     */
    protected void alertOnClose() {
        gameEndView.render();
    }
}
