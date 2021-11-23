// Main.java
// contains the main class for running the game

import ansi_terminal.*;

public class Main {
	public static void main(String args[]) {
		// put termain in raw mode
		Terminal.rawMode();
		//introduction
		System.out.print("Welcome to our Dungeon Crawler game.\n\r");
		Terminal.pause(2);
		String name = Terminal.getLine("What is your name adventurer? ");
		Terminal.pause(2);
		System.out.print("Defeat all of the enemies without dying and you win the game.\n\r");
		Terminal.pause(2);
		String input = Terminal.getLine("Would you like to load your game? (Y/N)");
		if (input.equals("Y")){
			Game gamei = new Game();
			gamei.loadGame();
			Terminal.cookedMode();
		}
		else if (input.equals("N")){
			// make and run the Game
			Game game = new Game();
			game.run();
			Terminal.cookedMode();
		}
	}
}

