package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import fr.univlille.info.J3.chasseaumonstre.model.Coordinate;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/*
 * Algorithme A* pour la recherche de chemin
 * 
 * @see Algorithm
 * @autor Anas Ouhdda
 * @autor Atilla Tas
 * @autor Karim Aoulad-Tayab
 * @autor Selim Hamza
 * @autor Yliess El Atifi
 */
public class AStar implements Algorithm {
    ICoordinate entry;
    ICoordinate exit;
    boolean[][] maze;
    double time;

    public AStar(ICoordinate entry, ICoordinate exit, boolean[][] maze) {
        this.entry = entry;
        this.exit = exit;
        this.maze = maze;
    }

    @Override
    public ICoordinate getEntry() {
        return entry;
    }

    @Override
    public ICoordinate getExit() {
        return exit;
    }

    @Override
    public boolean[][] getMaze() {
        return maze;
    }

    @Override
    public List<ICoordinate> execute() {
        if (entry == null || exit == null || maze == null) {
            return null;
        }
        time = System.currentTimeMillis();

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getFScore));
        Map<ICoordinate, Integer> gScore = new HashMap<>();
        Map<ICoordinate, ICoordinate> cameFrom = new HashMap<>();

        try {
            openSet.offer(new Node(entry, 0, heuristicCost(entry, exit)));
            gScore.put(entry, 0);

            while (!openSet.isEmpty() && openSet.size() < this.maze.length * this.maze[0].length) {
                Node current = openSet.poll();

                if (current.getCoordinate().equals(exit)) {
                    time = System.currentTimeMillis() - time;
                    return reconstructPath(cameFrom, current.getCoordinate());
                }

                for (ICoordinate neighbor : getNeighbors(current.getCoordinate())) {
                    int tentativeGScore = gScore.getOrDefault(current.getCoordinate(), Integer.MAX_VALUE) + 1;

                    if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        cameFrom.put(neighbor, current.getCoordinate());
                        gScore.put(neighbor, tentativeGScore);

                        int fScore = tentativeGScore + heuristicCost(neighbor, exit);
                        Node neighborNode = new Node(neighbor, tentativeGScore, fScore);
                        openSet.offer(neighborNode);
                    }
                }
            }
        } catch (OutOfMemoryError e) {
            return null;
        }

        time = System.currentTimeMillis() - time;
        return null;
    }

    @Override
    public List<ICoordinate> reconstructPath(Map<ICoordinate, ICoordinate> cameFrom, ICoordinate current) {
        List<ICoordinate> totalPath = new ArrayList<>();
        totalPath.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(0, current);
        }

        return totalPath;
    }

    @Override
    public int heuristicCost(ICoordinate a, ICoordinate b) {
        // Heuristique : distance de Manhattan entre les deux points
        return Math.abs(a.getRow() - b.getRow()) + Math.abs(a.getCol() - b.getCol());
    }

    @Override
    public List<ICoordinate> getNeighbors(ICoordinate current) {
        List<ICoordinate> neighbors = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int row = current.getRow();
            int col = current.getCol();

            switch (i) {
                case 0:
                    row--;
                    break;
                case 1:
                    row++;
                    break;
                case 2:
                    col--;
                    break;
                case 3:
                    col++;
                    break;
            }

            if (row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col]) {
                neighbors.add(new Coordinate(row, col));
            }
        }

        return neighbors;
    }

    @Override
    public double getTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTime'");
    }

    

}
