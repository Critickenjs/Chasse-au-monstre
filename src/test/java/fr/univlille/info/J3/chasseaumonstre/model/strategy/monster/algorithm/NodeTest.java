package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import fr.univlille.info.J3.chasseaumonstre.model.Coordinate;

public class NodeTest {
    private ICoordinate coord;
    private Node node;
    private int gScore, fScore;

    @BeforeEach
    public void setUp() {
        coord = new Coordinate(1, 1);
        gScore = 5;
        fScore = 10;
        node = new Node(coord, gScore, fScore);
    }

    @Test
    public void testGetCoord() {
        ICoordinate coord = node.getCoordinate();
        assert(coord.getRow() == 1);
        assert(coord.getCol() == 1);
    }

    @Test
    public void testGetGScore() {
        int gScore = node.getGScore();
        assert(gScore == 5);
    }

    @Test
    public void testGetFScore() {
        int fScore = node.getFScore();
        assert(fScore == 10);
    }

    @Test
    public void testSetGScore() {
        node.setGScore(15);
        int gScore = node.getGScore();
        assert(gScore == 15);
    }

    @Test
    public void testSetFScore() {
        node.setFScore(20);
        int fScore = node.getFScore();
        assert(fScore == 20);
    }

    @Test
    public void testGetRow() {
        int row = node.getRow();
        assert(row == coord.getRow());
    }

    @Test
    public void testGetCol() {
        int col = node.getCol();
        assert(col == coord.getCol());
    }

    @Test
    public void testToString() {
        String str = node.toString();
        assert(str.equals(coord.toString()));
    }
}
