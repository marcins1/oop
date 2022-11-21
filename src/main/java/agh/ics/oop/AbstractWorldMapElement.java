package agh.ics.oop;

import java.util.ArrayList;

abstract class AbstractWorldMapElement implements IMapElement {
    protected Vector2d position;

    public AbstractWorldMapElement(Vector2d position){
        this.position = position;
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public Vector2d getPosition(){
        return this.position;
    }
}
