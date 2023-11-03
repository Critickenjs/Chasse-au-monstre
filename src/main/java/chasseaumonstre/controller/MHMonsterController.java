package chasseaumonstre.controller;

import chasseaumonstre.App;
import chasseaumonstre.controller.utils.UtilsController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHHunterView;
import chasseaumonstre.views.MHMonsterView;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
 * Classe abstraite représentant un contrôleur du joueur monstre
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
public class MHMonsterController extends MHPlayerController {

    private final double VOLUME = 100;
    private final double LOW_VOLUME = 0.05;

    private boolean moved;

    public MHMonsterController(Stage stage, MonsterHunterModel model) {
        super(stage, model);
    }

    /*
     * Initialise le contrôleur, affiche le nom du monstre et initialise la zone
     */
    public void initialize() {
        this.characterName.setText("Le Monstre \n" + this.model.getMonsterName());
        this.alertHistory.setVvalue(1.0);
    }

    public MonsterHunterModel getModel() {
        return this.model;
    }

    public VBox getContentV() {
        return this.contentV;
    }

    /*
     * Définit la vue à contrôler
     * 
     * @param monsterView : la vue 
     */
    public void setVue(MHMonsterView monsterView) {
        this.monsterView = monsterView;
    }

    /*
     * Gère le clic sur le bouton "Passer le tour"
     */
    @FXML
    public void onSkipTurn() {
        moved = false;
        this.model.nextTurn();
        this.hunterView.render();
    }

    /*
     * Fait avancer le monstre
     * 
     * @param moveX : la coordonnée X de la case visée
     * @param moveY : la coordonnée Y de la case visée
     * @return true si le monstre a bougé, false sinon
     */
    private boolean advance(int moveX, int moveY) {
        if (model.getMonster().estAdjacente(moveX, moveY)) {
            if (model.getMonster().isVisited(moveX, moveY)) {
                visitedAlert(moveX, moveY);
                return false;
            }
            moved = true;
            skipTurn.setDisable(false);
            ICoordinate coord = model.getMonster().getCoord();
            model.getMaze()[coord.getRow()][coord.getCol()] = CellInfo.EMPTY;
            model.getMonster().setCoord(moveX, moveY, model.getTurn());
            model.getMaze()[moveX][moveY] = CellInfo.MONSTER;
        } else {
            UtilsController.playSound(UtilsController.WRONG_SOUND_PATH, LOW_VOLUME);
            farAlert(moveX, moveY);
        }
        monsterView.update();
        return moved;
    }

    /*
     * Gère le déplacement du monstre
     * 
     * @param moveX : la coordonnée X de la case visée
     * @param moveY : la coordonnée Y de la case visée
     * @return la valeur de la case visée
     */
    public CellInfo handleMove(int moveX, int moveY) {
        CellInfo cellValue = model.getMaze()[moveX][moveY];
        switch (cellValue) {
            case EMPTY:
                if (advance(moveX, moveY)) {
                    UtilsController.playSound(UtilsController.STEPS_SOUND_PATH, VOLUME);
                    pathAlert(moveX, moveY);
                    this.updateHistory();
                }
                break;

            case WALL:
                UtilsController.playSound(UtilsController.WRONG_SOUND_PATH, LOW_VOLUME);
                wallAlert(moveX, moveY);
                break;

            case EXIT:
                if (advance(moveX, moveY)) {
                    this.updateHistory();
                    winAlert();
                }
                break;

            default:
                break;
        }
        return cellValue;
    }

    public boolean hasMoved() {
        return moved;
    }

    /*
     * Définit la vue du chasseur
     * 
     * @param hunterView : la vue du chasseur
     */
    public void setHunterView(MHHunterView hunterView) {
        this.hunterView = hunterView;
    }

    /*
     * Alerte le joueur que la case visée est vide
     * 
     * @param cellX : la coordonnée X de la case visée
     * @param cellY : la coordonnée Y de la case visée
     */
    protected void pathAlert(int cellX, int cellY) {
        this.alertHeader.setText("You walk on a empty case.");
        this.alertBody.setText("Coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.BLUE);
    }

    /*
     * Alerte le joueur que la case visée est un mur
     * 
     * @param cellX : la coordonnée X de la case visée
     * @param cellY : la coordonnée Y de la case visée
     */
    protected void wallAlert(int cellX, int cellY) {
        this.alertHeader.setText("You cannot walk on a wall.");
        this.alertBody.setText("Coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.RED);
    }

    /*
     * Alerte le joueur que la case visée a déjà été visitée
     * 
     * @param cellX : la coordonnée X de la case visée
     * @param cellY : la coordonnée Y de la case visée
     */
    private void visitedAlert(int cellX, int cellY) {
        this.alertHeader.setText("You already walked on this case.\n Keep searching!");
        this.alertBody.setText("Coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.ORANGE);
    }

    /*
     * Alerte le joueur que la case visée est trop loin
     * 
     * @param cellX : la coordonnée X de la case visée
     * @param cellY : la coordonnée Y de la case visée
     */
    private void farAlert(int cellX, int cellY) {
        this.alertHeader.setText("You are too far from this case!");
        this.alertBody.setText("Coordinates: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.ORANGE);
    }

    /*
     * Alerte le joueur que le monstre a gagné
     */
    protected void winAlert() {
        this.winAlert.setTitle("MONSTER Victory");
        this.winAlert.setHeaderText(null);
        this.winAlert.setContentText("The Monster leave the labyrinth. The Monster wins!");
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

    /*
     * Gère les mouvements via les touches ZQSD
     */
    public void keyPressedOnScene(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (hasMoved())
                return;

            int x = model.getMonster().getCoord().getRow();
            int y = model.getMonster().getCoord().getCol();

            KeyCode keyCode = event.getCode();

            switch (keyCode) {
                case Z:
                    y--;
                    break;
                case S:
                    y++;
                    break;
                case Q:
                    x--;
                    break;
                case D:
                    x++;
                    break;
                default:
                    break;
            }

            handleMove(x, y);
        });
    }
}
