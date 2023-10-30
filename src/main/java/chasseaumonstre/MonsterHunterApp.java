package chasseaumonstre;

import chasseaumonstre.controller.MonsterHunterController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MonsterHunterPartieVue;
import javafx.application.Application;
import javafx.stage.Stage;

public class MonsterHunterApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MonsterHunterModel model = new MonsterHunterModel(null, 21, 17);
        MonsterHunterPartieVue view = new MonsterHunterPartieVue();
        MonsterHunterController controller = new MonsterHunterController(model, null, view);

        view.start(primaryStage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
