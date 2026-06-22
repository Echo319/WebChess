package com.rdavies.game;

public class Piece {

    Player player; // WHITE or BLACK
    PieceType type;
    boolean isAlive;
    boolean isPromoted;
    boolean hasMoved;

    public Piece(Player player, PieceType type) {
        this.player = player;
        this.type = type;
        isAlive = true;
        isPromoted = false;
        hasMoved = false;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "player=" + player +
                ", type=" + type +
                ", isAlive=" + isAlive +
                ", isPromoted=" + isPromoted +
                ", hasMoved=" + hasMoved +
                '}';
    }
}
