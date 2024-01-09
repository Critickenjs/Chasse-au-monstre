package fr.univlille.info.J3.chasseaumonstre.model;

import java.util.Random;
import java.util.Stack;

/*
 * MazeGenerator génère un labyrinthe aléatoirement, validé par MazeValidator
 * 
 * @see CellInfo
 * @see MazeValidator
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class MazeGenerator {
    private final int width;
    private final int height;
    private final int[][] maze;
    private final Random random;
    private Coordinate entranceCoordinate;
    private Coordinate exitCoordinate;

    /*
     * Constructeur de MazeGenerator
     * 
     * @param width la largeur du labyrinthe
     * 
     * @param height la hauteur du labyrinthe
     */
    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[width][height];
        this.random = new Random();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int[][] getMaze() {
        return this.maze;
    }

    /*
     * Génère un labyrinthe aléatoirement (tableau d'entiers) avec une entrée et une
     * sortie
     * avec un pourcentage d'obstacles
     */
    public void generatePlateau(int obstacle) {

        if (obstacle < 0 || obstacle > 100) {
            throw new IllegalArgumentException("Le pourcentage doit être compris entre 0 et 100 inclus.");
        }
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                maze[x][y] = random.nextInt(100) < obstacle ? 1 : 0;

        this.generateEntanceAndExit();
        this.dfs();

    }

    private void generateEntanceAndExit() {
        int entrancex;
        int exitx;
        if (random.nextInt(2) == 1) {
            exitx = random.nextInt((height - (height / 4) - 1), height - 1);
            entrancex = random.nextInt(0, height / 4);
        } else {
            entrancex = random.nextInt(height - (height / 4) - 1, height - 1);
            exitx = random.nextInt(0, height / 4);
        }
        this.entranceCoordinate = new Coordinate(random.nextInt(width), entrancex);
        this.exitCoordinate = new Coordinate(random.nextInt(width), exitx);
        this.maze[entranceCoordinate.getRow()][entranceCoordinate.getCol()] = 0;
        this.maze[exitCoordinate.getRow()][exitCoordinate.getCol()] = 0;
    }

    public void dfs() {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] { this.getEntranceCol(), this.getEntranceRow() });

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int currentX = current[0];
            int currentY = current[1];

            if (currentX < 0 || currentX >= maze.length || currentY < 0 || currentY >= maze[0].length
                    || maze[currentX][currentY] == 1 || visited[currentX][currentY]) {
                continue;
            }

            visited[currentX][currentY] = true;

            if (currentX == this.getExitCol() && this.getExitRow() == currentY) {
                break;
            } else {
                maze[currentX][currentY] = 0;
            }

            if (currentX - 1 >= 0 && !visited[currentX - 1][currentY]) {
                stack.push(new int[] { currentX - 1, currentY });
            }
            if (currentX + 1 < maze.length && !visited[currentX + 1][currentY]) {
                stack.push(new int[] { currentX + 1, currentY });
            }
            if (currentY - 1 >= 0 && !visited[currentX][currentY - 1]) {
                stack.push(new int[] { currentX, currentY - 1 });
            }
            if (currentY + 1 < maze[0].length && !visited[currentX][currentY + 1]) {
                stack.push(new int[] { currentX, currentY + 1 });
            }
        }
    }

    public Coordinate getEntranceCoordinate() {
        return entranceCoordinate;
    }

    public Coordinate getExitCoordinate() {
        return exitCoordinate;
    }

    public int getEntranceRow() {
        return getEntranceCoordinate().getRow();
    }

    public int getEntranceCol() {
        return getEntranceCoordinate().getCol();
    }

    public int getExitRow() {
        return getExitCoordinate().getRow();
    }

    public int getExitCol() {
        return getExitCoordinate().getCol();
    }

    public static boolean toBoolean(int n) {
        return n == 0;
    }

    public boolean[][] toBoolean() {
        return toBoolean(maze);
    }

    /*
     * Convertit le labyrinthe en un tableau de booléens
     * 
     * @return le labyrinthe converti
     */
    public static boolean[][] toBoolean(int[][] maze) {
        boolean[][] labyrinth = new boolean[maze.length][maze[0].length];
        for (int y = 0; y < maze.length; y++)
            for (int x = 0; x < maze[0].length; x++)
                labyrinth[y][x] = toBoolean(maze[y][x]);
        return labyrinth;
    }

    @Override
    public String toString() {
        String output = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                output += maze[x][y] == 1 ? "█" : " ";
            output += "\n";
        }
        return output;
    }
}
