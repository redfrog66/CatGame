package catgame.state;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Realised I cannot create the walls the "easy way" - by sorting them into Lists. Tried that two times, did not work.
 * */
@Getter
@Setter
@AllArgsConstructor
public class Board {
    //A matric for the Grid

    @Setter(AccessLevel.NONE)
    private Fields[][] fields = new Fields[7][7];

    //Initial State of the ball.
    @Setter(AccessLevel.PACKAGE)
    private int BallRow = 1;
    @Setter(AccessLevel.PACKAGE)
    private int BallCol = 4;


    //instantiation of the Fields
    public Board(){
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 7; j++) {
                fields[i][j] = new Fields(false,false,false,false,false,false);
            }
        }
        //The whole board has a big outer border
        for (int i = 0; i < 7; i++)
        {
            fields[0][i].setTop(true);
            fields[6][i].setBottom(true);
            fields[i][0].setLeft(true);
            fields[i][6].setRight(true);
        }
        //1.
        fields[0][0].setRight(true);
        fields[0][1].setLeft(true);
        fields[0][2].setBottom(true);
        fields[0][3].setRight(true);
        fields[0][4].setLeft(true);
        fields[0][6].setBottom(true);
        //2.
        fields[1][2].setTop(true);
        //the ball
        fields[BallRow][BallCol].setBall(true);
        fields[1][6].setTop(true);
        //3.
        fields[2][1].setBottom(true);
        fields[2][2].setRight(true);
        fields[2][3].setLeft(true);
        fields[2][5].setRight(true);
        fields[2][6].setLeft(true);
        //4.
        fields[3][1].setTop(true);
        fields[3][3].setRight(true);
        fields[3][3].setBottom(true);
        fields[3][4].setLeft(true);
        fields[3][4].setRight(true);
        fields[3][5].setLeft(true);
        fields[3][6].setBottom(true);
        //5.
        fields[4][0].setBottom(true);
        fields[4][3].setTop(true);
        fields[4][4].setBottom(true);
        fields[4][6].setTop(true);
        //6.
        fields[5][0].setTop(true);
        fields[5][1].setRight(true);
        fields[5][2].setFinish(true);
        fields[5][2].setLeft(true);
        fields[5][2].setBottom(true);
        fields[5][2].setRight(true);
        fields[5][3].setLeft(true);
        fields[5][4].setTop(true);
        //7.
        fields[6][2].setTop(true);
        fields[6][3].setRight(true);
        fields[6][4].setLeft(true);
        fields[6][5].setRight(true);
        fields[6][6].setLeft(true);
    }
}
