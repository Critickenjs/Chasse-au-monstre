package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

import java.util.*;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/*
 * Algorithme de Dijkstra pour la recherche de chemin
 * 
 * @see Algorithm
 * @autor Anas Ouhdda
 * @autor Atilla Tas
 * @autor Karim Aoulad-Tayab
 * @autor Selim Hamza
 * @autor Yliess El Atifi
 */
public class Dijkstra implements Algorithm {
    private final ICoordinate entry;
    private final ICoordinate exit;
    private final boolean[][] maze;
    private double time;

    public Dijkstra(ICoordinate entry, ICoordinate exit, boolean[][] maze) {
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
     * Exécute l'algorithme de Dijkstra
     * 
     * @return la liste des coordonnées du chemin
     */
    @Override
    public List<ICoordinate> execute() {
        if (entry == null || exit == null || maze == null) {
            return null;
        }
        time = System.currentTimeMillis();

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getGScore));
        Map<ICoordinate, Integer> gScore = new HashMap<>();
        Map<ICoordinate, Node> cameFrom = new HashMap<>();

        try {
            openSet.offer(new Node(entry, 0, 0));
            gScore.put(entry, 0);

            while (!openSet.isEmpty()) {
                Node current = openSet.poll();

                if (current.getCoordinate().equals(exit)) {
                    time = System.currentTimeMillis() - time;
                    return AlgorithmUtils.reconstructPath(cameFrom, current);
                }

                for (ICoordinate neighbor : AlgorithmUtils.getNeighbors(current.getCoordinate(), maze)) {
                    int tentativeGScore = gScore.getOrDefault(current.getCoordinate(), Integer.MAX_VALUE) + 1;

                    if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        cameFrom.put(neighbor, current);
                        gScore.put(neighbor, tentativeGScore);

                        Node neighborNode = new Node(neighbor, tentativeGScore, 0);
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
    public double getTime() {
        return time;
    }
}
