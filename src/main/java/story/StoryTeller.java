package story;

import java.util.Scanner;

import characters.player.Player;
import rpggame.RpgGameApp;
import story.firstpathsplit.Church;
import story.firstpathsplit.Forest;
import story.firstpathsplit.Graveyard;
import story.mainpaths.AppliedChryogenics;
import story.mainpaths.Dumpster;
import story.mainpaths.Laboratory;
import story.mainpaths.River;
import story.mainpaths.RobotArms;

public class StoryTeller {

	// TODO: Spring this shit eventually, with Beans of Player, EnemyFactory,
	// Combat, Scanner, etc?
//	private static Combat combat;

	private static boolean churchCompleted;
	private static boolean graveyardCompleted;
	private static boolean forestCompleted;

	private static boolean riverCompleted;
	private static boolean appliedChryogenicsCompleted;
	private static boolean robotArmsCompleted;

	public static void introScene(Scanner scanner) {
		System.out.println("You are walking minding your own business, as you do, when...");
		RpgGameApp.nextLine(scanner);
		System.out.println("Something appears from the darkness!");
		RpgGameApp.nextLine(scanner);
		System.out.println("A strange ball of energy with what looks like an old man inside...");
		RpgGameApp.nextLine(scanner);
		System.out.println(
				"He quickly explains that there is NO TIME to explain - he forces part of the strange energy at you");
		RpgGameApp.nextLine(scanner);
		System.out.println("...and it engulfs your whole body");
		RpgGameApp.nextLine(scanner);
		System.out.println("Before the old man leaves he has a few important questions...");
		System.out.println("--------------------------");
		RpgGameApp.nextLine(scanner);
	}

	public static void firstFight(Scanner scanner) {
		System.out.println(
				"The old man leaves with a *ZAP* while screamng 'Don't forget, you can trust Pr..ssor... .arn..orth!!!'");
		RpgGameApp.nextLine(scanner);
		System.out.println("Damn...what did he say?...");
		RpgGameApp.nextLine(scanner);
		System.out.println("Before you can think you realise why he got the hell outta there");
		RpgGameApp.nextLine(scanner);
		System.out.println("Something is storming towards you....aaah shit, here we go again.");
		RpgGameApp.nextLine(scanner);
	}

	public static void churchGraveyardOrForest(Player player, Scanner scanner) throws InterruptedException {
		System.out.println("You walk alone for many miles before seeing the path split into three...");
		RpgGameApp.nextLine(scanner);
		String choice = "";

		while (true) {

			if (churchCompleted == true && graveyardCompleted == true && forestCompleted == true) {
				afterChurchGraveyardOrForest(scanner);
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

	private static void afterChurchGraveyardOrForest(Scanner scanner) {
		System.out.println("You walk away victorious, and continue down a sodden old trail into the misty unknown");
		RpgGameApp.nextLine(scanner);
		System.out.println("The old man in the glowing orb confronts you again");
		RpgGameApp.nextLine(scanner);
		System.out.println("You need some more tricks if you are going to get through here");
		RpgGameApp.nextLine(scanner);
		System.out.println("Let me show you a trick");
		RpgGameApp.nextLine(scanner);
		System.out.println("You can Crit if you use your stats to the fullest");
		RpgGameApp.nextLine(scanner);
		System.out.println(
				"If you have an Agility weapon equipped you can crit depending on how high your Intelligence is!");
		RpgGameApp.nextLine(scanner);
		System.out.println(
				"If you have an Intelligence weapon equipped you can crit depending on how high your Agility is!");
		RpgGameApp.nextLine(scanner);
		System.out.println("Further down the road I have seen a very powerful enemy indeed");
		RpgGameApp.nextLine(scanner);
		System.out.println("This enemy will be 2 levels higher than you, so be careful!");
		RpgGameApp.nextLine(scanner);
		System.out.println("...and good luck, who knows what you'll find further along the *ZAP* - he's gone");
		RpgGameApp.nextLine(scanner);
		System.out.println("--------------------------");
	}

	public static void meetFry(Player player, Scanner scanner) {
		System.out.println("Hey There! - says a voice in the distance");
		RpgGameApp.nextLine(scanner);
		System.out.println("The voice approaches and it is a young gentleman with pointy Red Hair");
		RpgGameApp.nextLine(scanner);
		System.out.println("I rode away on my Scooty Puf Jr. when I saw that thing you were fighting?");
		RpgGameApp.nextLine(scanner);
		System.out.println("Did you kill it???");
		RpgGameApp.nextLine(scanner);
		System.out.println("Well in any case, the Professy told me to come here");
		RpgGameApp.nextLine(scanner);
		System.out.println("He wants me to give you some of my intellijeans");
		RpgGameApp.nextLine(scanner);
		System.out.println("My jeans aren't intelli?!");
		RpgGameApp.nextLine(scanner);
		System.out.println("oooh maybe this will work");
		RpgGameApp.nextLine(scanner);
		System.out.println("There we go - PLUS ONE TO YOUR INTELLIGENCE, YAY!");
		RpgGameApp.nextLine(scanner);
		System.out.println("Goodbye friendly weirdo - I'm sure I'll see you again soon");
		player.setIntelligence(player.getIntelligence() + 1);
		RpgGameApp.nextLine(scanner);
		RpgGameApp.printPlayer(player);

	}

	public static void mainPaths(Player player, Scanner scanner) throws InterruptedException {
		System.out.println("As you continue, the landscape opens up providing you with many different options...");
		RpgGameApp.nextLine(scanner);
		String choice = "";

		while (true) {

			if (riverCompleted == true && appliedChryogenicsCompleted == true && robotArmsCompleted == true) {
				afterMainPaths();
				break;
			}

			System.out.println("Where do you wish to venture too?");
			System.out.println("1: River");
			System.out.println("2: Applied Chryogenics");
			System.out.println("3: Robot Arms Apts.");
			System.out.println("4: Laboratory (Vendor)");
			System.out.println("5: Dumpster (Vendor)");
			choice = scanner.nextLine();
			if (choice.isBlank()) {
				continue;
			}

			else if (choice.equals("1") && riverCompleted == false) {
				riverCompleted = River.startRiver(player, scanner);
			} else if (choice.equals("1") && riverCompleted == true) {
				System.out.println("You've already cleared the River");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("2") && appliedChryogenicsCompleted == false) {
				appliedChryogenicsCompleted = AppliedChryogenics.startAppliedChryogenics(player, scanner);
			} else if (choice.equals("2") && appliedChryogenicsCompleted == true) {
				System.out.println("You've already cleared Applied Chryogenics");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("3") && robotArmsCompleted == false) {
				robotArmsCompleted = RobotArms.startRobotArms(player, scanner);
			} else if (choice.equals("3") && robotArmsCompleted == true) {
				System.out.println("You've already cleared Robot Arms Apartments");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("4")) {
				Laboratory.start(player, scanner);
			} else if (choice.equals("5")) {
				Dumpster.start(player, scanner);
			} else {
				continue;
			}

		}

	}

	private static void afterMainPaths() {
		System.out.println("THIS IS AFTER MAIN PATHS");
	}

}
