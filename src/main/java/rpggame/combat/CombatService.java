package rpggame.combat;

import java.util.Random;
import java.util.Scanner;

import rpggame.gamecharacter.enemy.Enemy;
import rpggame.gamecharacter.player.Player;
import rpggame.story.StoryTellerService;
import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public class CombatService {

	private final Scanner scanner;

	public CombatService(Scanner scanner) {
		this.scanner = scanner;
	}

	public void pickWeapon(Player player) {
		int choice = 0;
		// before each battle set weapon to 'no weapon'
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
				StoryTellerService.printLineBreak();
				continue;
			}
			// if number doesnt exist in backpack, continue
			if (choice > player.getBackPack().size() || choice <= 0) {
				System.out.println("No weapon exists there");
				StoryTellerService.printLineBreak();
				continue;
				// if your agility is too low, continue
			} else if (player.getAgility() < player.getBackPack().get(choice - 1).getAgilityRequired()) {
				System.out.println("Your Agility is too low for this weapon");
				StoryTellerService.printLineBreak();
				continue;
				// if you intelligence is too low, continue
			} else if (player.getIntelligence() < player.getBackPack().get(choice - 1).getIntelligenceRequired()) {
				System.out.println("Your Intelligence is too low for this weapon");
				StoryTellerService.printLineBreak();
				continue;
			} else {
				// else equip weapon
				player.setWeapon(player.getBackPack().get(choice - 1));
				StoryTellerService.printLineBreak();
				System.out.println("Weapon Selected");
				StoryTellerService.printLineBreak();
				StoryTellerService.nextLine(scanner);
				break;
			}
		}
	}

	public void showAndAddDroppedWeaponAndMoney(Player player, Enemy enemy, int enemyStrengthBeforeCombat) {
		// enemy drops weapon and auto added to your backpack
		StoryTellerService.printLineBreak();
		// enemy drops randomised money between 0 and strength
		Random random = new Random();
		int moneyDropped = random.nextInt(enemyStrengthBeforeCombat);
		player.setNixonBucks(player.getNixonBucks() + moneyDropped);
		if (moneyDropped == 1) {
			System.out.println(moneyDropped + " NixonBuck collected");
		} else {
			System.out.println(moneyDropped + " NixonBucks collected");
		}
		StoryTellerService.nextLine(scanner);
		System.out.println("-- " + enemy.getName() + " dropped their weapon --");
		Weapon droppedWeapon = enemy.getWeapon();
		System.out.println(droppedWeapon.getName());
		System.out.println("Agility Required: " + droppedWeapon.getAgilityRequired());
		System.out.println("Intelligence Required: " + droppedWeapon.getIntelligenceRequired());
		System.out.println("Weapon Type: " + droppedWeapon.getWeaponType());
		StoryTellerService.printLineBreak();
		StoryTellerService.nextLine(scanner);

		boolean alreadyInBackpack = player.checkForWeaponInBackpack(droppedWeapon);
		if (!alreadyInBackpack) {
			player.addWeaponToBackpack(droppedWeapon);
			System.out.println("Weapon added to backpack");
			StoryTellerService.nextLine(scanner);
		} else {
			System.out.println("You already have this weapon");
			StoryTellerService.nextLine(scanner);
		}
	}

	public void increaseStats(Player player) {
		System.out.println("You can now increase one of your stats by 1");
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

	public void printCombatHasBegun() {
		StoryTellerService.printLineBreak();
		System.out.println("COMBAT HAS BEGUN!!!");
		StoryTellerService.printLineBreak();
	}

	public void printVersusText(Player player, Enemy enemy) {
		StoryTellerService.printLineBreak();
		System.out.println(player);
		System.out.println("vs");
		System.out.println(enemy);
		StoryTellerService.printLineBreak();
		StoryTellerService.nextLine(scanner);
	}

	public void printVersusTextTwoEnemies(Player player, Enemy enemy, Enemy enemy2) {
		StoryTellerService.printLineBreak();
		System.out.println(player);
		System.out.println("vs");
		System.out.println(enemy);
		System.out.println("and");
		System.out.println(enemy2);
		StoryTellerService.printLineBreak();
		StoryTellerService.nextLine(scanner);
	}

	public void printPlayerAndOneEnemy(Player player, Enemy enemy) {
		StoryTellerService.printLineBreak();
		System.out.println(player);
		System.out.println(enemy);
		StoryTellerService.printLineBreak();
		StoryTellerService.nextLine(scanner);
	}

	public void printWinText(Player player) {
		StoryTellerService.printPlayer(player);
		System.out.println("...and you continue on your journey");
		StoryTellerService.printLineBreak();
		StoryTellerService.nextLine(scanner);
	}

	public void printLossText() {
		StoryTellerService.printLineBreak();
		System.out.println("You learn from this loss and continue...");
		StoryTellerService.printLineBreak();
		StoryTellerService.nextLine(scanner);
	}

}
