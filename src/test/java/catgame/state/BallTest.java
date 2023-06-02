package catgame.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BallTest {
    private Ball ball;

    @BeforeEach
    public void setup() {
        ball = new Ball();
    }

    @Test
    public void testIsGoal() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = ball.isGoal();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testCheckMove() {
        // Arrange
        Direction upDirection = Direction.UP;
        Direction rightDirection = Direction.RIGHT;
        Direction downDirection = Direction.DOWN;
        Direction leftDirection = Direction.LEFT;

        // Act & Assert
        assertTrue(ball.checkMove(upDirection));
        assertTrue(ball.checkMove(rightDirection));
        assertTrue(ball.checkMove(downDirection));
        assertTrue(ball.checkMove(leftDirection));
    }
/*
    @Test
    public void testMove() {
        // Initial ball position
        int initialRow = ball.board.getBallRow();
        int initialCol = ball.board.getBallCol();

        // Move the ball to the right
        int[] newPosition = ball.move(Direction.RIGHT);

        // Verify that the ball has moved to the right
        Assertions.assertEquals(initialRow, newPosition[0]);
        Assertions.assertEquals(initialCol + 1, newPosition[1]);

        // Verify that the ball's new position is marked as occupied
        Assertions.assertTrue(ball.board.getFields()[newPosition[2]][newPosition[3]].isBall());
    }

 */
}