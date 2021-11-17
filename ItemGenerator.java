import java.util.*;//The ItemGenerator class creats an object of a random type, name, weight ,value, and strength, then adds it to the user's inventory
public class ItemGenerator{
	public static Item generate(){
		Random rng = new Random();
		ArrayList<Item> obj = new ArrayList<Item>();
		Item one = new Item(ItemType.Weapon, "God Sword", 3, 100,10);
		Item two = new Item(ItemType.Weapon, "Diamond Sword", 10, 76,8);
		Item three = new Item(ItemType.Weapon, "Gold Sword", 11, 44,7);
		Item four = new Item(ItemType.Weapon, "Grass Sword", 1, 30,6);
		Item five = new Item(ItemType.Weapon, "Silver Sword", 4, 21,7);
		Item seven = new Item(ItemType.Armor, "God Armor", 3, 100,3);
		Item eight = new Item(ItemType.Armor, "Leather Armor", 5, 25,1);
		Item nine = new Item(ItemType.Armor, "Obsidian Armor", 14, 65,1);
		Item ten = new Item(ItemType.Armor, "Diamond Armor", 10, 55,1);
		Item eleven = new Item(ItemType.Armor, "Wooden Armor", 5, 4,1);
		Item twelve = new Item(ItemType.Armor, "Encryption Armor",10, 10,2);
		Item thirteen = new Item(ItemType.Other, "Alpha Cap", 2, 100,0);
		Item fourteen = new Item(ItemType.Other, "Keyboard", 6, 6,2);
		Item fifteen = new Item(ItemType.Other, "Pencil", 1, 2,5);
		Item sixteen = new Item(ItemType.Other, "Gold Nugget", 10, 100,2);
		Item seventeen = new Item(ItemType.Other, "Top hat", 1, 10,0);
		Item eighteen = new Item(ItemType.Other, "Phone charger", 2, 5,0);
		obj.add(one);
		obj.add(two);
		obj.add(three);
		obj.add(four);
		obj.add(five);
		obj.add(seven);
		obj.add(eight);
		obj.add(nine);
		obj.add(ten);
		obj.add(eleven);
		obj.add(twelve);
		obj.add(thirteen);
		obj.add(fourteen);
		obj.add(fifteen);
		obj.add(sixteen);
		obj.add(seventeen);
		obj.add(eighteen);
		int x = rng.nextInt(obj.size());
		return obj.get(x);
	}
}
