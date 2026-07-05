package com.rdavies.messages;

import com.rdavies.game.Piece;

public class MoveResponse {

    // probably a bit of json that is the board
    boolean success;
    String board;


    public MoveResponse(boolean success, String board) {
        this.success = success;
        this.board = board;
    }

}
