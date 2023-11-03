package chasseaumonstre.controller.utils;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class UtilsController {

    public static final String FXML_LOCATION = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "main" + File.separator + "resources" + File.separator + "maquette" + File.separator;

    /*
     * Joue un son
     * 
     * @param SOUND : le chemin du son à jouer
     * @param volume : le volume du son à jouer
     * @return true si le son a été joué, false sinon
     */
    public static boolean playSound(String soundFile, double volume) {
        try {
            Media sound = new Media(new File(soundFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            mediaPlayer.setVolume(volume); // Ajustez le volume ici

            mediaPlayer.play();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static void hovereffect(Button btn) {
        btn.setOnMouseEntered(UtilsController::onMouseEntered);
        btn.setOnMouseExited(UtilsController::onMouseExited);
    }
    public static void onMouseEntered(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #2e2e2e5F; -fx-background-radius: 10px; -fx-border-style: solid; -fx-border-width: 2px; -fx-border-color: #5073f2; -fx-border-radius: 3%;");
    }

    public static void onMouseExited(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #2e2e2e5F; -fx-background-radius: 10px; -fx-border-style: solid; -fx-border-color: #2e2e2e; -fx-border-radius: 3%;");
    }
    
}
