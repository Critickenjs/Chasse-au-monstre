package chasseaumonstre.controller;

import java.io.File;

import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHHunterView;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MHMonsterController {

    private final double VOLUME = 0.05;

    private Stage stage;
    private MonsterHunterModel model;
    private MHHunterView partieView;

    public MHMonsterController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
    }

    public MonsterHunterModel getModel() {
        return this.model;
    }

    public void setVue(MHHunterView partieView) {
        this.partieView = partieView;
    }

    public void handleMove(int shotX, int shotY) {
        CellInfo cellValue = model.getMaze()[shotX][shotY];
        
        // TODO
    }

    public void playSound(String SOUND, double volume) {
        try {
            Media sound = new Media(new File(SOUND).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            mediaPlayer.setVolume(volume); // Ajustez le volume ici

            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pathAlert(int cellX, int cellY) {
        // TODO
    }

    private void wallAlert(int cellX, int cellY) {
        // TODO
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setTitle("Move information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
