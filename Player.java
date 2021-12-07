// Player.java

import ansi_terminal.*;
import java.io.PrintWriter;
import java.util.Scanner;

public class Player extends Character {
    private Inventory items;
    private int score;

    public Player(Position start) {
        // our starting details
        super(start.getRow(), start.getCol(), '@', Color.CYAN, 50);

        // we can carry 100 pounds of items
        items = new Inventory(100);

        // give them some basic stuff to start with
        items.addAndEquip(new Item(ItemType.Weapon, "wood sword", 5, 2, 5));
        items.addAndEquip(new Item(ItemType.Armor, "fur Armor", 8, 15, 1));
	this.score = 0;
    }
    /**
      *Constructor that reads in from the save.txt file
      *@param in Scanner object 
      *@return Returns a player object with the values listed in the save.txt file
      */
    public Player(Scanner in) {
	    super(in);
    }
    /**
      * Save method for player, takes PrintWriter object and prints to file player stats
      * @param pw PrintWriter object linked to save file
      * @return prints to file player information
      */
    public void save(PrintWriter pw){
	   super.save(pw);
	   pw.println(this.getScore());
    }

    @Override
    public int getDamage() {
        Item weapon = items.getEquippedWeapon();
        if (weapon != null) {
            return weapon.getStrength();
        } else {
            // if we have no weapon, our fists are pretty weak...
            return 1;
        }
    }

    @Override
    public String getName() {
        return "Player";
    }

    @Override
    public int getProtection() {
        Item armor = items.getEquippedArmor();
        if (armor != null) {
            return armor.getStrength();
        } else {
            // without armor, we have no protection
            return 0;
        }
    }

    public Inventory getInventory() {
        return items;
    }
    /**
     *Returns the current score of the player
     * @return An int representing the player's score
      */
    @Override
    public int getScore(){
	return score;
    }
    /**
    *updates the players score varible after defeating an enemy
    */
    @Override
    public void setScore(int enemyPointValue){
	this.score += enemyPointValue;
    }

}

