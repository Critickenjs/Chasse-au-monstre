package chasseaumonstre.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import SubjectObserver.Observer;
import SubjectObserver.Subject;
import chasseaumonstre.model.strategy.hunter.Hunter;
import chasseaumonstre.model.strategy.monster.Monster;
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
public class MonsterHunterModel extends Subject implements Serializable, Observer {
    private CellInfo[][] maze;
    private Integer turn;
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
        this.turn = 1;
        this.width = width;
        this.height = height;
        this.monster = new Monster();
        this.hunter = new Hunter();
        this.monster.initialize(new boolean[width][height]);
        this.hunter.initialize(new boolean[width][height]);
        this.monster.attach(this);
        this.hunter.attach(this);
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
        mazeGenerator.generatePlateau(15);
        int [][] tmpMaze = mazeGenerator.getMaze();

        MazeValidator mazeValidator = new MazeValidator(width, heigth, tmpMaze);

        while (!mazeValidator.isValid()) {
            mazeGenerator.generate();
            mazeValidator.setMaze(mazeGenerator.getMaze());
        }
        entrance = mazeGenerator.getEntranceCoordinate();
        exit = mazeGenerator.getExitCoordinate();
        monster.setCoord(entrance.getRow(), entrance.getCol(), 0);

        this.maze = mazeGenerator.toCellInfo();
    }

    public CellInfo[][] getMaze() {
        return this.maze;
    }

    public Integer getTurn() {
        return this.turn;
    }

    public Coordinate getEntrance() {
        return entrance;
    }

    public Coordinate getExit() {
        return exit;
    }

    public void nextTurn() {
        this.turn++;
    }

    @Override
    public void update(Subject subj) {
        this.notifyObservers();
    }
    
    @Override
    public void update(Subject subj, Object data) {
        this.update(subj);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.maze);
        oos.writeObject(this.turn);
        oos.writeObject(this.width);
        oos.writeObject(this.height);
        oos.writeObject(this.monsterName);
        oos.writeObject(this.hunterName);
        oos.writeObject(this.monster);
        oos.writeObject(this.hunter);
        oos.writeObject(this.entrance);
        oos.writeObject(this.exit);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        this.maze = (CellInfo[][])ois.readObject();
        this.turn = (Integer)ois.readObject();
        this.width = (int)ois.readObject();
        this.height = (int)ois.readObject();
        this.monsterName = (String)ois.readObject();
        this.hunterName = (String)ois.readObject();
        this.monster = (Monster)ois.readObject();
        this.hunter = (Hunter)ois.readObject();
        this.entrance = (Coordinate)ois.readObject();
        this.exit = (Coordinate)ois.readObject();
    }
}
