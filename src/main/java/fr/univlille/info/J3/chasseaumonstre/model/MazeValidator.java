package fr.univlille.info.J3.chasseaumonstre.model;

import java.util.List;

import fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm.AStar;
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
    private final int width;
    private final int height;
    private int[][] maze;
    private final boolean[][] visited;
    private MazeGenerator mazeGenerator;

    /*
     * Constructeur de MazeValidator
     * 
     * @param width la largeur du labyrinthe
     * @param height la hauteur du labyrinthe
     * @param maze le labyrinthe généré
     */
    public MazeValidator(MazeGenerator mazeGenerator) {
        this.width = mazeGenerator.getWidth();
        this.height = mazeGenerator.getHeight();
        this.maze = mazeGenerator.getMaze();
        this.visited = new boolean[width][height];
        this.mazeGenerator = mazeGenerator;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public int[][] getMaze() {
        return this.maze;
    }

    /*
     * Initialise le tableau visited à false
     */
    private void initializeVisited() {
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                visited[x][y] = false;
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
     * @return true si le labyrinthe contient un chemin entre l'entrée et la sortie, false sinon
     */
    public boolean isValid() {
        List<ICoordinate> astar = new AStar(this.getEntrance(), this.getExit(), this.mazeGenerator.toBoolean()).execute();
        this.initializeVisited();
        if(astar == null) {
            return false;
        }
        return true;
    }
}



