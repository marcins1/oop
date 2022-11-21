package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    private final MoveDirection[] moves;
    private final ArrayList<Animal> animals;
    private final IWorldMap map;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions){
        this.moves = directions;
        this.animals = new ArrayList<Animal>();
        this.map = map;
        for (Vector2d position:positions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                this.animals.add(animal);
            }
        }
        System.out.println("Starting positions");
        System.out.println(map);
    }

    public void run(){
        int counter = 0;
        int animal_number = this.animals.size();
        for (MoveDirection direction:this.moves) {
            this.animals.get(counter % animal_number).move(direction);
            System.out.println("Move: " + direction);
            System.out.println(this.map);
            counter++;
        }
    }
}
