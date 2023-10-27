package chasseaumonstre.strategy;

import chasseaumonstre.model.ICellEvent;
import chasseaumonstre.model.ICoordinate;

public interface IStrategy {
    public ICoordinate play();
    public void update(ICellEvent event);
}
