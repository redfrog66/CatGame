package catgame.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    /*
    @Test
    public void testBoardInitialization() {
        // Arrange
        Board board = new Board();

        // Assert
        assertFalse(board.getFields()[0][1].isLeft());
        assertFalse(board.getFields()[0][2].isBottom());
        assertFalse(board.getFields()[0][3].isRight());
        assertFalse(board.getFields()[0][4].isLeft());
        assertFalse(board.getFields()[0][6].isBottom());
    }
    */
    @Test
    public void testBallPosition() {
        // Arrange
        Board board = new Board();
        int ballRow = board.getBallRow();
        int ballCol = board.getBallCol();

        // Assert
        assertTrue(board.getFields()[ballRow][ballCol].isBall());
    }

}