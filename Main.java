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
		String name = Terminal.getLine("What is your name adventurer?");
		Terminal.pause(2);

		// make and run the Game
		Game game = new Game();
		game.run();

		// put terminal back into cooked mode
		Terminal.cookedMode();
	}
}

