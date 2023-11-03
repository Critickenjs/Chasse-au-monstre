package chasseaumonstre;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import chasseaumonstre.model.CellEvent;
import chasseaumonstre.model.Coordinate;
import chasseaumonstre.model.strategy.monster.Monster;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;


public class MonsterTest {
    private Monster monster;

    @Before
    public void setUp() {
        boolean[][] visitedLocations = new boolean[5][5];  
        monster = new Monster(visitedLocations);
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
        monster.setVisited(2, 3); 
        assertTrue(monster.isVisited(2, 3)); 
    }

    @Test
    public void testGetVisitedTurn() {
        monster.setCoord(1, 1, 5); 
        int visitedTurn = monster.getVisitedTurn(1, 1);
        assertEquals(5, visitedTurn);
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
       

    
}

