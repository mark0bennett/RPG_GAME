package rpggame.utils;

import java.util.List;
import java.util.Random;

import rpggame.enemy.Enemy;
import rpggame.player.Player;
import rpggame.weapon.BareHands;
import rpggame.weapon.Weapon;

public class EnemyFactory {

	// all weapons in a List
	private static List<Weapon> weaponList = AllWeaponsListCreator.createListAllWeaponsFromCsvFile();
	// could have the creator itself here instead?
//	private static AllWeaponsListCreator allWeaponsListCreator;
	
	private static Random random = new Random();

	public static Enemy createWeakEnemy(Player player) {
		int currentStatPoints = player.getCurrentTotalStatPoints();
		int strength = 0;
		int agility = 0;
		int intelligence = 0;
		Weapon weapon = new BareHands();

		int[] enemyStats = pickEnemyStatsWeak(currentStatPoints);
		strength = enemyStats[0];
		agility = enemyStats[1];
		intelligence = enemyStats[2];

		weapon = pickEnemyWeapon(agility, intelligence);
		
		return new Enemy(strength, agility, intelligence, weapon);
	}

	// TODO: maybe another class EqualEnemy that extends Enemy? so it can toString
	// something different etc

	public static Enemy createEnemyCustomLevel(Player player, int level) {
		int currentStatPoints = player.getCurrentTotalStatPoints() + level;
		int strength = 0;
		int agility = 0;
		int intelligence = 0;
		Weapon weapon = new BareHands();

		int[] enemyStats = pickEnemyStatsCustomExactLevel(currentStatPoints);
		strength = enemyStats[0];
		agility = enemyStats[1];
		intelligence = enemyStats[2];

		weapon = pickEnemyWeapon(agility, intelligence);

		return new Enemy(strength, agility, intelligence, weapon);
	}

	private static Weapon pickEnemyWeapon(int agility, int intelligence) {
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
	
	private static int[] pickEnemyStatsWeak(int currentStatPoints) {
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
		return new int[] {strength, agility, intelligence};
	}
	
	private static int[] pickEnemyStatsCustomExactLevel(int currentStatPoints) {
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
		return new int[] {strength, agility, intelligence};
	}

}
