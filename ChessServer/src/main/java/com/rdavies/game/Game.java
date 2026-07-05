package com.rdavies.game;

import com.rdavies.move.Move;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Piece[][] board = new Piece[8][8];

    private List<Piece> taken = new ArrayList<>();

    public Game() {
        board = initGame();
    }

    private Piece[][] initGame() {
        Piece[][] board = new Piece[8][8];

        // fill in nothing pieces
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y< board[x].length; y++) {
                board[x][y] = new Piece(x, y);
            }
        }

        // Gen 16 pawns 8 for white 8 for black
        for(int i = 0; i < board.length; i++) {
                board[1][i] = new Piece(Player.WHITE, PieceType.PAWN, i, 1);
        }
        for(int j = 0; j < board.length; j++) {
                board[6][j] = new Piece(Player.BLACK, PieceType.PAWN, j, 6);
        }

        // ROOKs
        board[0][0] = new Piece(Player.WHITE, PieceType.ROOK, 0,0);
        board[0][7] = new Piece(Player.WHITE, PieceType.ROOK, 0,7);
        board[7][0] = new Piece(Player.BLACK, PieceType.ROOK, 7,0);
        board[7][7] = new Piece(Player.BLACK, PieceType.ROOK,7,7);

        // KNIGHTS
        board[0][1] = new Piece(Player.WHITE, PieceType.KNIGHT,0,1);
        board[0][6] = new Piece(Player.WHITE, PieceType.KNIGHT,0,6);
        board[7][1] = new Piece(Player.BLACK, PieceType.KNIGHT,7,1);
        board[7][6] = new Piece(Player.BLACK, PieceType.KNIGHT,7,6);

        // BISHOPs
        board[0][2] = new Piece(Player.WHITE, PieceType.BISHOP,0,2);
        board[0][5] = new Piece(Player.WHITE, PieceType.BISHOP,0,5);
        board[7][2] = new Piece(Player.BLACK, PieceType.BISHOP,7,2);
        board[7][5] = new Piece(Player.BLACK, PieceType.BISHOP,7,5);

        // QUEENs
        board[0][3] = new Piece(Player.WHITE, PieceType.QUEEN,0,3);
        board[7][3] = new Piece(Player.BLACK, PieceType.QUEEN,7,3);

        // KINGs
        board[0][4] = new Piece(Player.WHITE, PieceType.KING,0,4);
        board[7][4] = new Piece(Player.BLACK, PieceType.KING,7,4);

        return board;
    }

    public Piece getPosition(int file, int rank) {
        return board[file][rank];
    }

    public boolean update(Piece piece, Move move) {
        // replace the existing position with nothing
        board[piece.x][piece.y] = new Piece(piece.x, piece.y);

        if(move.isCapture) {
            board[move.toRank][move.toFile].isAlive = false;
            taken.add(board[move.toRank][move.toFile]);
        }

        if(piece.hasMoved) {
            piece.hasMoved = false;
        }

        board[move.toRank][move.toFile] = piece;

        return false;
    }



}
