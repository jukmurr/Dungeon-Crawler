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
    // the size of the room

    private int rows;
    private int cols;	
    public World() {
        // this initializes the room to one specific space
        rows = 30;
        cols = 60;
	room1 = new Room("Room1.txt");
	room2 = new Room("Room2.txt");
	room3 = new Room("Room3.txt");
  	currentRoom = room1;
    }
	
//    public Room getRoomCurrent(Position position){
    public boolean moveRoom(Position position){
	    currentRoom = room1;
	    if ((currentRoom == room1) && ((position.getRow() == 27) && (position.getCol() == 45))){
			    currentRoom = room2;
			    return true;
	    } else if ((currentRoom.equals(room2)) && ((position.getRow() == 28) && (position.getCol() == 34))){
			    currentRoom = room3;
			    return true;
	    } else if ((currentRoom.equals(room3)) && ((position.getRow() == 24) && (position.getCol() == 18))){
			    currentRoom = room1;
			    return true;
	    }
    	return false;
    }

    public Room getRoomCurrent(){
	return currentRoom;
    }

}
