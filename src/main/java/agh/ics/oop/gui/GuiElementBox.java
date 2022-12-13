package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    public final VBox guiElement;

    public GuiElementBox(IMapElement mapElement) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("src/main/resources/" + mapElement.getResourceName()));
        ImageView img = new ImageView(image);
        img.setFitWidth(20);
        img.setFitHeight(20);
        Label label;
        if (mapElement instanceof Animal){
            label = new Label("Z: " + mapElement.getPosition());
        } else {
            label = new Label("Trawa");
        }
        VBox vbox = new VBox();
        vbox.getChildren().add(img);
        vbox.getChildren().add(label);
        vbox.setAlignment(Pos.CENTER);
        this.guiElement = vbox;
    }
}
