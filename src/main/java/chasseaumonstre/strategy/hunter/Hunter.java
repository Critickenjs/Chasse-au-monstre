package chasseaumonstre.strategy.hunter;

public class Hunter implements IHunterStrategy {
    private boolean[][] shootLocations;
    private String name;

    @Override
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
