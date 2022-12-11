package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    private GrassField map;
    private GridPane grid;

    public void init(){
        //String[] dane = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        try{
            this.grid = new GridPane();
            String[] data = getParameters().getRaw().toArray(String[]::new);
            MoveDirection[] directions = OptionsParser.parse(data);
            this.map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            SimulationEngine engine = new SimulationEngine(directions, this.map, positions);
            engine.run();
            System.out.println("End positions");
            System.out.println(this.map);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
    }

    public void draw(){
        Label coordinates = new Label("y\\x");
        int rowSize = 25;
        int columnSize = 25;
        GridPane.setHalignment(coordinates, HPos.CENTER);
        this.grid.add(coordinates, 0, 0);
        this.grid.getColumnConstraints().add(new ColumnConstraints(columnSize));
        this.grid.getRowConstraints().add(new RowConstraints(rowSize));
        for (int i = 0; i <= this.map.getUpperRight().x - this.map.getLowerLeft().x; i++){
            Label x = new Label(Integer.toString(map.getLowerLeft().x + i));
            GridPane.setHalignment(x, HPos.CENTER);
            this.grid.add(x, i + 1, 0);
            this.grid.getColumnConstraints().add(new ColumnConstraints(columnSize));
        }
        for (int i = 0; i <= this.map.getUpperRight().y - this.map.getLowerLeft().y; i++){
            Label y = new Label(Integer.toString(this.map.getUpperRight().y - i));
            GridPane.setHalignment(y, HPos.CENTER);
            this.grid.add(y, 0, i + 1);
            this.grid.getRowConstraints().add(new RowConstraints(rowSize));
        }
        for (int i = 0; i <= this.map.getUpperRight().x - this.map.getLowerLeft().x; i++){
            for (int j = 0; j <= this.map.getUpperRight().y - this.map.getLowerLeft().y; j++){
                Object mapElement = this.map.objectAt(new Vector2d(this.map.getLowerLeft().x + i, this.map.getLowerLeft().y + j));
                if (mapElement != null){
                    Label element = new Label(mapElement.toString());
                    GridPane.setHalignment(element, HPos.CENTER);
                    this.grid.add(element,  i + 1, this.map.getUpperRight().y - this.map.getLowerLeft().y - j + 1);
                }
            }
        }
    }


    public void start(Stage primaryStage){
        this.grid = new GridPane();
        draw();
        this.grid.setGridLinesVisible(true);
        Scene scene = new Scene(this.grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
