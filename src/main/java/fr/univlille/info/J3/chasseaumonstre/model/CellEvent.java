package fr.univlille.info.J3.chasseaumonstre.model;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent;

/*
 * CellEvent représente un événement qui se produit sur une cellule
 * 
 * @see ICellEvent
 * @author Anas Ouhdda
 * @author Atilla Tas
 * @author Karim Aoulad-Tayab
 * @author Selim Hamza
 * @author Yliess El Atifi
 */
public class CellEvent implements ICellEvent {
    private CellInfo state;
    private Integer turn;
    private Coordinate coord;

    /*
     * Constructeur de CellEvent
     * 
     * @param state l'état de la cellule
     * @param turn le tour où l'événement se produit
     * @param coord les coordonnées de la cellule
     */
    public CellEvent(CellInfo state, Integer turn, Coordinate coord) {
        this.state = state;
        this.turn = turn;
        this.coord = coord;
    }

    public CellInfo getState() {
        return this.state;
    }

    public int getTurn() {
        return this.turn;
    }

    public Coordinate getCoord() {
        return this.coord;
    }
}