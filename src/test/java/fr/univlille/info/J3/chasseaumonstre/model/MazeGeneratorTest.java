package fr.univlille.info.J3.chasseaumonstre.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        final int EMPTY = 0;
        final int WALL = 1;
        final int MONSTER = 2;
        final int EXIT = 4;
        int[][] cellInfoInt = generator.getMaze();
        MazeValidator mazeValidator = new MazeValidator(generator);
        if (mazeValidator.isValid()) {
        assertEquals(MONSTER, cellInfoInt[generator.getEntranceRow()][0]);
        assertEquals(EXIT, cellInfoInt[generator.getExitRow()][9]);
        boolean foundEmpty = false;
        boolean foundWall = false;

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (cellInfoInt[x][y] == WALL) {
                    foundWall = true;
                } else if (cellInfoInt[x][y] == EMPTY) {
                    foundEmpty = true;
                }
            }
        }

        assertTrue(foundEmpty);
        assertTrue(foundWall);
            
        }
    
        
    }
}
