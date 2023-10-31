package chasseaumonstre.controller;

import java.io.File;

import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MonsterHunterPartieVue;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MonsterHunterController {
    private Stage stage;
    private MonsterHunterModel model;
    private MonsterHunterPartieVue partieView;
    private String musicFile = "C:\\Users\\ylies\\Desktop\\saé\\J3_SAE3A\\src\\main\\resources\\audio\\Gun shoot - Sound effect.mp3";
    

    public MonsterHunterController(Stage stage, MonsterHunterModel model) {
        this.stage = stage;
        this.model = model;
    }

    public MonsterHunterModel getModel() {
        return this.model;
    }

    public void setVue(MonsterHunterPartieVue partieView) {
        this.partieView = partieView;
    }


    public void handleShot(int shotX, int shotY) {
        CellInfo cellValue = model.getMaze()[shotX][shotY];

        switch (cellValue) {
            case EMPTY:
                pathAlert(shotX, shotY);
                break;

            case WALL:
                wallAlert(shotX, shotY);
                break;

            default:
                break;
        }
    }
    public void playSound(String musicFile, double volume) {
    try {
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        mediaPlayer.setVolume(volume); // Ajustez le volume ici 

        mediaPlayer.play();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    private void pathAlert(int cellX, int cellY) {
        playSound(musicFile,0.5 );
        showAlert("You shot a path cell. Keep searching!\nCoordinates: (" + cellX + ", " + cellY + ").");
        
    }

    private void wallAlert(int cellX, int cellY) {
        playSound(musicFile,0.5 );
        showAlert("You shot a wall. Keep searching!");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setTitle("Shot Result");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
