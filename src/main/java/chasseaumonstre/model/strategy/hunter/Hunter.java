package chasseaumonstre.model.strategy.hunter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import fr.univlille.iutinfo.cam.player.hunter.IHunterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/*
 * Réprésente le chasseur et sa stratégie
 * 
 * @see IHunterStrategy
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class Hunter implements IHunterStrategy, Serializable {
    private boolean[][] shootLocations;
    private String name;
    private boolean[][] visited;
    private int[][] visitedTurn;

    /*
     * Constructeur de Hunter
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
    }

    /*
     * Constructeur de Hunter, qui initialise les coordonnées des tirs du chasseur
     * 
     * @param row la ligne de la cellule
     * @param col la colonne de la cellule
     */
    public void initialize(int row, int col) {
        this.shootLocations = new boolean[row][col];
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

    public boolean hasShot(int x, int y) {
        return this.shootLocations[x][y];
    }

    public void shoot(int x, int y) {
        this.shootLocations[x][y] = true;
    }

    

    /*
     * Joue un tour du chasseur
     */
    @Override
    public ICoordinate play() {
        throw new UnsupportedOperationException("Unimplemented method 'play'");
    }

    /*
     * Met à jour les coordonnées des tirs du chasseur
     * 
     * @param event l'événement qui se produit sur une cellule
     */
    @Override
    public void update(ICellEvent event) {
        ICoordinate coord = event.getCoord();
        this.shootLocations[coord.getRow()][coord.getCol()] = true;
    }

    public boolean isVisited(int x, int y) {
        return visited[x][y];
    }

    public int getVisitedTurn(int x, int y) {
        return visitedTurn[x][y];
    }
    public void setVisitedTurn(int res,int x, int y) {
        visitedTurn[x][y] = res;
    }

    public void setVisited(int cellX, int cellY) {
        this.visited[cellX][cellY] = true;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.shootLocations);
        oos.writeObject(this.name);
        oos.writeObject(this.visited);
        oos.writeObject(this.visitedTurn);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        this.shootLocations = (boolean[][])ois.readObject();
        this.name = (String)ois.readObject();
        this.visited = (boolean[][])ois.readObject();
        this.visitedTurn = (int[][])ois.readObject();
    }
}
