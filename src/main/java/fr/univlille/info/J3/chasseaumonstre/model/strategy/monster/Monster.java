package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import SubjectObserver.*;
import fr.univlille.info.J3.chasseaumonstre.App;
import fr.univlille.info.J3.chasseaumonstre.model.Coordinate;
import fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm.AStar;
import fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm.MonsterAlgorithm;
import fr.univlille.iutinfo.cam.player.monster.IMonsterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/**
 * Réprésente le monstre et sa stratégie
 * 
 * @see IMonsterStrategy
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class Monster extends Subject implements IMonsterStrategy, Serializable {
    private Coordinate exit;
    private Coordinate entry;
    private Coordinate coord;
    private boolean[][] maze;
    private Integer[][] visitedTurn;
    private boolean ai;
    private int turn;
    private Class<? extends IMonsterStrategy> algorithmClass;
    private IMonsterStrategy algorithm;

    public Monster() {
        this.exit = null;
        this.entry = null;
        this.coord = null;
        this.visitedTurn = null;
        this.ai = false;
        this.turn = 0;
        this.algorithmClass = AStar.class;
    }

    /**
     * Constructeur de Monster
     * 
     * @param locations les coordonnées des cellules visitées par le monstre
     */
    public Monster(boolean[][] locations) {
        this();
        initialize(locations);
    }

    public List<Observer> attachedObservers() {
        return this.attached;
    }

    /**
     * Constructeur de Monster
     * 
     * @param row la ligne de la cellule
     * 
     * @param col la colonne de la cellule
     */
    public void initialize(boolean[][] locations) {
        this.maze = locations;
        this.visitedTurn = new Integer[locations.length][locations[0].length];
    }

    public Coordinate getExit() {
        return exit;
    }

    public boolean isAi() {
        return ai;
    }

    public void setAi(boolean ai) {
        if (ai && (algorithm == null)) {
            this.executeAlgorithm();
        }
        this.ai = ai;
    }

    public void setAlgorithm(Class<? extends IMonsterStrategy> algorithm) {
        this.algorithmClass = algorithm;
    }

    @SuppressWarnings("unchecked")
    public void setAlgorithm(String algorithm) {
        try {
            this.algorithmClass = (Class<? extends IMonsterStrategy>) Class.forName("fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm." + algorithm);
        } catch (ClassNotFoundException e) {
            this.algorithmClass = AStar.class;
        }
    }

    public Class<? extends IMonsterStrategy> getAlgorithmClass() {
        return this.algorithmClass;
    }

    /**
     * Exécute l'algorithme de recherche de chemin
     * 
     * @see Algorithm
     * 
     * @see AStar
     */
    private void executeAlgorithm() {
        try {
            algorithm = this.algorithmClass.getConstructor(ICoordinate.class, ICoordinate.class, boolean[][].class)
                    .newInstance(this.entry, this.exit, this.maze);
        } catch (Exception e) {
            algorithm = new AStar(this.entry, this.exit, this.maze);
        }
        algorithm.initialize(this.maze);
    }

    /**
     * Définit la sortie du labyrinthe dans la mémoire du monstre
     * 
     * @param row la ligne de la cellule
     * 
     * @param col la colonne de la cellule
     */
    public void setExit(int row, int col) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        setExit(new Coordinate(row, col));
    }

    private void setExit(Coordinate exit) {
        this.exit = exit;
    }

    public Coordinate getEntry() {
        return entry;
    }

    public Integer[][] getVisitedTurn() {
        return this.visitedTurn;
    }

    /**
     * Définit l'entrée du labyrinthe dans la mémoire du monstre
     * 
     * @param row la ligne de la cellule
     * 
     * @param col la colonne de la cellule
     */
    public void setEntry(int row, int col) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        setEntry(new Coordinate(row, col));
    }

    private void setEntry(Coordinate entry) {
        this.entry = entry;
    }

    public Coordinate getCoord() {
        return coord;
    }

    /**
     * Définit les coordonnées actuelles du monstre dans sa mémoire, et notifie le
     * modèle principal
     * 
     * @param row  la ligne de la cellule
     * 
     * @param col  la colonne de la cellule
     * 
     * @param turn le tour auquel le monstre a visité la cellule
     * 
     * @see MonsterHunterModel
     */
    public void setCoord(int row, int col, int turn) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        Coordinate coord = new Coordinate(row, col);
        this.coord = coord;
        setVisited(row, col, turn);
        this.notifyObservers(coord);
    }

    /**
     * Définit les coordonnées actuelles du monstre dans sa mémoire
     * 
     * @param row la ligne de la cellule
     * 
     * @param col la colonne de la cellule
     */
    public boolean isVisited(int row, int col) {
        try {
            checkCoord(row, col);
            return visitedTurn[row][col] != null;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Vérifie si le monstre est adjacent à une cellule
     * 
     * @param x1 la ligne de la cellule
     * 
     * @param y1 la colonne de la cellule
     * 
     * @return true si le monstre est adjacent à la cellule, false sinon
     */
    public boolean estAdjacente(int x1, int y1) {
        int diffX = Math.abs(x1 - this.coord.getRow());
        int diffY = Math.abs(y1 - this.coord.getCol());

        return (diffX == 1 && diffY == 0) || (diffX == 0 && diffY == 1);
    }

    /**
     * Vérifie si le monstre la case est visible par le monstre (distance)
     * 
     * @param x la ligne de la cellule
     * 
     * @param y la colonne de la cellule
     * 
     * @return true si la cellule est visible, false sinon
     */
    public boolean estVisible(int x, int y) {
        int diffX = Math.abs(x - this.coord.getRow());
        int diffY = Math.abs(y - this.coord.getCol());

        return (diffX <= getFov() && diffY <= getFov());
    }

    /**
     * Définit que le monstre a déjà visité une cellule
     * 
     * @param row la ligne de la cellule
     * 
     * @param col la colonne de la cellule
     */
    public void setVisited(Coordinate coord, int turn) {
        setVisited(coord.getRow(), coord.getCol(), turn);
    }

    private void checkCoord(int row, int col) throws ArrayIndexOutOfBoundsException {
        if ((row < 0 || row >= maze.length) || (col < 0 || col >= maze[0].length)) {
            throw new ArrayIndexOutOfBoundsException(
                    "Row " + row + "/" + maze.length + " or column " + col + "/" + maze[0].length);
        }
    }

    /**
     * Connaitre à quel tour une cellule a été visitée
     * 
     * @param row la ligne de la cellule
     * 
     * @param col la colonne de la cellule
     * 
     * @return le tour auquel la cellule a été visitée
     */
    public int getVisitedTurn(int row, int col) {
        try {
            if (visitedTurn[row][col] == null) {
                return -1;
            }
            return visitedTurn[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    /**
     * Joue un tour du monstre si celui-ci est dirigé par IA
     */
    @Override
    public ICoordinate play() {
        ICoordinate next = algorithm.play();
        if (next != null) {
            setCoord(next.getRow(), next.getCol(), turn++);
        }
        return next;
    }

    /**
     * Met à jour la mémoire du monstre
     * 
     * @param event l'événement qui se produit sur une cellule
     */
    public void update(ICellEvent event) {
        switch (event.getState()) {
            case MONSTER:
                setCoord(event.getCoord().getRow(), event.getCoord().getCol(), event.getTurn());
            case EXIT:
                setExit(new Coordinate(event.getCoord()));
            case WALL:
                setEntry(new Coordinate(event.getCoord()));
                break;
            default:
                setVisited(new Coordinate(event.getCoord()), event.getTurn());
                break;
        }
    }

    public void setVisited(int row, int col, int turn) {
        visitedTurn[row][col] = turn;
    }

    /**
     * Connaitre le champ de vision du monstre
     * 
     * @return le champ de vision du monstre
     */
    public int getFov() {
        return App.PREFERENCES.getInt("monsterFov", 2);
    }

    /**
     * Définit le champ de vision du monstre
     * 
     * @param fov le champ de vision du monstre
     */
    public void setFov(int fov) throws IllegalArgumentException {
        if (fov < 0) {
            throw new IllegalArgumentException("Le champ de vision doit être positif");
        }
        App.PREFERENCES.putInt("monsterFov", fov);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.exit);
        oos.writeObject(this.entry);
        oos.writeObject(this.coord);
        oos.writeObject(this.maze);
        oos.writeObject(this.visitedTurn);
        oos.writeObject(this.ai);
        oos.writeObject(this.turn);
        oos.writeObject(this.algorithmClass);
        oos.writeObject(this.algorithm);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        this.exit = (Coordinate) ois.readObject();
        this.entry = (Coordinate) ois.readObject();
        this.coord = (Coordinate) ois.readObject();
        this.maze = (boolean[][]) ois.readObject();
        this.visitedTurn = (Integer[][]) ois.readObject();
        this.ai = (boolean) ois.readObject();
        this.turn = (int) ois.readObject();
        this.algorithmClass = (Class<? extends MonsterAlgorithm>) ois.readObject();
        this.algorithm = (IMonsterStrategy) ois.readObject();
    }
}
