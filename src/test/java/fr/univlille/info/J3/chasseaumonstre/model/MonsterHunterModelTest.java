package fr.univlille.info.J3.chasseaumonstre.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

public class MonsterHunterModelTest {

    private MonsterHunterModel model;

    @Before
    public void setUp() {
        model = new MonsterHunterModel();
        model.setHeight(7);
        model.setWidth(7);
        model.initialize();
    }

    @Test
    public void testMonsterHunterModelConstructor() {
        MonsterHunterModel testModel = new MonsterHunterModel();
        assertEquals(1, testModel.getTurn().intValue());
        assertNotNull(testModel.getMonster());
        assertNotNull(testModel.getHunter());
    }

    @Test
    public void testMonsterName() {
        MonsterHunterModel testModel = new MonsterHunterModel();
        String testMonsterName = "TestMonster";
        testModel.setMonsterName(testMonsterName);
        assertEquals(testMonsterName, testModel.getMonsterName());
    }

    @Test
    public void testHunterName() {
        MonsterHunterModel testModel = new MonsterHunterModel();
        String testHunterName = "TestHunter";
        testModel.setHunterName(testHunterName);
        assertEquals(testHunterName, testModel.getHunterName());
    }

    @Test
    public void testConstructorInitialization() {
        assertEquals(7, model.getWidth());
        assertEquals(7, model.getHeight());
        assertNotNull(model.getMonster());
        assertNotNull(model.getHunter());
        assertEquals(1, model.getTurn().intValue());
        assertNotNull(model.getMaze());
        assertNotNull(model.getEntrance());
        assertNotNull(model.getExit());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetWidthInvalid() {
        MonsterHunterModel testModel = new MonsterHunterModel();
        testModel.setWidth(6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHeightInvalid() {
        MonsterHunterModel testModel = new MonsterHunterModel();
        testModel.setHeight(4);
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void testSetObstaclesInvalid() {
        MonsterHunterModel testModel = new MonsterHunterModel();
        testModel.setObstacles(42);
    }*/

    @Test
    public void testNextTurn() {
        MonsterHunterModel testModel = new MonsterHunterModel();
        int initialTurn = testModel.getTurn();
        testModel.nextTurn();
        assertEquals(initialTurn + 1, testModel.getTurn().intValue());
    }

    /*@Test
    public void testImportMaze() throws IOException {
        MonsterHunterModel testModel = new MonsterHunterModel();
        File testFile = new File(
                "/home/infoetu/atilla.tas.etu/S3/SAÉ3A/J3_SAE3A/src/test/java/fr/univlille/info/J3/chasseaumonstre/model/importMaze");
        testModel.importMaze(testFile);
        assertEquals(7, testModel.getHeight());
        assertEquals(7, testModel.getWidth());
        assertNotNull(testModel.getMaze());
        assertEquals(3, testModel.getMonster().getCoord().getCol());
        assertEquals(0, testModel.getMonster().getCoord().getRow());
    }*/
}
