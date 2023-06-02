package catgame.controllers;

import catgame.player.Player;
import catgame.player.PlayerManager;
import catgame.state.Ball;
import catgame.state.Board;
import catgame.state.Direction;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class CatgameController {

    /**
     * The player
     */
    @FXML
    private Label playerNameLabel;
    private String playerName;
    void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Our step counter
     */
    @FXML
    private Label steps;
    private IntegerProperty stepCount = new SimpleIntegerProperty();

    /**
     * Our board, Stackpanes and the ball
     */
    @FXML
    private GridPane gameBoard;
    private Board board = new Board();
    private StackPane[][] board_fields = new StackPane[7][7];
    private Ball ball = new Ball();


    /**
     * This will help us decide if we solved the game or not
     */
    private BooleanProperty isSolved = new SimpleBooleanProperty();


    /**
     * Cute exit
     */
    @FXML
    private Button exitbutton;
    @FXML
    private void handleCloseButton(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     * This lets the player start over again
     */
    @FXML
    private Button restartbutton;
    @FXML
    private void handleRestartButton(ActionEvent actionEvent) {
        resetGame();
    }

    /**
     * This button will quick solve the game. Should extend with waiting between steps,
     * but I don't have the mental capacity to do that, I'm sorry.
     */
    @FXML
    private Button solvebutton;
    @FXML
    private void handleSolveButton(ActionEvent actionEvent) { solver(); }
    private void solver() {
        ArrayList<Direction> directionArrayList = new ArrayList<>();
        directionArrayList.add(Direction.RIGHT);
        directionArrayList.add(Direction.DOWN);
        directionArrayList.add(Direction.LEFT);
        directionArrayList.add(Direction.DOWN);
        directionArrayList.add(Direction.LEFT);
        directionArrayList.add(Direction.UP);
        directionArrayList.add(Direction.LEFT);
        directionArrayList.add(Direction.DOWN);
        directionArrayList.add(Direction.LEFT);
        directionArrayList.add(Direction.UP);
        directionArrayList.add(Direction.RIGHT);
        directionArrayList.add(Direction.UP);
        directionArrayList.add(Direction.RIGHT);
        directionArrayList.add(Direction.UP);
        directionArrayList.add(Direction.LEFT);
        directionArrayList.add(Direction.DOWN);
        directionArrayList.add(Direction.RIGHT);
        directionArrayList.add(Direction.DOWN);

        for (int i = 0; i < directionArrayList.size(); i++) {
            performMove(directionArrayList.get(i));
        }
    }

    /**
     * This method will initialize the game and set the default state.
     */
    @FXML
    public void initialize() {
        steps.textProperty().bind(stepCount.asString());
        populateGrid();
        finishCirc();

        registerKeyEventHandler();
        Platform.runLater(() -> playerNameLabel.setText(playerName));
        Logger.info("Starting game");
        resetGame();
    }

    /**
     * Setting default state for the game.
     */
    private void resetGame() {
        stepCount.set(0);
        isSolved.set(false);

        removeCircle();
        ball = new Ball();
        finishCirc();
    }


    /**
     * Initializing the 7x7 matrix, and adding table fields to grid.
     */
    private void populateGrid() {
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                board_fields[row][col] = fieldMaker(row, col);
                gameBoard.add(board_fields[row][col], col, row);
            }
        }
    }

    /**
     * Setting every StackPane borders, which will become the labyrinth walls.
     *
     * @param i StackPane row number
     * @param j StackPane column number
     * @return StackPane returning fields with appropriate wall settings.
     */
    private StackPane fieldMaker(int i, int j) {
        var field = new StackPane();
        //Setting walls borderStrokeStyle
        double top = board.getFields()[i][j].isTop() ? 5 : 1;
        double bottom = board.getFields()[i][j].isBottom() ? 5 : 1;
        double left = board.getFields()[i][j].isLeft() ? 5 : 1;
        double right = board.getFields()[i][j].isRight() ? 5 : 1;
        field.setBorder(new Border(new BorderStroke(Color.web("#588061"), Color.web("#588061"), Color.web("#588061"), Color.web("#588061"),
                BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
                null, new BorderWidths(top, right, bottom, left), null)));
        return field;
    }

    /**
     * Setting finish field, and adding circle to its default stack pane.
     */
    private void finishCirc() {
        //Drawing the ball
        addCircle(1, 4);
        //Finish field:
        Text finish = new Text("GOAL");
        finish.setFont(Font.font("Gabriola", FontWeight.BOLD, 25));
        board_fields[5][2].getChildren().add(finish);
    }

    /**
     * This will help to add the circle in every move to its appropriate stackpane.
     */
    private void addCircle(int i, int j) {
        Circle ball = new Circle(20, 20, 20);
        ball.setFill(Color.web("#F58092"));
        board_fields[i][j].getChildren().add(ball);
    }

    /**
     * This will remove every circle
     */
    private void removeCircle() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                board_fields[i][j].getChildren().clear();
            }
        }
    }

    /**
     * We can perform move until we hit a wall
     */
    private void performMove(Direction direction) {
        int[] balldirection;
        if (ball.checkMove(direction)) {
            stepCount.set(stepCount.get()+1);
            while (ball.checkMove(direction)) {
                Logger.info("Move: {}", direction);
                balldirection = ball.move(direction);
                board_fields[balldirection[0]][balldirection[1]].getChildren().clear();
                Logger.trace("New state of the ball: row: {} col: {} ", balldirection[2], balldirection[3]);
                addCircle(balldirection[2], balldirection[3]);
                if (ball.isGoal())
                {
                    isSolved.set(true);
                    try {
                        Finished(isSolved);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else {
            Logger.warn("Invalid move: {}", direction);
        }
    }

    /**
     * This is where we will handling finish. Once the game is done,
     * we add the player to the JSON file, and set the finishline to true
     */
    PlayerManager playerManager = new PlayerManager();
    private boolean finishline = false;
    private void Finished(BooleanProperty isFinished) throws IOException {
        if (isFinished.get()) {
            Logger.info("Player {} has solved the game in {} steps", playerName);

            Player player = new Player(playerName, stepCount.intValue());
            playerManager.addPlayer(player);
            finishline = true;
        }
    }

    /**
     * If we finished the game, we can get to the finish screen. If not, we will be warned to finish the game first.
     */
    @FXML
    private Button finishButton;

    @FXML
    private void handleFinishButton(ActionEvent actionEvent) throws IOException {
        if(finishline) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/finishscreen.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            Logger.info("End scene loaded");
        } else {
            Logger.warn("Finish the game!");
        }

    }

    /**
     * This will handle all the key combinations we can possibly use to do things in the game
     */
    private void registerKeyEventHandler() {
        final KeyCombination restartKeyCombination = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
        final KeyCombination quitKeyCombination = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
        Platform.runLater(() -> gameBoard.getScene().setOnKeyPressed(
                keyEvent -> {
                    if (restartKeyCombination.match(keyEvent)) {
                        Logger.debug("Restarting game");

                        resetGame();
                    } else if (quitKeyCombination.match(keyEvent)) {
                        Logger.debug("Exiting");
                        Platform.exit();
                    } else if (keyEvent.getCode() == KeyCode.UP) {
                        Logger.debug("Up pressed");
                        performMove(Direction.UP);
                    } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                        Logger.debug("Right pressed");
                        performMove(Direction.RIGHT);
                    } else if (keyEvent.getCode() == KeyCode.DOWN) {
                        Logger.debug("Down pressed");
                        performMove(Direction.DOWN);
                    } else if (keyEvent.getCode() == KeyCode.LEFT) {
                        Logger.debug("Left pressed");
                        performMove(Direction.LEFT);
                    }
                }
        ));
    }

}