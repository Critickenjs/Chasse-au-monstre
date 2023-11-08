package chasseaumonstre.controller;

import chasseaumonstre.App;
import chasseaumonstre.controller.utils.UtilsController;
import chasseaumonstre.model.MonsterHunterModel;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
public class MHHunterController extends MHPlayerController {
    private final double VOLUME = 0.05;

    private boolean shot;

    public MHHunterController(Stage stage, MonsterHunterModel model) {
        super(stage, model);
    }

    /*
     * Initialise le contrôleur, affiche le nom du chasseur et initialise la zone
     */
    public void initialize() {
        this.characterName.setText("Le Chasseur \n" + this.model.getHunterName());
        this.alertHistory.setVvalue(1.0);
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
        shot = false;
        this.monsterView.render();
    }

    /*
     * Gère les tirs du chasseur
     * 
     * @param shotX : la coordonnée X de la cellule visée
     * @param shotY : la coordonnée Y de la cellule visée
     * @return la valeur de la cellule visée
     */
    public CellInfo handleShot(int shotX, int shotY) {
        this.model.getHunter().shoot(shotX, shotY);
        shot = true;
        skipTurn.setDisable(false);
        CellInfo cellValue = model.getMaze()[shotX][shotY];

        switch (cellValue) {
            case EMPTY:
                pathAlert(shotX, shotY);
                this.updateHistory();
                break;

            case WALL:
                wallAlert(shotX, shotY);
                this.updateHistory();
                break;

            case MONSTER:
                monsterAlert(shotX, shotY);
                this.updateHistory();
                winAlert();
                break;

            default:
                break;
        }

        return cellValue;
    }


    /*
     * Savoir si le chasseur a tiré
     * 
     * @return true si le chasseur a tiré, false sinon
     */
    public boolean hasShot() {
        return shot;
    }

    /*
     * Alerte le joueur que la cellule visée est vide
     * 
     * @param cellX : la coordonnée X de la cellule visée
     * @param cellY : la coordonnée Y de la cellule visée
     */
    protected void pathAlert(int cellX, int cellY) {
        UtilsController.playSound(UtilsController.GUN_SHOT_SOUND_PATH, VOLUME);
        this.alertHeader.setText("Vous avez tiré sur le chemin.");
        this.alertBody.setText("Coordonnées: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.ORANGE);
    }

    /*
     * Alerte le joueur que la cellule visée est un mur
     * 
     * @param cellX : la coordonnée X de la cellule visée
     * @param cellY : la coordonnée Y de la cellule visée
     */
    protected void wallAlert(int cellX, int cellY) {
        UtilsController.playSound(UtilsController.METAL_SOUND_PATH, VOLUME);
        this.alertHeader.setText("Vous avez tiré sur un mur.");
        this.alertBody.setText("Coordonnées: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.RED);
    }

    /*
     * Alerte le joueur que la cellule visée est le monstre
     * 
     * @param cellX : la coordonnée X de la cellule visée
     * @param cellY : la coordonnée Y de la cellule visée
     */
    private void monsterAlert(int cellX, int cellY) {
        UtilsController.playSound(UtilsController.GUN_SHOT_SOUND_PATH, VOLUME);
        this.alertHeader.setText("VOUS AVEZ GAGNÉ !");
        this.alertBody.setText("Vous avez trouvé le monstre aux\ncoordonnées: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.GREEN);
    }
    
    /*
     * Alerte le joueur que le monstre a été tué et qu'il a gagné
     */
    protected void winAlert() {
        UtilsController.playSound(UtilsController.MONSTERKILL_SOUND_PATH, VOLUME);
        this.winAlert.setTitle("Victoire du CHASSEUR");
        this.winAlert.setHeaderText(null);
        this.winAlert.setContentText("Le Chasseur a abattu le Monstre. Le Chasseur gagne !");
        this.winAlert.showAndWait();

        alertOnClose();
    }

    /*
     * Retourne au menu principal lorsque la fenêtre est fermée
     */
    protected void alertOnClose() {
        Platform.runLater(() -> {
            try {
                new App().start(new Stage());
                this.stage.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        });
    }
}
