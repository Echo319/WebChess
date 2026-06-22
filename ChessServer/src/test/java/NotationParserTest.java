import com.rdavies.game.PieceType;
import com.rdavies.move.Move;
import com.rdavies.utils.NotationParser;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class NotationParserTest {

/*
    0 RNBQKBNR
    1 PPPPPPPP
    2 00000000
    3 00000000
    4 00000000
    5 00000000
    6 PPPPPPPP
    7 RNBQKBNR

      ABCDEFGH
      01234567
 */

    @Test
    public void notationToCoords() {
        String s = "e4";

        int x = s.charAt(0) - 'a';
        int y = Character.getNumericValue(s.charAt(1)) - 1;


        assertEquals(4, x);
        assertEquals(3, y);
    }

    @Test
    public void regExTest() {
        String regExString = "^[KQBNR]";
        String notation = "Qh3f1";
        String badNotation = "de5";
        Pattern regEx = Pattern.compile(regExString);
        boolean match = regEx.matcher(notation).find();
        boolean noMatch = regEx.matcher(badNotation).find();
        assertTrue(match);
        assertFalse(noMatch);
    }

    @Test
    public void stringSplitting() {
        String s = "hello=Q";
        String promotion = s.split("=")[1];
        PieceType t = PieceType.charToPiece(promotion.toCharArray()[0]);
        assertEquals(PieceType.QUEEN, t);
    }

    @Test
    public void stringChange() {
        String s = "hello=Q";
        s = s.replace("=", "");
        System.out.println(s);
    }

    @Test
    public void charToInt() {
        String s = "e4";
        char[] chars = s.toCharArray();
        char file = chars[0];
        int rank = Character.getNumericValue(chars[1]);

        assertEquals('e', file);
        assertEquals(4, rank);
    }

    @Test
    public void pawnMove() {
        String move =  "e4";

        NotationParser parser = new NotationParser();

        Move actual = parser.parse(move);

        assertSame(PieceType.PAWN, actual.pieceType);
        assertSame('e', actual.toFile);
        assertSame(4, actual.toRank);

        String methodName = StackWalker.getInstance()
                .walk(frames -> frames.findFirst().map(StackWalker.StackFrame::getMethodName).orElse("unknown"));
        System.out.println(methodName + " : " + actual);
    }

    @Test
    public void knightMove() {
        String move = "Ne4";

        NotationParser parser = new NotationParser();

        Move actual = parser.parse(move);

        assertSame(PieceType.KNIGHT, actual.pieceType);
        assertSame('e', actual.toFile);
        assertSame(4, actual.toRank);

        String methodName = StackWalker.getInstance()
                .walk(frames -> frames.findFirst().map(StackWalker.StackFrame::getMethodName).orElse("unknown"));
        System.out.println(methodName + " : " + actual);
    }

    @Test
    public void Castle() {
        String move = "O-O-O";

        NotationParser parser = new NotationParser();

        Move actual = parser.parse(move);

        assertTrue(actual.isQueenCastle);
        assertEquals(PieceType.KING, actual.pieceType);

        String methodName = StackWalker.getInstance()
                .walk(frames -> frames.findFirst().map(StackWalker.StackFrame::getMethodName).orElse("unknown"));
        System.out.println(methodName + " : " + actual);
    }

    @Test
    public void complexFileAndRankMove() {
        String move = "Qh3f1";

        NotationParser parser = new NotationParser();

        Move actual = parser.parse(move);

        assertSame(PieceType.QUEEN, actual.pieceType);
        assertSame('f', actual.toFile);
        assertSame(1, actual.toRank);
        assertSame('h', actual.fromFile);
        assertSame(3, actual.fromRank);

        String methodName = StackWalker.getInstance()
                .walk(frames -> frames.findFirst().map(StackWalker.StackFrame::getMethodName).orElse("unknown"));
        System.out.println(methodName + " : " + actual);
    }

    @Test
    public void complexFileMove() {
        String move = "Qhf1";

        NotationParser parser = new NotationParser();

        Move actual = parser.parse(move);

        assertSame(PieceType.QUEEN, actual.pieceType);
        assertSame('f', actual.toFile);
        assertSame(1, actual.toRank);
        assertSame('h', actual.fromFile);

        String methodName = StackWalker.getInstance()
                .walk(frames -> frames.findFirst().map(StackWalker.StackFrame::getMethodName).orElse("unknown"));
        System.out.println(methodName + " : " + actual);
    }
    @Test
    public void complexRankMove() {
        String move = "Q3f1";

        NotationParser parser = new NotationParser();

        Move actual = parser.parse(move);

        assertSame(PieceType.QUEEN, actual.pieceType);
        assertSame('f', actual.toFile);
        assertSame(1, actual.toRank);
        assertSame(3, actual.fromRank);

        String methodName = StackWalker.getInstance()
                .walk(frames -> frames.findFirst().map(StackWalker.StackFrame::getMethodName).orElse("unknown"));
        System.out.println(methodName + " : " + actual);
    }

}
