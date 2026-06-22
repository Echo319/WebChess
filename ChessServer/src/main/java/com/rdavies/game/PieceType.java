package com.rdavies.game;

public enum PieceType {
    NOTHING ('0'),
    PAWN ('P'),
    ROOK ('R'),
    KNIGHT ('N'),
    BISHOP ('B'),
    QUEEN ('Q'),
    KING ('K');

    private final char c;
    private PieceType(char piece) {
        this.c = piece;
    }

    public char getChar() {
        return c;
    }


    public static PieceType charToPiece(char c) {
         return switch (c) {
            case 'B' -> PieceType.BISHOP;
            case 'N' -> PieceType.KNIGHT;
            case 'R' -> PieceType.ROOK;
            case 'Q' -> PieceType.QUEEN;
            case 'K' -> PieceType.KING;
            case 'P' -> PieceType.PAWN;
             default -> null;
        };
    }

}
