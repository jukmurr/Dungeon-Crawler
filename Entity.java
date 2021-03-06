// Entity.java
// this class represents one moveable, drawable thing in the game
import java.io.PrintWriter;
import ansi_terminal.*;
import java.util.Scanner;

public class Entity {
    // the location of the entity in space
    private Position position;

    // the character used to draw it
    private char display;

    // the color used for drawing
    private Color color;

    public Entity(int row, int col, char display, Color color) {
        position = new Position(row, col);
        this.display = display;
        this.color = color;
    }
    /**
      *Constructor that reads in from the save.txt file. 
      *@param in Scanner object 
      *@return Returns an entity object with the values listed in the save.txt file.
      */
    public Entity(Scanner in){
	    int row = in.nextInt();
	    int col = in.nextInt();
	    char display = in.next().charAt(0);
    }

    // move the entity to a new location
    public void setPosition(int row, int col) {
        position = new Position(row, col);
    }

    // get the position of this entity
    public Position getPosition() {
        return position;
    }

    public int getRow() {
        return position.getRow();
    }

    public int getCol() {
        return position.getCol();
    }
    public char getDisplay(){
	    return display;
    }
    public Color getColor(){
	    return color;
    }
    
    /**
      * Save method prints to file the row, column, and display character of an entity object
      * @param pw PrintWriter object which is linked to save file
      * @return prints information into file
      */
    public void save(PrintWriter pw){
	    pw.println(this.getRow());
	    pw.println(this.getCol());
	    pw.println(this.getDisplay());
	    pw.println(this.getColor());
    }

    // translate the entity in space, unless it would hit a wall
    public boolean move(int rowChange, int colChange, Room room) {
        // find new position
        int newRow = position.getRow() + rowChange;
        int newCol = position.getCol() + colChange;

        if (room.canGo(newRow, newCol)) {
            // draw a space where it currently is
            Terminal.warpCursor(position.getRow(), position.getCol());
            System.out.print(" ");

            // and then move it
            position = new Position(newRow, newCol);
            return true;
        } else {
            return false;
        }
    }

    // draw this entity to the screen
    public void draw() {
        Terminal.warpCursor(position.getRow(), position.getCol());
        Terminal.setForeground(color);
        System.out.print(display);
        Terminal.reset();
    }
}

