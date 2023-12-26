package fr.univlille.info.J3.chasseaumonstre.controller.utils;

import java.io.File;
import java.util.Arrays;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/*
 * Classe utilitaire pour les contrôleurs
 * 
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class UtilsController {

    public static final String FXML_LOCATION = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "maquette" + File.separator;
    public static final String SOUND_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "audio" + File.separator;
    public static final String GUN_SHOT_SOUND_PATH = SOUND_PATH + "gun-shot.mp3";
    public static final String METAL_SOUND_PATH = SOUND_PATH + "metal.wav";
    public static final String MONSTERKILL_SOUND_PATH = SOUND_PATH + "monsterkill.mp3";
    public static final String STEPS_SOUND_PATH = SOUND_PATH + "steps.wav";
    public static final String WRONG_SOUND_PATH = SOUND_PATH + "error.mp3";
    public static final String HUNTER_WIN_SOUND_PATH = SOUND_PATH + "hunterwin.mp3";
    public static final String MUSIC_SOUND_PATH = SOUND_PATH + "mononoke.mp3";
    public static final String MUSIC2_SOUND_PATH = SOUND_PATH + "music2.mp3";


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

    public static boolean playBackgroundMusicOnRepeat() {
        try {
            Media sound1 = new Media(new File(MUSIC_SOUND_PATH).toURI().toString());
            MediaPlayer mediaPlayer1 = new MediaPlayer(sound1);

            mediaPlayer1.setVolume(0.4);// Ajustez le volume ici
            
            Media sound2 = new Media(new File(MUSIC2_SOUND_PATH).toURI().toString());
            MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);

            mediaPlayer2.setVolume(0.7);
            
            mediaPlayer1.play();
            mediaPlayer1.setOnEndOfMedia(() -> {
                mediaPlayer2.play();
                mediaPlayer2.setOnEndOfMedia(() -> {
                    mediaPlayer1.play();
                });
            });

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkAddressSyntax(String address) {
        String[] addressElems = address.split(":");
        // Vérifier que l'addresse contient une ip et un port
        if(addressElems.length != 2)
            return false;
        else {
            String[] ip = addressElems[0].split("\\.");
            // Vérifier que l'addresse ip est codée sur 4 octets
            if(ip.length != 4)
                return false;
            // Vérifier que chaque élement séparé par un point est un nombre et que ce nombre est entre 0 et 255
            try {
                int n;
                for(String num : ip) {
                    n = Integer.parseInt(num);
                    if(n < 0 || n > 255)
                        return false;
                }
                // Vérifier que le port est un nombre
                Integer.parseInt(addressElems[1]);
            } catch(NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    /*
     * Ajoute un effet de survol à un bouton
     * 
     * @param btn : le bouton à modifier
     */
    public static void hovereffect(Button btn) {
        btn.setOnMouseEntered(UtilsController::onMouseEntered);
        btn.setOnMouseExited(UtilsController::onMouseExited);
    }
    
    private static void onMouseEntered(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #2e2e2e5F; -fx-background-radius: 10px; -fx-border-style: solid; -fx-border-width: 2px; -fx-border-color: #5073f2; -fx-border-radius: 3%; -fx-cursor: hand;");
    }

    private static void onMouseExited(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #2e2e2e5F; -fx-background-radius: 10px; -fx-border-style: solid; -fx-border-color: #2e2e2e; -fx-border-radius: 3%;");
    }
    
}
