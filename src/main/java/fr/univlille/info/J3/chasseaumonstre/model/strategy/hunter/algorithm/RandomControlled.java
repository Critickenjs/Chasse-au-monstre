package fr.univlille.info.J3.chasseaumonstre.model.strategy.hunter.algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import fr.univlille.info.J3.chasseaumonstre.model.Coordinate;
import fr.univlille.iutinfo.cam.player.hunter.IHunterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

public class RandomControlled implements IHunterStrategy {
    private int arg0;
    private int arg1;
    private Stack<ICoordinate> neighboursCellsExploration;
    private boolean[][] visited;
    private boolean[][] shoot;

    @Override
    public void initialize(int arg0, int arg1) {
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.neighboursCellsExploration = new Stack<>();
        this.visited = new boolean[arg0][arg1];
        this.shoot = new boolean[arg0][arg1];

    }

    public boolean isVisited(int row, int col) {
        return this.visited[row][col];
    }

    public boolean hasShot(int row, int col) {
        return this.shoot[row][col];
    }

    @Override
    public ICoordinate play() {
        ICoordinate coordinate;
        if (!this.neighboursCellsExploration.isEmpty()) {
            coordinate = this.neighboursCellsExploration.pop();
            List<ICoordinate> neighbours = this.getNeighbours(coordinate);
            for (ICoordinate c : neighbours) {
                if (!this.hasShot(c.getRow(), c.getCol()) && this.isVisited(c.getRow(), c.getCol())) {
                    shoot[c.getRow()][c.getCol()] = true;
                    this.neighboursCellsExploration.push(c);
                    return c;
                }
            }
        }
        Random r = new Random();
        ICoordinate shuffleCoordinate = new Coordinate(r.nextInt(this.visited.length),
                r.nextInt(this.visited[0].length));
        while (this.hasShot(shuffleCoordinate.getRow(), shuffleCoordinate.getCol())) {
            shuffleCoordinate = new Coordinate(r.nextInt(this.visited.length), r.nextInt(this.visited[0].length));
        }
        this.neighboursCellsExploration.push(shuffleCoordinate);
        return shuffleCoordinate;
    }

    /**
     * retourne les voisins d'une cellule
     *
     * @param coordinate les coordonnées de la cellule
     * @return la liste de voisins.
     */
    private List<ICoordinate> getNeighbours(ICoordinate coordinate) {
        List<ICoordinate> coordinates = new ArrayList<>();
        Integer x = coordinate.getCol();
        Integer y = coordinate.getRow();
        // ---
        coordinates.add(new Coordinate(y, x - 1));
        coordinates.add(new Coordinate(y, x + 1));
        // ---
        coordinates.add(new Coordinate(y - 1, x - 1));
        coordinates.add(new Coordinate(y - 1, x));
        coordinates.add(new Coordinate(y - 1, x + 1));
        // ---
        coordinates.add(new Coordinate(y + 1, x - 1));
        coordinates.add(new Coordinate(y + 1, x));
        coordinates.add(new Coordinate(y + 1, x + 1));
        Iterator<ICoordinate> it = coordinates.iterator();
        ICoordinate c;
        while (it.hasNext()) {
            c = it.next();
            if (!((c.getCol() >= 0 && c.getCol() < this.visited[0].length)
                    && (c.getRow() >= 0 && c.getRow() < this.visited.length)))
                it.remove();
        }
        return coordinates;
    }

    @Override
    public void update(ICellEvent arg0) {

    }
}
