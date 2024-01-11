package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.univlille.info.J3.chasseaumonstre.model.Coordinate;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/**
 * AlgorithmUtils contient des méthodes utiles pour les algorithmes
 * 
 * @autor Anas Ouhdda
 * @autor Atilla Tas
 * @autor Karim Aoulad-Tayab
 * @autor Selim Hamza
 * @autor Yliess El Atifi
 */
public class AlgorithmUtils {
    /**
     * Reconstruit le chemin
     * 
     * @param cameFrom la map des coordonnées
     * @param current  la coordonnée courante
     * @return la liste des coordonnées du chemin
     */
    public static List<ICoordinate> reconstructPath(Map<ICoordinate, Node> cameFrom, Node current) {
        List<ICoordinate> totalPath = new ArrayList<>();
        totalPath.add(current.getCoordinate());

        while (cameFrom.containsKey(current.getCoordinate())) {
            current = cameFrom.get(current.getCoordinate());
            totalPath.add(0, current.getCoordinate());
        }

        return totalPath;
    }

    /**
     * Retourne les voisins d'une coordonnée
     * 
     * @param current la coordonnée courante
     * @return la liste des voisins
     */
    public static List<ICoordinate> getNeighbors(ICoordinate current, boolean[][] maze) {
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
}
