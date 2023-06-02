package catgame.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A class that represents the player. We will use this class to handle saving user info into JSON.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    public String name;
    public int steps;
}
