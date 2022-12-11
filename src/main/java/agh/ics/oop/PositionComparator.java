package agh.ics.oop;

import java.util.Comparator;

public class PositionComparator implements Comparator<Vector2d> {
    private final boolean sortByX;

    public PositionComparator(boolean sortByX) {
        this.sortByX = sortByX;
    }

    @Override
    public int compare(Vector2d firstElement, Vector2d secondElement) {
        if (sortByX) {
            return Integer.compare(firstElement.x, secondElement.x);
        } else {
            return Integer.compare(firstElement.y, secondElement.y);
        }
    }
}
