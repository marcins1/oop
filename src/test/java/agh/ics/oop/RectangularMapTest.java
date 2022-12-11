package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    public void canMoveToTest(){
        RectangularMap map = new RectangularMap(4, 4);
        map.place(new Animal(map, new Vector2d(2,2)));
        assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        assertTrue(map.canMoveTo(new Vector2d(3, 3)));
        assertFalse(map.canMoveTo(new Vector2d(4, 4)));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    public void placeTest() {
        RectangularMap map = new RectangularMap(4, 4);
        assertTrue(map.place(new Animal(map, new Vector2d(2, 2))));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(2, 2))));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(4, 4))));
    }

    @Test
    public void isOccupiedTest() {
        RectangularMap map = new RectangularMap(4, 4);
        map.place(new Animal(map, new Vector2d(2, 2)));
        assertFalse(map.isOccupied(new Vector2d(1, 1)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    public void objectAtTest(){
        RectangularMap map = new RectangularMap(4, 4);
        map.place(new Animal(map, new Vector2d(2, 2)));
        assertNull(map.objectAt(new Vector2d(3, 3)));
        assertTrue(map.objectAt(new Vector2d(2, 2)) instanceof Animal);
    }
}
