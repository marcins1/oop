package agh.ics.oop;

public class Grass extends AbstractWorldMapElement{
    public Grass(Vector2d position){
        super(position);
    }

    @Override
    public String toString(){
        return "*";
    }

    public String getResourceName(){
        return "grass.png";
    }
}