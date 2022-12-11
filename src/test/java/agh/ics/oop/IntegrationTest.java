package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {
    @Test
    public void runTest(){
        MoveDirection[] test = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD};
        RectangularMap map1 = new RectangularMap(10, 5);
        RectangularMap map2 = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        SimulationEngine engine = new SimulationEngine(test, map2, positions);

        assertArrayEquals(test, OptionsParser.parse(new String[] {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"}));
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(new String[]{"f", "b", "fb", "backward"}));

        Animal animal0 = new Animal(map1, positions[0]);
        assertEquals("N", animal0.toString());
        assertTrue(animal0.isAt(new Vector2d(2, 2)));
        assertTrue(map1.place(animal0));
        Animal animalc0 = (Animal) map1.objectAt(new Vector2d(2, 2));
        assertTrue(animalc0.isAt(new Vector2d(2, 2)));
        engine.run();
        Animal animalc1 = (Animal) map2.objectAt(new Vector2d(2, 0));
        Animal animalc2 = (Animal) map2.objectAt(new Vector2d(3, 4));
        assertTrue(animalc1.isAt(new Vector2d(2, 0)));
        assertTrue(animalc2.isAt(new Vector2d(3, 4)));
    }
}
