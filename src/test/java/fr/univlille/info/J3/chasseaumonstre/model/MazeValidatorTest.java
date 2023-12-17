package fr.univlille.info.J3.chasseaumonstre.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeValidatorTest {
    private MazeValidator mazeValidator;
    private int[][] maze;

    @BeforeEach
    public void setUp() throws Exception {
        mazeValidator = new MazeValidator(new MazeGenerator(5, 5));
    }

    @Test
    public void testValidMaze() {
        boolean[][] path = new boolean[][] {
                { true, false, false, false, false },
                { true, true, true, true, false },
                { false, false, false, true, false },
                { false, true, true, true, false },
                { false, false, false, true, true }
        };

        Coordinate entrance = new Coordinate(0, 0);
        Coordinate exit = new Coordinate(4, 4);
        assertTrue(MazeValidator.isValid(path, entrance, exit));
    }

    @Test
    public void testInvalidMaze() {
        maze = new int[][] {
                { 2, 1, 1, 1, 4 },
                { 0, 0, 0, 0, 1 },
                { 1, 1, 1, 0, 1 },
                { 1, 0, 0, 0, 1 },
                { 1, 1, 1, 1, 0 }
        };

        mazeValidator.setMaze(maze);
        assertFalse(mazeValidator.isValid());
    }

    @Test
    public void testMazeWithNoPath() {
        maze = new int[][] {
                { 2, 1, 1, 1, 4 },
                { 0, 0, 1, 0, 1 },
                { 1, 1, 1, 0, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 1, 1, 1 }
        };

        mazeValidator.setMaze(maze);
        assertFalse(mazeValidator.isValid());
    }

    @Test
    public void testEmptyMaze() {
        maze = new int[][] {
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 }
        };

        mazeValidator.setMaze(maze);
        assertFalse(mazeValidator.isValid());
    }
}