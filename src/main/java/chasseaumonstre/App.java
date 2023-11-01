package chasseaumonstre;

import chasseaumonstre.controller.MHMenuController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHMenuView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image("https://4.bp.blogspot.com/-boWx8QCf9bA/UYrk_pyI0aI/AAAAAAAAAoo/936FQO4QlNQ/s1600/dj.png"));
        MonsterHunterModel model = new MonsterHunterModel(21, 17);
        new MHMenuView(stage, new MHMenuController(stage, model));
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
