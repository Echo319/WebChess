package com.rdavies.move;

import com.rdavies.game.PieceType;

public class Move {

    public PieceType pieceType = null;
    public int fromFile = -1; // col
    public int fromRank = -1; // row
    public int toFile = -1; // col
    public int toRank = -1; // row
    public boolean isCheck = false;
    public boolean isCheckmate = false;
    public boolean isCapture = false;
    public boolean isPromotion = false;
    public PieceType promotedTo = null;
    public boolean isKingCastle = false;
    public boolean isQueenCastle = false;

    @Override
    public String toString() {
        return "Move{" +
                "pieceType=" + pieceType +
                ", fromFile=" + fromFile +
                ", fromRank=" + fromRank +
                ", toFile=" + toFile +
                ", toRank=" + toRank +
                ", isCheck=" + isCheck +
                ", isCheckmate=" + isCheckmate +
                ", isCapture=" + isCapture +
                ", isPromotion=" + isPromotion +
                ", promotedTo=" + promotedTo +
                ", isKingCastle=" + isKingCastle +
                ", isQueenCastle=" + isQueenCastle +
                '}';
    }


}
