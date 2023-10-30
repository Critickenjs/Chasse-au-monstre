package chasseaumonstre.model;

import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/*
 * Coordinate représente les coordonnées d'une cellule
 * 
 * @see ICoordinate
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class Coordinate implements ICoordinate {
    private Integer row;
    private Integer col;

    /*
     * Constructeur de Coordinate
     * 
     * @param row la ligne de la cellule
     * @param col la colonne de la cellule
     */
    public Coordinate(Integer row, Integer col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
