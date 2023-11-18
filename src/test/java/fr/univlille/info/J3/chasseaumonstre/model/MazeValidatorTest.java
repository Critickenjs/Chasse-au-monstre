package fr.univlille.info.J3.chasseaumonstre.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeValidatorTest {
    private int width;
    private int height;
    private int[][] maze;
    private MazeValidator mazeValidator;

    @BeforeEach
    public void setUp() {
        width = 5;
        height = 5;
        maze = new int[][]{
            {2, 0, 0, 0, 4},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0}
        };
        mazeValidator = new MazeValidator(width, height, maze);
    }

    @Test
    public void testValidMaze1() {
        boolean isValid = mazeValidator.isValid();
        assertTrue(isValid);
    }

    @Test
    public void testValidMaze2() {
        int[][] maze = new int[][]{
            {2, 1, 1, 1, 4},
            {0, 0, 0, 0, 0},
            {1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0},
            {1, 1, 1, 0, 1}
        };

        mazeValidator.setMaze(maze);
        boolean isValid = mazeValidator.isValid();
        assertTrue(isValid);
    }

    @Test
    public void testInvalidMaze1() {
        int[][] maze = new int[][]{
            {2, 1, 1, 1, 4},
            {0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1},
            {1, 0, 0, 1, 1},
            {1, 1, 1, 1, 1}
        };

        mazeValidator.setMaze(maze);
        boolean isValid = mazeValidator.isValid();
        assertFalse(isValid);
    }

    @Test
    public void testInvalidMaze2() {
        int[][] maze = new int[][]{
            {2, 1, 1, 1, 4},
            {0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 0}
        };

        mazeValidator.setMaze(maze);
        boolean isValid = mazeValidator.isValid();
        assertFalse(isValid);
    }

    @Test
    public void testMazeWithNoPath() {
        int[][] maze = new int[][]{
            {2, 1, 1, 1, 4},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 0, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1}
        };

        mazeValidator.setMaze(maze);
        boolean isValid = mazeValidator.isValid();
        assertFalse(isValid);
    }

    @Test
    public void testEmptyMaze() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1}
        };

        mazeValidator.setMaze(maze);
        boolean isValid = mazeValidator.isValid();
        assertFalse(isValid);
    }
}
