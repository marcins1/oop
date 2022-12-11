package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap{
    private final int width;
    private final int height;

    public RectangularMap(int width, int height){
        this.height = height;
        this.width = width;
    }

    public boolean canMoveTo(Vector2d position){
        return position.x >= 0 && position.x < this.width && position.y >= 0 && position.y < this.height && super.canMoveTo(position);
    }

    public boolean place(Animal animal){
        Vector2d animalPosition = animal.getPosition();
        if (animalPosition.x >= 0 && animalPosition.x < this.width && animalPosition.y >= 0 && animalPosition.y < this.height){
            return super.place(animal);
        }
        throw new IllegalArgumentException("Can't place grass on position: " + animalPosition);
    }

    public Vector2d getLowerLeft(){
        return new Vector2d(0, 0);
    }

    public Vector2d getUpperRight(){
        return new Vector2d(this.width - 1, this.height - 1);
    }
}