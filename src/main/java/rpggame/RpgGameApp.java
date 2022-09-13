package rpggame;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;
import story.StoryTeller;

public class RpgGameApp {

	public static void main(String[] args) throws InterruptedException {

		// TODO: PUT ON GIT HUB!!!!!!!!!!!!!!!!!!!!
		// SAVE GAME TO A TXT FILE WITH STATS, and LOAD IN A GAME with story progression
		// can we bring up a menu at any time between things happening, to delete
		// weapon, view stats, visit a vender etc
		// story progression, have a boolean when a 'section' is completed?
		// tests
		// add sound?
		// add some ascii art
		// package into a jar/exe file for execution on any computer

		Scanner scanner = new Scanner(System.in);

		StoryTeller.introScene();
		Player player = createPlayer(scanner);

		printPlayer(player);

		StoryTeller.firstFight();
		Combat.oneEnemy(player, EnemyFactory.createEnemy(player), scanner);

		StoryTeller.churchGraveyardOrForest(player, scanner);

		System.out.println("now back in main method");
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
			System.out.println("Choose stats for your character, 10 max to start");
			System.out.println("Strength (which is your hp):");
			strength = Integer.valueOf(scanner.nextLine());
			System.out.println("Agility (adds to damage and to equip agility weapons):");
			agility = Integer.valueOf(scanner.nextLine());
			System.out.println("Intelligence (adds to damage and to equip intelligence weapons):");
			intelligence = Integer.valueOf(scanner.nextLine());

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
}
