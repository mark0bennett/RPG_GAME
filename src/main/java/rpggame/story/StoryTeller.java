package rpggame.story;

import java.util.Scanner;

import rpggame.combat.Combat;
import rpggame.player.Player;

public class StoryTeller implements Story {

	private final Scanner scanner;
	private final Combat combat;

	private boolean churchCompleted;
	private boolean graveyardCompleted;
	private boolean forestCompleted;

	private static boolean riverCompleted;
	private static boolean appliedChryogenicsCompleted;
	private static boolean robotArmsCompleted;

	public StoryTeller() {
		this.scanner = new Scanner(System.in);
		this.combat = new Combat();
	}

	public void intro() {
		System.out.println("--------------------------");
		System.out.println("WELCOME TO - RPG_GAME -");
		System.out.println("Press 'Enter' to advance the text");
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

	public Player createPlayer() {
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

	public void startFirstFight(Player player) {
		System.out.println(
				"The old man leaves with a *ZAP* while screamng 'Don't forget, you can trust Pr..ssor... .arn..orth!!!'");
		nextLine(scanner);
		System.out.println("Damn...what did he say?...");
		nextLine(scanner);
		System.out.println("Before you can think you realise why he got the hell outta there");
		nextLine(scanner);
		System.out.println("Something quite strange is heading towards you....wearing a sleek yet intimidating outfit");
		nextLine(scanner);

		// START COMBAT HERE?
		// Combat class will have its own EnemyFactory and maybe its own scanner too?
		combat.oneEnemy(player, "Zapp Brannigan", 0, false);
	}

	public void firstPathsSplit(Player player) {
		System.out.println("You walk alone for many miles before seeing the path split into three...");
		nextLine(scanner);
		String choice = "";

		while (true) {

			if (churchCompleted == true && graveyardCompleted == true && forestCompleted == true) {
				afterFirstPathsSplit(player);
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

	private boolean startChurch(Player player) {
		System.out.println("YOU ENTER THE CHURCH...");
		nextLine(scanner);
		System.out.println("It's quiet, a little too quiet...");
		nextLine(scanner);
		System.out.println("...");
		System.out.println("*CRASH!!!!!!!!*");
		nextLine(scanner);
		System.out.println("Preacherbot: 'Sinner, feel the cold metal love of Robotology!'");
nextLine(scanner);
		boolean churchCompleted = combat.oneEnemy(player, "Preacherbot", 0, false);
		return churchCompleted;
	}

	private boolean startGraveyard(Player player) {
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
		System.out.println("Zombie Jesus: 'They nailed me to the cross, this time it's personal'");
		nextLine(scanner);
		System.out.println("Man I hate Zombies, have it you!");
nextLine(scanner);
		boolean graveyardCompleted = combat.oneEnemy(player, "Zombie Jesus", 0, false);
		return graveyardCompleted;
	}

	private boolean startForest(Player player) {
		System.out.println("YOU ENTER THE FOREST...cautiously optimistic...");
		nextLine(scanner);
		System.out.println("A sign reads - TURN AROUND NOW - Prof. Farnsw......");
		nextLine(scanner);
		System.out.println("A flash from the bushes...");
		nextLine(scanner);
		System.out.println("Ranger Park: 'Oh, I thought you were Bigfoot, you know he roams these forests'");
		nextLine(scanner);
		System.out.println(
				"Ranger Park: 'But what nice feet you have, if I chop of your feet they could almost be mistaken for Bigfoot's'");
nextLine(scanner);
		boolean forestCompleted = combat.oneEnemy(player, "Ranger Park the Park Ranger", 0, false);
		return forestCompleted;
	}

	private void afterFirstPathsSplit(Player player) {
		System.out.println("You walk away victorious, and continue down a sodden old trail into the wild green yonder");
		nextLine(scanner);
		System.out.println("The old man in the glowing orb confronts you again");
		nextLine(scanner);
		System.out.println("Old Man: 'I have Good News for you'");
		nextLine(scanner);
		System.out.println("Old Man: 'Let me show you a trick'");
		nextLine(scanner);
		System.out.println("Old Man: 'You can Crit if you use your stats to the fullest'");
		nextLine(scanner);
		System.out.println(
				"Old Man: 'If you have an Agility weapon equipped you can crit depending on how high your Intelligence is!'");
		nextLine(scanner);
		System.out.println(
				"Old Man: 'If you have an Intelligence weapon equipped you can crit depending on how high your Agility is!'");
		nextLine(scanner);
		System.out.println("Old Man: 'Further down the road I have seen a very powerful enemy indeed'");
		nextLine(scanner);
		System.out.println("Old Man: 'This enemy will be 2 levels higher than you, so be careful!'");
		nextLine(scanner);
		System.out.println("*ZAP* - he's gone");
		nextLine(scanner);
		System.out.println("--------------------------");
		combat.oneEnemy(player, "LRRR Ruler of the Planet Omicron Persei 8", 2, true);
	}

	public void meetFirstNPC(Player player) {
		System.out.println("Unknown: 'Hey There!' - says a voice in the distance");
		nextLine(scanner);
		System.out.println("The voice approaches and it is a young gentleman with pointy Red Hair");
		nextLine(scanner);
		System.out.println("Fry: 'I rode away on my Scooty Puff Jr. when I saw that thing you were fighting'");
		nextLine(scanner);
		System.out.println("Fry: 'Did you kill it???'");
		nextLine(scanner);
		System.out.println("Fry: 'Well in any case, the Professy told me to come here'");
		nextLine(scanner);
		System.out.println("Fry: 'He wants me to give you some of my intelli-jeans'");
		nextLine(scanner);
		System.out.println("Fry: 'My jeans aren't intelli?!'");
		nextLine(scanner);
		System.out.println("Fry: 'oooh maybe this will work'");
		nextLine(scanner);
		System.out.println("Fry: 'There we go - PLUS ONE TO YOUR INTELLIGENCE, I've got plenty to spare'");
		nextLine(scanner);
		System.out.println("Fry: 'Goodbye friendly weirdo - I'm sure I'll see you again soon'");
		nextLine(scanner);
		player.setIntelligence(player.getIntelligence() + 1);
		StoryTeller.printPlayer(player);
	}

	public void mainPathsSplit(Player player) {
		System.out.println("As you continue, the landscape opens up providing you with many different options...");
		nextLine(scanner);
		String choice = "";

		while (true) {

			System.out.println("Where do you wish to venture too?");
			System.out.println("1: River");
			System.out.println("2: Applied Chryogenics");
			System.out.println("3: Robot Arms Apts.");
			System.out.println("4: Laboratory (Vendor)");
			System.out.println("5: Dumpster (Vendor)");
			System.out.println("6: Continue the journey...");
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
			} else if (choice.equals("6") && riverCompleted == true && appliedChryogenicsCompleted == true
					&& robotArmsCompleted == true) {
				afterMainPaths();
				break;
			} else {
				System.out.println("You should probably check out these places first, and clear them out!");
				continue;
			}
		}
	}

	private boolean startRiver(Player player) {
		System.out.println("YOU ENTER THE RIVER");
		nextLine(scanner);
		System.out.println("Is that, Joan Rivers?");
		nextLine(scanner);
		System.out.println("No it's just a big blob of green muck");
		nextLine(scanner);
		System.out.println("Joan Rivers: 'I'm angry that I'll never be as good looking as my friend here HGB!'");
		nextLine(scanner);
		System.out.println("Joan Rivers & HGB: 'Let's get him!'");
		nextLine(scanner);
		boolean riverCompleted = combat.twoEnemies(player, "Joan River's Head", 1, "Horrible Gelatinous Blob", 1, true);
		return riverCompleted;
	}

	private boolean startAppliedChryogenics(Player player) {
		System.out.println("YOU ENTER APPLIED CHRYOGENICS");
		nextLine(scanner);
		System.out.println("Terry: 'Welcome to the world of tomorrow!'");
		nextLine(scanner);
		System.out.println("Ipji: 'Do your jobs, all of you, while I eat this curry'");
		nextLine(scanner);
		System.out.println("...?");
		nextLine(scanner);
		System.out.println("Ipji: 'Ooooh no career chip in you I see'");
		nextLine(scanner);
		System.out.println("Ipji: 'Michelle, help me hold him down while I implant him with a Janitor chip'");
		nextLine(scanner);
		boolean appliedChryogenicsCompleted = combat.twoEnemies(player, "Michelle", 2, "Ipji", 2, true);
		return appliedChryogenicsCompleted;
	}

	private boolean startRobotArms(Player player) {
		System.out.println("YOU ENTER ROBOT ARMS APARTMENTS");
		nextLine(scanner);
		System.out.println("*Music is playing*");
		nextLine(scanner);
		System.out.println("Robot Devil: 'Cigars are evil, you won't miss them, we'll find ways to...'");
		nextLine(scanner);
		System.out.println("Robot Devil: 'How rude! To interrupt my ironic song!'");
		nextLine(scanner);
		System.out.println("Roboto: 'I wanna stab him with my stabbing knife!'");
		nextLine(scanner);
		boolean robotArmsCompleted = combat.twoEnemies(player, "Robot Devil", 3, "Roberto", 3, true);
		return robotArmsCompleted;
	}

	private void startLaboratory(Player player) {
		System.out.println("YOU ENTER THE LABORATORY - A BIG SIGN 'PLANET EXPRESS'");
		// can sell items from another csv
		System.out.println("the professor does something, sells special magic items?");
		System.out.println("finally we can meet in safety");
	}

	private void startDumpster(Player player) {
		System.out.println("YOU WALK UP TO THE DUMPSTER'");
		// can sell items from another csv
		System.out.println("zoidberg does something, sells special agi items?");
		System.out.println("I know you saw my gravestone, but look im not dead");
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
