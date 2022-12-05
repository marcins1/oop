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
            // Eating the grass
            grasses.remove(position);
            Vector2d newGrassPosition;
            do{
                newGrassPosition = getPositionForGrass();
            } while (newGrassPosition.equals(position));
            grasses.put(newGrassPosition, new Grass(newGrassPosition));
            //
            return true;
        } else return mapElement == null;
    }

    public boolean place(Grass grass){
        Vector2d position = grass.getPosition();
        if (!isOccupied(position)){
            this.grasses.put(position, grass);
            return true;
        }
        return false;
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
        Vector2d ll = new Vector2d(0, 0);
        for (Vector2d position:this.grasses.keySet()) {
            ll = ll.lowerLeft(position);
        }
        for (Vector2d position:this.animals.keySet()) {
            ll = ll.lowerLeft(position);
        }
        return ll;
    }

    public Vector2d getUpperRight(){
        Vector2d ur = new Vector2d(0, 0);
        for (Vector2d position:this.grasses.keySet()) {
            ur = ur.upperRight(position);
        }
        for (Vector2d position:this.animals.keySet()) {
            ur = ur.upperRight(position);
        }
        return ur;
    }

    public Vector2d getPositionForGrass(){
        Random random = new Random();
        Vector2d position;
        do{
            position = new Vector2d(random.nextInt((int)Math.sqrt(this.quantity * 10)), random.nextInt((int)Math.sqrt(this.quantity * 10)));
        } while (objectAt(position) != null);
        return position;
    }

    public void spawnGrasses(int number){
        Random random = new Random();
        for (int i = 0; i < number; i++){
            Vector2d position;
            do{
                position = new Vector2d(random.nextInt((int)Math.sqrt(this.quantity * 10)), random.nextInt((int)Math.sqrt(this.quantity * 10)));
            } while (!place(new Grass(position)));
        }
    }
}
