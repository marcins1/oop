package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap {
    protected List<Animal> animals = new ArrayList<>();
    protected MapVisualizer visualizer = new MapVisualizer(this);

    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position);
    }

    public boolean place(Animal animal){
        Vector2d position = animal.getPosition();
        if (!isOccupied(position)){
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position){
        return objectAt(position) instanceof Animal;
    }

    public Object objectAt(Vector2d position){
        for (Animal animal: this.animals) {
            if (animal.isAt(position)){
                return animal;
            }
        }
        return null;
    }

    abstract Vector2d getLowerLeft();

    abstract Vector2d getUpperRight();

    @Override
    public String toString() {
        return visualizer.draw(getLowerLeft(), getUpperRight());
    }
}
