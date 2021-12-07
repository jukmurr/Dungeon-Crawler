// Inventory.java
// allows for storing some number of items for the player

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import ansi_terminal.*;

public class Inventory {
    // the actual list of items
    private ArrayList<Item> items;
    private ItemType type;

    // which item is equipped, if any
    private Item equippedArmor;
    private Item equippedWeapon;


    // the max weight limit for the player here
    private int maxWeight;

    public Inventory(int maxWeight) {
        items = new ArrayList<Item>();
        this.maxWeight = maxWeight;
    }
    /**
      *Constructor that reads in from the save.txt file.
      *@param in Scanner object 
      *@return Returns an Inventory with the values listed in the save.txt file
      */
    public Inventory(Scanner in){
	    maxWeight = in.nextInt();
	    for (int i=0;i<items.size(); i++){
		    String name = in.nextLine();
		    int weight = in.nextInt();
		    int value = in.nextInt();
		    int strength = in.nextInt();
		    type = ItemType.valueOf(in.next());

	    }
	    String eaName = in.nextLine();
	    int eaWeight = in.nextInt();
	    int eaValue = in.nextInt();
	    int eaStrength = in.nextInt();
	    String ewName = in.nextLine();
	    int ewWeight = in.nextInt();
	    int ewValue = in.nextInt();
	    int ewStrength = in.nextInt();
    }

    /**
      * Save method for inventory object, prints maxWeight, iterates through inventory arrayList and prints item information
      * equippedWeapon and equippedArmor information are also saved
      * @param pw PrintWriter object linked to save file
      * @return printed information in file
      */

    public void save(PrintWriter pw){
	pw.println(maxWeight);
	for(int i=0;i<items.size();i++){
	 	pw.println(items.get(i).getName());
	 	pw.println(items.get(i).getWeight());
	 	pw.println(items.get(i).getValue());
	 	pw.println(items.get(i).getStrength());
	 	pw.println(items.get(i).getType());
	 	}	
	pw.println(equippedArmor.getName());
	pw.println(equippedArmor.getWeight());
	pw.println(equippedArmor.getValue());
	pw.println(equippedArmor.getStrength());
	pw.println(equippedWeapon.getName());
	pw.println(equippedWeapon.getWeight());
	pw.println(equippedWeapon.getValue());
	pw.println(equippedWeapon.getStrength());

    }

    // returns true on success, false when full
    public boolean add(Item item) {
        if ((item.getWeight() + totalWeight()) > maxWeight) {
            return false;
        } else {
            items.add(item);
            return true;
        }
    }

    // this method not only adds the item, but equips it into the correct slot
    // it is used for setting up the player's starting gear
    public void addAndEquip(Item item) {
        items.add(item);

        if (item.getType() == ItemType.Weapon) {
            equippedWeapon = item;
        } else if (item.getType() == ItemType.Armor) {
            equippedArmor = item;
        }
    }

    // get the equipped weapon and armor
    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public Item getEquippedArmor() {
        return equippedArmor;
    }

    // returns the total weight of all items stored
    public int totalWeight() {
        int total = 0;
        for (Item i : items) {
            total += i.getWeight();
        }
        return total;
    }

    // print all of the items in the list, that match they given type (can be null)
    // returns the number of items matching they type
    private int print(ItemType filter) {
        // clear the terminal so we print over all else
        Terminal.clear();

        // print a heading row
        // the numbers and junk are to make it print in nice columns
        Terminal.setForeground(Color.RED);
        System.out.printf("%-4s %-40s %-8s %-8s %-8s\n\r", "No.", "Name", "Weight", "Value", "Strength");
        Terminal.reset();

        // print each item out
        int num = 0;
        for (Item i : items) {
            if (filter == null || i.getType() == filter) {
                System.out.printf("%-4d %-40s %-8s %-8s %-8s", num + 1, i.getName(), i.getWeight(), i.getValue(), i.getStrength());

                // tell them if this thing is equipped
                if (i == equippedArmor) {
                    System.out.print(" (equipped armor)");
                } else if (i == equippedWeapon) {
                    System.out.print(" (equipped weapon)");
                }
                System.out.print("\n\r");

                num++;
            }
        }

        return num;
    }

    // stay here until the user is ready to go back
    public void pressAnyKey() {
        System.out.printf("\n\rPress any key to return...\n\r");
        Terminal.getKey();
    }

    // print all of the items in the list
    public void print() {
        print(null);
        pressAnyKey();
    }

    // drop an item from the inventory, return what was dropped
    public Item drop() {
        Item toDrop = pickItem(null);
        if (toDrop != null) {
            // if we're dropping our equipped stuff, remove those too!
            if (equippedWeapon == toDrop) {
                equippedWeapon = null;
            } else if (equippedArmor == toDrop) {
                equippedArmor = null;
            }

            items.remove(toDrop);
        }

        if (toDrop != null) {
            System.out.print("You dropped the " + toDrop.getName() + "...\n\r");
        } else {
            System.out.print("You dropped nothing...\n\r");
        }

        pressAnyKey();
        return toDrop;
    }

    // equip something
    private Item equip(ItemType type) {
        Item thing = pickItem(type);
        if (thing != null) {
            System.out.print("You equipped the " + thing.getName() + "\n\r");
        } else {
            System.out.print("You equipped nothing...\n\r");
        }
        pressAnyKey();
        return thing;
    }

    // equip a weapon
    public void equipWeapon() {
        equippedWeapon = equip(ItemType.Weapon);
    }

    // equip a piece of armor
    public void equipArmor() {
        equippedArmor = equip(ItemType.Armor);
    }

    // a method which allows users to choose an item
    // this is private - only called by drop and equip
    private Item pickItem(ItemType filter) {
        // print all the matching items
        int options = print(filter);

        if (options == 0) {
            System.out.print("You have no appropriate items!\n\r");
            return null;
        }

        // give them a cancel option as well
        System.out.print((options + 1) + "    None\n\r");

        // get their choice, only allowing ints in the correct range
        int choice = 0;
        do {
            String entry = Terminal.getLine("Select an item: ");
            try {
                choice = Integer.parseInt(entry) - 1;
            } catch (NumberFormatException e) {
                choice = -1;
            }
        } while (choice < 0 || choice > options);

        // go through and skip items until we reach this one
        int realIndex = 0;
        for (Item i : items) {
            if (filter == null || i.getType() == filter) {
                if (choice == 0) {
                    break;
                }
                choice--;
            }
            realIndex++;
        }

        // return the thing they chose
        if (realIndex < 0 || realIndex >= items.size()) {
            return null;
        } else {
            return items.get(realIndex);
        }
    }
}

