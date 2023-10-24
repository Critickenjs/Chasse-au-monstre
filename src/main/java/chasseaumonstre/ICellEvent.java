package chasseaumonstre;

public interface ICellEvent {

    public CellInfo getState();

    public Integer getTurn();

    public ICoordinate getCoordinate();
}
