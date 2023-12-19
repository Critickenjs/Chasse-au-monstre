package fr.univlille.info.J3.chasseaumonstre.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
     * 0 : chemin
     * 1 : mur
     * 2 : monstre (entrée)
     * 3 : chasseur
     * 4 : sortie
     * 
     * 
     * @return le labyrinthe généré
     */
    public void generate() {
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                maze[x][y] = 1;

        int entrance = random.nextInt(width);
        int exit = random.nextInt(width);
        maze[entrance][0] = 0;
        maze[exit][height - 1] = 0;

        this.entranceCoordinate = new Coordinate(entrance, 0);
        this.exitCoordinate = new Coordinate(exit, height - 1);
        this.generatePath(entrance, exit);
    }

    /*
     * Génère un labyrinthe aléatoirement (tableau d'entiers) avec une entrée et une
     * sortie
     * avec un pourcentage d'obstacles
     */
    public void generatePlateau(int obstacle) {
        int entrancex = 0;
        int entrancey = 0;
        int exit = 0;
        if (obstacle < 0 || obstacle > 100) {
            throw new IllegalArgumentException("Le pourcentage doit être compris entre 0 et 100 inclus.");
        }
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                maze[x][y] = random.nextInt(100) < obstacle ? 1 : 0;

        entrancex = random.nextInt(width);
        entrancey = random.nextInt(height / 2 - 1);
        exit = random.nextInt(width);
        maze[entrancex][entrancey] = 0;
        maze[exit][height - 1] = 0;

        this.entranceCoordinate = new Coordinate(entrancex, entrancey);
        this.exitCoordinate = new Coordinate(exit, height - 1);
        this.dfs(entrancex, entrancey, exit, height - 1);

    }

    private void generatePath(int x, int y) {
        int nextX, nextY;
        List<Integer> directions = Arrays.asList(1, 2, 3, 4);
        Collections.shuffle(directions);

        for (int direction : directions) {
            nextX = x;
            nextY = y;
            switch (direction) {
                case 1:
                    nextY = y - 2;
                    break;
                case 2:
                    nextY = y + 2;
                    break;
                case 3:
                    nextX = x - 2;
                    break;
                case 4:
                    nextX = x + 2;
                    break;
            }

            if ((nextX > 0 && nextX < width - 1) && (nextY > 0 && nextY < height - 1) && maze[nextX][nextY] == 1) {
                maze[nextX][nextY] = 0;
                maze[(x + nextX) / 2][(y + nextY) / 2] = 0;
                if ((nextX > 1 && nextX < width - 2) && (nextY > 1 && nextY < height - 2) && maze[nextX][nextY] == 1) {
                    this.generatePath(nextX, nextY);
                }
            }
        }
    }

    public void dfs(int startX, int startY, int endX, int endY) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] { startX, startY });

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int currentX = current[0];
            int currentY = current[1];

            if (currentX < 0 || currentX >= maze.length || currentY < 0 || currentY >= maze[0].length
                    || maze[currentX][currentY] == 1 || visited[currentX][currentY]) {
                continue;
            }

            visited[currentX][currentY] = true;

            if (currentX == endX && currentY == endY) {
                 break;
            } else {
                maze[currentX][currentY] = 0;
            }

            stack.push(new int[] { currentX - 1, currentY });
            stack.push(new int[] { currentX + 1, currentY });
            stack.push(new int[] { currentX, currentY - 1 });
            stack.push(new int[] { currentX, currentY + 1 });
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
