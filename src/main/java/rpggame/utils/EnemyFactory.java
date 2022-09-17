package rpggame.utils;

import java.util.List;
import java.util.Random;

import rpggame.enemy.Enemy;
import rpggame.player.Player;
import rpggame.weapon.Weapon;

public class EnemyFactory {

	// all weapons into a List from csv file
	public static List<Weapon> weaponList = WeaponsListCreator.createListWeaponsFromCsvFile("AllWeapons.csv");
	private Random random = new Random();
	
	public Enemy createEnemyCustomLevel(Player player, String name, int level) {
		int currentStatPoints = player.getCurrentTotalStatPoints() + level;
		String enenmyName = name;
		int strength = 0;
		int agility = 0;
		int intelligence = 0;
		Weapon weapon = weaponList.get(0);

		int[] enemyStats = pickEnemyStatsCustomExactLevel(currentStatPoints);
		strength = enemyStats[0];
		agility = enemyStats[1];
		intelligence = enemyStats[2];

		weapon = pickEnemyWeapon(agility, intelligence);

		return new Enemy(enenmyName, strength, agility, intelligence, weapon);
	}

	private Weapon pickEnemyWeapon(int agility, int intelligence) {
		// roll a number to the size of weapon List and check stats that it can be
		// equipped
		while (true) {
			int weaponIndex = random.nextInt(weaponList.size());
			if (agility < weaponList.get(weaponIndex).getAgilityRequired()
					|| intelligence < weaponList.get(weaponIndex).getIntelligenceRequired()) {
				continue;
			} else {
				return weaponList.get(weaponIndex);
			}
		}
	}

	private int[] pickEnemyStatsWeak(int currentStatPoints) {
		int strength;
		int agility;
		int intelligence;

		while (true) {
			// plus 1 so a stat cannot be 0
			strength = random.nextInt(currentStatPoints) + 1;
			agility = random.nextInt(currentStatPoints) + 1;
			intelligence = random.nextInt(currentStatPoints) + 1;
			if (strength + agility + intelligence > currentStatPoints) {
				continue;
			} else {
				break;
			}
		}
		return new int[] { strength, agility, intelligence };
	}

	private int[] pickEnemyStatsCustomExactLevel(int currentStatPoints) {
		int strength;
		int agility;
		int intelligence;

		while (true) {
			// plus 1 so a stat cannot be 0
			strength = random.nextInt(currentStatPoints) + 1;
			agility = random.nextInt(currentStatPoints) + 1;
			intelligence = random.nextInt(currentStatPoints) + 1;
			if (strength + agility + intelligence != currentStatPoints) {
				continue;
			} else {
				break;
			}
		}
		return new int[] { strength, agility, intelligence };
	}

}
