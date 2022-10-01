package rpggame.combat;

import java.util.Random;
import java.util.Scanner;

import rpggame.gamecharacter.enemy.Enemy;
import rpggame.gamecharacter.player.Player;
import rpggame.utils.Printer;
import rpggame.weapon.Weapon;

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
			Printer.printBackpack(player);
			System.out.println("Which weapon would you like to use (type a number)");
			// pick a number
			try {
				choice = Integer.valueOf(scanner.nextLine());
				// throw exception if number not typed in
			} catch (Exception e) {
				System.out.println("Type a number that exists in your backpack");
				Printer.printLineBreak();
				continue;
			}
			// if number doesnt exist in backpack, continue
			if (choice > player.getBackPack().size() || choice <= 0) {
				System.out.println("No weapon exists there");
				Printer.printLineBreak();
				continue;
				// if your agility is too low, continue
			} else if (player.getAgility() < player.getBackPack().get(choice - 1).getAgilityRequired()) {
				System.out.println("Your Agility is too low for this weapon");
				Printer.printLineBreak();
				continue;
				// if you intelligence is too low, continue
			} else if (player.getIntelligence() < player.getBackPack().get(choice - 1).getIntelligenceRequired()) {
				System.out.println("Your Intelligence is too low for this weapon");
				Printer.printLineBreak();
				continue;
			} else {
				// else equip weapon
				player.setWeapon(player.getBackPack().get(choice - 1));
				Printer.printLineBreak();
				System.out.println("Weapon Selected");
				Printer.printLineBreak();
				Printer.nextLine(scanner);
				break;
			}
		}
	}

	public void showAndAddDroppedWeaponAndMoney(Player player, Enemy enemy, int enemyStrengthBeforeCombat) {
		// enemy drops weapon and auto added to your backpack
		Printer.printLineBreak();
		// enemy drops randomised money between 0 and strength
		Random random = new Random();
		int moneyDropped = random.nextInt(enemyStrengthBeforeCombat);
		player.setNixonBucks(player.getNixonBucks() + moneyDropped);
		if (moneyDropped == 1) {
			System.out.println(moneyDropped + " NixonBuck collected");
		} else {
			System.out.println(moneyDropped + " NixonBucks collected");
		}
		Printer.nextLine(scanner);
		System.out.println("-- " + enemy.getName() + " dropped their weapon --");
		Weapon droppedWeapon = enemy.getWeapon();
		System.out.println(droppedWeapon.getName());
		System.out.println("Agility Required: " + droppedWeapon.getAgilityRequired());
		System.out.println("Intelligence Required: " + droppedWeapon.getIntelligenceRequired());
		System.out.println("Weapon Type: " + droppedWeapon.getWeaponType());
		Printer.printLineBreak();
		Printer.nextLine(scanner);

		boolean alreadyInBackpack = player.checkForWeaponInBackpack(droppedWeapon);
		if (!alreadyInBackpack) {
			player.addWeaponToBackpack(droppedWeapon);
			System.out.println("Weapon added to backpack");
			Printer.nextLine(scanner);
		} else {
			System.out.println("You already have this weapon");
			Printer.nextLine(scanner);
		}
	}

	public void increaseStats(Player player) {
		System.out.println("You can now increase one of your stats by 1");
		while (true) {
			Printer.printPlayer(player);
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
