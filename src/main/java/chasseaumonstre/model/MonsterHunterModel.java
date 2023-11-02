package chasseaumonstre.model;

import chasseaumonstre.strategy.hunter.Hunter;
import chasseaumonstre.strategy.monster.Monster;
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
    private String monsterName, hunterName;
    private Monster monster;
    private Hunter hunter;
    private Coordinate entrance, exit;

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
        this.monster = new Monster();
        this.hunter = new Hunter();
        this.monster.initialize(new boolean[width][height]);
        this.hunter.initialize(new boolean[width][height]);
        this.initializeMaze(width, height);
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public String getHunterName() {
        return hunterName;
    }

    public void setHunterName(String hunterName) {
        this.hunterName = hunterName;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
    
    public Monster getMonster() {
        return monster;
    }

    public Hunter getHunter() {
        return hunter;
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
        entrance = new Coordinate(mazeGenerator.getEntrance(), 0);
        exit = new Coordinate(mazeGenerator.getExit(), heigth - 1);
        monster.setCoord(entrance.getRow(), entrance.getCol());

        this.maze = mazeGenerator.toCellInfo();
    }

    public CellInfo[][] getMaze() {
        return this.maze;
    }

    public Integer getStep() {
        return this.step;
    }

    public Coordinate getEntrance() {
        return entrance;
    }

    public Coordinate getExit() {
        return exit;
    }

    public void nextStep() {
        this.step++;
    }
}
