package chasseaumonstre.model;

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

    /*
     * Constructeur de MazeValidator
     * 
     * @param width la largeur du labyrinthe
     * @param height la hauteur du labyrinthe
     * @param maze le labyrinthe généré
     */
    public MazeValidator(int width, int height, int[][] maze) {
        this.width = width;
        this.height = height;
        this.maze = maze;
        this.visited = new boolean[width][height];
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public int[][] getMaze() {
        return this.maze;
    }

    private void initializeVisited() {
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                visited[x][y] = false;
    }

    private int getEntrance() {
        for(int i = 0; i < this.width; i++)
            if (maze[i][0] == 0)
                return i;
        return -1;
    }

    /*
     * Vérifie si le labyrinthe contient un chemin entre l'entrée et la sortie
     * 
     * @return true si le labyrinthe contient un chemin entre l'entrée et la sortie, false sinon
     */
    public boolean isValid() {
        this.initializeVisited();
        int entranceX = this.getEntrance();
        return entranceX == -1 ? false : dfs(entranceX, 0);
    }

    private boolean dfs(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height || maze[x][y] == 1 || visited[x][y])
            return false;
        
        visited[x][y] = true;

        if (y == height - 1)
            return true;

        return dfs(x - 1, y) || dfs(x + 1, y) || dfs(x, y - 1) || dfs(x, y + 1);
    }
}

