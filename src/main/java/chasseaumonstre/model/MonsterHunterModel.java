package chasseaumonstre.model;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;

public class MonsterHunterModel {
    private CellInfo[][] maze;
    private Integer step;

    public MonsterHunterModel(CellInfo[][] maze, int width, int heigth) {
        this.maze = maze;
        this.step = 0;
        this.initializeMaze(width, heigth);
    }

    private void initializeMaze(int width, int heigth) {
        MazeGenerator mazeGenerator = new MazeGenerator(width, heigth);
        mazeGenerator.generate();
        int [][] tmpMaze = mazeGenerator.getMaze();

        MazeValidator mazeValidator = new MazeValidator(width, heigth, tmpMaze);

        while (!mazeValidator.isValid()) {
            mazeGenerator.generate();
            tmpMaze = mazeGenerator.getMaze();
        }

        this.maze = mazeGenerator.toCellInfo();
    }

    public CellInfo[][] getMaze() {
        return this.maze;
    }

    public Integer getStep() {
        return this.step;
    }
}
