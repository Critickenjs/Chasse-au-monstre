package fr.univlille.info.J3.chasseaumonstre.controller;

import fr.univlille.info.J3.chasseaumonstre.App;
import fr.univlille.info.J3.chasseaumonstre.controller.utils.UtilsController;
import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;
import fr.univlille.info.J3.chasseaumonstre.views.MHMonsterView;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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

    private static final double VOLUME = 100;
    private static final double LOW_VOLUME = 0.05;

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
        Image fogImage = new Image("https://cdn.discordapp.com/attachments/1159749679353974806/1172561801574109214/fog.png?ex=6560c446&is=654e4f46&hm=179e40d2cf2e2a6cd19f72a721db7dbcba4c816b9bb6b3d26fd77ed71709df80&");
        BackgroundImage myBI = new BackgroundImage(fogImage,
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, true));
        contentV.setBackground(new Background(myBI));
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
        if (model.getHunter().isAi()) {
            this.model.getHunter().play();
            this.monsterView.render();
            this.model.nextTurn();
        } else {
            this.hunterView.render();
        }
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
            /* -----> Décommenter pour ne plus pouvoir revenir sur ses pas
            if (model.getMonster().isVisited(moveX, moveY)) {
                visitedAlert(moveX, moveY);
                return false;
            }*/
            moved = true;
            skipTurn.setDisable(false);
            model.getMonster().setCoord(moveX, moveY, model.getTurn());
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
     * @param moveX : la coordonnée X de la case de destination
     * @param moveY : la coordonnée Y de la case de destination
     * @return la valeur de la case de destination
     */
    public CellInfo handleMove(int moveX, int moveY) {
        boolean isWall = !model.getMaze()[moveX][moveY];
        ICoordinate exit = model.getExit();

        if (isWall) {
            UtilsController.playSound(UtilsController.WRONG_SOUND_PATH, LOW_VOLUME);
            wallAlert(moveX, moveY);
            return CellInfo.WALL;
        } else if (exit.getRow() == moveX && exit.getCol() == moveY) {
            if (advance(moveX, moveY)) {
                this.updateHistory();
                winAlert();
            }
            return CellInfo.EXIT;
        } else {
            if (advance(moveX, moveY)) {
                UtilsController.playSound(UtilsController.STEPS_SOUND_PATH, VOLUME);
                pathAlert(moveX, moveY);
                this.updateHistory();
            }
            return CellInfo.EMPTY;
        }
    }

    public boolean hasMoved() {
        return moved;
    }

    /*
     * Alerte le joueur que la case visée est vide
     * 
     * @param cellX : la coordonnée X de la case visée
     * @param cellY : la coordonnée Y de la case visée
     */
    protected void pathAlert(int cellX, int cellY) {
        this.alertHeader.setText("Vous marchez sur une case vide");
        this.alertBody.setText("Coordonnées: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.BLUE);
    }

    /*
     * Alerte le joueur que la case visée est un mur
     * 
     * @param cellX : la coordonnée X de la case visée
     * @param cellY : la coordonnée Y de la case visée
     */
    protected void wallAlert(int cellX, int cellY) {
        this.alertHeader.setText("Vous ne pouvez pas marcher sur un mur.");
        this.alertBody.setText("Coordonnées: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.RED);
    }

    /* -----> Décommenter pour ne plus pouvoir revenir sur ses pas
     * Alerte le joueur que la case visée a déjà été visitée
     * 
     * @param cellX : la coordonnée X de la case visée
     * @param cellY : la coordonnée Y de la case visée
     
    private void visitedAlert(int cellX, int cellY) {
        this.alertHeader.setText("Vous avez déjà marché sur cette case.");
        this.alertBody.setText("Coordonnées: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.ORANGE);
    }
    */

    /*
     * Alerte le joueur que la case visée est trop loin
     * 
     * @param cellX : la coordonnée X de la case visée
     * @param cellY : la coordonnée Y de la case visée
     */
    private void farAlert(int cellX, int cellY) {
        this.alertHeader.setText("Vous êtes trop loin de cette case !");
        this.alertBody.setText("Coordonnées: (" + cellX + ", " + cellY + ")");
        this.alertHeader.setTextFill(Color.ORANGE);
    }

    /*
     * Alerte le joueur que le monstre a gagné
     */
    public void winAlert() {
        this.winAlert.setTitle("Victoire du MONSTRE");
        this.winAlert.setHeaderText(null);
        this.winAlert.setContentText("Le Monstre a atteint la sortie du Labyrinthe. Le Monstre gagne !");
        this.winAlert.showAndWait();

        alertOnClose();
    }

    public void hunterWinAlert() {
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
