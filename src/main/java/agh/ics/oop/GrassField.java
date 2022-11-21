package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;

public class GrassField extends AbstractWorldMap{
    private final int quantity;
    private final ArrayList<Grass> grasses;

    public GrassField(int quantity){
        this.quantity = quantity;
        this.grasses = new ArrayList<Grass>();
        spawnGrasses(quantity);
    }

    public boolean canMoveTo(Vector2d position){
        Object mapElement = objectAt(position);
        if (mapElement instanceof Grass){
            // Eating the grass
            grasses.remove(mapElement);
            Vector2d newGrassPosition;
            do{
                newGrassPosition = getPositionForGrass();
            } while (newGrassPosition.equals(position));
            grasses.add(new Grass(newGrassPosition));
            //
            return true;
        } else return mapElement == null;
    }

    public boolean place(Grass grass){
        Vector2d position = grass.getPosition();
        if (!isOccupied(position)){
            this.grasses.add(grass);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position){
        if (super.isOccupied(position)){
            return true;
        }
        for (Grass grass: this.grasses) {
            if (grass.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    public Object objectAt(Vector2d position){
        Animal check = (Animal) super.objectAt(position);
        if (check != null){
            return check;
        }
        for (Grass grass: this.grasses) {
            if (grass.getPosition().equals(position)){
                return grass;
            }
        }
        return null;
    }

    public Vector2d getLowerLeft(){
        Vector2d ll = new Vector2d(0, 0);
        for (Grass grass:this.grasses) {
            ll = ll.lowerLeft(grass.getPosition());
        }
        for (Animal animal: this.animals) {
            ll = ll.lowerLeft(animal.getPosition());
        }
        return ll;
    }

    public Vector2d getUpperRight(){
        Vector2d ur = new Vector2d(0, 0);
        for (Grass grass:this.grasses) {
            ur = ur.upperRight(grass.getPosition());
        }
        for (Animal animal: this.animals) {
            ur = ur.upperRight(animal.getPosition());
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
