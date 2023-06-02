package catgame.controllers;

import catgame.player.Player;
import catgame.player.PlayerManager;
import catgame.utility.ScreenChangerHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class FinishController {

    /**
     * the exit button, for the 3rd time
     */
    @FXML
    private javafx.scene.control.Button exitbutton;

    @FXML
    private void handleCloseButton(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     * The Player who was the last on the JSON, is pur currentPlayer
     */
    @FXML
    private Label currentUser;
    @FXML
    private Label currentScore;

    public void initialize(){
        PlayerManager playerManager = new PlayerManager();
        Player currentPlayer = playerManager.getLastAddedPlayer();

        if (currentPlayer != null) {
            currentUser.setText(currentPlayer.getName());
            currentScore.setText(String.valueOf(currentPlayer.getSteps()));
        } else {
            Logger.warn("No players found in the JSON file.");
        }
    }
}

