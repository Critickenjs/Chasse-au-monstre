package chasseaumonstre.controller;

import chasseaumonstre.controller.utils.UtilsController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHHunterView;
import chasseaumonstre.views.MHMonsterView;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MHMonsterController {

    @FXML
    private VBox contentV;

    @FXML
    private GridPane maze;

    @FXML
    private Label characterName;

    @FXML
    private Label alertHeader;

    @FXML
    private Label alertBody;

    @FXML
    private Button skipTurn;

    private Stage stage;
    private MonsterHunterModel model;
    private MHMonsterView partieView;
    private MHHunterView hunterView;
    
    public MHMonsterController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
        this.maze = new GridPane();
    }
    
    public void initialize() {
        this.characterName.setText("Le Monstre \n" + this.model.getMonsterName());
    }
    
    public MonsterHunterModel getModel() {
        return this.model;
    }
    
    public VBox getContentV() {
        return this.contentV;
    }
    
    public void setVue(MHMonsterView partieView) {
        this.partieView = partieView;
    }

    @FXML
    public void onSkipTurn() {
        this.model.nextStep();
        this.hunterView.render();
    }
    
    public CellInfo handleMove(int moveX, int moveY) {
        model.getMonster().setVisited(moveX, moveY);
        CellInfo cellValue = model.getMaze()[moveX][moveY];
        switch (cellValue) {
            case EMPTY:
            pathAlert(moveX, moveY);
            break;
            
            case WALL:
            wallAlert(moveX, moveY);
            break;
            
            default:
            break;
        }
        return cellValue;
    }
    
    public void setHunterView(MHHunterView hunterView) {
        this.hunterView = hunterView;
    }
    
    private void pathAlert(int cellX, int cellY) {
        this.alertHeader.setText("You walk on a empty case.\n Keep walking!");
        this.alertBody.setText("Coordinates:\n (" + cellX + ", " + cellY + ")");
        alertHeader.setStyle("-fx-text-fill: blue;");
    }
    
    private void wallAlert(int cellX, int cellY) {
        this.alertHeader.setText("you can't walk on a wall.\n Keep searching!");
        this.alertBody.setText("Coordinates:\n (" + cellX + ", " + cellY + ")");
        alertHeader.setStyle("-fx-text-fill: red;");
    }
    
    
}


