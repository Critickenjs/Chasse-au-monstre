package chasseaumonstre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chasseaumonstre.model.MazeGenerator;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;

import static org.junit.jupiter.api.Assertions.*;

public class MazeGeneratorTest {
    private MazeGenerator generator;

    @BeforeEach
    public void setUp() {
        generator = new MazeGenerator(10, 10);
        generator.generate();
    }

    @Test
    public void testMazeGeneration() {
        int[][] maze = generator.getMaze();

        assertEquals(10, maze.length);
        assertEquals(10, maze[0].length);

        assertEquals(0, maze[generator.getEntranceRow()][0]); // Entrée
        assertEquals(0, maze[generator.getExitRow()][9]); // Sortie
    }

    @Test
    public void testCellInfoConversion() {
        CellInfo[][] cellInfo = generator.toCellInfo();

        assertEquals(CellInfo.MONSTER, cellInfo[generator.getEntranceRow()][0]);
        assertEquals(CellInfo.EXIT, cellInfo[generator.getExitRow()][9]);
        boolean foundEmpty = false;
        boolean foundWall = false;

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (cellInfo[x][y] == CellInfo.WALL) {
                    foundWall = true;
                } else if (cellInfo[x][y] == CellInfo.EMPTY) {
                    foundEmpty = true;
                }
            }
        }

        assertTrue(foundEmpty);
        assertTrue(foundWall);
    }
}
