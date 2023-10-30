package chasseaumonstre.model;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;;

public class MonsterHunterModel {
    private CellInfo[][] maze;
    private Integer step;

    public MonsterHunterModel(CellInfo[][] maze) {
        this.maze = maze;
        this.step = 0;
    }

    public CellInfo[][] getMaze() {
        return this.maze;
    }

    public Integer getStep() {
        return this.step;
    }
    
}
