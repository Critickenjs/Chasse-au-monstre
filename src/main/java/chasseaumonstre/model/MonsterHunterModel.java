package chasseaumonstre.model;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent;

public class MonsterHunterModel {
    private ICellEvent.CellInfo[] labyrinth;
    private Integer step;

    public MonsterHunterModel(ICellEvent.CellInfo[] labyrinth, Integer step) {
        this.labyrinth = labyrinth;
        this.step = step;
    }

    public ICellEvent.CellInfo[] getLabyrinth() {
        return this.labyrinth;
    }

    public Integer getStep() {
        return this.step;
    }
    
}
