package fr.univlille.info.J3.chasseaumonstre;

import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;

import fr.univlille.info.J3.chasseaumonstre.controller.MHMenuController;
import fr.univlille.info.J3.chasseaumonstre.model.MonsterHunterModel;
import fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm.AStar;
import fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm.MonsterAlgorithm;
import fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm.DepthFirstSearch;
import fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm.Dijkstra;
import fr.univlille.info.J3.chasseaumonstre.views.MHMenuView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import fr.univlille.info.J3.chasseaumonstre.controller.utils.UtilsController;

public class App extends Application {
    public final static Preferences PREFERENCES = Preferences.userNodeForPackage(App.class);
    private final String ICON_URL = "https://4.bp.blogspot.com/-boWx8QCf9bA/UYrk_pyI0aI/AAAAAAAAAoo/936FQO4QlNQ/s1600/dj.png";
    public final static Class<? extends MonsterAlgorithm> DEFAULT_ALGORITHM = AStar.class;
    public final static List<Class<? extends MonsterAlgorithm>> ALGORITHMS_MONSTER = Arrays.asList(
        AStar.class, Dijkstra.class, DepthFirstSearch.class
    );

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