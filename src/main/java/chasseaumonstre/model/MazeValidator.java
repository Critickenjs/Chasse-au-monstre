package chasseaumonstre.model;

public class MazeValidator {
    private final int width;
    private final int height;
    private final int[][] maze;
    private final boolean[][] visited;

    public MazeValidator(int width, int height, int[][] maze) {
        this.width = width;
        this.height = height;
        this.maze = maze;
        this.visited = new boolean[width][height];
    }

    public boolean isPathValid() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                visited[x][y] = false;
            }
        }

        int entranceX = -1;
        for (int x = 0; x < width; x++) {
            if (maze[x][0] == 0) {
                entranceX = x;
                break;
            }
        }

        if (entranceX == -1) {
            return false;
        }

        return dfs(entranceX, 0);
    }

    private boolean dfs(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height || maze[x][y] == 1 || visited[x][y]) {
            return false;
        }

        visited[x][y] = true;

        if (y == height - 1) {
            return true;
        }

        return dfs(x - 1, y) || dfs(x + 1, y) || dfs(x, y - 1) || dfs(x, y + 1);
    }
}

