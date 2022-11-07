package agh.ics.oop;

import java.util.stream.Stream;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        return Stream.of(args)
                .filter(e -> (e.equals("f") || e.equals("forward") || e.equals("b") || e.equals("backward") || e.equals("l") || e.equals("left") || e.equals("r") || e.equals("right")))
                .map(OptionsParser::mapDirection)
                .toArray(MoveDirection[]::new);
    }

    public static MoveDirection mapDirection(String element){
        return switch (element) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            default -> MoveDirection.LEFT;
        };
    }
}
