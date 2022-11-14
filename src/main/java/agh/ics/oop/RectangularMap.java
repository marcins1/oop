package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    private final Animal[][] map;
    private final int width;
    private final int height;

    public RectangularMap(int width, int height){
        this.map = new Animal[height][width];
        this.height = height;
        this.width = width;
    }

    public boolean canMoveTo(Vector2d position){
        return position.x >= 0 && position.x < this.width && position.y >= 0 && position.y < this.height;
    }

    public boolean place(Animal animal){
        Vector2d position = animal.getPosition();
        if (!isOccupied(position)){
            this.map[position.y][position.x] = animal;
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position){
        return map[position.y][position.x] != null;
    }

    public Object objectAt(Vector2d position){
        return this.map[position.y][position.x];
    }

    public void remove(Vector2d position){
        this.map[position.y][position.x] = null;
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(new Vector2d(0, 0), new Vector2d(this.width - 1, this.height - 1));
    }
}
