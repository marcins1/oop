package agh.ics.oop;

import javax.swing.*;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        out.println(animal);
        for (MoveDirection d:OptionsParser.parse(args)) {
            animal.move(d);
            out.println(animal);
        }
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
