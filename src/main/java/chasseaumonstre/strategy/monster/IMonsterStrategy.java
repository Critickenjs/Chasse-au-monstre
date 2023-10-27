package chasseaumonstre.strategy.monster;

import chasseaumonstre.strategy.IStrategy;

public interface IMonsterStrategy extends IStrategy {
    public void initialize(Integer nbRows, Integer nbCols);
}
