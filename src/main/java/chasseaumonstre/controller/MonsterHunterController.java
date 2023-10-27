package chasseaumonstre.controller;

import chasseaumonstre.model.MonsterHunterModel;
import chasseaumonstre.views.MonsterHunterMenuVue;
import chasseaumonstre.views.MonsterHunterPartieVue;

public class MonsterHunterController {
    private MonsterHunterModel model;
    private MonsterHunterPartieVue partieVue;
    private MonsterHunterMenuVue menuVue;

    public MonsterHunterController() {
        model = new MonsterHunterModel();
        partieVue = new MonsterHunterPartieVue(this);
        menuVue = new MonsterHunterMenuVue(this);
        isHuntersTurn = true; 
    }
    
}
