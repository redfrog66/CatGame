package catgame.utility;

import catgame.controllers.CatScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class ScreenChangerHelper {

    public static void loadNewFXML(FXMLLoader fxmlLoader, String resourceName, Stage stage) throws IOException {
        Logger.trace("Switching screens");
        fxmlLoader.setLocation(fxmlLoader.getClass().getResource(resourceName));
        Parent root = fxmlLoader.load();

        var basedBackground = new LinearGradient(0, 0, 0, 600, false, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(242, 182, 173)),
                new Stop(0.7, Color.rgb(254, 221, 214)),
                new Stop(1, Color.rgb(253, 233, 233)));
        //stage.setScene(new CatScene(fxml, 800, 600, Color.PINK)); - this would make a simple pink background, leaving this here for testing
        Scene scene = new CatScene(root, 1000, 800, basedBackground);

        stage.setScene(scene);
        stage.show();
    }
}
