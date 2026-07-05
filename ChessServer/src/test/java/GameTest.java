import com.rdavies.game.Game;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {


    @Test
    public void initGameTest() {
        Game g = new Game();

        int count = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                System.out.println("Square: " + count + " board pos : " + i + ", " + j + " Piece : " + g.getPosition(i, j));
                count++;
            }
        }

    }

    @Test
    public void fenStringTest() {
        Game g = new Game();

        String actual = g.toFenString();
        String expected = "rnbkqbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBKQBNR";

        assertEquals(expected, actual);
    }



}
