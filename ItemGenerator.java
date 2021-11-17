import java.util.*;//The ItemGenerator class creats an object of a random type, name, weight ,value, and strength, then adds it to the user's inventory
public class ItemGenerator{

	private static String[] weapons = {"sword", "dagger", "bow", "mace", "battle-axe", "war-hammer"};
	private static String[] armor = {"lether armor", "iron armor", "steel armor","gold armor","fur armor", "diamond armor"};
	private static String[] other = {"necklace", "bone", "book", "potion", "torch"};
	private static int weight;
	private static int value;
	private static String name;
	private static int strength;
	private static ItemType itemType;

	public static Item generate(){
	Random rng = new Random();
	//generate a number between 0 and 2, 0=other,1=weapon,2=armor
	int type = rng.nextInt(3);
	//creating an 'other' object
	if(type == 0){
		itemType = ItemType.Other;
		int x = rng.nextInt(5);
		name = other[x];
		strength = 0;		
	}
	//creating a 'weapon' object
	if(type == 1){
		itemType = ItemType.Weapon;
		strength = rng.nextInt(30);
		int x = rng.nextInt(6);
		name = weapons[x];
	}
	//creating an 'armor' object
	if(type == 2){
		itemType = ItemType.Armor;
		strength = rng.nextInt(50);
		int x = rng.nextInt(6);
		name = armor[x];
	}
	weight = rng.nextInt(20);
	weight = weight + 1;
	value = rng.nextInt(100);
	value = value + 1;
	Item newItem = new Item(itemType, name, weight, value , strength); 
	return newItem;
}
}
