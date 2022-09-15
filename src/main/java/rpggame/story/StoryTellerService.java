package rpggame.story;

import java.util.Scanner;

import rpggame.combat.Combat;
import rpggame.player.Player;

public class StoryTellerService {

	private final Scanner scanner;
	private final Combat combat;

	private boolean churchCompleted;
	private boolean graveyardCompleted;
	private boolean forestCompleted;

	private static boolean riverCompleted;
	private static boolean appliedChryogenicsCompleted;
	private static boolean robotArmsCompleted;

	public StoryTellerService() {
		this.scanner = new Scanner(System.in);
		this.combat = new Combat();
	}

	public void intro() {
		System.out.println("--------------------------");
		System.out.println("WELCOME TO - RPG_GAME -");
		System.out.println("Press 'Enter' to advance text");
		System.out.println("--------------------------");
		System.out.println("You are walking minding your own business, as you do, when...");
		nextLine(scanner);
		System.out.println("Something appears from the darkness!");
		nextLine(scanner);
		System.out.println("A strange ball of energy with what looks like an old man inside...");
		nextLine(scanner);
		System.out.println(
				"He quickly explains that there is NO TIME to explain - he forces part of the strange energy at you");
		nextLine(scanner);
		System.out.println("...and it engulfs your whole body");
		nextLine(scanner);
		System.out.println("Before the old man leaves he has a few important questions...");
		System.out.println("--------------------------");
		nextLine(scanner);
	}

	Player createPlayer() {
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
				continue;
			}

			// TODO: this gets printed when you type stupid shit into the stats
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

	public void startFirstFight(Player player) throws InterruptedException {
		System.out.println(
				"The old man leaves with a *ZAP* while screamng 'Don't forget, you can trust Pr..ssor... .arn..orth!!!'");
		nextLine(scanner);
		System.out.println("Damn...what did he say?...");
		nextLine(scanner);
		System.out.println("Before you can think you realise why he got the hell outta there");
		nextLine(scanner);
		System.out.println("Something is storming towards you....aaah shit, here we go again.");
		nextLine(scanner);

		// START COMBAT HERE?
		// Combat class will have its own EnemyFactory and maybe its own scanner too?
		combat.oneEnemy(player, 0, false);
	}

	public void churchGraveyardOrForest(Player player) throws InterruptedException {
		System.out.println("You walk alone for many miles before seeing the path split into three...");
		nextLine(scanner);
		String choice = "";

		while (true) {

			if (churchCompleted == true && graveyardCompleted == true && forestCompleted == true) {
				afterChurchGraveyardOrForest(player);
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
				churchCompleted = startChurch(player);
			} else if (choice.equals("1") && churchCompleted == true) {
				System.out.println("You've already cleared the Church");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("2") && graveyardCompleted == false) {
				graveyardCompleted = startGraveyard(player);
			} else if (choice.equals("2") && graveyardCompleted == true) {
				System.out.println("You've already cleared the Graveyard");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("3") && forestCompleted == false) {
				forestCompleted = startForest(player);
			} else if (choice.equals("3") && forestCompleted == true) {
				System.out.println("You've already cleared the Graveyard");
				System.out.println("--------------------------");
				continue;
			}
		}
	}

	private boolean startChurch(Player player) throws InterruptedException {
		System.out.println("YOU ENTER THE CHURCH...");
		nextLine(scanner);
		System.out.println("It's quiet, a little too quiet...");
		nextLine(scanner);
		System.out.println("...");
		System.out.println("*CRASH!!!!!!!!*");
		nextLine(scanner);
		System.out.println("Something huge has just smashed through the wall and is coming RIGHT AT YOU!");

		boolean churchCompleted = combat.oneEnemy(player, 0, false);
		return churchCompleted;
	}

	private boolean startGraveyard(Player player) throws InterruptedException {
		System.out.println("YOU ENTER THE GRAVEYARD...cautiously...");
		nextLine(scanner);
		System.out.println("What's that!");
		nextLine(scanner);
		System.out.println("...nothing");
		nextLine(scanner);
		System.out.println("You wipe some dust from a gravestone to read the inscription...");
		nextLine(scanner);
		System.out.println("Here Lies...");
		nextLine(scanner);
		System.out.println("...");
		nextLine(scanner);
		System.out.println("Here Lies Dr. Zoidberg - Many said that he was...");
		nextLine(scanner);
		System.out.println(" Noises - *uuuuuuuh*");
		nextLine(scanner);
		System.out.println("Man I hate Zombies, have it you!");

		boolean graveyardCompleted = combat.oneEnemy(player, 0, false);
		return graveyardCompleted;
	}

	private boolean startForest(Player player) throws InterruptedException {
		System.out.println("YOU ENTER THE FOREST...cautiously optimistic...");
		nextLine(scanner);
		System.out.println("A sign reads - TURN AROUND NOW - Prof. Farnsw......");
		nextLine(scanner);
		System.out.println("Some horrid looking animal falls from the trees above");
		nextLine(scanner);
		System.out.println("An Atomic Mutant Man, who would be well suited to play Basketball...");
		nextLine(scanner);
		System.out.println("That Cannon in your chest doesn't frighten me!");

		boolean forestCompleted = combat.oneEnemy(player, 0, false);
		return forestCompleted;
	}

	private void afterChurchGraveyardOrForest(Player player) throws InterruptedException {
		System.out.println("You walk away victorious, and continue down a sodden old trail into the misty unknown");
		nextLine(scanner);
		System.out.println("The old man in the glowing orb confronts you again");
		nextLine(scanner);
		System.out.println("You need some more tricks if you are going to get through here");
		nextLine(scanner);
		System.out.println("Let me show you a trick");
		nextLine(scanner);
		System.out.println("You can Crit if you use your stats to the fullest");
		nextLine(scanner);
		System.out.println(
				"If you have an Agility weapon equipped you can crit depending on how high your Intelligence is!");
		nextLine(scanner);
		System.out.println(
				"If you have an Intelligence weapon equipped you can crit depending on how high your Agility is!");
		nextLine(scanner);
		System.out.println("Further down the road I have seen a very powerful enemy indeed");
		nextLine(scanner);
		System.out.println("This enemy will be 2 levels higher than you, so be careful!");
		nextLine(scanner);
		System.out.println("...and good luck, who knows what you'll find further along the *ZAP* - he's gone");
		nextLine(scanner);
		System.out.println("--------------------------");
		combat.oneEnemy(player, 2, true);
	}

	public void meetFry(Player player) {
		System.out.println("Unknown: 'Hey There!' - says a voice in the distance");
		nextLine(scanner);
		System.out.println("The voice approaches and it is a young gentleman with pointy Red Hair");
		nextLine(scanner);
		System.out.println("Fry: 'I rode away on my Scooty Puf Jr. when I saw that thing you were fighting'");
		nextLine(scanner);
		System.out.println("Fry: 'Did you kill it???'");
		nextLine(scanner);
		System.out.println("Fry: 'Well in any case, the Professy told me to come here'");
		nextLine(scanner);
		System.out.println("He wants me to give you some of my intellijeans");
		nextLine(scanner);
		System.out.println("My jeans aren't intelli?!");
		nextLine(scanner);
		System.out.println("oooh maybe this will work");
		nextLine(scanner);
		System.out.println("There we go - PLUS ONE TO YOUR INTELLIGENCE, YAY!");
		nextLine(scanner);
		System.out.println("Goodbye friendly weirdo - I'm sure I'll see you again soon");
		player.setIntelligence(player.getIntelligence() + 1);
		StoryTellerService.printPlayer(player);
	}

	public void mainPaths(Player player) throws InterruptedException {
		System.out.println("As you continue, the landscape opens up providing you with many different options...");
		nextLine(scanner);
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
				riverCompleted = startRiver(player);
			} else if (choice.equals("1") && riverCompleted == true) {
				System.out.println("You've already cleared the River");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("2") && appliedChryogenicsCompleted == false) {
				appliedChryogenicsCompleted = startAppliedChryogenics(player);
			} else if (choice.equals("2") && appliedChryogenicsCompleted == true) {
				System.out.println("You've already cleared Applied Chryogenics");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("3") && robotArmsCompleted == false) {
				robotArmsCompleted = startRobotArms(player);
			} else if (choice.equals("3") && robotArmsCompleted == true) {
				System.out.println("You've already cleared Robot Arms Apartments");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("4")) {
				startLaboratory(player);
			} else if (choice.equals("5")) {
				startDumpster(player);
			} else {
				continue;
			}
		}
	}

	private boolean startRiver(Player player) throws InterruptedException {
		System.out.println("YOU ENTER THE RIVER");
		nextLine(scanner);
		boolean riverCompleted = combat.twoEnemies(player, 1, 1, true);
		return riverCompleted;
	}

	private boolean startAppliedChryogenics(Player player) throws InterruptedException {
		System.out.println("YOU ENTER APPLIED CHRYOGENICS");
		nextLine(scanner);
		boolean appliedChryogenicsCompleted = combat.twoEnemies(player, 2, 2, true);
		return appliedChryogenicsCompleted;
	}

	private boolean startRobotArms(Player player) throws InterruptedException {
		System.out.println("YOU ENTER ROBOT ARMS APARTMENTS");
		nextLine(scanner);
		boolean robotArmsCompleted = combat.twoEnemies(player, 3, 3, true);
		return robotArmsCompleted;
	}

	private void startLaboratory(Player player) {
		System.out.println("YOU ENTER THE LABORATORY - A BIG SIGN 'PLANET EXPRESS'");
		System.out.println("the professor does something, sells magic items?");
	}

	private void startDumpster(Player player) {
		System.out.println("YOU WALK UP TO THE DUMPSTER'");
		System.out.println("zoidberg does something, sells agi items?");
	}

	private void afterMainPaths() {
		System.out.println("THIS IS AFTER MAIN PATHS");
	}

	public static void nextLine(Scanner scanner) {
		String input = scanner.nextLine();
	}

	public static void printPlayer(Player player) {
		System.out.println("--------------------------");
		System.out.println(player);
		System.out.println("--------------------------");
	}

}
