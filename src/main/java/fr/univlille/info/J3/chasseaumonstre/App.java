package fr.univlille.info.J3.chasseaumonstre;

import java.util.prefs.Preferences;

import fr.univlille.info.J3.chasseaumonstre.controller.MHMenuController;
import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;
import fr.univlille.info.J3.chasseaumonstre.views.MHMenuView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import fr.univlille.info.J3.chasseaumonstre.controller.utils.UtilsController;

public class App extends Application {
    public final static Preferences PREFERENCES = Preferences.userNodeForPackage(App.class);
    private final String ICON_URL = "https://4.bp.blogspot.com/-boWx8QCf9bA/UYrk_pyI0aI/AAAAAAAAAoo/936FQO4QlNQ/s1600/dj.png";

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