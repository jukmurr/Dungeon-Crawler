import java.util.ArrayList;

import ansi_terminal.*;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class World {
    // the grid holds the room geometry
    Scanner stdin = new Scanner(System.in);
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
    }
	
    public Room getRoomCurrent(){
	if (room1.getCurrentRoom() == true){
		return room1;
	} else if (room2.getCurrentRoom() == true){
		return room2;
	} else if (room3.getCurrentRoom() == true){
		return room3;
	}
	return null;
    }

}
