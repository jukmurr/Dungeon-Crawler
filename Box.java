// Box.java
// represents a pickup-able item
import java.util.Scanner;
import ansi_terminal.*;
import java.io.PrintWriter;

public class Box extends Entity {
    // the Item that is in the box
    private Item item;
    private ItemType type;

    // add a box with a given item in it
    public Box(int row, int col, Item item) {
        super(row, col, 'i', Color.MAGENTA);
        this.item = item;
    }
    /**
      *Construcotr that reads in from the save.txt file.
      *@param Scanner paramater takes in a scanner object called in
      *@return Box Returns a Box object with the values listed in the save.txt file.
      */
    public Box(Scanner in){
	    super(in);
	    in.nextLine();
	    type = ItemType.valueOf(in.next());
	    String name = in.nextLine();
	    int weight = in.nextInt();
	    int value = in.nextInt();
	    int strength = in.nextInt();
	    

    }
    public void save(PrintWriter pw){
	    pw.println("Box");
	    super.save(pw);
	    pw.println(item.getType());
	    pw.println(item.getName());
	    pw.println(item.getWeight());
	    pw.println(item.getValue());
	    pw.println(item.getStrength());
    }
    public Item getItem() {
        return item;
    }
}


