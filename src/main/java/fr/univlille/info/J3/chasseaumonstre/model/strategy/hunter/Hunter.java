package fr.univlille.info.J3.chasseaumonstre.model.strategy.hunter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import SubjectObserver.Observer;
import SubjectObserver.Subject;
import fr.univlille.info.J3.chasseaumonstre.model.Coordinate;
import fr.univlille.iutinfo.cam.player.hunter.IHunterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/**
 * Réprésente le chasseur et sa stratégie
 * 
 * @see IHunterStrategy
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class Hunter extends Subject implements IHunterStrategy, Serializable {
    private boolean[][] shootLocations;
    private String name;
    private boolean[][] visited;
    private int[][] visitedTurn;
    private boolean ai;
    private Stack<ICoordinate> neighboursCellsExploration;
    private Class<? extends IHunterStrategy> algorithmClass;
    private IHunterStrategy algorithm;

    /**
     * Constructeur de Hunter
     * 
     * @param locations les coordonnées des tirs du chasseur
     */
    public Hunter() {
        // TODO : Ajouter la stratégie par défaut
    }

    /*
     * Initialisation des coordonnées des tirs du chasseur
     * 
     * @param locations les coordonnées des tirs du chasseur
     */
    public void initialize(boolean[][] locations) {
        this.shootLocations = locations;
        this.visited = locations;
        this.visitedTurn = new int[locations.length][locations[0].length];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[0].length; j++) {
                visitedTurn[i][j] = -1;
            }
        }
        this.neighboursCellsExploration = new Stack<>();
    }

    /**
     * Constructeur de Hunter, qui initialise les coordonnées des tirs du chasseur
     * 
     * @param row la ligne de la cellule
     * @param col la colonne de la cellule
     */
    public void initialize(int row, int col) {
        this.shootLocations = new boolean[row][col];
    }

    public List<Observer> attachedObservers() {
        return this.attached;
    }

    public boolean[][] getShootLocations() {
        return this.shootLocations;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlgorithm(Class<? extends IHunterStrategy> algorithm) {
        this.algorithmClass = algorithm;
    }

    @SuppressWarnings("unchecked")
    public void setAlgorithm(String algorithm) {
        try {
            this.algorithmClass = (Class<? extends IHunterStrategy>) Class.forName("fr.univlille.info.J3.chasseaumonstre.model.strategy.hunter.algorithm." + algorithm);
        } catch (ClassNotFoundException e) {
            // TODO : Définir un comportement par défaut
            // this.algorithmClass = 
        }
    }

    public Class<? extends IHunterStrategy> getAlgorithmClass() {
        return this.algorithmClass;
    }

    /**
     * Connaitre si une cellule a été tirée
     * 
     * @param x la ligne de la cellule
     * @param y la colonne de la cellule
     * @return true si la cellule a été tirée, false sinon
     */
    public boolean hasShot(int x, int y) {
        return this.shootLocations[x][y];
    }

    /**
     * Effectue un tir sur une cellule et en informe le modèle principal
     * 
     * @param x la ligne de la cellule
     * @param y la colonne de la cellule
     */
    public void shoot(int x, int y) {
        this.shootLocations[x][y] = true;
        this.notifyObservers(new Coordinate(x, y));
    }

    public boolean isAi() {
        return ai;
    }

    public void setAi(boolean ai) {
        this.ai = ai;
    }

    private List<ICoordinate> getNeighbours(ICoordinate coordinate) {
        List<ICoordinate> coordinates = new ArrayList<>();
        Integer x = coordinate.getCol();
        Integer y = coordinate.getRow();
        // ---
        coordinates.add(new Coordinate(y, x - 1));
        coordinates.add(new Coordinate(y, x + 1));
        // ---
        coordinates.add(new Coordinate(y - 1, x - 1));
        coordinates.add(new Coordinate(y - 1, x));
        coordinates.add(new Coordinate(y - 1, x + 1));
        // ---
        coordinates.add(new Coordinate(y + 1, x - 1));
        coordinates.add(new Coordinate(y + 1, x));
        coordinates.add(new Coordinate(y + 1, x + 1));
        Iterator<ICoordinate> it = coordinates.iterator();
        ICoordinate c;
        while (it.hasNext()) {
            c = it.next();
            if (!((c.getCol() >= 0 && c.getCol() < this.visited[0].length)
                    && (c.getRow() >= 0 && c.getRow() < this.visited.length)))
                it.remove();
        }
        return coordinates;
    }

    /**
     * Genere coordonnées d'une case aleatoirement
     * Verifie si elle a déja été ciblé
     * Tire dessus si non
     * Retourne les coordonnées de la case tirée
     */
    @Override
    public ICoordinate play() {
        // TODO : Remplacer par l'algorithme du chasseur
        ICoordinate coordinate;
        if (!this.neighboursCellsExploration.isEmpty()) {
            coordinate = this.neighboursCellsExploration.pop();
            List<ICoordinate> neighbours = this.getNeighbours(coordinate);
            for (ICoordinate c : neighbours) {
                if (!this.hasShot(c.getRow(), c.getCol()) && this.isVisited(c.getRow(), c.getCol())) {
                    this.shoot(c.getRow(), c.getCol());
                    this.neighboursCellsExploration.push(c);
                    return c;
                }
            }
        }
        Random r = new Random();
        ICoordinate shuffleCoordinate = new Coordinate(r.nextInt(this.visited.length),
                r.nextInt(this.visited[0].length));
        while (this.hasShot(shuffleCoordinate.getRow(), shuffleCoordinate.getCol())) {
            shuffleCoordinate = new Coordinate(r.nextInt(this.visited.length), r.nextInt(this.visited[0].length));
        }
        this.shoot(shuffleCoordinate.getRow(), shuffleCoordinate.getCol());
        this.neighboursCellsExploration.push(shuffleCoordinate);
        return shuffleCoordinate;
    }

    /**
     * Met à jour les coordonnées des tirs du chasseur
     * 
     * @param event l'événement qui se produit sur une cellule
     */
    @Override
    public void update(ICellEvent event) {
        ICoordinate coord = event.getCoord();
        this.shoot(coord.getRow(), coord.getCol());
    }

    /**
     * Connaitre si une cellule a été visitée
     * 
     * @param x la ligne de la cellule
     * @param y la colonne de la cellule
     * @return true si la cellule a été visitée, false sinon
     */
    public boolean isVisited(int x, int y) {
        return visited[x][y];
    }

    /**
     * Connaitre à quel tour une cellule a été visitée
     * 
     * @param x la ligne de la cellule
     * @param y la colonne de la cellule
     * @return le tour auquel la cellule a été visitée
     */
    public int getVisitedTurn(int x, int y) {
        return visitedTurn[x][y];
    }

    /**
     * Met à jour la cellule visitée
     * 
     * @param cellX la ligne de la cellule
     * @param cellY la colonne de la cellule
     * @param turn  le tour auquel la cellule a été visitée
     */
    public void setVisited(int cellX, int cellY, int turn) {
        this.visited[cellX][cellY] = true;
        this.visitedTurn[cellX][cellY] = turn;
        this.notifyObservers();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.shootLocations);
        oos.writeObject(this.name);
        oos.writeObject(this.visited);
        oos.writeObject(this.visitedTurn);
        oos.writeObject(this.ai);
        oos.writeObject(this.neighboursCellsExploration);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        this.shootLocations = (boolean[][]) ois.readObject();
        this.name = (String) ois.readObject();
        this.visited = (boolean[][]) ois.readObject();
        this.visitedTurn = (int[][]) ois.readObject();
        this.ai = (boolean) ois.readObject();
        this.neighboursCellsExploration = (Stack<ICoordinate>) ois.readObject();
    }
}
