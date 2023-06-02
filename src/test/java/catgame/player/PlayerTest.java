package catgame.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerInitialization() {
        // Arrange
        String name = "Almacska";
        int steps = 10;

        // Act
        Player player = new Player(name, steps);

        // Assert
        assertEquals(name, player.getName());
        assertEquals(steps, player.getSteps());
    }

    @Test
    public void testPlayerGettersAndSetters() {
        // Arrange
        Player player = new Player();

        // Act
        String name = "Gombacska";
        int steps = 5;
        player.setName(name);
        player.setSteps(steps);

        // Assert
        assertEquals(name, player.getName());
        assertEquals(steps, player.getSteps());
    }
}