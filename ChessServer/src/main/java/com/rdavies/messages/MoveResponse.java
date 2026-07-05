package com.rdavies.messages;

public class MoveResponse {

    boolean success;
    String board;


    public MoveResponse(boolean success, String board) {
        this.success = success;
        this.board = board;
    }

}
