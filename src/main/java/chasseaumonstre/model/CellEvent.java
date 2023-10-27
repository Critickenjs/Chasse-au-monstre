package chasseaumonstre.model;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent;

public class CellEvent implements ICellEvent {
    private CellInfo state;
    private Integer turn;
    private Coordinate coord;

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