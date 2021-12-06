// Room.java
// provides code for the drawing of a room
// also provides starting locations for the player, boxes, and enemies
/**
  * Creates a room for a game.
  * @author JA
  */
import java.util.ArrayList;
import ansi_terminal.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;

public class Room {
    // the grid holds the room geometry
    Scanner sc;
    private String fileName;
    // the size of the room
    private int rows;
    private int cols;
    private String[] grid;
    private String name;
    private File file;
    private String fileLine;

    /**
      * Constructs a room by reading in a file and by taking a size for it,
      * it turns the room into a grid
      * @param name name of the file
      */
    public Room(String name) {
        // this initializes the room to one specific space
        rows = 30;
        cols = 60;
	this.name = name;
        // the actual room geometry
        // the i cells refer to where an item should be placed at
	try {
		sc = new Scanner(new BufferedReader(new FileReader(name)));
		grid = new String[30];
		for (int i = 0; i < 30; i++){
			fileLine = sc.nextLine();
			grid[i] = (fileLine);
		}
		//File file = new File(input.nextLine())};
	}
	catch (FileNotFoundException e) {
		System.out.println("Cannot find the room file");
	}
    }

    public String[] getGrid() {
		return grid;
    }
    // returns the player's strting location in this room
	public Position getPlayerStart() {
        	for (int row = 0; row < rows; row++) {
           		for (int col = 0; col < cols; col++) {
                		if (grid[row].charAt(col) == '@') {
                    			return new Position(row, col);
                }
            }
        }

        return null;
    }
    // returns a set of item boxes for this map, this is here because it depends on
    // the room geometry for where the boxes make sense to be
    public ArrayList<Box> getBoxes() {
        ArrayList<Box> boxes = new ArrayList<Box>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == 'i') {
                    boxes.add(new Box(row, col, ItemGenerator.generate()));
                }
            }
        }

        return boxes;
    }

// returns a set of enemies from this map, similarly to the boxes above
    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '*') {
                    enemies.add(EnemyGenerator.generate(row, col));
                }
            }
        }

        return enemies;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void draw() {
     	Terminal.clear();
       	for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
               	char cell = grid[row].charAt(col);
               	if (cell == '#') {
                // a unicode block symbol
                    System.out.print('\u2588');
		} else if (cell == '!'){
			System.out.print("!");
		}else {
                   // whatever else, just draw a blank (we DONT draw starting items from map)
                   System.out.print(' ');
               	}
           }

            System.out.print("\n\r");
       	}
    }	

    // draws the map to the screen
    public boolean canGo(int row, int col) {
        	return grid[row].charAt(col) != '#';
    }	
}


