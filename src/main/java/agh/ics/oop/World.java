package agh.ics.oop;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        out.println("Start");
        Direction[] directions = Stream.of(args)
                .filter(e -> ("f".equals(e) || "b".equals(e) || "r".equals(e) || "l".equals(e)))
                .map(World::mapDirection)
                .toArray(Direction[]::new);
        Stream.of(directions)
                .forEach(e -> out.println("Zwierzak " + getDirection(e)));
        out.println("Stop");
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
