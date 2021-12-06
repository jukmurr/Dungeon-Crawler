import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
public class EnemyGenerator {
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static Enemy generate(int row, int col) {
        Random rng = new Random();
	Enemy zombie = new Enemy("zombie",row,col,30,4,2,60);
	Enemy ghost = new Enemy("ghost",row,col,20,3,1,40);
	Enemy skeleton = new Enemy("skeleton",row,col,10,3,1,20);
	Enemy rat = new Enemy("rat",row,col,15,3,2,30);

	
        //EnemyType type = EnemyType.values()[new Random().nextInt(EnemyType.values().length)]

        enemies.add(zombie);
	enemies.add(ghost);
	enemies.add(skeleton);
	enemies.add(rat);
	Collections.shuffle(enemies);
        return enemies.get(0);
    }
}
