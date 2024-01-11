package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

import java.util.List;

import fr.univlille.iutinfo.cam.player.monster.IMonsterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/*
 * MonsterAlgorithm représente l'algorithme de recherche de chemin
 * 
 * @see AStar
 * @autor Anas Ouhdda
 * @autor Atilla Tas
 * @autor Karim Aoulad-Tayab
 * @autor Selim Hamza
 * @autor Yliess El Atifi
 */
public abstract class MonsterAlgorithm implements IMonsterStrategy {
    protected ICoordinate entry;
    protected ICoordinate exit;
    protected boolean[][] maze;
    protected List<ICoordinate> path;
    protected double time;

    public MonsterAlgorithm(ICoordinate entry, ICoordinate exit) {
        this(entry, exit, null);
    }

    public MonsterAlgorithm(ICoordinate entry, ICoordinate exit, boolean[][] maze) {
        this.entry = entry;
        this.exit = exit;
        this.initialize(maze);
    }

    public ICoordinate getEntry() {
        return entry;
    }

    public ICoordinate getExit(){
        return exit;
    }

    public boolean[][] getMaze() {
        return maze;
    }

    public double getTime() {
        return time;
    }

    /*
     * Exécute l'algorithme
     * 
     * @return la liste des coordonnées du chemin
     */
    protected abstract List<ICoordinate> execute();

    /*
     * Renvoie le prochain coup
     * 
     * @return le prochain coup
     */
    public ICoordinate play() {
        if (path == null) {
            path = execute();
        }

        if (path != null && !path.isEmpty()) {
            return path.remove(0);
        }
        return null;
    }

    public void update(ICellEvent event) {
        // TODO Auto-generated method stub
    }

    /*
     * Initialise l'algorithme
     * 
     * @param maze le labyrinthe
     */
    public void initialize(boolean[][] maze) {
        this.maze = maze;
    }
}
