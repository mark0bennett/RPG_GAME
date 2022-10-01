package rpggame.utils;

import java.util.Scanner;

import rpggame.gamecharacter.enemy.Enemy;
import rpggame.gamecharacter.player.Player;

public class Printer {

	private static Scanner scanner = new Scanner(System.in);

	public static void nextLine(Scanner scanner) {
		scanner.nextLine();
	}

	public static void printBackpack(Player player) {
		// show weapons in backpack
		Printer.printLineBreak();
		System.out.println("Backpack Contents");
		for (int i = 0; i < player.getBackPack().size(); i++) {
			System.out.println((i + 1) + ": " + player.getBackPack().get(i));
		}
		Printer.printLineBreak();
	}

	public static void printPlayer(Player player) {
		printLineBreak();
		System.out.println(player);
		printLineBreak();
	}

	public static void printLineBreak() {
		System.out.println(
				"----------------------------------------------------------------------------------------------------");
	}

	public static void printCombatHasBegun() {
		Printer.printLineBreak();
		System.out.println("COMBAT HAS BEGUN!!!");
		Printer.printLineBreak();
	}

	public static void printVersusTextOneEnemy(Player player, Enemy enemy) {
		Printer.printLineBreak();
		System.out.println(player);
		System.out.println("vs");
		System.out.println(enemy);
		Printer.printLineBreak();
		Printer.nextLine(scanner);
	}

	public static void printVersusTextTwoEnemies(Player player, Enemy enemy, Enemy enemy2) {
		Printer.printLineBreak();
		System.out.println(player);
		System.out.println("vs");
		System.out.println(enemy);
		System.out.println("and");
		System.out.println(enemy2);
		Printer.printLineBreak();
		Printer.nextLine(scanner);
	}

	public static void printPlayerAndOneEnemy(Player player, Enemy enemy) {
		Printer.printLineBreak();
		System.out.println(player);
		System.out.println(enemy);
		Printer.printLineBreak();
		Printer.nextLine(scanner);
	}

	public static void printWinText(Player player) {
		Printer.printPlayer(player);
		System.out.println("...and you continue on your journey");
		Printer.printLineBreak();
		Printer.nextLine(scanner);
	}

	public static void printLossText() {
		Printer.printLineBreak();
		System.out.println("You learn from this loss and continue...");
		Printer.printLineBreak();
		Printer.nextLine(scanner);
	}

}
