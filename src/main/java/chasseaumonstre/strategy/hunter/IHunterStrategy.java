package chasseaumonstre.strategy.hunter;

import chasseaumonstre.strategy.IStrategy;

public interface IHunterStrategy extends IStrategy {
    public void initialize(boolean[][] locations);
}
