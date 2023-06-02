package catgame.state;

public class Ball{
    Board board = new Board();
    /**
     * {@return whether the maze is solved}
     */
    public boolean isGoal()
    {
        return board.getFields()[5][2].isBall();
    }

    /**
     *
     * @return
     */
    private boolean canMoveUp(){
        return !board.getFields()[board.getBallRow()][board.getBallCol()].isTop();
    }

    private boolean canMoveDown(){
        return !board.getFields()[board.getBallRow()][board.getBallCol()].isBottom();
    }

    private boolean canMoveLeft(){
        return !board.getFields()[board.getBallRow()][board.getBallCol()].isLeft();
    }

    private boolean canMoveRight(){return !board.getFields()[board.getBallRow()][board.getBallCol()].isRight();
    }

    /**
     * {@return whether the block can be moved to the direction specified}
     *
     * @param direction a direction to which the block is intended to be moved
     */
    public boolean checkMove(Direction direction) {
        return switch (direction) {
            case UP -> canMoveUp();
            case RIGHT -> canMoveRight();
            case DOWN -> canMoveDown();
            case LEFT -> canMoveLeft();
        };
    }


    /**
     * Representation for the move.
     * @return int[] which contains the ball actual position
     * */
    public int[] move(Direction direction){
        int [] prev_actual = new int[4];
        prev_actual[0] = board.getBallRow();
        prev_actual[1] = board.getBallCol();
        board.getFields()[board.getBallRow()][board.getBallCol()].setBall(false);
        int rowChange = direction.getRowChange();
        int colChange = direction.getColChange();
        board.setBallRow(board.getBallRow() + rowChange);
        board.setBallCol(board.getBallCol() + colChange);
        board.getFields()[board.getBallRow()][board.getBallCol()].setBall(true);
        prev_actual[2] = board.getBallRow();
        prev_actual[3] = board.getBallCol();
        return prev_actual;
    }
}
