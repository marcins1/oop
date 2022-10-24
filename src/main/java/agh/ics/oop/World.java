package agh.ics.oop;

import javax.swing.*;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        /* out.println("Start");
        Direction[] directions = Stream.of(args)
                .filter(e -> ("f".equals(e) || "b".equals(e) || "r".equals(e) || "l".equals(e)))
                .map(World::mapDirection)
                .toArray(Direction[]::new);
        Stream.of(directions)
                .forEach(e -> out.println("Zwierzak " + getDirection(e)));
        out.println("Stop"); */
        Vector2d position1 = new Vector2d(1,2);
        out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        out.println(position2);
        out.println(position1.add(position2));

        MapDirection direction = MapDirection.NORTH;
        out.println(direction);
        out.println(direction.next());
        out.println(direction.previous());
        out.println(direction.toUnitVector());
    }

    public static Direction mapDirection(String element){
        return switch (element) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "r" -> Direction.RIGHT;
            default -> Direction.LEFT;
        };
    }

    public static String getDirection(Direction element){
        return switch (element) {
            case FORWARD -> "idzie do przodu";
            case BACKWARD -> "idzie do tylu";
            case RIGHT -> "skreca w prawo";
            default -> "skreca w lewo";
        };
    }
}
