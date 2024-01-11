package fr.univlille.info.J3.chasseaumonstre.model.strategy.monster.algorithm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import fr.univlille.info.J3.chasseaumonstre.model.Coordinate;

public class DepthFirstSearchTest {
    private boolean[][] map;
    private MonsterAlgorithm dfs, astar, dijkstra;
    private ICoordinate entry, exit;
    private ICoordinate wrongEntry;

    @BeforeEach
    public void setUp() {
        map = new boolean[5][5];
        map[0][0] = false; map[1][0] = true;   map[2][0] = true;   map[3][0] = true;   map[4][0] = true;
        map[0][1] = true; map[1][1] = true;  map[2][1] = false;  map[3][1] = false;  map[4][1] = true;
        map[0][2] = true; map[1][2] = true;  map[2][2] = true;   map[3][2] = true;  map[4][2] = true;
        map[0][3] = true; map[1][3] = false;  map[2][3] = false;  map[3][3] = true;  map[4][3] = false;
        map[0][4] = true; map[1][4] = true;   map[2][4] = true;   map[3][4] = false;   map[4][4] = true;
        entry = new Coordinate(1, 1);
        exit = new Coordinate(3, 3);
        wrongEntry = new Coordinate(0, 0);
        dfs = new DepthFirstSearch(entry, exit, map);
        astar = new AStar(entry, exit, map);
        dijkstra = new Dijkstra(entry, exit, map);
    }

    @Test
    public void testGetEntry() {
        ICoordinate algoEntry = dfs.getEntry();
        assertEquals(algoEntry, entry);
        algoEntry = astar.getEntry();
        assertEquals(algoEntry, entry);
        algoEntry = dijkstra.getEntry();
        assertEquals(algoEntry, entry);
    }

    @Test
    public void testGetExit() {
        ICoordinate algoExit = dfs.getExit();
        assertEquals(algoExit, exit);
        algoExit = astar.getExit();
        assertEquals(algoExit, exit);
        algoExit = dijkstra.getExit();
        assertEquals(algoExit, exit);
    }

    @Test
    public void testGetMaze() {
        boolean[][] algoMap = dfs.getMaze();
        assertArrayEquals(algoMap, map);
        algoMap = astar.getMaze();
        assertArrayEquals(algoMap, map);
        algoMap = dijkstra.getMaze();
        assertArrayEquals(algoMap, map);
    }

    @Test
    public void testExecute() {
        ICoordinate entry = dfs.getEntry();
        ICoordinate exit = dfs.getExit();
        boolean[][] map = dfs.getMaze();
        dfs = new DepthFirstSearch(entry, exit, map);
        List<ICoordinate> path = dfs.execute();
        assertNotNull(path);
        assertEquals(path.size(), 7);

        entry = astar.getEntry();
        exit = astar.getExit();
        map = astar.getMaze();
        astar = new AStar(entry, exit, map);
        path = astar.execute();
        assertNotNull(path);
        assertEquals(path.size(), 5);

        entry = dijkstra.getEntry();
        exit = dijkstra.getExit();
        map = dijkstra.getMaze();
        dijkstra = new Dijkstra(entry, exit, map);
        path = dijkstra.execute();
        assertNotNull(path);
        assertEquals(path.size(), 5);
    }

    @Test
    public void testExecuteWithWrongEntry() {
        dfs = new DepthFirstSearch(wrongEntry, exit, map);
        List<ICoordinate> path = dfs.execute();
        assertEquals(path, null);

        astar = new AStar(wrongEntry, exit, map);
        path = astar.execute();
        assertEquals(path, null);

        dijkstra = new Dijkstra(wrongEntry, exit, map);
        path = dijkstra.execute();
        assertEquals(path, null);
    }

    @Test
    public void testExecuteWithWrongMap() {
        boolean[][] wrongMap = new boolean[5][5];
        dfs = new DepthFirstSearch(entry, exit, wrongMap);
        List<ICoordinate> path = dfs.execute();
        assertEquals(path, null);

        astar = new AStar(entry, exit, wrongMap);
        path = astar.execute();
        assertEquals(path, null);

        dijkstra = new Dijkstra(entry, exit, wrongMap);
        path = dijkstra.execute();
        assertEquals(path, null);
    }

}
