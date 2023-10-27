package chasseaumonstre.model;

public class MonsterHunterModel {
    private CellInfo[] labyrinth;
    private Integer step;

    public MonsterHunterModel(CellInfo[] labyrinth, Integer step) {
        this.labyrinth = labyrinth;
        this.step = step;
    }

    public CellInfo[] getLabyrinth() {
        return this.labyrinth;
    }

    public Integer getStep() {
        return this.step;
    }
    
}
