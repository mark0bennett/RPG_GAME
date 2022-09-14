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
		System.out.println("You are walking minding your own business, as you do, when...");
		Thread.sleep(ONE_SECOND);
		System.out.println("Something appears from the darkness!");
		Thread.sleep(ONE_SECOND);
		System.out.println("A strange ball of energy with what looks like an old man inside...");
		Thread.sleep(ONE_SECOND);
		System.out.println("He quickly explains that there is NO TIME to explain - he forces part of the strange energy at you");
		Thread.sleep(ONE_SECOND);
		System.out.println("...and it engulfs your whole body");
		Thread.sleep(ONE_SECOND);
		System.out.println("Before the old man leaves he has a few important questions...");
		System.out.println("--------------------------");
		Thread.sleep(ONE_SECOND);
	}

	public static void firstFight() throws InterruptedException {
		System.out.println("The old man leaves with a *ZAP* while screamng 'Don't forget, you can trust Pr..ssor... .arn..orth!!!'");
		Thread.sleep(ONE_SECOND);
		System.out.println("Damn...what did he say?...");
		Thread.sleep(ONE_SECOND);
		System.out.println("Before you can think you realise why he got the hell outta there");
		Thread.sleep(ONE_SECOND);
		System.out.println("Something is storming towards you....aaah shit, here we go again.");
		Thread.sleep(ONE_SECOND);
	}

	public static void churchGraveyardOrForest(Player player, Scanner scanner) throws InterruptedException {
		System.out.println("You walk alone for many miles before seeing the path split into three...");
		Thread.sleep(ONE_SECOND);
		String choice = "";
		
		while (true) {

			if (churchCompleted == true && graveyardCompleted == true && forestCompleted == true) {
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

	public static void afterChurchGraveyardOrForest() throws InterruptedException {
		System.out.println("You walk away victorious, and continue down a sodden old trail into the misty unknown");
		Thread.sleep(ONE_SECOND);
		System.out.println("The old man in the glowing orb confronts you again");
		Thread.sleep(ONE_SECOND);
		System.out.println("You need some more tricks if you are going to get through here");
		Thread.sleep(ONE_SECOND);
		System.out.println("Let me show you a trick");
		Thread.sleep(ONE_SECOND);
		System.out.println("You can Crit if you use your stats to the fullest");
		Thread.sleep(ONE_SECOND);
		System.out.println("If you have an Agility weapon equipped you can crit depending on how high your Intelligence is!");
		Thread.sleep(ONE_SECOND);
		System.out.println("If you have an Intelligence weapon equipped you can crit depending on how high your Agility is!");
		Thread.sleep(ONE_SECOND);
		System.out.println("Further down the road I have seen a very powerful enemy indeed");
		Thread.sleep(ONE_SECOND);
		System.out.println("This enemy will be 2 levels higher than you, so be careful!");
		Thread.sleep(ONE_SECOND);
		System.out.println("...and good luck, who knows what you'll find further along the *ZAP* - he's gone");
		Thread.sleep(ONE_SECOND);
		System.out.println("--------------------------");
	}

	public static void goToVendor() {
		//should we add money to the player to buy shit?

	}

}
