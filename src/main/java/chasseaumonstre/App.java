package chasseaumonstre;

import chasseaumonstre.controller.MHMenuController;
import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MHMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MonsterHunterModel model = new MonsterHunterModel(21, 17);
        new MHMenuView(stage, new MHMenuController(stage, model));

        stage.setResizable(false);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
