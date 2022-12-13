package agh.ics.oop;

import java.util.Comparator;

public class PositionComparator implements Comparator<Vector2d> {
    private final boolean sortByX;

    public PositionComparator(boolean sortByX) {
        this.sortByX = sortByX;
    }

    @Override
    public int compare(Vector2d firstElement, Vector2d secondElement) {
        int value;
        if (sortByX) {
            value = Integer.compare(firstElement.x, secondElement.x);
            if (value == 0) return Integer.compare(firstElement.y, secondElement.y);
        } else {
            value = Integer.compare(firstElement.y, secondElement.y);
            if (value == 0) return Integer.compare(firstElement.x, secondElement.x);
        }
        return value;
    }
}
