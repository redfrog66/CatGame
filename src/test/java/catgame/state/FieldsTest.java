package catgame.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FieldsTest {

    @Test
    public void testFieldsInitialization() {
        // Arrange
        boolean top = true;
        boolean bottom = false;
        boolean left = true;
        boolean right = false;
        boolean ball = true;
        boolean finish = false;

        // Act
        Fields fields = new Fields(top, bottom, left, right, ball, finish);

        // Assert
        assertEquals(top, fields.isTop());
        assertEquals(bottom, fields.isBottom());
        assertEquals(left, fields.isLeft());
        assertEquals(right, fields.isRight());
        assertEquals(ball, fields.isBall());
        assertEquals(finish, fields.isFinish());
    }

    @Test
    public void testFieldsGettersAndSetters() {
        // Arrange
        Fields fields = new Fields(false, true, true, false, true, false);

        // Act
        fields.setTop(true);
        fields.setBottom(false);
        fields.setLeft(false);
        fields.setRight(true);
        fields.setBall(false);
        fields.setFinish(true);

        // Assert
        assertTrue(fields.isTop());
        assertFalse(fields.isBottom());
        assertFalse(fields.isLeft());
        assertTrue(fields.isRight());
        assertFalse(fields.isBall());
        assertTrue(fields.isFinish());
    }
}
