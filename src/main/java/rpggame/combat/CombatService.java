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

	public boolean oneEnemy(Player player, String name, int level, boolean withCrits) throws InterruptedException {
		boolean wonBattle = false;

		printCombatHasBegun();
		System.out.println(player);
		player.printBackpack();
		pickWeapon(player);
		Enemy enemy = enemyFactory.createEnemyCustomLevel(player, name, level);
		printVersusText(player, enemy);

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

	public boolean twoEnemies(Player player, String name1, int level, String name2, int level2, boolean withCrits)
			throws InterruptedException {
		boolean wonBattle = false;

		printCombatHasBegun();
		player.printBackpack();
		pickWeapon(player);
		Enemy enemy = enemyFactory.createEnemyCustomLevel(player, name1, level);
		Enemy enemy2 = enemyFactory.createEnemyCustomLevel(player, name2, level2);
		printVersusTextTwoEnemies(player, enemy, enemy2);

		int playerStrengthBeforeCombat = player.getStrength();
		System.out.println("Combat is automated for now...");

		// TODO: Need to create attackWithNoCritsTwoEnemies
		if (!withCrits) {
			wonBattle = attacksWithNoCritsTwoEnemies(player, enemy, enemy2);
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
		System.out.println("--------------------------");
		System.out.println(player);
		System.out.println("vs");
		System.out.println(enemy);
		System.out.println("--------------------------");
		StoryTellerService.nextLine(scanner);
	}

	private void printVersusTextTwoEnemies(Player player, Enemy enemy, Enemy enemy2) throws InterruptedException {
		System.out.println("--------------------------");
		System.out.println(player);
		System.out.println("vs");
		System.out.println(enemy);
		System.out.println("and");
		System.out.println(enemy2);
		System.out.println("--------------------------");
		StoryTellerService.nextLine(scanner);
	}

	private void showAndAddDroppedWeapon(Player player, Enemy enemy) throws InterruptedException {
		// enemy drops weapon and auto added to your backpack
		System.out.println("--------------------------");
		StoryTellerService.nextLine(scanner);
		System.out.println(enemy.getName() + " dropped their weapon...");
		Weapon droppedWeapon = enemy.dropWeapon();
		System.out.println(droppedWeapon);
		System.out.println("Agility Required: " + droppedWeapon.getAgilityRequired());
		System.out.println("Intelligence Required: " + droppedWeapon.getIntelligenceRequired());
		System.out.println("Weapon Type: " + droppedWeapon.getWeaponType());
		System.out.println("--------------------------");
		StoryTellerService.nextLine(scanner);
		// TODO: STILL DOUBLE ADDING WEAPONS WHEN THEY ARE ALREADY IN BACKPACK - doing
		// it manually for now
		boolean alreadyInBackpack = player.checkForWeaponInBackpack(droppedWeapon);
		if (!alreadyInBackpack) {
			player.addWeaponToBackpack(droppedWeapon);
			System.out.println("Weapon added to backpack");
			StoryTellerService.nextLine(scanner);
		} else {
			System.out.println("You already have this weapon");
			StoryTellerService.nextLine(scanner);
		}
		System.out.println("--------------------------");
		player.printBackpack();
		System.out.println("--------------------------");
	}

	private void increaseStats(Player player) {
		while (true) {
			StoryTellerService.printPlayer(player);
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

	private boolean attacksWithNoCrits(Player player, Enemy enemy) {
		boolean wonBattle = false;
		while (true) {
			// you attack
			System.out.println("You attack " + enemy.getName() + " for " + player.attack() + " damage!");
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
			System.out.println(enemy.getName() + " attacks for " + enemy.attack() + " damage!");
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
			printPlayerAndOneEnemy(player, enemy);
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
			System.out.println(
					"You attack " + enemy.getName() + " for " + playerAttack + " damage plus " + playerCrit + " Crit!");
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
			System.out
					.println(enemy.getName() + " attacks for " + enemyAttack + " damage plus " + enemyCrit + " Crit!");
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
			printPlayerAndOneEnemy(player, enemy);
		}
		return wonBattle;
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
			System.out.println(
					"You attack " + enemy.getName() + " for " + playerAttack + " damage plus " + playerCrit + " Crit!");
			// enemy strength changed from attack
			enemy.setStrength(enemy.getStrength() - (playerAttack + playerCrit));
			StoryTellerService.nextLine(scanner);
			// check if first enemy is dead
			if (enemy.getStrength() < 1) {
				System.out.println("You Killed " + enemy.getName());
				firstEnemyDead = true;
				break;
			}
			// enemy attacks
			// for clarity
			int[] enemyAttackNumbers = enemy.attackCrit();
			int enemyAttack = enemyAttackNumbers[0];
			int enemyCrit = enemyAttackNumbers[1];
			System.out
					.println(enemy.getName() + " attacks for " + enemyAttack + " damage plus " + enemyCrit + " Crit!");
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
			printPlayerAndTwoEnemies(player, enemy, enemy2);
		}

		if (firstEnemyDead) {
			while (true) {
				// for clarity
				int[] playerAttackNumbers = player.attackCrit();
				int playerAttack = playerAttackNumbers[0];
				int playerCrit = playerAttackNumbers[1];
				// you attack
				System.out.println("You attack " + enemy2.getName() + " for " + playerAttack + " damage plus "
						+ playerCrit + " Crit!");
				// enemy strength changed from attack
				enemy2.setStrength(enemy2.getStrength() - (playerAttack + playerCrit));
				StoryTellerService.nextLine(scanner);
				// check if second enemy is dead
				if (enemy2.getStrength() < 1) {
					System.out.println("You Killed " + enemy2.getName() + " and Won!");
					wonBattle = true;
					break;
				}
				// enemy attacks
				// for clarity
				int[] enemy2AttackNumbers = enemy2.attackCrit();
				int enemy2Attack = enemy2AttackNumbers[0];
				int enemy2Crit = enemy2AttackNumbers[1];
				System.out.println(
						enemy2.getName() + " attacks for " + enemy2Attack + " damage plus " + enemy2Crit + " Crit!");
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
				printPlayerAndOneEnemy(player, enemy2);
			}
		}
		return wonBattle;
	}

	private boolean attacksWithNoCritsTwoEnemies(Player player, Enemy enemy, Enemy enemy2) throws InterruptedException {
		boolean wonBattle = false;
		boolean firstEnemyDead = false;
		while (true) {
			// TODO: just attacks the first enemy then the second for now, maybe add a
			// choice later
			StoryTellerService.nextLine(scanner);
			int playerAttack = player.attack();
			// you attack
			System.out.println("You attack " + enemy.getName() + " for " + playerAttack + " damage!");
			// enemy strength changed from attack
			enemy.setStrength(enemy.getStrength() - playerAttack);
			StoryTellerService.nextLine(scanner);
			// check if first enemy is dead
			if (enemy.getStrength() < 1) {
				System.out.println("You Killed " + enemy.getName());
				firstEnemyDead = true;
				break;
			}
			// enemy attacks
			int enemyAttack = enemy.attack();
			System.out.println(enemy.getName() + " attacks for " + enemyAttack + " damage!");
			// enemy strength changed from attack
			player.setStrength(player.getStrength() - enemyAttack);
			StoryTellerService.nextLine(scanner);
			// check if enemy is dead
			if (player.getStrength() < 1) {
				StoryTellerService.nextLine(scanner);
				System.out.println("You Lost!");
				wonBattle = false;
				break;
			}
			printPlayerAndTwoEnemies(player, enemy, enemy2);
		}

		if (firstEnemyDead) {
			while (true) {
				int playerAttack = player.attack();
				// you attack
				System.out.println("You attack " + enemy2.getName() + " for " + playerAttack + " damage!!");
				// enemy strength changed from attack
				enemy2.setStrength(enemy2.getStrength() - playerAttack);
				StoryTellerService.nextLine(scanner);
				// check if second enemy is dead
				if (enemy2.getStrength() < 1) {
					System.out.println("You Killed " + enemy2.getName() + " and Won!");
					wonBattle = true;
					break;
				}
				// enemy attacks
				int enemy2Attack = enemy2.attack();
				System.out.println(enemy2.getName() + " attacks for " + enemy2Attack + " damage!");
				// enemy strength changed from attack
				player.setStrength(player.getStrength() - enemy2Attack);
				StoryTellerService.nextLine(scanner);
				// check if enemy is dead
				if (player.getStrength() < 1) {
					StoryTellerService.nextLine(scanner);
					System.out.println("You Lost!");
					wonBattle = false;
					break;
				}
				printPlayerAndOneEnemy(player, enemy2);
			}
		}
		return wonBattle;
	}
	
	private void printPlayerAndOneEnemy(Player player, Enemy enemy) {
		System.out.println("--------------------------");
		System.out.println(player);
		System.out.println(enemy);
		System.out.println("--------------------------");
		StoryTellerService.nextLine(scanner);
	}
	
	private void printPlayerAndTwoEnemies(Player player, Enemy enemy, Enemy enemy2) {
		System.out.println("--------------------------");
		System.out.println(player);
		System.out.println(enemy);
		System.out.println(enemy2);
		System.out.println("--------------------------");
		StoryTellerService.nextLine(scanner);
	}

	private void printWinText(Player player) {
		StoryTellerService.printPlayer(player);
		System.out.println("...and you continue on your journey");
		System.out.println("--------------------------");
		StoryTellerService.nextLine(scanner);
	}

	private void printLossText() {
		System.out.println("--------------------------");
		System.out.println("You learn from this loss and continue...");
		System.out.println("--------------------------");
		StoryTellerService.nextLine(scanner);
	}

}
