package chasseaumonstre.controller;

import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MonsterHunterPartieVue;
import javafx.stage.Stage;

public class MonsterHunterController {
    private Stage stage;
    private MonsterHunterModel model;
    private MonsterHunterPartieVue partieView;

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
}
