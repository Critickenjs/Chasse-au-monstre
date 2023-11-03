package chasseaumonstre.model.strategy.hunter;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import chasseaumonstre.model.CellEvent;
import chasseaumonstre.model.Coordinate;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

public class HunterTest {
    private Hunter hunter;

    @Before
    public void setUp() {
        hunter = new Hunter();
        boolean[][] locations = new boolean[5][5];
        hunter.initialize(locations);
        hunter.setName("Hunter1");
    }

    @Test
    public void testGetShootLocations() {
        boolean[][] shootLocations = hunter.getShootLocations();
        assertNotNull(shootLocations);
        assertEquals(5, shootLocations.length);
        assertEquals(5, shootLocations[0].length);
    }

    @Test
    public void testGetName() {
        assertEquals("Hunter1", hunter.getName());
    }

    @Test
    public void testHasShot() {
        assertFalse(hunter.hasShot(2, 2));
        hunter.shoot(2, 2);
        assertTrue(hunter.hasShot(2, 2));
    }

    @Test
    public void testUpdate() {
        ICoordinate coord = new Coordinate(3,3);
        CellEvent event = new CellEvent(CellInfo.EMPTY, 1, new Coordinate(coord.getRow(), coord.getCol()));

        assertFalse(hunter.hasShot(3, 3));
        hunter.update(event);
        assertTrue(hunter.hasShot(3, 3));
    }

    @Test
    public void testIsVisited() {
        assertFalse(hunter.isVisited(1, 1));
        hunter.setVisited(1, 1, 2);
        assertTrue(hunter.isVisited(1, 1));
    }

    @Test
    public void testGetVisitedTurn() {
        assertEquals(-1, hunter.getVisitedTurn(2, 2));
        hunter.setVisited(2, 2, 5);
        assertEquals(5, hunter.getVisitedTurn(2, 2));
    }
}

