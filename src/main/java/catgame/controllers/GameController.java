package catgame.controllers;

import catgame.utility.ScreenChangerHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class GameController {


    @FXML
    private TextField textField;

    /**
     * When we click on the play button, the main screen appears.
     * We cannot click the button if the Player doesn't write a name.
     */
    @FXML
    private Button playbutton;
    @FXML
    private FXMLLoader fxmlLoader = new FXMLLoader();
    @FXML
    private void switchScene(ActionEvent event) throws IOException {
        if (textField.getText().isEmpty()){
            Logger.warn("Player name field is empty!");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ScreenChangerHelper.loadNewFXML(fxmlLoader,"/fxml/catgame.fxml",stage);
            fxmlLoader.<CatgameController>getController().setPlayerName(textField.getText());
            Logger.info("Player name set to {}", textField.getText());
        }
    }

    /**
     * This button exits the game.
     */
    @FXML
    private Button exitbutton;
    @FXML
    private void handleCloseButton(ActionEvent actionEvent) {
        Platform.exit();
    }


}
