// Enemy.java

import java.util.Random;
import ansi_terminal.*;
import java.io.PrintWriter;
import java.util.Scanner;

public class Enemy extends Character {
    private String name;
    private int damage;
    private int protection;
    private int score;
    private static Random rng;
    private boolean battleActive;

    public Enemy(String name, int row, int col, int hp, int damage, int protection, int score) {
        super(row, col, '*', Color.RED, hp);
        this.name = name;
        this.damage = damage;
        this.protection = protection;
	this.score = score;
        this.battleActive = false;
        rng = new Random();
    }
    /**
      *Constructor that reads in from the save.txt file.
      *@param in Scanner object 
      *@return Returns an enemy object with the values listed in the save.txt file
      */
    public Enemy(Scanner in){
	    super(in);
	    in.nextLine();
	    name = in.nextLine();
	    damage = in.nextInt();
	    protection = in.nextInt();
    }

    /**
      * Save method take in PrintWriter object and save object information to file, "enemy" is printed as the first line of output
      * for easier data processing and human readability
      * @param pw PrintWriter object linked to save file
      * @return object information printed to file
      */
    public void save(PrintWriter pw){
	    pw.println("enemy");
	    super.save(pw);
	    pw.println(this.getName());
	    pw.println(this.getDamage());
	    pw.println(this.getProtection());
	    pw.println(this.getScore());
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getProtection() {
        return protection;
    }

    @Override
    public String getName() {
        return name;
    }
    /**
    *returns the point value of the enemy it is called on
    *@return an int representing the point value of the enemy
    */
    @Override
    public int getScore() {
	return score;
    }
    @Override
    public void setScore(int n){
    }

    public void setBattleActive() {
        battleActive = true;
    }
    

    // randomly move this enemy in the room
    public void walk(Room room) {
        // if a battle is active with this enemy, they DONT walk right after
        if (battleActive) {
            battleActive = false;
            return;
        }

        // loop forever until we move correctly
        while (true) {
            int choice = rng.nextInt(4);
            switch (choice) {
                case 0:
                    if (move(0, 1, room)) return;
                    break;
                case 1:
                    if (move(0, -1, room)) return;
                    break;
                case 2:
                    if (move(1, 0, room)) return;
                    break;
                case 3:
                    if (move(-1, 0, room)) return;
                    break;
            }
        }
    }
}


