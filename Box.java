// Box.java
// represents a pickup-able item

import ansi_terminal.*;
import java.io.PrintWriter;

public class Box extends Entity {
    // the Item that is in the box
    private Item item;

    // add a box with a given item in it
    public Box(int row, int col, Item item) {
        super(row, col, 'i', Color.MAGENTA);
        this.item = item;
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


