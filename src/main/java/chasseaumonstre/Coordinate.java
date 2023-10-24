package chasseaumonstre;

public class Coordinate implements ICoordinate{
    private Integer row;
    private Integer col;

    public Coordinate(Integer row, Integer col){
        this.row = row;
        this.col = col;
    }

    public Integer getRow() {
        return this.row;
    }

    public Integer getCol() {
        return this.col;
    }
}
