package agh.ics.oop;

import java.util.stream.Stream;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        return Stream.of(args)
                .map(OptionsParser::mapDirection)
                .toArray(MoveDirection[]::new);
    }

    public static MoveDirection mapDirection(String element){
        return switch (element) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "l", "left" -> MoveDirection.LEFT;
            default -> throw new IllegalArgumentException(element + " is not legal move specification");
        };
    }
}
