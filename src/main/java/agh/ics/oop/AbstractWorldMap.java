package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected MapVisualizer visualizer = new MapVisualizer(this);

    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position);
    }

    public boolean place(Animal animal){
        Vector2d position = animal.getPosition();
        if (!isOccupied(position)){
            animal.addObserver(this);
            this.animals.put(position, animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position){
        return objectAt(position) instanceof Animal;
    }

    public Object objectAt(Vector2d position){
        return animals.get(position);
    }

    abstract Vector2d getLowerLeft();

    abstract Vector2d getUpperRight();

    @Override
    public String toString() {
        return visualizer.draw(getLowerLeft(), getUpperRight());
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
    }
}
