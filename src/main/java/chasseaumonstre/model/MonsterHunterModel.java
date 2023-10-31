package chasseaumonstre.model;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;

/*
 * MonsterHunterModel représente le modèle, qui se charge du déroulement du jeu
 * 
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class MonsterHunterModel {
    private CellInfo[][] maze;
    private Integer step;
    private int width, height;

    /*
     * Constructeur de MonsterHunterModel
     * 
     * @param maze le labyrinthe généré
     * @param width la largeur du labyrinthe
     * @param heigth la hauteur du labyrinthe
     */
    public MonsterHunterModel(int width, int height) {
        this.step = 0;
        this.width = width;
        this.height = height;
        this.initializeMaze(width, height);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
    
    /*
     * Génère un labyrinthe aléatoirement validé par MazeValidator
     * 
     * @see MazeValidator
     * @param width la largeur du labyrinthe
     * @param heigth la hauteur du labyrinthe
     */
    private void initializeMaze(int width, int heigth) {
        MazeGenerator mazeGenerator = new MazeGenerator(width, heigth);
        mazeGenerator.generate();
        int [][] tmpMaze = mazeGenerator.getMaze();

        MazeValidator mazeValidator = new MazeValidator(width, heigth, tmpMaze);

        while (!mazeValidator.isValid()) {
            mazeGenerator.generate();
            mazeValidator.setMaze(mazeGenerator.getMaze());
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
