// Player.java

import ansi_terminal.*;
import java.io.PrintWriter;

public class Player extends Character {
    private Inventory items;

    public Player(Position start) {
        // our starting details
        super(start.getRow(), start.getCol(), '@', Color.CYAN, 50);

        // we can carry 100 pounds of items
        items = new Inventory(100);

        // give them some basic stuff to start with
        // TODO make up your own starting equipment!
        items.addAndEquip(new Item(ItemType.Weapon, "Iron Dagger", 5, 12, 7));
        items.addAndEquip(new Item(ItemType.Armor, "Leather Armor", 15, 20, 3));
    }
    public void save(PrintWriter pw){
	    pw.println(this.getRow());
	    pw.println(this.getCol());
	    pw.println(this.getDisplay());
	    pw.println(this.getColor());
	    pw.println(this.getHealth());
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
}

