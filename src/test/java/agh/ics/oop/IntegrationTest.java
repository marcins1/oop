package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {
    @Test
    public void runTest(){
        Animal animal0 = new Animal();
        assertEquals("Orientacja: Polnoc, Polozenie: (2,2)", animal0.toString());
        assertTrue(animal0.isAt(new Vector2d(2, 2)));

        animal0.move(MoveDirection.RIGHT);
        assertEquals("Orientacja: Wschod, Polozenie: (2,2)", animal0.toString());
        animal0.move(MoveDirection.LEFT);
        assertEquals("Orientacja: Polnoc, Polozenie: (2,2)", animal0.toString());

        animal0.move(MoveDirection.FORWARD);
        assertTrue(animal0.isAt(new Vector2d(2, 3)));
        animal0.move(MoveDirection.BACKWARD);
        assertTrue(animal0.isAt(new Vector2d(2, 2)));

        Animal animal1 = new Animal();
        Animal animal2 = new Animal();
        Animal animal3 = new Animal();
        Animal animal4 = new Animal();
        animal3.move(MoveDirection.LEFT);
        animal4.move(MoveDirection.LEFT);
        for (int i = 0; i < 4; i++){
            animal1.move(MoveDirection.FORWARD);
            animal2.move(MoveDirection.BACKWARD);
            animal3.move(MoveDirection.FORWARD);
            animal4.move(MoveDirection.BACKWARD);
        }
        assertTrue(animal1.isAt(new Vector2d(2, 4)));
        assertTrue(animal2.isAt(new Vector2d(2, 0)));
        assertTrue(animal3.isAt(new Vector2d(0, 2)));
        assertTrue(animal4.isAt(new Vector2d(4, 2)));

        MoveDirection[] test1 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
        MoveDirection[] test2 = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.BACKWARD};
        assertArrayEquals(test1, OptionsParser.parse(new String[] {"forward", "backward", "left", "right", "f", "b", "l", "r"}));
        assertArrayEquals(test2, OptionsParser.parse(new String[] {"forward", "backyard", "le", "r", "r", "back", "l", "l", "f", "front", "b"}));
    }
}
