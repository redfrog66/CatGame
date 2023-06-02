package catgame.controllers;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CatScene extends Scene {
    /**
     * This class turns our scenes into a cat! Doesn't wok well, but at least its cute! =^..^=
     */
    private final static Group rootContent = new Group();

    public CatScene(Parent parent, double width, double height, Paint backgroundPaint) {
        super(rootContent, width, height, Color.TRANSPARENT);
        var head = new Ellipse(width / 2f, height / 2f, width / 2f, height / 2f);
        var ear1 = new Polygon();

        ear1.getPoints().addAll(
                70d, 0d,
                0d, height / 2d,
                width / 4d + 100, height / 4d
        );

        var ear2 = new Polygon();
        ear2.getPoints().addAll(
                width - 70d, 0d,
                width / 4d * 3 - 100, height / 4d,
                width, height / 2d
        );

        rootContent.setClip(Shape.union(head, Shape.union(ear1, ear2)));
        var background = new Rectangle(0,0, width, height);
        background.setFill(backgroundPaint);
        rootContent.getChildren().add(background);
        rootContent.getChildren().add(parent);

    }
}