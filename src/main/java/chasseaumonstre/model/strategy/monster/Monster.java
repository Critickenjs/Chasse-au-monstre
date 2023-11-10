package chasseaumonstre.model.strategy.monster;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import SubjectObserver.Subject;
import chasseaumonstre.model.Coordinate;
import fr.univlille.iutinfo.cam.player.monster.IMonsterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/*
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
    private ICoordinate exit;
    private ICoordinate entry;
    private ICoordinate coord;
    private boolean[][] visited;
    private Integer[][] visitedTurn;

    public Monster() {
        this.exit = null;
        this.entry = null;
        this.coord = null;
        this.visited = null;
        this.visitedTurn = null;
    }

    /*
     * Constructeur de Monster
     * 
     * @param locations les coordonnées des cellules visitées par le monstre
     */
    public Monster(boolean[][] locations) {
        this();
        initialize(locations);
    }

    /*
     * Constructeur de Monster
     * 
     * @param row la ligne de la cellule
     * @param col la colonne de la cellule
     */
    public void initialize(boolean[][] locations) {
        this.visited = locations;
        this.visitedTurn = new Integer[locations.length][locations[0].length];
    }

    public ICoordinate getExit() {
        return exit;
    }

    /*
     * Définit la sortie du labyrinthe dans la mémoire du monstre
     * 
     * @param row la ligne de la cellule
     * @param col la colonne de la cellule
     */
    public void setExit(int row, int col) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        setExit(new Coordinate(row, col));
    }

    private void setExit(ICoordinate exit) {
        this.exit = exit;
    }

    public ICoordinate getEntry() {
        return entry;
    }
    

    public boolean[][] getVisited() {
        return visited;
    }

    /*
     * Définit l'entrée du labyrinthe dans la mémoire du monstre
     * 
     * @param row la ligne de la cellule
     * @param col la colonne de la cellule
     */
    public void setEntry(int row, int col) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        setEntry(new Coordinate(row, col));
    }

    private void setEntry(ICoordinate entry) {
        this.entry = entry;
    }

    public ICoordinate getCoord() {
        return coord;
    }

    public void setCoord(ICoordinate coord) {
        this.coord = coord;
    }

    public void setCoord(int row, int col, int turn) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        setCoord(new Coordinate(row, col));
        visitedTurn[row][col] = turn;
        setVisited(row, col);
        this.notifyObservers();
    }

    /*
     * Définit les coordonnées actuelles du monstre dans sa mémoire
     * 
     * @param row la ligne de la cellule
     * @param col la colonne de la cellule
     */
    public boolean isVisited(int row, int col) {
        try {
            checkCoord(row, col);
            return visited[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /*
     * Vérifie si le monstre a déjà visité une cellule
     * 
     * @param row la ligne de la cellule
     * @param col la colonne de la cellule
     * @return true si le monstre a déjà visité la cellule, false sinon
     */
    public void setVisited(int row, int col) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        visited[row][col] = true;
        this.notifyObservers();
    }

    /*
     * Vérifie si le monstre est adjacent à une cellule
     * 
     * @param x1 la ligne de la cellule
     * @param y1 la colonne de la cellule
     * @return true si le monstre est adjacent à la cellule, false sinon
     */
    public boolean estAdjacente(int x1, int y1) {
        int diffX = Math.abs(x1- this.coord.getRow());
        int diffY = Math.abs(y1 -this.coord.getCol());

        
        return (diffX == 1 && diffY == 0) || (diffX == 0 && diffY == 1);
    }
    public boolean estVisible(int x,int y) {
        int diffX = Math.abs(x- this.coord.getRow());
        int diffY = Math.abs(y -this.coord.getCol());

        
        return (diffX <= 2 && diffY <= 2);
    }

    /*
     * Définit que le monstre a déjà visité une cellule
     * 
     * @param row la ligne de la cellule
     * @param col la colonne de la cellule
     */
    public void setVisited(ICoordinate coord) {
        setVisited(coord.getRow(), coord.getCol());
    }

    private void checkCoord(int row, int col) throws ArrayIndexOutOfBoundsException {
        if ((row < 0 || row >= visited.length) || (col < 0 || col >= visited[0].length)) {
            throw new ArrayIndexOutOfBoundsException("Row " + row + "/" + visited.length + " or column " + col + "/" + visited[0].length);
        }
    }

    /*
     * Connaitre à quel tour une cellule a été visitée
     * 
     * @param row la ligne de la cellule
     * @param col la colonne de la cellule
     * @return le tour auquel la cellule a été visitée
     */
    public int getVisitedTurn(int row, int col) {
        try {
            return visitedTurn[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    /*
     * Joue un tour du monstre
     */
    @Override
    public ICoordinate play() {
        // TODO (partie 2 - implémentation de l'IA du monstre)
        throw new UnsupportedOperationException("Unimplemented method 'play'");
    }

    /*
     * Met à jour la mémoire du monstre
     * 
     * @param event l'événement qui se produit sur une cellule
     */
    public void update(ICellEvent event) {
        switch (event.getState()) {
            case MONSTER:
                setCoord(event.getCoord());
            case EXIT:
                setExit(event.getCoord());
            case WALL:
                setEntry(event.getCoord());
                break;
            default:
                setVisited(event.getCoord());
                break;
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.exit);
        oos.writeObject(this.entry);
        oos.writeObject(this.coord);
        oos.writeObject(this.visited);
        oos.writeObject(this.visitedTurn);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        this.exit = (ICoordinate)ois.readObject();
        this.entry = (ICoordinate)ois.readObject();
        this.coord = (ICoordinate)ois.readObject();
        this.visited = (boolean[][])ois.readObject();
        this.visitedTurn = (Integer[][])ois.readObject();
    }
}
