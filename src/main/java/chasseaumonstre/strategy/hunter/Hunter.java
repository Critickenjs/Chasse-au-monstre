package chasseaumonstre.strategy.hunter;

import fr.univlille.iutinfo.cam.player.hunter.IHunterStrategy;

public class Hunter implements IHunterStrategy {
    private boolean[][] shootLocations;
    private String name;

    public void initialize(boolean[][] locations) {
        this.shootLocations = locations;
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
}
