package agh.ics.oop;

import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    private final TreeSet<Vector2d> sortedByX = new TreeSet<Vector2d>(new PositionComparator(true));
    private final TreeSet<Vector2d> sortedByY = new TreeSet<Vector2d>(new PositionComparator(false));

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removeElement(oldPosition);
        insertElement(newPosition);
    }

    public void insertElement(Vector2d element){
        sortedByX.add(element);
        sortedByY.add(element);
    }

    public void removeElement(Vector2d element){
        sortedByX.remove(element);
        sortedByY.remove(element);
    }

    public Vector2d getLowerLeft(){
        return new Vector2d(sortedByX.first().x, sortedByY.first().y);
    }

    public Vector2d getUpperRight(){
        return new Vector2d(sortedByX.last().x, sortedByY.last().y);
    }
}
