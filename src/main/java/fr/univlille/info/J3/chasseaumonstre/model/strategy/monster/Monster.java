package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import SubjectObserver.Subject;
import fr.univlille.info.J3.chasseaumonstre.App;
import fr.univlille.info.J3.chasseaumonstre.model.Coordinate;
import fr.univlille.iutinfo.cam.player.monster.IMonsterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;

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
    private Coordinate exit;
    private Coordinate entry;
    private Coordinate coord;
    private boolean[][] maze;
    private Integer[][] visitedTurn;

    public Monster() {
        this.exit = null;
        this.entry = null;
        this.coord = null;
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
        this.maze = locations;
        this.visitedTurn = new Integer[locations.length][locations[0].length];
    }

    public Coordinate getExit() {
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

    private void setExit(Coordinate exit) {
        this.exit = exit;
    }

    public Coordinate getEntry() {
        return entry;
    }
    

    public boolean[][] getVisited() {
        return null;
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

    private void setEntry(Coordinate entry) {
        this.entry = entry;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public void setCoord(int row, int col, int turn) throws ArrayIndexOutOfBoundsException {
        checkCoord(row, col);
        setCoord(new Coordinate(row, col));
        visitedTurn[row][col] = turn;
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
            return visitedTurn[row][col] != null;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
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

    /*
     * Vérifie si le monstre la case est visible par le monstre (distance)
     * 
     * @param x la ligne de la cellule
     * @param y la colonne de la cellule
     * @return true si la cellule est visible, false sinon
     */
    public boolean estVisible(int x,int y) {
        int diffX = Math.abs(x- this.coord.getRow());
        int diffY = Math.abs(y -this.coord.getCol());
        
        return (diffX <= getFov() && diffY <= getFov());
    }

    /*
     * Définit que le monstre a déjà visité une cellule
     * 
     * @param row la ligne de la cellule
     * @param col la colonne de la cellule
     */
    public void setVisited(Coordinate coord, int turn) {
        setVisited(coord.getRow(), coord.getCol(), turn);
    }

    private void checkCoord(int row, int col) throws ArrayIndexOutOfBoundsException {
        if ((row < 0 || row >= maze.length) || (col < 0 || col >= maze[0].length)) {
            throw new ArrayIndexOutOfBoundsException("Row " + row + "/" + maze.length + " or column " + col + "/" + maze[0].length);
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
    public Coordinate play() {
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
                setCoord(new Coordinate(event.getCoord()));
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

    /*
     * Connaitre le champ de vision du monstre
     * 
     * @return le champ de vision du monstre
     */
    public int getFov() {
        return App.PREFERENCES.getInt("monsterFov", 2);
    }

    /*
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

    private List<Coordinate> aStar() {
        if (entry == null || exit == null || maze == null) {
            return null;
        }

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getFScore));
        Map<Coordinate, Integer> gScore = new HashMap<>();
        Map<Coordinate, Coordinate> cameFrom = new HashMap<>();

        openSet.offer(new Node(entry, 0, heuristicCost(entry, exit)));
        gScore.put(entry, 0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.getCoordinate().equals(exit)) {
                return reconstructPath(cameFrom, current.getCoordinate());
            }

            for (Coordinate neighbor : getNeighbors(current.getCoordinate())) {
                int tentativeGScore = gScore.getOrDefault(current.getCoordinate(), Integer.MAX_VALUE) + 1;

                if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, current.getCoordinate());
                    gScore.put(neighbor, tentativeGScore);

                    int fScore = tentativeGScore + heuristicCost(neighbor, exit);
                    Node neighborNode = new Node(neighbor, tentativeGScore, fScore);
                    openSet.offer(neighborNode);
                }
            }
        }

        return null;
    }

    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> cameFrom, Coordinate current) {
        List<Coordinate> totalPath = new ArrayList<>();
        totalPath.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(0, current);
        }

        return totalPath;
    }

    private int heuristicCost(Coordinate a, Coordinate b) {
        // Heuristique : distance de Manhattan entre les deux points
        return Math.abs(a.getRow() - b.getRow()) + Math.abs(a.getCol() - b.getCol());
    }

    private List<Coordinate> getNeighbors(Coordinate current) {
        List<Coordinate> neighbors = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int row = current.getRow();
            int col = current.getCol();

            switch (i) {
                case 0:
                    row--;
                    break;
                case 1:
                    row++;
                    break;
                case 2:
                    col--;
                    break;
                case 3:
                    col++;
                    break;
            }

            if (row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col]) {
                neighbors.add(new Coordinate(row, col));
            }
        }

        return neighbors;
    }

    

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.exit);
        oos.writeObject(this.entry);
        oos.writeObject(this.coord);
        oos.writeObject(this.maze);
        oos.writeObject(this.visitedTurn);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        this.exit = (Coordinate)ois.readObject();
        this.entry = (Coordinate)ois.readObject();
        this.coord = (Coordinate)ois.readObject();
        this.maze = (boolean[][])ois.readObject();
        this.visitedTurn = (Integer[][])ois.readObject();
    }

    public static void main(String[] args) {
        boolean[][] maze = {
            {false, false, false, false, false},
            {false, true, true, true, false},
            {false, false, false, true, false},
            {false, true, true, true, false},
            {false, false, false, false, false}
        };        

        Monster monster = new Monster();
        monster.initialize(maze);

        monster.setEntry(1, 1);
        monster.setExit(3, 3);

        List<Coordinate> path = monster.aStar();

        if (path != null) {
            System.out.println("Chemin trouvé : ");
            for (Coordinate coord : path) {
                System.out.println(coord);
            }
        } else {
            System.out.println("Aucun chemin trouvé.");
        }
    }
}
