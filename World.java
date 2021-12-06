/**
  * Initiates and stores the rooms of the game's world as well as
  * tells which room a player is currently in.
  * @author JA
  */

import java.util.ArrayList;
import ansi_terminal.*;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class World {
    // the grid holds the room geometry
    Scanner stdin = new Scanner(System.in);
    public Room currentRoom;
    public String response;
    public Room room1;
    public Room room2;
    public Room room3;
    private int rows;
    private int cols;

    /**
      * Constructs a new world object that sets the size of each room and creates 3 new rooms.
      */  
    public World() {
        rows = 30;
        cols = 60;
	room1 = new Room("Room1.txt");
	room2 = new Room("Room2.txt");
	room3 = new Room("Room3.txt");
  	currentRoom = room1;
    }

    /**
      * Takes the position of a player and reveals which tells which room they are in.
      * @param position the position of the player
      * @return true if room is changed
      */
    public boolean moveRoom(Position position){
	    currentRoom = room1;
	    if ((currentRoom == room1) && ((position.getRow() == 27) && (position.getCol() == 6))){
			    currentRoom = room2;
			    return true;
	    } else if ((currentRoom.equals(room2)) && ((position.getRow() == 3) && (position.getCol() == 5))){
			    currentRoom = room3;
			    return true;
	    }
    	return false;
    }
	
    /**
      * Gets the current room.
      */
    public Room getRoomCurrent(){
	return currentRoom;
    }

}
