package chasseaumonstre.strategy.monster;

import chasseaumonstre.model.Coordinate;
import fr.univlille.iutinfo.cam.player.monster.IMonsterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

public class Monster implements IMonsterStrategy {
    private ICoordinate exit;
    private ICoordinate entry;
    private ICoordinate coord;
    private boolean[][] visited;

    public Monster() {
        this.exit = null;
        this.entry = null;
        this.coord = null;
        this.visited = null;
    }

    public Monster(boolean[][] locations) {
        this();
        initialize(locations);
    }

    public void initialize(boolean[][] locations) {
        this.visited = locations;
    }

    public ICoordinate getExit() {
        return exit;
    }

    public void setExit(int row, int col) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        setExit(new Coordinate(row, col));
    }

    private void setExit(ICoordinate exit) {
        this.exit = exit;
    }

    public ICoordinate getEntry() {
        return entry;
    }

    public void setEntry(int row, int col) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        setEntry(new Coordinate(row, col));
    }

    private void setEntry(ICoordinate entry) {
        this.entry = entry;
    }

    public ICoordinate getCoord() {
        return coord;
    }

    private void setCoord(ICoordinate coord) {
        this.coord = coord;
    }

    public void setCoord(int row, int col) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        setCoord(new Coordinate(row, col));
    }

    public boolean isVisited(int row, int col) {
        try {
            checkCoord(row, col);
            return visited[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public void setVisited(int row, int col) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        visited[row][col] = true;
    }

    public void setVisited(ICoordinate coord) {
        setVisited(coord.getRow(), coord.getCol());
    }

    private void checkCoord(int row, int col) throws ArrayIndexOutOfBoundsException {
        if ((row < 0 || row >= visited.length) || (col < 0 || col >= visited[0].length)) {
            throw new ArrayIndexOutOfBoundsException("Row " + row + "/" + visited.length + " or column " + col + "/" + visited[0].length);
        }
    }

    public Coordinate play() {
        // TODO (partie 2 - implémentation de l'IA du monstre)
        return null;
    }

    public void update(ICellEvent event) {
        switch (event.getState()) {
            case MONSTER:
                setCoord(event.getCoord());
            case EXIT:
                setExit(event.getCoord());
            case WALL:
                setEntry(event.getCoord());
                break;
            default:
                setVisited(event.getCoord());
                break;
        }
    }
}
