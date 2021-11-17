import java.util.Random;

public class EnemyGenerator {
    private static String[] enemyTypes = {"zombie","ghost","skeleton","rat"};
    public static Enemy generate(int row, int col) {
        Random rng = new Random();
	
        //EnemyType type = EnemyType.values()[new Random().nextInt(EnemyType.values().length)];
	int name = rng.nextInt(4);
        int damage = rng.nextInt(31);
        int hp = rng.nextInt(101);
	int protection = rng.nextInt(25);

        Enemy enemy = new Enemy(enemyTypes[name], row, col, hp, damage, protection);
        return enemy;
    }
}
