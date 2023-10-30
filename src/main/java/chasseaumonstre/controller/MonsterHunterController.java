package chasseaumonstre.controller;

import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MonsterHunterMenuVue;
import chasseaumonstre.views.MonsterHunterPartieVue;

public class MonsterHunterController {
    private MonsterHunterModel model;
    private MonsterHunterPartieVue partieView;
    private MonsterHunterMenuVue menuView;

    public MonsterHunterController(MonsterHunterModel model, MonsterHunterMenuVue menuView, MonsterHunterPartieVue partieView) {
        this.model = model;
        this.partieView = partieView;
        this.menuView = menuView;

        partieView.setController(this);
    }
}
