//Game.java
// contains logic for running the Game
/**
  *The Game class contains all of the logic for running the game
  *@author LM
  */
import java.util.Scanner;
import java.util.ArrayList;
import ansi_terminal.*;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Game {
    Scanner sc = new Scanner(System.in);
    private World world;
    private Player player;
    private ArrayList<Box> boxes;
    private ArrayList<Enemy> enemies; 
    private ArrayList<String> roomNames;

    public Game() {
        world = new World();
	player = new Player(world.getRoomCurrent().getPlayerStart());
        boxes = world.getRoomCurrent().getBoxes();
        enemies = world.getRoomCurrent().getEnemies();
    }
		    


    // prints a help menu to the left of the map
    /**
      *Method shows a list of the possible commands in the game.  
      *Load isn't working how it is supposed to, but the logic is there.
      *
      */
    private void showHelp() {
        String[] cmds = {"Commands:",
                         "---------",
                         "Move: Arrow Keys",
                         "Pickup an item: p",
                         "Drop an item: d",
                         "List items: l",
                         "Equip weapon: w",
                         "Equip armor: a",
                         "Quit: q",
			 "Save: m",
			 "Load: n"
        };
        Terminal.setForeground(Color.GREEN);
        for (int row = 0; row < cmds.length; row++) {
            Terminal.warpCursor(row + 1, world.getRoomCurrent().getCols());
            System.out.print(cmds[row]);
        }
        Terminal.reset();
    }

    // right under the map we keep a line for status messages
    private void setStatus(String mesg) {
        // clear anything old first
        Terminal.warpCursor(world.getRoomCurrent().getRows(), 0);
        for (int i = 0; i < 100; i++) {
            System.out.print(" ");
        }

        // then print the message
        Terminal.warpCursor(world.getRoomCurrent().getRows(), 0);
        System.out.print(mesg);
    }

    // code for when the player tries to pickup an item
    private void pickup() {
        Box thing = checkForBox();
        if (thing == null) {
            setStatus("There is nothing here to pick up...");
            Terminal.pause(1.25);
        } else {
            if (player.getInventory().add(thing.getItem())) {
                setStatus("You added the " + thing.getItem().getName() + " to your inventory.");
                boxes.remove(thing);
            } else {
                setStatus("This is too large for you to add!");
            }
            Terminal.pause(1.25);
        }
    }

    // code for when the player tries to drop an item
    private void drop() {
        if (checkForBox() == null) {
            Item dropped = player.getInventory().drop();
            if (dropped != null) {
                boxes.add(new Box(player.getRow(), player.getCol(), dropped));
            }
            redrawMapAndHelp();
        } else {
            setStatus("You cannot drop something on an existing item...");
            Terminal.pause(1.25);
        }
    }

    // handle the key which was read - return false if we quit the game
    /**
      *Deals with the logic if a command is pressed. Added case for n (load) and m (save)
      *@return boolean
      *@param key paramter takes in a Key for the key entered
      */
    private boolean handleKey(Key key) {
        switch (key) {
            case p:
                pickup();
                break;

	    case l:
                player.getInventory().print();
                redrawMapAndHelp();
                break;

            case d:
                drop();
                break;

            case w:
                player.getInventory().equipWeapon();
                redrawMapAndHelp();
                break;

            case a:
                player.getInventory().equipArmor();
                redrawMapAndHelp();
                break;

            
	    case n:
		try{File f = new File("save.txt");
			Scanner in = new Scanner(f);
			player = new Player(in);
			for(Enemy enemy:enemies){
				Enemy e = new Enemy(in);
			}
			for (Box object: boxes){
				Box b = new Box(in);
			}
			Inventory i = new Inventory(in);
		}catch(Exception e){
			System.out.println("Oops something went wrong");
		}

           
	    case m:
		try{PrintWriter pw=new PrintWriter("save.txt");
			player.save(pw);
			for(Enemy enemy:enemies){
				enemy.save(pw);
			}
			for(Box object:boxes){
				object.save(pw);
			}
			player.getInventory().save(pw);
			
			
			pw.close();
		}
	
		catch(Exception e){
			System.out.print("oops that didn't work");
		}

	    // handle movement
            case LEFT: player.move(0, -1, world.getRoomCurrent());
                break;
            case RIGHT: player.move(0, 1, world.getRoomCurrent());
                break;
            case UP: player.move(-1, 0, world.getRoomCurrent());
                break;
            case DOWN: player.move(1, 0, world.getRoomCurrent());
                break;

            // and finally the quit command
            case q:
                return false;
        }

        return true;
    }

    // this is called when we need to redraw the room and help menu
    // this happens after going into a menu like for choosing items
    private void redrawMapAndHelp() {
        world.getRoomCurrent().draw();
        showHelp();
    }

    // returns a Box if the player is on it -- otherwise null
    private Box checkForBox() {
        Position playerLocation = player.getPosition();
        for (Box box : boxes) {
            if (playerLocation.equals(box.getPosition())) {
                return box;
            }
        }

        return null;
    }

    // check for battles and return false if player has died
    private boolean checkBattles() {
        Position playerLocation = player.getPosition();

        // look for an enemy that is close
        Enemy opponent = null;
        for (Enemy enemy : enemies) {
            if (playerLocation.isAdjacent(enemy.getPosition())) {
                opponent = enemy;
            }
        }

        // now do the battle
        if (opponent != null) {
            opponent.setBattleActive();
            return player.fight(opponent, world.getRoomCurrent(), enemies);
        }

        return true;
    }

    public void run() {
        // draw these for the first time now
        redrawMapAndHelp();

        boolean playing = true;
        while (playing) {
            for (Box box : boxes) {
                box.draw();
            }
            for (Enemy enemy : enemies) {
                enemy.draw();
            }
            player.draw();

            // read a key from the user
            Terminal.warpCursor(world.getRoomCurrent().getRows() + 1, 0);
            Key key = Terminal.getKey();
            playing = handleKey(key);

            // clear status by default
            setStatus("");

            // move the enemies
            for (Enemy enemy : enemies) {
                enemy.walk(world.getRoomCurrent());
            }

            // check for battles
            if (checkBattles() == false) {
                setStatus("You have been killed :(\n\r");
                playing = false;
            }

            // check if we are on a box and print what's in it
            Box thingHere = checkForBox();
            if (thingHere != null) {
                setStatus("Here you find: " + thingHere.getItem().getName());
            }
	    
//	    if (player.getPosition().equals('!')){ //need to have it so that the position of the player equals the position of the !
//		    System.out.print("Would you like to move rooms?(Y/N) ");
//		    String response = sc.next();
//		    if (response.equals("Y")){
//		        world.moveRoom(player.getPosition());
//			redrawMapAndHelp();
//		    }
//	    }

	    if (world.moveRoom(player.getPosition())){
		redrawMapAndHelp();
  	    }
    	}
    }	
}

