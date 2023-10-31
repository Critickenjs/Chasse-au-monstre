package chasseaumonstre.controller;

import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MonsterHunterPartieVue;

public class MonsterHunterController {
    private MonsterHunterModel model;
    private MonsterHunterPartieVue partieView;

    public MonsterHunterController(MonsterHunterModel model) {
        this.model = model;
    }

    public MonsterHunterModel getModel() {
        return this.model;
    }

    public void setVue(MonsterHunterPartieVue partieView) {
        this.partieView = partieView;
    }
}
