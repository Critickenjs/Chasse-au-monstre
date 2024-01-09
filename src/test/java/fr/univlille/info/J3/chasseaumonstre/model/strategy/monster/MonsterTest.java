package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import fr.univlille.info.J3.chasseaumonstre.model.CellEvent;
import fr.univlille.info.J3.chasseaumonstre.model.Coordinate;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

public class MonsterTest {
    private Monster monster;

    @Before
    public void setUp() {
        boolean[][] map = new boolean[][]{{true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}};
        monster = new Monster(map);
    }

    @Test
    public void testSetExit() {
        monster.setExit(4, 4); 
        ICoordinate exit = monster.getExit();
        assertEquals(4, exit.getRow());
        assertEquals(4, exit.getCol());
    }

    @Test
    public void testSetEntry() {
        monster.setEntry(0, 0); 
        ICoordinate entry = monster.getEntry();
        assertEquals(0, entry.getRow());
        assertEquals(0, entry.getCol());
    }

    @Test
    public void testIsVisited() {
        assertFalse(monster.isVisited(2, 3)); 
        monster.setVisited(2, 3, 5); 
        assertTrue(monster.isVisited(2, 3)); 
        monster.setVisited(new Coordinate(2, 4), 5);
        assertTrue(monster.isVisited(2, 4));
    }

    @Test
    public void testGetVisitedTurn() {
        monster.setCoord(1, 1, 5); 
        Integer visitedTurn = monster.getVisitedTurn(1, 1);
        assertEquals(Integer.valueOf(5), visitedTurn);
        visitedTurn = monster.getVisitedTurn(99, 99);
        assertEquals(Integer.valueOf(-1), visitedTurn);
        visitedTurn = monster.getVisitedTurn(2, 2);
        assertEquals(Integer.valueOf(-1), visitedTurn);
    }

    @Test
    public void testEstAdjacente() {
        monster.setCoord(1, 1, 5); 
        assertTrue(monster.estAdjacente(1, 2));
        assertFalse(monster.estAdjacente(3, 3));
    }

    @Test
    public void testUpdate() {
        Coordinate coord = new Coordinate(3, 3);
        ICellEvent event = new CellEvent(CellInfo.MONSTER, 1, coord);

        assertNull(monster.getCoord());
        monster.update(event);
        assertEquals(coord, monster.getCoord());
    }

    @Test
    public void testAi() {
        assertFalse(monster.isAi());
        monster.setAi(true);
        assertTrue(monster.isAi());
    }
       
    @Test
    public void testFov() {
        monster.setFov(5);
        assertEquals(5, monster.getFov());
    }

    @Test
    public void testEstVisible() {
        monster.setFov(1);
        monster.setCoord(1, 1, 5); 
        assertTrue(monster.estVisible(1, 2));
        assertFalse(monster.estVisible(3, 3));
    }
}

