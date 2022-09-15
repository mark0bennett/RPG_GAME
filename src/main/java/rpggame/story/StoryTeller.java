package rpggame.story;

import java.util.Scanner;

import rpggame.RpgGameApp;
import rpggame.player.Player;
import story.firstpathsplit.Church;
import story.firstpathsplit.Forest;
import story.firstpathsplit.Graveyard;
import story.mainpaths.AppliedChryogenics;
import story.mainpaths.Dumpster;
import story.mainpaths.Laboratory;
import story.mainpaths.River;
import story.mainpaths.RobotArms;

public class StoryTeller {

	private static boolean riverCompleted;
	private static boolean appliedChryogenicsCompleted;
	private static boolean robotArmsCompleted;

	private final StoryTellerService storyTellerService;

	public StoryTeller(StoryTellerService storyTellerService) {
		this.storyTellerService = storyTellerService;
	}

	public void intro() {
		storyTellerService.intro();
	}

	public Player createPlayer() {
		Player player = storyTellerService.createPlayer();
		return player;
	}

	public void startFirstFight(Player player) throws InterruptedException {
		storyTellerService.startFirstFight(player);
	}
	
	public void churchGraveyardOrForest(Player player) {
		storyTellerService.churchGraveyardOrForest(player);
	}

	public void printPlayer(Player player) {
		storyTellerService.printPlayer(player);
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
