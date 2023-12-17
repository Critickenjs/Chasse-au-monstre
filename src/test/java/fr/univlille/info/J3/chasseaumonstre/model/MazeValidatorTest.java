package fr.univlille.info.J3.chasseaumonstre.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MazeValidatorTest {

    

    @Test
    public void testValidMazeGenerated() {
        MazeGenerator mazeGenerator = new MazeGenerator(5, 5);
        mazeGenerator.generate();
        MazeValidator mazeValidator = new MazeValidator(mazeGenerator);
        assertTrue(mazeValidator.isValid());
    }

    @Test
    public void testValidMazeWithObstaclesGenerated() {
        MazeGenerator mazeGenerator = new MazeGenerator(5, 5);
        mazeGenerator.generatePlateau(30); 
        MazeValidator mazeValidator = new MazeValidator(mazeGenerator);
        assertTrue(mazeValidator.isValid());
    }

    @Test
    public void testInvalidMazeGenerated() {
        int[][] maze = new int[][] {
                { 2, 1, 1, 1, 4 },
                { 0, 0, 0, 0, 1 },
                { 1, 1, 1, 0, 1 },
                { 1, 0, 0, 0, 1 },
                { 1, 1, 1, 1, 0 }
        };

        MazeValidator mazeValidator = new MazeValidator(new MazeGenerator(5, 5));
        mazeValidator.setMaze(maze);
        assertFalse(mazeValidator.isValid());
    }

    @Test
    public void testMazeWithNoPathGenerated() {
        int[][] maze = new int[][] {
                { 2, 1, 1, 1, 4 },
                { 0, 0, 1, 0, 1 },
                { 1, 1, 1, 0, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 1, 1, 1 }
        };

        MazeValidator mazeValidator = new MazeValidator(new MazeGenerator(5, 5));
        mazeValidator.setMaze(maze);
        assertFalse(mazeValidator.isValid());
    }

    @Test
    public void testEmptyMazeGenerated() {
        int[][] maze = new int[][] {
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 }
        };

        MazeValidator mazeValidator = new MazeValidator(new MazeGenerator(5, 5));
        mazeValidator.setMaze(maze);
        assertFalse(mazeValidator.isValid());
    }
}