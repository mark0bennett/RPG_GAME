package characters.utils;

import java.util.List;
import java.util.Random;

import characters.enemy.Enemy;
import characters.player.Player;
import weapons.BareHands;
import weapons.Weapon;
import weapons.utils.AllWeaponsListCreator;

public class EnemyFactory {

	// all weapons in a List
	private static List<Weapon> weaponList = AllWeaponsListCreator.createListAllWeaponsFromCsvFile();
	// could have the creator itself here instead?
//	private static AllWeaponsListCreator allWeaponsListCreator;

	public static Enemy createEnemy(Player player) {
		int currentStatPoints = player.getCurrentTotalStatPoints();
		int strength = 0;
		int agility = 0;
		int intelligence = 0;
		Weapon weapon = new BareHands();

		Random random = new Random();

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

		// roll a number to the size of weapon List and check stats that it can be
		// equipped
		while (true) {
			int weaponIndex = random.nextInt(weaponList.size());
			if (agility < weaponList.get(weaponIndex).getAgilityRequired()
					|| intelligence < weaponList.get(weaponIndex).getIntelligenceRequired()) {
				continue;
			} else {
				weapon = weaponList.get(weaponIndex);
				break;
			}
		}

		return new Enemy(strength, agility, intelligence, weapon);
	}

	//TODO: maybe another class EqualEnemy that extends Enemy? so it can toString something different etc
	public static Enemy createEqualEnemy(Player player) {
		int currentStatPoints = player.getCurrentTotalStatPoints();
		int strength = 0;
		int agility = 0;
		int intelligence = 0;
		Weapon weapon = new BareHands();

		Random random = new Random();

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

		// roll a number to the size of weapon List and check stats that it can be
		// equipped
		while (true) {
			int weaponIndex = random.nextInt(weaponList.size());
			if (agility < weaponList.get(weaponIndex).getAgilityRequired()
					|| intelligence < weaponList.get(weaponIndex).getIntelligenceRequired()) {
				continue;
			} else {
				weapon = weaponList.get(weaponIndex);
				break;
			}
		}

		return new Enemy(strength, agility, intelligence, weapon);
	}

}
