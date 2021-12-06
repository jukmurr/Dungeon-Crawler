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
    public Player(Scanner in) {
	    super(in);
    }
    
    public void save(PrintWriter pw){
	   super.save(pw);
	   //pw.println(items.print());
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
    
    @Override
    public int getScore(){
	return score;
    }

    @Override
    public void setScore(int enemyPointValue){
	this.score += enemyPointValue;
    }

}

