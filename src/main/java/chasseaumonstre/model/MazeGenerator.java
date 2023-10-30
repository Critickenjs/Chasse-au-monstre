package chasseaumonstre.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    public int[][] getMaze() {
        return this.maze;
    }

    public void generate() {
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                maze[x][y] = 1;

        int entrance = random.nextInt(width);
        int exit = random.nextInt(width);
        maze[entrance][0] = 0;
        maze[exit][height - 1] = 0;

        this.generatePath(entrance, exit);
    }

    private void generatePath(int x, int y) {
        int nextX, nextY;
        List<Integer> directions = Arrays.asList(1,2,3,4);
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
                this.generatePath(nextX, nextY);
            }
        }
    }

    public CellInfo[][] toCellInfo() {
        CellInfo[][] labyrinth = new CellInfo[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (maze[x][y]) {
                    case 0:
                        labyrinth[x][y] = CellInfo.EMPTY;
                        break;
                    default:
                        labyrinth[x][y] = CellInfo.WALL;
                        break;
                }
            }
        }
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
