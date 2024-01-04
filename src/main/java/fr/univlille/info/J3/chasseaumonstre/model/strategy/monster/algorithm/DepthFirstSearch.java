package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

import java.util.*;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/*
 * Algorithme de recherche en profondeur (DFS) pour la recherche de chemin
 * 
 * @see Algorithm
 * @author [Votre nom ou pseudonyme]
 */
public class DepthFirstSearch implements Algorithm {
    private final ICoordinate entry;
    private final ICoordinate exit;
    private final boolean[][] maze;
    private double time;

    public DepthFirstSearch(ICoordinate entry, ICoordinate exit, boolean[][] maze) {
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
     * Exécute l'algorithme de recherche en profondeur (DFS)
     * 
     * @return la liste des coordonnées du chemin
     */
    @Override
    public List<ICoordinate> execute() {
        if (entry == null || exit == null || maze == null) {
            return null;
        }
        time = System.currentTimeMillis();

        ArrayDeque<ICoordinate> p = new ArrayDeque<>();
        Set<ICoordinate> marked = new HashSet<>();
        Set<ICoordinate> markedPath = new HashSet<>();

        p.offer(new Node(entry, 0, 0));
        marked.add(entry);

        while (p.peek() != null) {
            ICoordinate current = p.poll();
            if (current.equals(exit)) {
                time = System.currentTimeMillis() - time;
                System.out.println(p);
                return new ArrayList<>(markedPath);
            }
            for (ICoordinate neighboor: AlgorithmUtils.getNeighbors(current, maze)) {
                if (!(marked.contains(neighboor) || markedPath.contains(neighboor))) {
                    markedPath.add(neighboor);
                    p.add(neighboor);
                } else {
                    marked.add(neighboor);
                }
            } 
        }

        time = System.currentTimeMillis() - time;
        return null;
    }

    @Override
    public double getTime() {
        return time;
    }
}
