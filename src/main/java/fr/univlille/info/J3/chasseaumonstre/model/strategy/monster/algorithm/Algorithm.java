package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

import java.util.List;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/**
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

    /**
     * Exécute l'algorithme
     * 
     * @return la liste des coordonnées du chemin
     */
    public List<ICoordinate> execute();

}
