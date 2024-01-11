package fr.univlille.info.J3.chasseaumonstre;

import java.util.prefs.Preferences;

import fr.univlille.info.J3.chasseaumonstre.controller.MHMenuController;
import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;
import fr.univlille.info.J3.chasseaumonstre.views.MHMenuView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import fr.univlille.info.J3.chasseaumonstre.controller.utils.UtilsController;

/**
 * Classe principale qui permert de lancer le jeu
 * 
 * @autor Anas Ouhdda
 * @autor Atilla Tas
 * @autor Karim Aoulad-Tayab
 * @autor Selim Hamza
 * @autor Yliess El Atifi
 */
public class App extends Application {
    public final static Preferences PREFERENCES = Preferences.userNodeForPackage(App.class);
    private final String ICON_URL = "https://4.bp.blogspot.com/-boWx8QCf9bA/UYrk_pyI0aI/AAAAAAAAAoo/936FQO4QlNQ/s1600/dj.png";

    /**
     * Cette méthode est l'initialisation de l'application.
     * Elle initialise le modèle MonsterHunterModel, définit l'icône de la scène,
     * crée le MHMenuView et le MHMenuController, joue la musique de fond,
     * et met la scène en mode plein écran.
     *
     * @param stage le stage principal de l'application
     * @throws Exception si une erreur survient pendant l'initialisation
     */
    @Override
    public void start(Stage stage) throws Exception {
        MonsterHunterModel model = new MonsterHunterModel();
        stage.getIcons().add(new Image(ICON_URL));
        new MHMenuView(stage, new MHMenuController(stage, model));
        UtilsController.playBackgroundMusicOnRepeat();
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}