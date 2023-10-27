package chasseaumonstre.strategy.hunter;

import fr.univlille.iutinfo.cam.player.hunter.IHunterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

public class Hunter implements IHunterStrategy {
    private boolean[][] shootLocations;
    private String name;

    public void initialize(boolean[][] locations) {
        this.shootLocations = locations;
    }

    public void initialize(int row, int col) {
        this.shootLocations = new boolean[row][col];
    }

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
    public ICoordinate play() {
        throw new UnsupportedOperationException("Unimplemented method 'play'");
    }

    @Override
    public void update(ICellEvent event) {
        ICoordinate coord = event.getCoord();
        this.shootLocations[coord.getRow()][coord.getCol()] = true;
    }
}
