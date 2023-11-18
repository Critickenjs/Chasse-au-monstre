package chasseaumonstre.model;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import SubjectObserver.Observer;
import SubjectObserver.Subject;
import chasseaumonstre.App;
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
    public static final int DEFAULT_WIDTH = 21;
    public static final int DEFAULT_HEIGHT = 17;
    private CellInfo[][] maze;
    private Integer turn;
    private String monsterName, hunterName;
    private Monster monster;
    private Hunter hunter;
    private Coordinate entrance, exit;

    /*
     * Constructeur de MonsterHunterModel
     */
    public MonsterHunterModel() {
        this.turn = 1;
        this.monster = new Monster();
        this.hunter = new Hunter();
    }

    public void initialize() {
        this.monster.initialize(new boolean[getWidth()][getHeight()]);
        this.hunter.initialize(new boolean[getWidth()][getHeight()]);
        this.monster.attach(this);
        this.hunter.attach(this);
        this.initializeMaze();
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
        return App.PREFERENCES.getInt("mazeWidth", DEFAULT_WIDTH);
    }

    public int getHeight() {
        return App.PREFERENCES.getInt("mazeHeight", DEFAULT_HEIGHT);
    }

    public int getObstacles() {
        return App.PREFERENCES.getInt("obstacles", 1);
    }
    
    public void setWidth(int width) throws IllegalArgumentException {
        if (width >= 7 && width % 2 > 0) {
            App.PREFERENCES.putInt("mazeWidth", width);
        } else {
            throw new IllegalArgumentException("La largeur doit être impaire et supérieure ou égale à 7");
        }
    }

    public void setHeight(int height) throws IllegalArgumentException {
        if (height >= 5) {
            App.PREFERENCES.putInt("mazeHeight", height);
        } else {
            throw new IllegalArgumentException("La hauteur doit être supérieure ou égale à 5");
        }
    }

    public void setObstacles(int obstacles) throws IllegalArgumentException {
        if (obstacles >= 1 && obstacles <= 80) {
            App.PREFERENCES.putInt("obstacles", obstacles);
        } else {
            throw new IllegalArgumentException("Le pourcentage doit être compris entre 0 et 80 inclus.");
        }
    }

    public Monster getMonster() {
        return monster;
    }

    public Hunter getHunter() {
        return hunter;
    }

    public void initializePlayers() {
        this.monster.initialize(new boolean[getWidth()][getHeight()]);
        this.hunter.initialize(new boolean[getWidth()][getHeight()]);
    }

    /*
     * Génère un labyrinthe aléatoirement validé par MazeValidator
     * 
     * @see MazeValidator
     */
    public void initializeMaze() {
        if(this.maze == null) {
            MazeGenerator mazeGenerator = new MazeGenerator(getWidth(), getHeight());
            mazeGenerator.generatePlateau(getObstacles());
            int [][] tmpMaze = mazeGenerator.getMaze();

            MazeValidator mazeValidator = new MazeValidator(getWidth(), getHeight(), tmpMaze);

            while (!mazeValidator.isValid()) {
                mazeGenerator.generate();
                mazeValidator.setMaze(mazeGenerator.getMaze());
            }
            entrance = mazeGenerator.getEntranceCoordinate();
            exit = mazeGenerator.getExitCoordinate();
            monster.setCoord(entrance.getRow(), entrance.getCol(), 0);

            this.maze = mazeGenerator.toCellInfo();
        }
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

    public void importMaze(File file) throws NumberFormatException, IOException {
        Path p = Paths.get(file.toString());
        List<String> lines = Files.readAllLines(p);
        this.setHeight(lines.size());
        this.setWidth(lines.get(0).split(",").length);
        CellInfo[][] labyrinth = new CellInfo[getHeight()][getWidth()];
        int entranceX = 0;
        int entranceY = 0;
        String[] line;
        for(int i = 0; i < this.getHeight(); i++) {
            line = lines.get(i).split(",");
            for(int j = 0; j < this.getWidth(); j++) {
                labyrinth[i][j] = MazeGenerator.toCellInfo(Integer.parseInt(line[j]));
                if(i == 0 && labyrinth[i][j] == CellInfo.MONSTER) {
                    entranceX = j;
                    entranceY = i;
                }

            }
        }
        monster.setCoord(new Coordinate(entranceY, entranceX));
        this.maze = labyrinth;
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
        oos.writeObject(this.getWidth());
        oos.writeObject(this.getHeight());
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
        this.monsterName = (String)ois.readObject();
        this.hunterName = (String)ois.readObject();
        this.monster = (Monster)ois.readObject();
        this.hunter = (Hunter)ois.readObject();
        this.entrance = (Coordinate)ois.readObject();
        this.exit = (Coordinate)ois.readObject();
    }
}
