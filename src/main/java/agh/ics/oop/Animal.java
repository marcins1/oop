package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    public Animal(){
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    @Override
    public String toString() {
        return "Orientacja: " + direction + ", Polozenie: " + position;
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
            if (newPosition.precedes(new Vector2d(4, 4)) && newPosition.follows(new Vector2d(0, 0))){
                this.position = newPosition;
            }
        }
    }
}
