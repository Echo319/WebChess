package com.rdavies.game;

import com.rdavies.move.Move;
import com.rdavies.utils.NotationParser;

import java.util.Arrays;

public class GameState {
        // new
    private Game game = new Game();
    private NotationParser parser = new NotationParser();

    public GameState() {
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

}
