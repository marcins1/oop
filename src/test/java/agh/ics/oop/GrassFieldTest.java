package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrassFieldTest {
    @Test
    public void canMoveToTest(){
        GrassField map = new GrassField(1);
        map.place(new Animal(map, new Vector2d(5,5)));
        map.place(new Grass(new Vector2d(6,6)));
        assertTrue(map.canMoveTo(new Vector2d(7, 7)));
        assertTrue(map.canMoveTo(new Vector2d(1000, -1000)));
        assertTrue(map.canMoveTo(new Vector2d(6, 6)));
        assertFalse(map.canMoveTo(new Vector2d(5, 5)));
    }

    @Test
    public void placeTest() {
        GrassField map = new GrassField(0);
        assertTrue(map.place(new Animal(map, new Vector2d(5, 5))));
        assertTrue(map.place(new Animal(map, new Vector2d(-1000, 1000))));
        assertFalse(map.place(new Animal(map, new Vector2d(5, 5))));
        assertTrue(map.place(new Grass(new Vector2d(6, 6))));
        assertFalse(map.place(new Grass(new Vector2d(5, 5))));
        assertFalse(map.place(new Grass(new Vector2d(6, 6))));
    }

    @Test
    public void isOccupiedTest() {
        GrassField map = new GrassField(0);
        map.place(new Animal(map, new Vector2d(2, 2)));
        map.place(new Grass(new Vector2d(3, 3)));
        assertFalse(map.isOccupied(new Vector2d(1, 1)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(3, 3)));
    }

    @Test
    public void objectAtTest(){
        GrassField map = new GrassField(0);
        map.place(new Animal(map, new Vector2d(2, 2)));
        map.place(new Grass(new Vector2d(3, 3)));
        assertNull(map.objectAt(new Vector2d(4, 4)));
        assertTrue(map.objectAt(new Vector2d(2, 2)) instanceof Animal);
        assertTrue(map.objectAt(new Vector2d(3, 3)) instanceof Grass);
    }
}
