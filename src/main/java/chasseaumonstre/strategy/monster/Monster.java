package chasseaumonstre.strategy.monster;

import chasseaumonstre.model.CellEvent;
import chasseaumonstre.model.Coordinate;

public class Monster implements IMonsterStrategy {
    private Coordinate exit;
    private Coordinate entry;
    private Coordinate coord;
    private boolean[][] visited;

    public Monster() {
        this.exit = null;
        this.entry = null;
        this.coord = null;
        this.visited = null;
    }

    public Monster(Integer nbRows, Integer nbCols) {
        this();
        initialize(nbRows, nbCols);
    }

    public void initialize(Integer nbRows, Integer nbCols) {
        this.visited = new boolean[nbRows][nbCols];
    }

    public Coordinate getExit() {
        return exit;
    }

    public void setExit(int row, int col) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        setExit(new Coordinate(row, col));
    }

    private void setExit(Coordinate exit) {
        this.exit = exit;
    }

    public Coordinate getEntry() {
        return entry;
    }

    public void setEntry(int row, int col) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        setEntry(new Coordinate(row, col));
    }

    private void setEntry(Coordinate entry) {
        this.entry = entry;
    }

    public Coordinate getCoord() {
        return coord;
    }

    private void setCoord(Coordinate coord) {
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

    public void setVisited(Coordinate coord) {
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

    public void update(CellEvent event) {
        switch (event.getState()) {
            case MONSTER:
                setCoord(event.getCoordinate());
            case EXIT:
                setExit(event.getCoordinate());
            case ENTER:
                setEntry(event.getCoordinate());
            default:
                break;
        }
        setVisited(event.getCoordinate());
    }
}
