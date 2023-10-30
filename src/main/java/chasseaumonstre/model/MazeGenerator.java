package chasseaumonstre.model;

import java.util.Random;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;

public class MazeGenerator {
    private final int width;
    private final int height;
    private final int[][] maze;
    private final Random random;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[width][height];
        this.random = new Random();
    }

    public int[][] generateMazeWithEntranceAndExit() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                maze[x][y] = 1;
            }
        }

        int entranceX = random.nextInt(width - 2) + 1;
        int exitX = random.nextInt(width - 2) + 1;
        maze[entranceX][0] = 0;
        maze[exitX][height - 1] = 0;

        int startX = random.nextInt(width - 2) + 1;
        int startY = 1;
        maze[startX][startY] = 0;
        generatePath(startX, startY);

        return maze;
    }

    private void generatePath(int x, int y) {
        int[] directions = { 1, 2, 3, 4 };
        shuffleArray(directions);

        for (int direction : directions) {
            int nextX = x;
            int nextY = y;

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

            if (nextX > 0 && nextX < width - 1 && nextY > 0 && nextY < height - 1 && maze[nextX][nextY] == 1) {
                maze[nextX][nextY] = 0;
                maze[(x + nextX) / 2][(y + nextY) / 2] = 0;
                generatePath(nextX, nextY);
            }
        }
    }

    private void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }

    public void printMaze() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(maze[x][y] == 1 ? "█" : " ");
            }
            System.out.println();
        }
    }

    public CellInfo[][] toLabyrinth() {
        CellInfo[][] labyrinth = new CellInfo[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                switch (maze[i][j]) {
                    case 0:
                        labyrinth[i][j] = CellInfo.EMPTY;
                        break;
                    case 1:
                        labyrinth[i][j] = CellInfo.WALL;
                        break;
                    case 2:
                        labyrinth[i][j] = CellInfo.MONSTER;
                        break;
                    case 3:
                        labyrinth[i][j] = CellInfo.HUNTER;
                        break;
                    case 4:
                        labyrinth[i][j] = CellInfo.EXIT;
                        break;
                    default:
                        labyrinth[i][j] = CellInfo.EMPTY;
                        break;
                }
            }
        }

        return labyrinth;
    }

    public static void main(String[] args) {
        int width = 21;
        int height = 21;

        MazeGenerator mazeGenerator = new MazeGenerator(width, height);
        int[][] maze = mazeGenerator.generateMazeWithEntranceAndExit();
        
        CellInfo[][] labyrinth = mazeGenerator.toLabyrinth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (labyrinth[y][x] == CellInfo.WALL) {
                    System.out.print("█");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        MazeValidator pathValidator = new MazeValidator(width, height, maze);

        if (pathValidator.isPathValid()) {
            System.out.println("Il y a un chemin valide de l'entrée à la sortie.");
        } else {
            System.out.println("Il n'y a pas de chemin valide de l'entrée à la sortie.");
        }
    }
}
