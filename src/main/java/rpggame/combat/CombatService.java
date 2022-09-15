package rpggame.combat;

import java.util.Scanner;

import rpggame.enemy.Enemy;
import rpggame.player.Player;
import rpggame.story.StoryTellerService;
import rpggame.utils.EnemyFactory;
import rpggame.weapon.Weapon;

public class CombatService {

	private final Scanner scanner;
	private final EnemyFactory enemyFactory;

	public CombatService() {
		this.scanner = new Scanner(System.in);
		this.enemyFactory = new EnemyFactory();
	}

	// TODO: CREATE NEW ENEMIES IN HERE SOEMWHERE
	public boolean oneEnemy(Player player, int level, boolean withCrits) throws InterruptedException {
		boolean wonBattle = false;

		printCombatHasBegun();
		player.printBackpack();
		pickWeapon(player);
		// TODO: ENEMY CREATED HERE - CombatService needs its own EnemyFactory
		Enemy enemy = enemyFactory.createEnemyCustomLevel(player, level);
		printVersusText(player, enemy);

		// then into a while loop to attack and count hp(strength) during combat
		int playerStrengthBeforeCombat = player.getStrength();
		System.out.println("Combat is automated for now...");

		if (!withCrits) {
			wonBattle = attacksWithNoCrits(player, enemy);
		}

		if (withCrits) {
			wonBattle = attacksWithCrits(player, enemy);
		}

		// reset player strength to what it was before combat
		if (player.getStrength() < playerStrengthBeforeCombat) {
			System.out.println("Your strength has been restored");
			player.setStrength(playerStrengthBeforeCombat);
		}

		if (wonBattle) {
			showAndAddDroppedWeapon(player, enemy);
			System.out.println("You can now increase one of your stats by 1");
			increaseStats(player);
			printWinText(player);
		}

		if (!wonBattle) {
			printLossText();
		}
		return wonBattle;
	}

	public boolean twoEnemies(Player player, int level, int level2, boolean withCrits) throws InterruptedException {
		boolean wonBattle = false;

		printCombatHasBegun();
		player.printBackpack();
		pickWeapon(player);
		Enemy enemy = enemyFactory.createEnemyCustomLevel(player, level);
		Enemy enemy2 = enemyFactory.createEnemyCustomLevel(player, level2);
		printVersusTextTwoEnemies(player, enemy, enemy2);

		int playerStrengthBeforeCombat = player.getStrength();
		System.out.println("Combat is automated for now...");

		// TODO: Need to create attackWithNoCritsTwoEnemies
		if (!withCrits) {
			wonBattle = attacksWithNoCrits(player, enemy);
		}

		if (withCrits) {
			wonBattle = attacksWithCritsTwoEnemies(player, enemy, enemy2);
		}

		// reset player strength to what it was before combat
		if (player.getStrength() < playerStrengthBeforeCombat) {
			System.out.println("Your strength has been restored");
			player.setStrength(playerStrengthBeforeCombat);
		}

		if (wonBattle) {
			showAndAddDroppedWeapon(player, enemy);
			// TODO: this is lazy, reusing same method for 2nd enemy
			showAndAddDroppedWeapon(player, enemy2);
			StoryTellerService.nextLine(scanner);
			System.out.println("You can now increase one of your stats by 1");
			increaseStats(player);
			printWinText(player);
		}

		if (!wonBattle) {
			printLossText();
		}
		return wonBattle;
	}

	private void printVersusTextTwoEnemies(Player player, Enemy enemy, Enemy enemy2) throws InterruptedException {
		// show stats of you and your enemy
		System.out.println("--------------------------");
		System.out.println(player);
		StoryTellerService.nextLine(scanner);
		System.out.println("vs");
		StoryTellerService.nextLine(scanner);
		System.out.println(enemy);
		StoryTellerService.nextLine(scanner);
		System.out.println("and");
		System.out.println(enemy2);
		System.out.println("--------------------------");
	}

	private void printCombatHasBegun() {
		System.out.println("--------------------------");
		System.out.println("COMBAT HAS BEGUN!!!");
		System.out.println("--------------------------");
	}

	private void pickWeapon(Player player) {
		int choice = 0;
		while (true) {
			System.out.println("Which weapon would you like to use (type a number)");
			// pick a number
			try {
				choice = Integer.valueOf(scanner.nextLine());
				// throw exception if number not typed in
			} catch (Exception e) {
				System.out.println("Type a number that exists in your backpack");
				continue;
			}
			// if number doesnt exist in backpack, continue
			if (choice > player.getBackPack().size() || choice <= 0) {
				System.out.println("No weapon exists there");
				continue;
				// if your agility is too low, continue
			} else if (player.getAgility() < player.getBackPack().get(choice - 1).getAgilityRequired()) {
				System.out.println("Your Agility is too low for this weapon");
				continue;
				// if you intelligence is too low, continue
			} else if (player.getIntelligence() < player.getBackPack().get(choice - 1).getIntelligenceRequired()) {
				System.out.println("Your Intelligence is too low for this weapon");
				continue;
			} else {
				// else equip weapon
				player.setWeapon(player.getBackPack().get(choice - 1));
				System.out.println("Weapon Selected");
				break;
			}
		}
	}

	private void printVersusText(Player player, Enemy enemy) {
		// show stats of you and your enemy
		System.out.println("--------------------------");
		System.out.println(player);
		StoryTellerService.nextLine(scanner);
		System.out.println("vs");
		StoryTellerService.nextLine(scanner);
		System.out.println(enemy);
		System.out.println("--------------------------");
		StoryTellerService.nextLine(scanner);
	}

	private boolean attacksWithNoCrits(Player player, Enemy enemy) {
		boolean wonBattle = false;
		while (true) {
			// you attack
			System.out.println("You attack for " + player.attack() + " damage!");
			// enemy strength changed from attack
			enemy.setStrength(enemy.getStrength() - player.attack());
			StoryTellerService.nextLine(scanner);
			// check if enemy is dead
			if (enemy.getStrength() < 1) {
				System.out.println("You Won!");
				wonBattle = true;
				break;
			}
			// enemy attacks
			System.out.println("Enemy attacks for " + enemy.attack() + " damage!");
			// enemy strength changed from attack
			player.setStrength(player.getStrength() - enemy.attack());
			StoryTellerService.nextLine(scanner);
			// check if enemy is dead
			if (player.getStrength() < 1) {
				StoryTellerService.nextLine(scanner);
				System.out.println("You Lost!");
				wonBattle = false;
				break;
			}
			StoryTellerService.nextLine(scanner);
			System.out.println("--------------------------");
			StoryTellerService.nextLine(scanner);
			System.out.println(player);
			StoryTellerService.nextLine(scanner);
			System.out.println(enemy);
			StoryTellerService.nextLine(scanner);
			System.out.println("--------------------------");
			StoryTellerService.nextLine(scanner);
		}
		return wonBattle;
	}

	private boolean attacksWithCrits(Player player, Enemy enemy) {
		boolean wonBattle = false;
		while (true) {
			// for clarity
			int[] playerAttackNumbers = player.attackCrit();
			int playerAttack = playerAttackNumbers[0];
			int playerCrit = playerAttackNumbers[1];
			// you attack
			System.out.println("You attack for " + playerAttack + " damage plus " + playerCrit + " Crit!");
			// enemy strength changed from attack
			enemy.setStrength(enemy.getStrength() - (playerAttack + playerCrit));
			StoryTellerService.nextLine(scanner);
			// check if enemy is dead
			if (enemy.getStrength() < 1) {
				System.out.println("You Won!");
				wonBattle = true;
				break;
			}
			// enemy attacks
			// for clarity
			int[] enemyAttackNumbers = enemy.attackCrit();
			int enemyAttack = enemyAttackNumbers[0];
			int enemyCrit = enemyAttackNumbers[1];
			System.out.println("Enemy attacks for " + enemyAttack + " damage plus " + enemyCrit + " Crit!");
			// enemy strength changed from attack
			player.setStrength(player.getStrength() - (enemyAttack + enemyCrit));
			StoryTellerService.nextLine(scanner);
			// check if enemy is dead
			if (player.getStrength() < 1) {
				StoryTellerService.nextLine(scanner);
				System.out.println("You Lost!");
				wonBattle = false;
				break;
			}
			StoryTellerService.nextLine(scanner);
			System.out.println("--------------------------");
			StoryTellerService.nextLine(scanner);
			System.out.println(player);
			StoryTellerService.nextLine(scanner);
			System.out.println(enemy);
			StoryTellerService.nextLine(scanner);
			System.out.println("--------------------------");
			StoryTellerService.nextLine(scanner);
		}
		return wonBattle;
	}

	private void showAndAddDroppedWeapon(Player player, Enemy enemy) throws InterruptedException {
		// enemy drops weapon and auto added to your backpack
		StoryTellerService.nextLine(scanner);
		System.out.println("--------------------------");
		StoryTellerService.nextLine(scanner);
		System.out.println("The Enemy dropped its weapon...");
		Weapon droppedWeapon = enemy.dropWeapon();
		StoryTellerService.nextLine(scanner);
		System.out.println(droppedWeapon);
		StoryTellerService.nextLine(scanner);
		System.out.println("Agility Required: " + droppedWeapon.getAgilityRequired());
		StoryTellerService.nextLine(scanner);
		System.out.println("Intelligence Required: " + droppedWeapon.getIntelligenceRequired());
		StoryTellerService.nextLine(scanner);
		System.out.println("Weapon Type: " + droppedWeapon.getWeaponType());
		StoryTellerService.nextLine(scanner);
		System.out.println("--------------------------");
		StoryTellerService.nextLine(scanner);
		// TODO: STILL DOUBLE ADDING WEAPONS WHEN THEY ARE ALREADY IN BACKPACK - doing
		// it manually for now
		player.addWeaponToBackpack(droppedWeapon);
		System.out.println("--------------------------");
		player.printBackpack();
		System.out.println("--------------------------");
	}

	private void increaseStats(Player player) {
		StoryTellerService.printPlayer(player);
		while (true) {
			StoryTellerService.nextLine(scanner);
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

	private void printWinText(Player player) {
		StoryTellerService.printPlayer(player);
		StoryTellerService.nextLine(scanner);
		System.out.println("...and you continue on your journey");
		System.out.println("--------------------------");
	}

	private void printLossText() {
		System.out.println("--------------------------");
		StoryTellerService.nextLine(scanner);
		System.out.println("You learn from this loss and continue...");
		System.out.println("--------------------------");
	}

	private boolean attacksWithCritsTwoEnemies(Player player, Enemy enemy, Enemy enemy2) throws InterruptedException {
		boolean wonBattle = false;
		boolean firstEnemyDead = false;
		while (true) {
			// TODO: just attacks the first enemy then the second for now, maybe add a
			// choice later
			StoryTellerService.nextLine(scanner);
			// for clarity
			int[] playerAttackNumbers = player.attackCrit();
			int playerAttack = playerAttackNumbers[0];
			int playerCrit = playerAttackNumbers[1];
			// you attack
			System.out.println("You attack for " + playerAttack + " damage plus " + playerCrit + " Crit!");
			// enemy strength changed from attack
			enemy.setStrength(enemy.getStrength() - (playerAttack + playerCrit));
			StoryTellerService.nextLine(scanner);
			// check if first enemy is dead
			if (enemy.getStrength() < 1) {
				System.out.println("You Killed the first Enemy!");
				firstEnemyDead = true;
				break;
			}
			// enemy attacks
			// for clarity
			int[] enemyAttackNumbers = enemy.attackCrit();
			int enemyAttack = enemyAttackNumbers[0];
			int enemyCrit = enemyAttackNumbers[1];
			System.out.println("Enemy attacks for " + enemyAttack + " damage plus " + enemyCrit + " Crit!");
			// enemy strength changed from attack
			player.setStrength(player.getStrength() - (enemyAttack + enemyCrit));
			StoryTellerService.nextLine(scanner);
			// check if enemy is dead
			if (player.getStrength() < 1) {
				StoryTellerService.nextLine(scanner);
				System.out.println("You Lost!");
				wonBattle = false;
				break;
			}
			StoryTellerService.nextLine(scanner);
			System.out.println("--------------------------");
			StoryTellerService.nextLine(scanner);
			System.out.println(player);
			StoryTellerService.nextLine(scanner);
			System.out.println(enemy);
			StoryTellerService.nextLine(scanner);
			System.out.println(enemy2);
			StoryTellerService.nextLine(scanner);
			System.out.println("--------------------------");
			StoryTellerService.nextLine(scanner);
		}

		if (firstEnemyDead) {
			while (true) {
				// for clarity
				int[] playerAttackNumbers = player.attackCrit();
				int playerAttack = playerAttackNumbers[0];
				int playerCrit = playerAttackNumbers[1];
				// you attack
				System.out.println(
						"You attack the second enemy for " + playerAttack + " damage plus " + playerCrit + " Crit!");
				// enemy strength changed from attack
				enemy2.setStrength(enemy2.getStrength() - (playerAttack + playerCrit));
				StoryTellerService.nextLine(scanner);
				// check if second enemy is dead
				if (enemy2.getStrength() < 1) {
					System.out.println("You Killed the second Enemy and Won!");
					wonBattle = true;
					break;
				}
				// enemy attacks
				// for clarity
				int[] enemy2AttackNumbers = enemy2.attackCrit();
				int enemy2Attack = enemy2AttackNumbers[0];
				int enemy2Crit = enemy2AttackNumbers[1];
				System.out
						.println("Second Enemy attacks for " + enemy2Attack + " damage plus " + enemy2Crit + " Crit!");
				// enemy strength changed from attack
				player.setStrength(player.getStrength() - (enemy2Attack + enemy2Crit));
				StoryTellerService.nextLine(scanner);
				// check if enemy is dead
				if (player.getStrength() < 1) {
					StoryTellerService.nextLine(scanner);
					System.out.println("You Lost!");
					wonBattle = false;
					break;
				}
				StoryTellerService.nextLine(scanner);
				System.out.println("--------------------------");
				StoryTellerService.nextLine(scanner);
				System.out.println(player);
				StoryTellerService.nextLine(scanner);
				System.out.println(enemy2);
				StoryTellerService.nextLine(scanner);
				System.out.println("--------------------------");
				StoryTellerService.nextLine(scanner);
			}
		}
		return wonBattle;
	}

}
