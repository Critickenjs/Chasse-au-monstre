package chasseaumonstre;

import chasseaumonstre.controller.MonsterHunterController;
import chasseaumonstre.controller.MonsterHunterMenuController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MonsterHunterMenuVue;
import chasseaumonstre.views.MonsterHunterPartieVue;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MonsterHunterModel model = new MonsterHunterModel(21, 17);
        new MonsterHunterPartieVue(stage, new MonsterHunterController(model));
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
