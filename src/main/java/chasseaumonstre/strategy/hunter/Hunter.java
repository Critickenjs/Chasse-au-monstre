package chasseaumonstre.strategy.hunter;

import chasseaumonstre.model.ICellEvent;
import chasseaumonstre.model.ICoordinate;

public class Hunter implements IHunterStrategy {
    private boolean[][] shootLocations;
    private String name;

    public boolean[][] getShootLocations() {
        return this.shootLocations;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void initialize(boolean[][] locations) {
        this.shootLocations = locations;
    }

    @Override
    public ICoordinate play() {
        throw new UnsupportedOperationException("Unimplemented method 'play'");
    }

    @Override
    public void update(ICellEvent event) {
        ICoordinate coord = event.getCoordinate();
        this.shootLocations[coord.getRow()][coord.getCol()] = true;
    }
}
