package chasseaumonstre.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
public class Coordinate implements ICoordinate, Serializable {
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

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.row);
        oos.writeObject(this.col);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        this.row = (Integer)ois.readObject();
        this.col = (Integer)ois.readObject();
    }
}
