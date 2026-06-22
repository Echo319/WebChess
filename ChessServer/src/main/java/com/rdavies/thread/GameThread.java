package com.rdavies.thread;

import com.rdavies.game.GameState;
import com.rdavies.utils.NotationParser;

/***
    Game thread. Holds information for one game on one socket
 */
public class GameThread implements Runnable
{
    GameState gs = new GameState();
    NotationParser parser = new NotationParser();



    @Override
    public void run() {

    }
}
