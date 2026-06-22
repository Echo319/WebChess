import com.rdavies.game.Game;
import org.junit.Test;

public class GameTest {


    @Test
    public void initGameTest() {
        Game g = new Game();

        int count = 0;
        for(int i = 0; i < g.board.length; i++) {
            for(int j = 0; j < g.board[i].length; j++) {
                System.out.println("Square: " + count + "board pos : " + i + ", " + j + " Piece : " + g.board[i][j]);
            }
        }

    }


}
