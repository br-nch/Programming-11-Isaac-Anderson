import org.junit.Before;
import org.junit.Test;
import sample.Spell;
import sample.Wizard;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCases {
    Wizard test;
    ArrayList<Spell> testSpells;

    @Before
    public void setUp() {
        testSpells = new ArrayList<>();
        test = new Wizard("test", testSpells);
    }


    @Test
    public void changeLevelTest(){
        assertEquals(test.getLevel(), 1);
        test.changeLevel(2);
        assertEquals(test.getLevel(), 3);
        test.changeLevel(0);
        assertEquals(test.getLevel(), 3);
        test.changeLevel((-1));
        assertEquals(test.getLevel(), 2);
    }

    @Test
    public void addSpellTest() {
        assertTrue(test.getSpellList().isEmpty());
        test.addSpell(new Spell("Fireball", "Evocation", 3, "1 action", "VSM", 120, 0, "", false));
        assertEquals(test.getSpellList().get(0).getName(), "Fireball");
    }

    @Test
    public void FavSchoolTest(){
        assertTrue(test.getSpellList().isEmpty());
        test.addSpell(new Spell("Fireball", "Conjuration", 3, "1 action", "VSM", 120, 0, "", false));
        test.addSpell(new Spell("Fireball", "Evocation", 3, "1 action", "VSM", 120, 0, "", false));
        test.addSpell(new Spell("Fireball", "Evocation", 3, "1 action", "VSM", 120, 0, "", false));
        assertEquals(test.getFavouriteSchool(), "Evocation");
        ArrayList<Spell> testList;
        testList = test.getSpellList();
        testList.remove(2);
        testList.remove(1);
        test.setSpellList(testList);
        assertEquals(test.getFavouriteSchool(), "Conjuration");
        testList.add(new Spell("Fireball", "Evocation", 3, "1 action", "VSM", 120, 0, "", false));
        assertEquals(test.getFavouriteSchool(), "Conjuration and Evocation");
    }
}
