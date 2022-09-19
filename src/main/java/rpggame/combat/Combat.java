package rpggame.combat;

import java.util.Random;
import java.util.Scanner;

import rpggame.enemy.Enemy;
import rpggame.person.Person;
import rpggame.player.Player;
import rpggame.story.StoryTeller;
import rpggame.utils.EnemyFactory;
import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public class Combat {

	private final Scanner scanner;
	private final EnemyFactory enemyFactory;
	private final Random random;

	public Combat() {
		this.scanner = new Scanner(System.in);
		this.enemyFactory = new EnemyFactory();
		this.random = new Random();
	}

	public boolean oneEnemy(Player player, String name, int level, boolean withCrits) {
		boolean wonBattle = false;
		// print combat text and create enemy
		printCombatHasBegun();
		pickWeapon(player);
		Enemy enemy = enemyFactory.createEnemyCustomLevel(player, name, level);
		printVersusText(player, enemy);
		// for restoring health after battle
		int playerStrengthBeforeCombat = player.getStrength();
		// for dropping money based on enemy strength
		int enemyStrengthBeforeCombat = enemy.getStrength();
		// based on boolean passed in, battle with or without crits
		wonBattle = battleOneEnemy(player, enemy, withCrits);
		// reset player strength to what it was before combat
		if (player.getStrength() < playerStrengthBeforeCombat) {
			System.out.println("Your strength has been restored");
			player.setStrength(playerStrengthBeforeCombat);
		}
		// if you won, drop weapons and money and increase stats
		if (wonBattle) {
			showAndAddDroppedWeaponAndMoney(player, enemy, enemyStrengthBeforeCombat);
			increaseStats(player);
			printWinText(player);
		} else {
			printLossText();
		}
		return wonBattle;
	}

	public boolean twoEnemies(Player player, String name1, int level, String name2, int level2, boolean withCrits) {
		boolean wonBattle = false;
		// print combat text and create enemy
		printCombatHasBegun();
		pickWeapon(player);
		Enemy enemy = enemyFactory.createEnemyCustomLevel(player, name1, level);
		Enemy enemy2 = enemyFactory.createEnemyCustomLevel(player, name2, level2);
		printVersusTextTwoEnemies(player, enemy, enemy2);

		// for restoring health after battle
		int playerStrengthBeforeCombat = player.getStrength();
		// for dropping money based on enemy strength
		int enemyStrengthBeforeCombat = enemy.getStrength();
		// based on boolean passed in, battle with or without crits
		wonBattle = battleTwoEnemies(player, enemy, enemy2, withCrits);
		// reset player strength to what it was before combat
		if (player.getStrength() < playerStrengthBeforeCombat) {
			System.out.println("Your strength has been restored");
			player.setStrength(playerStrengthBeforeCombat);
		}
		// if you won, drop weapons and money and increase stats
		if (wonBattle) {
			showAndAddDroppedWeaponAndMoney(player, enemy, enemyStrengthBeforeCombat);
			showAndAddDroppedWeaponAndMoney(player, enemy2, enemyStrengthBeforeCombat);
			increaseStats(player);
			printWinText(player);
		} else {
			printLossText();
		}
		return wonBattle;
	}

	public void printCombatHasBegun() {
		StoryTeller.printLineBreak();
		System.out.println("COMBAT HAS BEGUN!!!");
		StoryTeller.printLineBreak();
	}

	private void pickWeapon(Player player) {
		int choice = 0;
		//before each battle set weapon to 'no weapon'
		Weapon noWeapon = new Weapon("No Weapon Selected", 0, 0, 0, WeaponType.INTELLIGENCE, 0);
		player.setWeapon(noWeapon);
		while (true) {
			System.out.println(player);
			player.printBackpack();
			System.out.println("Which weapon would you like to use (type a number)");
			// pick a number
			try {
				choice = Integer.valueOf(scanner.nextLine());
				// throw exception if number not typed in
			} catch (Exception e) {
				System.out.println("Type a number that exists in your backpack");
				StoryTeller.printLineBreak();
				continue;
			}
			// if number doesnt exist in backpack, continue
			if (choice > player.getBackPack().size() || choice <= 0) {
				System.out.println("No weapon exists there");
				StoryTeller.printLineBreak();
				continue;
				// if your agility is too low, continue
			} else if (player.getAgility() < player.getBackPack().get(choice - 1).getAgilityRequired()) {
				System.out.println("Your Agility is too low for this weapon");
				StoryTeller.printLineBreak();
				continue;
				// if you intelligence is too low, continue
			} else if (player.getIntelligence() < player.getBackPack().get(choice - 1).getIntelligenceRequired()) {
				System.out.println("Your Intelligence is too low for this weapon");
				StoryTeller.printLineBreak();
				continue;
			} else {
				// else equip weapon
				player.setWeapon(player.getBackPack().get(choice - 1));
				StoryTeller.printLineBreak();
				System.out.println("Weapon Selected");
				StoryTeller.printLineBreak();
				break;
			}
		}
	}

	private void showAndAddDroppedWeaponAndMoney(Player player, Enemy enemy, int enemyStrengthBeforeCombat) {
		// enemy drops weapon and auto added to your backpack
		StoryTeller.printLineBreak();
		// enemy drops randomised money between 0 and strength
		int moneyDropped = random.nextInt(enemyStrengthBeforeCombat);
		player.setNixonBucks(player.getNixonBucks() + moneyDropped);
		if (moneyDropped == 1) {
			System.out.println(moneyDropped + " NixonBuck collected");
		} else {
			System.out.println(moneyDropped + " NixonBucks collected");
		}
		StoryTeller.nextLine(scanner);
		System.out.println("-- " + enemy.getName() + " dropped their weapon --");
		Weapon droppedWeapon = enemy.dropWeapon();
		System.out.println(droppedWeapon.getName());
		System.out.println("Agility Required: " + droppedWeapon.getAgilityRequired());
		System.out.println("Intelligence Required: " + droppedWeapon.getIntelligenceRequired());
		System.out.println("Weapon Type: " + droppedWeapon.getWeaponType());
		StoryTeller.printLineBreak();
		StoryTeller.nextLine(scanner);

		boolean alreadyInBackpack = player.checkForWeaponInBackpack(droppedWeapon);
		if (!alreadyInBackpack) {
			player.addWeaponToBackpack(droppedWeapon);
			System.out.println("Weapon added to backpack");
			StoryTeller.nextLine(scanner);
		} else {
			System.out.println("You already have this weapon");
			StoryTeller.nextLine(scanner);
		}
	}

	private void increaseStats(Player player) {
		System.out.println("You can now increase one of your stats by 1");
		while (true) {
			StoryTeller.printPlayer(player);
			System.out.println("Type: 's' - to increase Strength");
			System.out.println("Type: 'a' - to increase Aglity");
			System.out.println("Type: 'i' - to increase Intelligence");

			String statToIncrease = scanner.nextLine().toUpperCase();
			if (statToIncrease.equals("S")) {
				player.setStrength(player.getStrength() + 1);
				break;
			} else if (statToIncrease.equals("A")) {
				player.setAgility(player.getAgility() + 1);
				break;
			} else if (statToIncrease.equals("I")) {
				player.setIntelligence(player.getIntelligence() + 1);
				break;
			} else {
				continue;
			}
		}
	}

	private boolean battleOneEnemy(Player player, Enemy enemy, boolean withCrits) {
		boolean wonBattle = false;
		while (true) {
			// you attack depending on withCrits
			// attacker is first variable passed in
			if (withCrits) {
				attackSequenceWithCrits(player, enemy);
			} else {
				attackSequenceNoCrits(player, enemy);
			}
			StoryTeller.nextLine(scanner);
			// check if enemy is dead
			if (enemy.getStrength() < 1) {
				System.out.println("You Won!");
				wonBattle = true;
				break;
			}
			// enemy attacks depending on withCrits
			// attacker is first variable passed in
			if (withCrits) {
				attackSequenceWithCrits(enemy, player);
			} else {
				attackSequenceNoCrits(enemy, player);
			}
			StoryTeller.nextLine(scanner);
			// check if enemy is dead
			if (player.getStrength() < 1) {
				System.out.println("You Lost!");
				wonBattle = false;
				break;
			}
			printPlayerAndOneEnemy(player, enemy);
		}
		return wonBattle;
	}

	private boolean battleTwoEnemies(Player player, Enemy enemy, Enemy enemy2, boolean withCrits) {
		boolean wonBattle = false;
		boolean firstEnemyDead = false;
		while (true) {
			// you attack depending on withCrits
			// attacker is first variable passed in
			if (withCrits) {
				attackSequenceWithCrits(player, enemy);
			} else {
				attackSequenceNoCrits(player, enemy);
			}
			StoryTeller.nextLine(scanner);
			// check if first enemy is dead
			if (enemy.getStrength() < 1) {
				System.out.println("You Killed " + enemy.getName());
				StoryTeller.nextLine(scanner);
				firstEnemyDead = true;
				break;
			}
			// enemy attacks depending on withCrits
			// attacker is first variable passed in
			if (withCrits) {
				attackSequenceWithCrits(enemy, player);
			} else {
				attackSequenceNoCrits(enemy, player);
			}
			StoryTeller.nextLine(scanner);
			// check if enemy is dead
			if (player.getStrength() < 1) {
				System.out.println("You Lost!");
				wonBattle = false;
				break;
			}
			printPlayerAndTwoEnemies(player, enemy, enemy2);
		}

		if (firstEnemyDead) {
			while (true) {
				// you attack depending on withCrits
				// attacker is first variable passed in
				if (withCrits) {
					attackSequenceWithCrits(player, enemy2);
				} else {
					attackSequenceNoCrits(player, enemy2);
				}
				StoryTeller.nextLine(scanner);
				// check if second enemy is dead
				if (enemy2.getStrength() < 1) {
					System.out.println("You Killed " + enemy2.getName() + " and Won!");
					wonBattle = true;
					break;
				}
				// enemy attacks depending on withCrits
				// attacker is first variable passed in
				if (withCrits) {
					attackSequenceWithCrits(enemy2, player);
				} else {
					attackSequenceNoCrits(enemy2, player);
				}
				StoryTeller.nextLine(scanner);
				// check if enemy is dead
				if (player.getStrength() < 1) {
					System.out.println("You Lost!");
					wonBattle = false;
					break;
				}
				printPlayerAndOneEnemy(player, enemy2);
			}
		}
		return wonBattle;
	}

	private void attackSequenceNoCrits(Person attacker, Person defender) {
		int attackDamage = attacker.getAttackDamage();
		System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + attackDamage + " damage!");
		defender.setStrength(defender.getStrength() - attackDamage);
	}

	private void attackSequenceWithCrits(Person attacker, Person defender) {
		int[] attackNumbers = attacker.getAttackCritDamage();
		int attackDamage = attackNumbers[0];
		int critDamage = attackNumbers[1];
		// you attack
		System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + attackDamage
				+ " damage plus " + critDamage + " Crit!");
		// enemy strength changed from attack
		defender.setStrength(defender.getStrength() - (attackDamage + critDamage));
	}

	public void printVersusText(Player player, Enemy enemy) {
		StoryTeller.printLineBreak();
		System.out.println(player);
		System.out.println("vs");
		System.out.println(enemy);
		StoryTeller.printLineBreak();
		StoryTeller.nextLine(scanner);
	}

	private void printVersusTextTwoEnemies(Player player, Enemy enemy, Enemy enemy2) {
		StoryTeller.printLineBreak();
		System.out.println(player);
		System.out.println("vs");
		System.out.println(enemy);
		System.out.println("and");
		System.out.println(enemy2);
		StoryTeller.printLineBreak();
		StoryTeller.nextLine(scanner);
	}

	private void printPlayerAndOneEnemy(Player player, Enemy enemy) {
		StoryTeller.printLineBreak();
		System.out.println(player);
		System.out.println(enemy);
		StoryTeller.printLineBreak();
		StoryTeller.nextLine(scanner);
	}

	private void printPlayerAndTwoEnemies(Player player, Enemy enemy, Enemy enemy2) {
		StoryTeller.printLineBreak();
		System.out.println(player);
		System.out.println(enemy);
		System.out.println(enemy2);
		StoryTeller.printLineBreak();
		StoryTeller.nextLine(scanner);
	}

	private void printWinText(Player player) {
		StoryTeller.printPlayer(player);
		System.out.println("...and you continue on your journey");
		StoryTeller.printLineBreak();
		StoryTeller.nextLine(scanner);
	}

	private void printLossText() {
		StoryTeller.printLineBreak();
		System.out.println("You learn from this loss and continue...");
		StoryTeller.printLineBreak();
		StoryTeller.nextLine(scanner);
	}

}
