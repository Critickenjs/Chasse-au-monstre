package fr.univlille.info.J3.chasseaumonstre.model;

import fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm.AStar;
import fr.univlille.iutinfo.cam.player.monster.IMonsterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/*
 * MazeValidator valide un labyrinthe généré, s'il contient bien un chemin entre l'entrée et la sortie
 * 
 * @see MazeGenerator
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class MazeValidator {
    private int[][] maze;
    private MazeGenerator mazeGenerator;

    /*
     * Constructeur de MazeValidator
     * 
     * @param width la largeur du labyrinthe
     * 
     * @param height la hauteur du labyrinthe
     * 
     * @param maze le labyrinthe généré
     */
    public MazeValidator(MazeGenerator mazeGenerator) {
        this.maze = mazeGenerator.getMaze();
        this.mazeGenerator = mazeGenerator;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public int[][] getMaze() {
        return this.maze;
    }

    private Coordinate getEntrance() {
        return this.mazeGenerator.getEntranceCoordinate();
    }

    private Coordinate getExit() {
        return this.mazeGenerator.getExitCoordinate();
    }

    /*
     * Vérifie si le labyrinthe contient un chemin entre l'entrée et la sortie
     * 
     * @return true si le labyrinthe contient un chemin entre l'entrée et la sortie,
     * false sinon
     */
    public boolean isValid() {
        return isValid(maze, getEntrance(), getExit());
    }

    public static boolean isValid(int[][] maze, Coordinate entrance, Coordinate exit) {
        return isValid(MazeGenerator.toBoolean(maze), entrance, exit);
    }

    public static boolean isValid(boolean[][] maze, Coordinate entrance, Coordinate exit) {

        IMonsterStrategy astar = new AStar(entrance, exit, maze);
        astar.initialize(maze);
        ICoordinate next = astar.play();
        if (next == null) {
            return false;
        }
        return true;
    }
}
