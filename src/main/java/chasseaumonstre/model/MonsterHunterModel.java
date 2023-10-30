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

    /*
     * Constructeur de MonsterHunterModel
     * 
     * @param maze le labyrinthe généré
     * @param width la largeur du labyrinthe
     * @param heigth la hauteur du labyrinthe
     */
    public MonsterHunterModel(int width, int heigth) {
        this.step = 0;
        this.initializeMaze(width, heigth);
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
