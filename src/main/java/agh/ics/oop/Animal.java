package agh.ics.oop;

public class Animal extends AbstractWorldMapElement{
    private MapDirection direction;
    private final IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition){
        super(initialPosition);
        this.direction = MapDirection.NORTH;
        this.map = map;
    }

    @Override
    public String toString() {
        return switch (this.direction){
            case NORTH -> "N";
            case SOUTH -> "S";
            case WEST -> "W";
            case EAST -> "E";
        };
    }

    public void move(MoveDirection direction){
        if (direction == MoveDirection.LEFT){
            this.direction = this.direction.previous();
        } else if (direction == MoveDirection.RIGHT){
            this.direction = this.direction.next();
        } else {
            Vector2d newPosition;
            if (direction == MoveDirection.FORWARD){
                newPosition = this.position.add(this.direction.toUnitVector());
            } else {
                newPosition = this.position.subtract(this.direction.toUnitVector());
            }
            if (this.map.canMoveTo(newPosition)){
                positionChanged(newPosition);
                this.position = newPosition;
            }
        }
    }

    public String getResourceName(){
        return switch (this.direction){
            case NORTH -> "up.png";
            case SOUTH -> "down.png";
            case WEST -> "left.png";
            case EAST -> "right.png";
        };
    }
}
