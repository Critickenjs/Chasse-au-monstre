package chasseaumonstre.model;

public class CellEvent implements ICellEvent {
    private CellInfo state;
    private Integer turn;
    private ICoordinate coord;

    public CellEvent(CellInfo state, Integer turn, ICoordinate coord) {
        this.state = state;
        this.turn = turn;
        this.coord = coord;
    }

    public CellInfo getState() {
        return this.state;
    }

    public Integer getTurn() {
        return this.turn;
    }

    public ICoordinate getCoordinate() {
        return this.coord;
    }
}