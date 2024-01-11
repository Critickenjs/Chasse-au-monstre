package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

import java.util.ArrayList;
import java.util.List;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import fr.univlille.info.J3.chasseaumonstre.model.Coordinate;

/**
 * DepthFirstSearch est une implémentation de l'interface Algorithm utilisant la
 * recherche en profondeur (DFS) pour la recherche de chemin dans un labyrinthe.
 * Elle explore le labyrinthe du point d'entrée au point de sortie, tentant de
 * trouver un chemin valide.
 * Si un chemin valide est trouvé, il est retourné ; sinon, null est retourné.
 *
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 * @see Algorithm
 */
public class DepthFirstSearch implements Algorithm {
    ICoordinate entry;
    ICoordinate exit;
    boolean[][] maze;
    double time;

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

    /**
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

        List<ICoordinate> path = new ArrayList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        if (dfs(entry, path, visited)) {
            time = System.currentTimeMillis() - time;
            return path;
        }

        time = System.currentTimeMillis() - time;
        return null;
    }

    /**
     * Méthode auxiliaire récursive pour l'algorithme de recherche en profondeur.
     *
     * @param current La coordonnée actuelle en cours d'exploration.
     * @param path    Le chemin actuel en cours de construction.
     * @param visited Tableau indiquant les cellules déjà visitées.
     * @return True si un chemin valide est trouvé, sinon False.
     */
    private boolean dfs(ICoordinate current, List<ICoordinate> path, boolean[][] visited) {
        int row = current.getRow();
        int col = current.getCol();

        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || visited[row][col] || !maze[row][col]) {
            return false;
        }

        visited[row][col] = true;
        path.add(current);

        if (current.equals(exit)) {
            return true;
        }

        if (dfs(new Coordinate(row - 1, col), path, visited) ||
                dfs(new Coordinate(row, col + 1), path, visited) ||
                dfs(new Coordinate(row + 1, col), path, visited) ||
                dfs(new Coordinate(row, col - 1), path, visited)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    @Override
    public double getTime() {
        return time;
    }
}
