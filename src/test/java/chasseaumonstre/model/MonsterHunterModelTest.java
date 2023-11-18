package chasseaumonstre.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
}
