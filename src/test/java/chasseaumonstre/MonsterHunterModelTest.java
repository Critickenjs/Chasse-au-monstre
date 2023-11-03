package chasseaumonstre;

import org.junit.Before;
import org.junit.Test;

import chasseaumonstre.model.MonsterHunterModel;

import static org.junit.Assert.*;

public class MonsterHunterModelTest {

    private MonsterHunterModel model;

    @Before
    public void setUp() {
        model = new MonsterHunterModel(5, 5);
    }

    @Test
    public void testConstructorInitialization() {
        assertEquals(5, model.getWidth());
        assertEquals(5, model.getHeight());
        assertNotNull(model.getMonster());
        assertNotNull(model.getHunter());
        assertEquals(1, model.getTurn().intValue());
        assertNotNull(model.getMaze());
        assertNotNull(model.getEntrance());
        assertNotNull(model.getExit());
    }
}
