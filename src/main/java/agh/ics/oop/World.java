package agh.ics.oop;

import java.util.Arrays;
import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        out.println("Start");
        run(replace(args));
        out.println("Stop");
    }

    public static Direction[] replace(String[] arr) {
        Direction[] directions = new Direction[arr.length];
        int counter = 0;
        for (String str : arr) {
            switch (str) {
                case "f" -> {
                    directions[counter] = Direction.FORWARD;
                    counter++;
                }
                case "b" -> {
                    directions[counter] = Direction.BACKWARD;
                    counter++;
                }
                case "r" -> {
                    directions[counter] = Direction.RIGHT;
                    counter++;
                }
                case "l" -> {
                    directions[counter] = Direction.LEFT;
                    counter++;
                }
                default -> {
                }
            }
        }
        return Arrays.copyOfRange(directions, 0, counter);
    }

    public static void run(Direction[] arr) {
        for (Direction dir : arr) {
            String message = switch (dir) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tylu";
                case RIGHT -> "Zwierzak skreca w prawo";
                case LEFT -> "Zwierzak skreca w lewo";
            };
            out.println(message);
        }
    }
}