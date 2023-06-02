package catgame.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A class that basically represents a cell in our grid, storing the walls, ball and finish.
 */
@Getter
@Setter
@AllArgsConstructor
public class Fields {
    private boolean top;
    private boolean bottom;
    private boolean left;
    private boolean right;
    private boolean ball;
    private boolean finish;
}
