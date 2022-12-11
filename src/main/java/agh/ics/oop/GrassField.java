package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap{
    private final int quantity;
    private final Map<Vector2d, Grass> grasses;

    public GrassField(int quantity){
        this.quantity = quantity;
        this.grasses = new HashMap<>();
        spawnGrasses(quantity);
    }

    public boolean canMoveTo(Vector2d position){
        Object mapElement = objectAt(position);
        if (mapElement instanceof Grass){
            eatGrass(position);
            return true;
        } else return mapElement == null;
    }

    public boolean place(Grass grass){
        Vector2d position = grass.getPosition();
        if (!isOccupied(position)){
            this.grasses.put(position, grass);
            this.boundary.insertElement(position);
            return true;
        } else {
            throw new IllegalArgumentException("Can't place grass on position: " + position);
        }
    }

    public boolean isOccupied(Vector2d position){
        if (super.isOccupied(position)){
            return true;
        }
        return objectAt(position) instanceof Grass;
    }

    public Object objectAt(Vector2d position){
        Animal check = (Animal) super.objectAt(position);
        if (check != null){
            return check;
        }
        return grasses.get(position);
    }

    public Vector2d getLowerLeft(){
        return boundary.getLowerLeft();
    }

    public Vector2d getUpperRight(){
        return boundary.getUpperRight();
    }

    public void spawnGrasses(int number){
        Random random = new Random();
        for (int i = 0; i < number; i++){
            Vector2d position;
            do{
                position = new Vector2d(random.nextInt((int)Math.sqrt(this.quantity * 10)), random.nextInt((int)Math.sqrt(this.quantity * 10)));
            } while (isOccupied(position));
            place(new Grass(position));
        }
    }

    public void eatGrass(Vector2d position){
        while (isOccupied(position)){
            grasses.remove(position);
            boundary.removeElement(position);
            spawnGrasses(1);
        }
    }
}
