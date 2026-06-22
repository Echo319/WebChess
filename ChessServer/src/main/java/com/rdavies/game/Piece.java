package com.rdavies.game;

public class Piece {

    Player player; // WHITE or BLACK
    PieceType type;
    char x;
    int y;
    boolean isAlive;
    boolean isPromoted;

    public Piece(Player player, PieceType type, char x, int y) {
        this.player = player;
        this.type = type;
        this.x = x;
        this.y = y;
        isAlive = true;
        isPromoted = false;
    }
}
