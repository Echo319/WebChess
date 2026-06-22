package com.rdavies.game;

public class Game {

    //List<Piece> pieces = new ArrayList<>();

    public Piece[][] board = new Piece[8][8];

    public Game() {
        //this.pieces = initGame();
        board = initGame();
    }

    private Piece[][] initGame() {
        Piece[][] board = new Piece[8][8];
        // Gen 16 pawns 8 for white 8 for black
        for(int i = 0; i < board.length; i++) {
                board[1][i] = new Piece(Player.WHITE, PieceType.PAWN);
        }
        for(int j = 0; j < board.length; j++) {
                board[6][j] = new Piece(Player.BLACK, PieceType.PAWN);
        }

        // ROOKs
        board[0][0] = new Piece(Player.WHITE, PieceType.ROOK);
        board[0][7] = new Piece(Player.WHITE, PieceType.ROOK);
        board[7][0] = new Piece(Player.BLACK, PieceType.ROOK);
        board[7][7] = new Piece(Player.BLACK, PieceType.ROOK);

        // KNIGHTS
        board[0][1] = new Piece(Player.WHITE, PieceType.KNIGHT);
        board[0][6] = new Piece(Player.WHITE, PieceType.KNIGHT);
        board[7][1] = new Piece(Player.BLACK, PieceType.KNIGHT);
        board[7][6] = new Piece(Player.BLACK, PieceType.KNIGHT);

        // BISHOPs
        board[0][2] = new Piece(Player.WHITE, PieceType.BISHOP);
        board[0][5] = new Piece(Player.WHITE, PieceType.BISHOP);
        board[7][2] = new Piece(Player.BLACK, PieceType.BISHOP);
        board[7][5] = new Piece(Player.BLACK, PieceType.BISHOP);

        // QUEENs
        board[0][3] = new Piece(Player.WHITE, PieceType.QUEEN);
        board[7][3] = new Piece(Player.BLACK, PieceType.QUEEN);

        // KINGs
        board[0][4] = new Piece(Player.WHITE, PieceType.KING);
        board[7][4] = new Piece(Player.BLACK, PieceType.KING);

        return board;
    }




}
