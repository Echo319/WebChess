package com.rdavies.game;

import com.rdavies.move.Move;
import com.rdavies.utils.NotationParser;

import java.util.Arrays;

public class GameState {
    // old
    private char[][] board = new char[8][8];

    // new
    private Game game = new Game();
    private NotationParser parser = new NotationParser();

    public GameState() {
        initBoard();
    }

    public void update(Move move) {
    }


    private boolean validMove(Move move, char piece) {
        boolean isValid = false;
        PieceType p = PieceType.charToPiece(piece);

        // difference between starting and ending
        int dx;
        int dy;

        switch (p) {
            default: break;
        }

        return isValid;
    }


    private void initBoard() {
        for(int row = 0; row < board.length; row++ ) {
            // Pawn rows
            if(row == 1 || row == 6)
            {
                Arrays.fill(board[row], PieceType.PAWN.getChar());
                continue;
            }
            // Back row
            if(row == 0 || row == 7) {
                for(int col = 0; col < board[row].length; col++) {
                    switch (col) {
                        case 0:
                        case 7:
                            board[row][col] = PieceType.ROOK.getChar();
                            break;
                        case 1:
                        case 6:
                            board[row][col] = PieceType.KNIGHT.getChar();
                            break;
                        case 2:
                        case 5:
                            board[row][col] = PieceType.BISHOP.getChar();
                            break;
                        case 3:
                            board[row][col] = PieceType.QUEEN.getChar();
                            break;
                        case 4:
                            board[row][col] = PieceType.KING.getChar();
                            break;
                       default:
                            assert(true);
                    }
                }
                continue;
            }
            Arrays.fill(board[row], PieceType.NOTHING.getChar());
        }


    }


}
