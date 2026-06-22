package com.rdavies.game;

import java.util.ArrayList;
import java.util.List;

public class Game {

    List<Piece> pieces = new ArrayList<>();

    public Game() {
        this.pieces = initGame();
    }

    private List<Piece> initGame() {
        // Gen 16 pawns 8 for white 8 for black
        for(int i = 0; i < 8; i++) {
                pieces.add(new Piece(Player.WHITE, PieceType.PAWN, (char) ('a' + i), 2));
        }
        for(int j = 0; j < 8; j++) {
                pieces.add(new Piece(Player.BLACK, PieceType.PAWN, (char) ('a' + j), 7));
        }

        // ROOKs
        pieces.add(new Piece(Player.WHITE, PieceType.ROOK, 'a', 1));
        pieces.add(new Piece(Player.WHITE, PieceType.ROOK, 'h', 1));
        pieces.add(new Piece(Player.BLACK, PieceType.ROOK, 'a', 8));
        pieces.add(new Piece(Player.BLACK, PieceType.ROOK, 'h', 8));

        // KNIGHTS
        pieces.add(new Piece(Player.WHITE, PieceType.KNIGHT, 'b', 1));
        pieces.add(new Piece(Player.WHITE, PieceType.KNIGHT, 'g', 1));
        pieces.add(new Piece(Player.BLACK, PieceType.KNIGHT, 'b', 8));
        pieces.add(new Piece(Player.BLACK, PieceType.KNIGHT, 'g', 8));

        // BISHOPs
        pieces.add(new Piece(Player.WHITE, PieceType.BISHOP, 'c', 1));
        pieces.add(new Piece(Player.WHITE, PieceType.BISHOP, 'f', 1));
        pieces.add(new Piece(Player.BLACK, PieceType.BISHOP, 'c', 8));
        pieces.add(new Piece(Player.BLACK, PieceType.BISHOP, 'f', 8));

        // QUEENs
        pieces.add(new Piece(Player.WHITE, PieceType.QUEEN, 'd', 1));
        pieces.add(new Piece(Player.BLACK, PieceType.QUEEN, 'd', 8));

        // KINGs
        pieces.add(new Piece(Player.WHITE, PieceType.KING, 'e', 1));
        pieces.add(new Piece(Player.BLACK, PieceType.KING, 'e', 8));

        return pieces;
    }




}
