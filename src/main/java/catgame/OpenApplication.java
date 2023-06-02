package catgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class OpenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/openscene.fxml"));
        Parent fxml = fxmlLoader.load();

        //Creating a nice gradient for the background
        var basedBackground = new LinearGradient(0, 0, 0, 600, false, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(242, 182, 173)),
                new Stop(0.7, Color.rgb(254, 221, 214)),
                new Stop(1, Color.rgb(253, 233, 233)));
        //stage.setScene(new CatScene(fxml, 800, 600, Color.PINK)); - this would make a simple pink background, leaving this here for testing
        Scene scene = new CatScene(fxml, 1000, 800, basedBackground);
        stage.setScene(scene);
        stage.show();
    }


    private class CatScene extends Scene {

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
}

