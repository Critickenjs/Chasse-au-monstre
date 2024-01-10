package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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

    /*
     * Execute l'algorithme A*
     * 
     * @return la liste des coordonnées du chemin
     */
    @Override
    public List<ICoordinate> execute() {
        if (entry == null || exit == null || maze == null || !maze[entry.getRow()][entry.getCol()]
                || !maze[exit.getRow()][exit.getCol()]) {
            return null;
        }
        time = System.currentTimeMillis();

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getFScore));
        Map<ICoordinate, Integer> gScore = new HashMap<>();
        Map<ICoordinate, Node> cameFrom = new HashMap<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        try {
            openSet.offer(new Node(entry, 0, heuristicCost(entry, exit)));
            gScore.put(entry, 0);

            while (!openSet.isEmpty() && openSet.size() < this.maze.length * this.maze[0].length) {
                Node current = openSet.poll();
                visited[current.getCoordinate().getRow()][current.getCoordinate().getCol()] = true;

                if (current.getCoordinate().equals(exit)) {
                    time = System.currentTimeMillis() - time;
                    return AlgorithmUtils.reconstructPath(cameFrom, current);
                }

                for (ICoordinate neighbor : AlgorithmUtils.getNeighbors(current.getCoordinate(), maze)) {
                    if (visited[neighbor.getRow()][neighbor.getCol()]) {
                        continue;
                    }
                    int tentativeGScore = gScore.getOrDefault(current.getCoordinate(), Integer.MAX_VALUE) + 1;

                    if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        cameFrom.put(neighbor, current);
                        gScore.put(neighbor, tentativeGScore);

                        int fScore = tentativeGScore + heuristicCost(neighbor, exit);
                        Node neighborNode = new Node(neighbor, tentativeGScore, fScore);
                        openSet.offer(neighborNode);
                    }
                }
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Out of memory");
        }

        time = System.currentTimeMillis() - time;
        return null;
    }

    /*
     * Calcule le coût heuristique
     * 
     * @param a la coordonnée a
     * 
     * @param b la coordonnée b
     * 
     * @return le coût heuristique
     */
    public int heuristicCost(ICoordinate a, ICoordinate b) {
        // Heuristique : distance de Manhattan entre les deux points
        return Math.abs(a.getRow() - b.getRow()) + Math.abs(a.getCol() - b.getCol());
    }

    @Override
    public double getTime() {
        return time;
    }
}
