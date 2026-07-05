package com.rdavies.game;

import com.rdavies.move.Move;
import com.rdavies.utils.NotationParser;


public class GameState {
        // new
    private Game game = new Game();
    private NotationParser parser = new NotationParser();
    private Player turnPlayer = Player.WHITE;

    public GameState() {

    }


    // from the position moved to we then step back to find the piece that moved
    private boolean validMove(Move move) {
        boolean isValid = false;
        Piece pieceToMove = null;

        // captured piece exists and is not their own piece
        if(move.isCapture) {
            // if capture find the piece that is gonna be captured
            Piece captured = game.getPosition(move.toFile, move.toRank);
            if(captured == null) {
                return false;
            }
            if(captured.player == turnPlayer) {
                return false;
            }
        }


        switch(move.pieceType) {
            case PAWN -> {
                pieceToMove = validatePawnMove(move);
            }
            case ROOK -> {
            }
            case KNIGHT -> {
            }
            case BISHOP -> {
            }
            case QUEEN -> {
            }
            case KING -> {
            }
            default -> {
                return false;
            }
        }

        if(pieceToMove == null) {
            return false;
        }

        isValid = game.update(pieceToMove, move);
        return isValid;
    }


    // from the given position we step back to find the pawn that made that move.
    private Piece validatePawnMove(Move move) {
        // if isCapture check diagonal
        if(move.isCapture) {
            //Piece captured = game.getPosition(move.toFile, move.toRank);
            if(turnPlayer == Player.WHITE) {
                // check -1, -1
                if(game.getPosition(move.toFile - 1,move.toRank -1 ) != null
                        && game.getPosition(move.toFile - 1,move.toRank -1 ).player == turnPlayer
                        && game.getPosition(move.toFile - 1,move.toRank -1 ).type == PieceType.PAWN) {
                    return game.getPosition(move.toFile - 1,move.toRank -1 );
                }
                // check +1, -1
                if(game.getPosition(move.toFile + 1,move.toRank -1 ) != null
                        && game.getPosition(move.toFile + 1,move.toRank -1 ).player == turnPlayer
                        && game.getPosition(move.toFile + 1,move.toRank -1 ).type == PieceType.PAWN) {
                    return game.getPosition(move.toFile + 1,move.toRank -1 );
                }
                return null;
            } else {
                // check -1 +1
                if(game.getPosition(move.toFile - 1,move.toRank  + 1 ) != null
                        && game.getPosition(move.toFile - 1,move.toRank + 1 ).player == turnPlayer
                        && game.getPosition(move.toFile - 1,move.toRank + 1 ).type == PieceType.PAWN) {
                    return game.getPosition(move.toFile - 1,move.toRank  + 1 );
                }
                // check +1 +1
                if(game.getPosition(move.toFile + 1,move.toRank + 1 ) != null
                        && game.getPosition(move.toFile + 1,move.toRank + 1 ).player == turnPlayer
                        && game.getPosition(move.toFile + 1,move.toRank + 1 ).type == PieceType.PAWN) {
                    return game.getPosition(move.toFile + 1,move.toRank + 1 );
                }
                return null;
            }
        }

        if(turnPlayer == Player.WHITE) {
            // moved one space
            if(game.getPosition(move.toFile,move.toRank - 1 ) != null
                    && game.getPosition(move.toFile,move.toRank - 1 ).player == turnPlayer
                    && game.getPosition(move.toFile,move.toRank - 1 ).type == PieceType.PAWN) {
                return game.getPosition(move.toFile,move.toRank - 1 );
            }
            // moved 2 spaces and is first move
            if(game.getPosition(move.toFile,move.toRank - 2 ) != null
                    && game.getPosition(move.toFile,move.toRank - 2 ).player == turnPlayer
                    && game.getPosition(move.toFile,move.toRank - 2 ).type == PieceType.PAWN
                    && !game.getPosition(move.toFile, move.toRank - 2).hasMoved) {
                return game.getPosition(move.toFile,move.toRank - 2 );
            }
            return null;
        }
        if(turnPlayer == Player.BLACK) {
            // moved one space
            if(game.getPosition(move.toFile,move.toRank + 1 ) != null
                    && game.getPosition(move.toFile,move.toRank + 1 ).player == turnPlayer
                    && game.getPosition(move.toFile,move.toRank + 1 ).type == PieceType.PAWN) {
                return game.getPosition(move.toFile,move.toRank + 1 );
            }
            // moved 2 spaces and is first move
            if(game.getPosition(move.toFile,move.toRank + 2 ) != null
                    && game.getPosition(move.toFile,move.toRank + 2 ).player == turnPlayer
                    && game.getPosition(move.toFile,move.toRank + 2 ).type == PieceType.PAWN
                    && !game.getPosition(move.toFile, move.toRank + 2).hasMoved) {
                return game.getPosition(move.toFile,move.toRank + 2 );
            }
            return null;
        }
        throw new RuntimeException("Cannot validate pawn move something is very wrong");
    }

}
