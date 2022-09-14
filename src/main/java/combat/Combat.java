package combat;

import java.util.Scanner;

import characters.enemy.Enemy;
import characters.player.Player;
import rpggame.RpgGameApp;
import weapons.Weapon;

public class Combat {

	// TODO: fight 2 enemies at once, different method?
	private static final int ONE_SECOND = 1000;
	private static final int MS_500 = 500;

	public static boolean oneEnemy(Player player, Enemy enemy, Scanner scanner, boolean withCrits)
			throws InterruptedException {
		boolean wonBattle = false;

		System.out.println("--------------------------");
		Thread.sleep(ONE_SECOND);
		System.out.println("COMBAT HAS BEGUN!!!");
		Thread.sleep(ONE_SECOND);
		System.out.println("--------------------------");
		Thread.sleep(ONE_SECOND);

		printBackpack(player);

		pickWeapon(player, scanner);

		// show stats of you and your enemy
		System.out.println("--------------------------");
		System.out.println(player);
		Thread.sleep(ONE_SECOND);
		System.out.println("vs");
		Thread.sleep(ONE_SECOND);
		System.out.println(enemy);
		System.out.println("--------------------------");
		Thread.sleep(ONE_SECOND);

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

			Thread.sleep(ONE_SECOND);
			Thread.sleep(ONE_SECOND);
			System.out.println("You can now increase one of your stats by 1");

			increaseStats(player, scanner);

			Thread.sleep(ONE_SECOND);
			RpgGameApp.printPlayer(player);
			Thread.sleep(ONE_SECOND);
			System.out.println("--------------------------");
			Thread.sleep(MS_500);
			System.out.println("...and you continue on your journey");
			System.out.println("--------------------------");
		}
		if (!wonBattle) {
			Thread.sleep(ONE_SECOND);
			System.out.println("--------------------------");
			Thread.sleep(MS_500);
			System.out.println("You learn from this loss and continue...");
			System.out.println("--------------------------");
		}
		return wonBattle;
	}

	private static void printBackpack(Player player) throws InterruptedException {
		// show weapons in backpack
		System.out.println("Backpack Contents");
		for (int i = 0; i < player.getBackPack().size(); i++) {
			Thread.sleep(MS_500);
			System.out.println((i + 1) + ": " + player.getBackPack().get(i));
		}
	}

	private static void pickWeapon(Player player, Scanner scanner) throws InterruptedException {
		int choice = 0;
		while (true) {
			Thread.sleep(MS_500);
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

	private static boolean attacksWithNoCrits(Player player, Enemy enemy) throws InterruptedException {
		boolean wonBattle = false;
		while (true) {
			Thread.sleep(ONE_SECOND);
			// you attack
			System.out.println("You attack for " + player.attack() + " damage!");
			// enemy strength changed from attack
			enemy.setStrength(enemy.getStrength() - player.attack());
			Thread.sleep(ONE_SECOND);
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
			Thread.sleep(ONE_SECOND);
			// check if enemy is dead
			if (player.getStrength() < 1) {
				Thread.sleep(ONE_SECOND);
				System.out.println("You Lost!");
				wonBattle = false;
				break;
			}
			Thread.sleep(ONE_SECOND);
			System.out.println("--------------------------");
			Thread.sleep(ONE_SECOND);
			System.out.println(player);
			Thread.sleep(ONE_SECOND);
			System.out.println(enemy);
			Thread.sleep(ONE_SECOND);
			System.out.println("--------------------------");
			Thread.sleep(ONE_SECOND);
		}
		return wonBattle;
	}

	private static boolean attacksWithCrits(Player player, Enemy enemy) throws InterruptedException {
		boolean wonBattle = false;
		while (true) {
			Thread.sleep(ONE_SECOND);
			//for clarity
			int[] playerAttackNumbers = player.attackCrit();
			int playerAttack = playerAttackNumbers[0];
			int playerCrit = playerAttackNumbers[1];
			// you attack
			System.out.println(
					"You attack for " + playerAttack + " damage plus " + playerCrit + " Crit!");
			// enemy strength changed from attack
			enemy.setStrength(enemy.getStrength() - (playerAttack + playerCrit));
			Thread.sleep(ONE_SECOND);
			// check if enemy is dead
			if (enemy.getStrength() < 1) {
				System.out.println("You Won!");
				wonBattle = true;
				break;
			}
			// enemy attacks
			//for clarity
			int[] enemyAttackNumbers = enemy.attackCrit();
			int enemyAttack = enemyAttackNumbers[0];
			int enemyCrit = enemyAttackNumbers[1];
			System.out.println(
					"Enemy attacks for " + enemyAttack + " damage plus " + enemyCrit + " Crit!");
			// enemy strength changed from attack
			player.setStrength(player.getStrength() - (enemyAttack + enemyCrit));
			Thread.sleep(ONE_SECOND);
			// check if enemy is dead
			if (player.getStrength() < 1) {
				Thread.sleep(ONE_SECOND);
				System.out.println("You Lost!");
				wonBattle = false;
				break;
			}
			Thread.sleep(ONE_SECOND);
			System.out.println("--------------------------");
			Thread.sleep(ONE_SECOND);
			System.out.println(player);
			Thread.sleep(ONE_SECOND);
			System.out.println(enemy);
			Thread.sleep(ONE_SECOND);
			System.out.println("--------------------------");
			Thread.sleep(ONE_SECOND);
		}
		return wonBattle;
	}

	private static void showAndAddDroppedWeapon(Player player, Enemy enemy) throws InterruptedException {
		// enemy drops weapon and auto added to your backpack
		Thread.sleep(ONE_SECOND);
		System.out.println("--------------------------");
		Thread.sleep(ONE_SECOND);
		System.out.println("The Enemy dropped its weapon...");
		Weapon droppedWeapon = enemy.dropWeapon();
		Thread.sleep(ONE_SECOND);
		System.out.println(droppedWeapon);
		Thread.sleep(MS_500);
		System.out.println("Agility Required: " + droppedWeapon.getAgilityRequired());
		Thread.sleep(MS_500);
		System.out.println("Intelligence Required: " + droppedWeapon.getIntelligenceRequired());
		Thread.sleep(MS_500);
		System.out.println("Weapon Type: " + droppedWeapon.getWeaponType());
		Thread.sleep(MS_500);
		System.out.println("--------------------------");
		Thread.sleep(ONE_SECOND);
		// TODO: STILL DOUBLE ADDING WEAPONS WHEN THEY ARE ALREADY IN BACKPACK - doing
		// it manually for now
		player.addWeaponToBackpack(droppedWeapon);
		System.out.println("--------------------------");
		System.out.println("Your Backpack: " + player.getBackPack());
		System.out.println("--------------------------");
	}

	private static void increaseStats(Player player, Scanner scanner) throws InterruptedException {
		while (true) {
			Thread.sleep(ONE_SECOND);
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

}
