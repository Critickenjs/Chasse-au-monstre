package chasseaumonstre.model;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;;

public class MonsterHunterModel {
    private CellInfo[][] labyrinth;
    private Integer step;

    public MonsterHunterModel(CellInfo[][] labyrinth) {
        this.labyrinth = labyrinth;
        this.step = 0;
    }

    public CellInfo[][] getLabyrinth() {
        return this.labyrinth;
    }

    public Integer getStep() {
        return this.step;
    }
    
}
