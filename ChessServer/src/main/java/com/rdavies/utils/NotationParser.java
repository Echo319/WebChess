package com.rdavies.utils;

import com.rdavies.game.PieceType;
import com.rdavies.game.Move;

import java.util.regex.Pattern;

public class NotationParser {

    Pattern powerPieceRegEx = Pattern.compile("^[KQBNR]");
    Pattern fileRegEx = Pattern.compile("^[a-h]");

    // so long or regular algebraic
    public Move parse(String notation) {
        Move move = new Move();

        // check +
        if(notation.contains("+")) {
            notation = notation.replace("+", "");
            move.isCheck = true;
        }

        // checkmate #
        if(notation.contains("#")) {
            notation = notation.replace("#", "");
            move.isCheckmate = true;
        }

        // capture x
        if(notation.contains("x")) {
            notation = notation.replace("x", "");
            move.isCapture = true;
        }

        // Promotion =
        PieceType promotion;
        if(notation.contains("=")) {
            notation = notation.replace("=", "");
            // split on the = get the next char and convert to PieceType
            move.promotedTo = PieceType.charToPiece(notation.split("=")[1].toCharArray()[0]);
            move.isPromotion = true;
        }

        // castle O-O || O-O-O
        if(notation.equals("O-O")) {
            move.pieceType = PieceType.KING;
            move.isKingCastle = true;
            return move;
        }
        if(notation.equals("O-O-O")) {
            move.pieceType = PieceType.KING;
            move.isQueenCastle = true;
            return move;
        }

        // pawn move
        if(notation.length() == 2) {
            move.pieceType = PieceType.PAWN;
            char[] chars = notation.toCharArray();
            move.toFile = fileToX(chars[0]);
            move.toRank = rankToY(chars[1]);
            return move;
        }

        //Pawn capture ex: ed5
        if(fileRegEx.matcher(notation).find()) {
            move.pieceType = PieceType.PAWN;
            move.fromFile = fileToX(notation.charAt(0));
            move.toFile = fileToX(notation.charAt(1));
            move.toRank = rankToY(notation.charAt(2));
            return move;
        }

        //Power piece move
        if(powerPieceRegEx.matcher(notation.substring(0, 1)).find()) {
            move.pieceType = PieceType.charToPiece(notation.charAt(0));
            // normal move eg : Ne4 knight to e4
            if(notation.length() == 3) {
                move.toFile = fileToX(notation.charAt(1));
                move.toRank = rankToY(notation.charAt(2));
                return move;
            }

            //Ambiguous power piece move
            //the file of departure (if they differ);
            //the rank of departure (if the files are the same but the ranks differ);
            //both the file and rank of departure (if neither file nor rank alone is sufficient to identify the piece). This only occurs when three or more pieces of the same type can move to the same square (double disambiguation).

            String moveTo = notation.substring(notation.length() - 2);
            RankAndFile moveToCoords = parsePlace(moveTo);
            if(moveToCoords == null) {
                return null;
            }
            move.toFile = moveToCoords.file;
            move.toRank = moveToCoords.rank;

            String moveFrom = notation.substring(1, notation.length() -2);
            RankAndFile moveFromCoords = parsePlace(moveFrom);

            if(moveFromCoords == null) {
                return null;
            }

            if(moveFromCoords.file != null) {
                move.fromFile = moveFromCoords.file;
            }
            if(moveFromCoords.rank != null) {
                move.fromRank = moveFromCoords.rank;
            }
            return move;
        }

        // fail case
        return null;
    }



    // Helper to get x, y coords from complex moves where one character is used in notation e.g Rce8 "rook on file c moved to e8"
    private RankAndFile parsePlace(String position) {
        char[] chars = position.toCharArray();
        if(position.length() == 2) {
            return new RankAndFile(fileToX(chars[0]), rankToY(chars[1]));
        }
        if(fileRegEx.matcher(position).find()) {
            return new RankAndFile(fileToX(chars[0]), null);
        }
        if(position.matches("[1-8]")) {
            return new RankAndFile(null, rankToY(chars[0]));
        }
        return null;
    }

    public static class RankAndFile {
        Integer file;
        Integer rank;

        // eg.. e4 == 4, 3
        RankAndFile(Integer file, Integer rank) {
            this.file = file;
            this.rank = rank;
        }
    }

    // Helpers to convert characters into board coordinates
    private int fileToX(char file) {
        return file - 'a';
    }

    private int rankToY(char rank) {
        return Character.getNumericValue(rank) - 1;
    }



}
