package agh.ics.oop;

import agh.ics.oop.gui.IMapChangeObserver;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable{
    private MoveDirection[] moves;
    private final ArrayList<Animal> animals;
    private final IWorldMap map;
    private final ArrayList<IMapChangeObserver> observers;
    private final int moveDelay;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions){
        this.moves = directions;
        this.animals = new ArrayList<Animal>();
        this.map = map;
        this.moveDelay = 0;
        for (Vector2d position:positions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                this.animals.add(animal);
            }
        }
        this.observers = new ArrayList<IMapChangeObserver>();
    }

    public SimulationEngine(IWorldMap map, Vector2d[] positions, int moveDelay){
        this.animals = new ArrayList<Animal>();
        this.map = map;
        this.moveDelay = moveDelay;
        for (Vector2d position:positions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                this.animals.add(animal);
            }
        }
        this.observers = new ArrayList<IMapChangeObserver>();
    }

    public void run() {
        int counter = 0;
        int animal_number = this.animals.size();
        for (MoveDirection direction:this.moves) {
            this.animals.get(counter % animal_number).move(direction);
            counter++;
            try{
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            for (IMapChangeObserver observer:this.observers){
                observer.mapChanged();
            }
        }
    }

    public void addObserver(IMapChangeObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(IMapChangeObserver observer){
        this.observers.remove(observer);
    }

    public void setMoves(MoveDirection[] moves){
        this.moves = moves;
    }
}
