// Main.java
// contains the main class for running the game

import ansi_terminal.*;

public class Main {
    public static void main(String args[]) {
        // put termain in raw mode
        Terminal.rawMode();

        //introduction
	System.out.print("Welcome to Dungeon Crawler.\n\r");
	Terminal.pause(2.5);
	System.out.print("What is your name adventurer?\n\r");
	Terminal.pause(3);
	System.out.print("Are you ready to begin?\n\r");
	Terminal.pause(2);

	// make and run the Game
        Game game = new Game();
        game.run();

        // put terminal back into cooked mode
        Terminal.cookedMode();
    }
}

