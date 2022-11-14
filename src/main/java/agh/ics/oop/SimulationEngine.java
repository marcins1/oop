package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    private final MoveDirection[] moves;
    private final ArrayList<Animal> animals;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions){
        this.moves = directions;
        this.animals = new ArrayList<Animal>();
        for (Vector2d position:positions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                this.animals.add(animal);
            }
        }
    }

    public void run(){
        int counter = 0;
        int animal_number = this.animals.size();
        for (MoveDirection direction:this.moves) {
            this.animals.get(counter % animal_number).move(direction);
            counter++;
        }
    }
}
