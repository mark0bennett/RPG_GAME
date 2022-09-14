package story;

import java.util.Scanner;

import characters.player.Player;
import story.firstpathsplit.Church;
import story.firstpathsplit.Forest;
import story.firstpathsplit.Graveyard;

public class StoryTeller {

	// TODO: Spring this shit eventually, with Beans of Player, EnemyFactory,
	// Combat, Scanner, etc?
//	private static Combat combat;

	private static final int ONE_SECOND = 1000;
	
	private static boolean churchCompleted;
	private static boolean graveyardCompleted;
	private static boolean forestCompleted;

	public static void introScene() throws InterruptedException {
		System.out.println("You are walking minding your own business, when...");
		Thread.sleep(ONE_SECOND);
		System.out.println("Something appears from the darkness!");
		Thread.sleep(ONE_SECOND);
		System.out.println("A strange thing offers you great power...");
		Thread.sleep(ONE_SECOND);
		System.out.println("...and of course you take it!");
		Thread.sleep(ONE_SECOND);
		System.out.println("He has some questions first though...");
		System.out.println("--------------------------");
		Thread.sleep(ONE_SECOND);
	}

	public static void firstFight() throws InterruptedException {
		System.out.println("Let's see how you go in a battle then aye.....");
		Thread.sleep(ONE_SECOND);
		System.out.println("A Monster appears out of nowhere!");
		Thread.sleep(ONE_SECOND);
	}

	public static void churchGraveyardOrForest(Player player, Scanner scanner) throws InterruptedException {
		System.out.println("You walk alone for many miles before seeing the path split into three...");
		Thread.sleep(ONE_SECOND);
		String choice = "";
		
		while (true) {

			if (churchCompleted == true && graveyardCompleted == true && forestCompleted == true) {
				System.out.println("Well Done, You cleared church graveyard and forest!");
				System.out.println("--------------------------");
				afterChurchGraveyardOrForest();
				break;
			}

			System.out.println("Where do you wish to venture too?");
			System.out.println("1: Church");
			System.out.println("2: Graveyard");
			System.out.println("3: Forest");
			choice = scanner.nextLine();
			if (choice.isBlank()) {
				continue;
			}

			else if (choice.equals("1") && churchCompleted == false) {
				churchCompleted = Church.startChurch(player, scanner);
			} else if (choice.equals("1") && churchCompleted == true) {
				System.out.println("You've already cleared the Church");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("2") && graveyardCompleted == false) {
				graveyardCompleted = Graveyard.startGraveyard(player, scanner);
			} else if (choice.equals("2") && graveyardCompleted == true) {
				System.out.println("You've already cleared the Graveyard");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("3") && forestCompleted == false) {
				forestCompleted = Forest.startForest(player, scanner);
			} else if (choice.equals("3") && forestCompleted == true) {
				System.out.println("You've already cleared the Graveyard");
				System.out.println("--------------------------");
				continue;
			}

		}

	}

	public static void afterChurchGraveyardOrForest() {
		// more stuff happens - meet an npc? and learn how to crit
		System.out.println("YOU ARE NOW IN THE METHOD AFTER CHURCH GRAVEYARD FOREST YAY");
	}

	public static void goToVendor() {
		//should we add money to the player to buy shit?

	}

}
