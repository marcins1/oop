package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class App extends Application implements IMapChangeObserver {
    private GrassField map;
    private GridPane grid;
    private SimulationEngine engine;

    public void init(){
        try{
            this.grid = new GridPane();
            this.grid.setAlignment(Pos.CENTER);
            this.map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            this.engine = new SimulationEngine(this.map, positions, 300);
            this.engine.addObserver(this);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void draw() throws FileNotFoundException {
        this.grid.setGridLinesVisible(false);
        this.grid.setGridLinesVisible(true);
        Label coordinates = new Label("y\\x");
        int rowSize = 35;
        int columnSize = 45;
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
                    this.grid.add(new GuiElementBox((IMapElement) mapElement).guiElement,  i + 1, this.map.getUpperRight().y - this.map.getLowerLeft().y - j + 1);
                }
            }
        }
    }

    public void start(Stage primaryStage) throws FileNotFoundException {
        TextField textField = new TextField();
        textField.setMaxWidth(200);
        Button button = new Button("Run");
        button.setMaxWidth(150);
        VBox inputBox = new VBox(textField, button);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setSpacing(5);
        VBox applicationBox = new VBox(this.grid, inputBox);
        applicationBox.setAlignment(Pos.CENTER);
        applicationBox.setSpacing(20);
        draw();
        Scene scene = new Scene(applicationBox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        button.setOnAction(e -> {
            String[] moves = textField.getText().split(" ");
            MoveDirection[] directions = OptionsParser.parse(moves);
            this.engine.setMoves(directions);
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
        });
    }

    @Override
    public void mapChanged() {
        Platform.runLater(() -> {
            try {
                this.grid.getRowConstraints().clear();
                this.grid.getColumnConstraints().clear();
                this.grid.getChildren().clear();
                draw();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
