package rpggame;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;
import story.StoryTeller;

public class RpgGameApp {

	public static void main(String[] args) throws InterruptedException {

		// SAVE GAME TO A TXT FILE WITH STATS, and LOAD IN A GAME with story progression
		// can we bring up a menu at any time between things happening, to delete
		// weapon, view stats, visit a vender etc
		// story progression, have a boolean when a 'section' is completed?
		// tests
		// add sound?
		// add some ascii art
		// find weapons randomly
		// package into a jar/exe file for execution on any computer
		
		//zoidberg isnt dead, you meet him later and he sells agi weapons
		//prof sells magic weapons
		//Leela, Amy, Bender when you meet them they increase stats
		
		//TODO: INSTEAD OF Thread.sleep() - use user input, like spacebar to progress the text?

		Scanner scanner = new Scanner(System.in);

		StoryTeller.introScene(scanner);
		Player player = createPlayer(scanner);
		
		printPlayer(player);
		
		StoryTeller.firstFight();
		Combat.oneEnemy(player, EnemyFactory.createWeakEnemy(player), scanner, false);

		StoryTeller.churchGraveyardOrForest(player, scanner);
		
		Combat.oneEnemy(player, EnemyFactory.createEnemyCustomLevel(player, 2), scanner, true);
		
		StoryTeller.meetFry(player);
		
		//TODO: perhaps now into a while loop? to, go to vendor, contiue journey, save?
		StoryTeller.mainPaths(player, scanner);

		System.out.println("now back in main method");
		
		//TODO: Boss fight with over-levelled boss...or 2
		printPlayer(player);

	}

	private static Player createPlayer(Scanner scanner) {
		String name = "";
		int strength = 0;
		int agility = 0;
		int intelligence = 0;
		while (true) {
			System.out.println("Choose a name for your character:");
			name = scanner.nextLine();
			if (name.isBlank()) {
				continue;
			} else {
				break;
			}
		}

		while (true) {
			try {
			System.out.println("Choose stats for your character, 10 max to start");
			System.out.println("Strength (which is your hp):");
			strength = Integer.valueOf(scanner.nextLine());
			System.out.println("Agility (adds to damage and to equip agility weapons):");
			agility = Integer.valueOf(scanner.nextLine());
			System.out.println("Intelligence (adds to damage and to equip intelligence weapons):");
			intelligence = Integer.valueOf(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Input numbers only");
			}

			//TODO: this gets printed when you type stupid shit into the stats
			if (strength < 1 || agility < 1 || intelligence < 1) {
				System.out.println("Come on, give yourself a chance...");
				continue;
			}

			if (strength + agility + intelligence > 10) {
				System.out.println("10 points max please...");
				continue;
			} else {
				break;
			}
		}
		return new Player(name, strength, agility, intelligence);
	}

	public static void printPlayer(Player player) {
		System.out.println("--------------------------");
		System.out.println(player);
		System.out.println("--------------------------");
	}
	
	public static void nextLine(Scanner scanner) {
		String input = scanner.nextLine();
		if(input.isBlank()) {
			
		}
	}
}
