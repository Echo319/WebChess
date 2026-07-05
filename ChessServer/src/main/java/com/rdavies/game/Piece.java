package com.rdavies.game;

public class Piece {

    Player player; // WHITE or BLACK
    PieceType type;
    int x;
    int y;
    boolean isAlive;
    boolean isPromoted;
    boolean hasMoved;

    // empty piece is there a protected way to create this so its only used in one place
    public Piece(int x, int y) {
        this.type = PieceType.NOTHING;
        this.x = x;
        this.y = y;
    }

    // actual piece
    public Piece(Player player, PieceType type, int x, int y) {
        this.player = player;
        this.type = type;
        this.x = x;
        this.y = y;
        isAlive = true;
        isPromoted = false;
        hasMoved = false;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "player=" + player +
                ", type=" + type +
                ", x=" + x +
                ", y=" + y +
                ", isAlive=" + isAlive +
                ", isPromoted=" + isPromoted +
                ", hasMoved=" + hasMoved +
                '}';
    }
}
