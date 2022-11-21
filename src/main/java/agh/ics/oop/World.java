package agh.ics.oop;

import javax.swing.*;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        //String[] dane = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println("End positions");
        System.out.println(map);
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
