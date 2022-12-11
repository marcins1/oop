package agh.ics.oop;

import java.util.ArrayList;

abstract class AbstractWorldMapElement implements IMapElement {
    protected Vector2d position;
    protected ArrayList<IPositionChangeObserver> observers;

    public AbstractWorldMapElement(Vector2d position){
        this.position = position;
        this.observers = new ArrayList<IPositionChangeObserver>();
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public Vector2d getPosition(){
        return this.position;
    }

    void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    void positionChanged(Vector2d newPosition){
        for (IPositionChangeObserver observer:this.observers) {
            observer.positionChanged(this.position, newPosition);
        }
    }
}
