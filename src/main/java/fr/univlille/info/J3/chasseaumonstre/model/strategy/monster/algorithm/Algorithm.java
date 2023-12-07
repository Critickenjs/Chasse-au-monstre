package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

import java.util.List;
import java.util.Map;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/*
 * Algorithm représente l'algorithme de recherche de chemin
 * 
 * @see AStar
 * @autor Anas Ouhdda
 * @autor Atilla Tas
 * @autor Karim Aoulad-Tayab
 * @autor Selim Hamza
 * @autor Yliess El Atifi
 */
public interface Algorithm {
    public ICoordinate getEntry();

    public ICoordinate getExit();

    public boolean[][] getMaze();

    public double getTime();

    /*
     * Exécute l'algorithme
     * 
     * @return la liste des coordonnées du chemin
     */
    public List<ICoordinate> execute();

    /*
     * Reconstruit le chemin
     * 
     * @param cameFrom la map des coordonnées
     * @param current la coordonnée courante
     * @return la liste des coordonnées du chemin
     */
    public List<ICoordinate> reconstructPath(Map<ICoordinate, ICoordinate> cameFrom, ICoordinate current);

    /*
     * Calcule le coût heuristique
     * 
     * @param a la coordonnée a
     * @param b la coordonnée b
     * @return le coût heuristique
     */
    public int heuristicCost(ICoordinate a, ICoordinate b);

    /*
     * Retourne les voisins d'une coordonnée
     * 
     * @param current la coordonnée courante
     * @return la liste des voisins
     */
    public List<ICoordinate> getNeighbors(ICoordinate current);

}
