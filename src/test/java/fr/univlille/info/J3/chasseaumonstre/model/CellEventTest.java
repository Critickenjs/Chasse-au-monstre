package fr.univlille.info.J3.chasseaumonstre.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;

public class CellEventTest {
    private CellEvent cellEvent1;
    private CellEvent cellEvent2;

    @BeforeEach
    public void setUp() {
        CellInfo cellInfo = CellInfo.EMPTY;
        Coordinate coordinate = new Coordinate(2, 3);
        cellEvent1 = new CellEvent(cellInfo, 5, coordinate);

        cellInfo = CellInfo.MONSTER; // Fix to MONSTER
        coordinate = new Coordinate(4, 5);
        cellEvent2 = new CellEvent(cellInfo, 10, coordinate);
    }

    @Test
    public void testGetState() {
        assertEquals(CellInfo.EMPTY, cellEvent1.getState()); // Fix to cellEvent1
        assertEquals(CellInfo.MONSTER, cellEvent2.getState());
    }

    @Test
    public void testGetTurn() {
        assertEquals(5, cellEvent1.getTurn());
        assertEquals(10, cellEvent2.getTurn());
    }

    @Test
    public void testGetCoord() {
        Coordinate expectedCoordinate1 = new Coordinate(2, 3);
        assertEquals(expectedCoordinate1.getRow(), cellEvent1.getCoord().getRow());
        assertEquals(expectedCoordinate1.getCol(), cellEvent1.getCoord().getCol());

        Coordinate expectedCoordinate2 = new Coordinate(4, 5);
        assertEquals(expectedCoordinate2.getRow(), cellEvent2.getCoord().getRow());
        assertEquals(expectedCoordinate2.getCol(), cellEvent2.getCoord().getCol());
    }
}
