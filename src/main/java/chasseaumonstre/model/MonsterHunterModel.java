package chasseaumonstre.model;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;;

public class MonsterHunterModel {
    private CellInfo[][] maze;
    private Integer step;

    public MonsterHunterModel(CellInfo[][] maze, int width, int heigth) {
        this.maze = maze;
        this.step = 0;

        initializeMaze(width, heigth);
    }

    private void initializeMaze(int width, int heigth) {
        MazeGenerator mazeGenerator = new MazeGenerator(width, heigth);
        int [][] tmpMaze = mazeGenerator.generateMazeWithEntranceAndExit();
        MazeValidator mazeValidator = new MazeValidator(width, heigth, tmpMaze);

        while (!mazeValidator.isPathValid()) {
            tmpMaze = mazeGenerator.generateMazeWithEntranceAndExit();
        }

        maze = mazeGenerator.toLabyrinth();
    }

    public CellInfo[][] getMaze() {
        return this.maze;
    }

    public Integer getStep() {
        return this.step;
    }
}
