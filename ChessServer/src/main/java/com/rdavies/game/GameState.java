package com.rdavies.game;

import com.rdavies.messages.MoveMessage;
import com.rdavies.utils.NotationParser;


public class GameState {

    private final String gameId;
    private final Game game = new Game();
    private final NotationParser parser = new NotationParser();
    private Player turnPlayer = Player.WHITE;

    public GameState(String gameId) {
        this.gameId = gameId;
    }

    public boolean handleMove(MoveMessage message) {
        if(turnPlayer != Player.valueOf(message.getPlayer())) {
            return false;
        }

        Move recievedMove = parser.parse(message.getMove());
        Piece pieceToMove = validMove(recievedMove);

        if(pieceToMove == null) {
            return false;
        }

        if(game.update(pieceToMove, recievedMove)) {
            turnPlayer = nextPlayer();
            return true;
        }
        return false;
    }

    public String getFenString() {
        return game.toFenString();
    }


    // from the position moved to we then step back to find the piece that moved
    private Piece validMove(Move move) {
        Piece pieceToMove = null;

        // captured piece exists and is not their own piece
        if(move.isCapture) {
            // if capture find the piece that is gonna be captured
            Piece captured = game.getPosition(move.toFile, move.toRank);
            if(captured == null) {
                return null;
            }
            if(captured.player == turnPlayer) {
                return null;
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
                return null;
            }
        }

        return pieceToMove;
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


    private Player nextPlayer() {
        if(this.turnPlayer == Player.WHITE)
            return Player.BLACK;
        else
            return Player.WHITE;
    }

}
