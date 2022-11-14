package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d position;
    private final IWorldMap map;

    public Animal(IWorldMap map){
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.direction = MapDirection.NORTH;
        this.map = map;
        this.position = initialPosition;
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

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
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
                Vector2d oldPosition = this.position;
                this.position = newPosition;
                if (!this.map.place(this)){
                    this.position = oldPosition;
                } else {
                    this.map.remove(oldPosition);
                }
            }
        }
    }

    public Vector2d getPosition(){
        return this.position;
    }
}
