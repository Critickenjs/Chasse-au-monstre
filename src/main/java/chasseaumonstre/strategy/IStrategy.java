package chasseaumonstre.strategy;

import chasseaumonstre.model.CellEvent;
import chasseaumonstre.model.Coordinate;

public interface IStrategy {
    public Coordinate play();
    public void update(CellEvent event);
}
