package rpggame.story;

import java.util.List;
import java.util.Scanner;

import rpggame.combat.Combat;
import rpggame.player.Player;
import rpggame.utils.WeaponsListCreator;
import rpggame.weapon.Weapon;

public class StoryTeller implements Story {

	private final Scanner scanner;
	private final Combat combat;

	private boolean churchCompleted;
	private boolean graveyardCompleted;
	private boolean forestCompleted;

	private boolean riverCompleted;
	private boolean appliedChryogenicsCompleted;
	private boolean robotArmsCompleted;

	private boolean momCorpCompleted;
	private boolean slurmFactoryCompleted;
	private boolean fishyJoesCompleted;

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

			if (churchCompleted && graveyardCompleted && forestCompleted) {
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

			else if (choice.equals("1") && !churchCompleted) {
				churchCompleted = startChurch(player);
			} else if (choice.equals("1") && churchCompleted) {
				System.out.println("You've already cleared the Church");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("2") && !graveyardCompleted) {
				graveyardCompleted = startGraveyard(player);
			} else if (choice.equals("2") && graveyardCompleted) {
				System.out.println("You've already cleared the Graveyard");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("3") && !forestCompleted) {
				forestCompleted = startForest(player);
			} else if (choice.equals("3") && forestCompleted) {
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
		System.out.println("Here Lies Dr. Zoidberg - Beloved Crab and Doctor...");
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

			else if (choice.equals("1") && !riverCompleted) {
				riverCompleted = startRiver(player);
			} else if (choice.equals("1") && riverCompleted) {
				System.out.println("You've already cleared the River");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("2") && !appliedChryogenicsCompleted) {
				appliedChryogenicsCompleted = startAppliedChryogenics(player);
			} else if (choice.equals("2") && appliedChryogenicsCompleted) {
				System.out.println("You've already cleared Applied Chryogenics");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("3") && !robotArmsCompleted) {
				robotArmsCompleted = startRobotArms(player);
			} else if (choice.equals("3") && robotArmsCompleted) {
				System.out.println("You've already cleared Robot Arms Apartments");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("4")) {
				startLaboratory(player);
			} else if (choice.equals("5")) {
				startDumpster(player);
			} else if (choice.equals("6") && riverCompleted && appliedChryogenicsCompleted && robotArmsCompleted) {
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
		System.out.println("YOU ENTER THE LABORATORY - A big sign reads - 'PLANET EXPRESS'");
		nextLine(scanner);
		System.out.println("Old Man: 'Hello my dear friend, come in, come in'");
		nextLine(scanner);
		System.out.println("Old Man: 'I'm Professor Farnsworth and you've got to help me!'");
		nextLine(scanner);
		System.out.println("Professor: 'You see I messed up an experiment and everyone has gone mad'");
		nextLine(scanner);
		System.out.println("Professor: 'Anyone who wasn't inside here of course'");
		nextLine(scanner);
		System.out.println("Professor: 'That's why I had to protect you with my hot glowing orb'");
		nextLine(scanner);
		System.out.println("Professor: ' Speaking of hot, I've got some items you might be interested in'");
		startProfessorVendor(player);
	}

	private List<Weapon> farnsworthWeapons = WeaponsListCreator.createListWeaponsFromCsvFile("WeaponsFarnsworth.csv");

	private void startProfessorVendor(Player player) {
		int choice = 0;
		while (true) {
			System.out.println("Your NixonBucks: " + player.getNixonBucks());
			System.out.println("1: EXIT");
			for (int i = 0; i < farnsworthWeapons.size(); i++) {
				System.out.println(
						(i + 2) + ": " + farnsworthWeapons.get(i) + " - Price: " + farnsworthWeapons.get(i).getPrice());
			}
			try {
				choice = Integer.valueOf(scanner.nextLine());
				// throw exception if number not typed in
			} catch (Exception e) {
				System.out.println("Type a number that exists in your backpack");
				continue;
			}
			if (choice == Integer.parseInt("1")) {
				break;
			}
			// if number doesnt exist in backpack, continue
			else if (choice > farnsworthWeapons.size() + 1 || choice <= 0) {
				System.out.println("No weapon exists there");
				continue;
			}
			// check you have enough NixonBucks
			else if (player.getNixonBucks() < farnsworthWeapons.get(choice - 2).getPrice()) {
				System.out.println("You don't have enough NixonBucks for this weapon");
				continue;
			} else {
				// else add weapon to backpack weapon
				boolean alreadyInBackpack = player.checkForWeaponInBackpack(farnsworthWeapons.get(choice - 2));
				if (alreadyInBackpack) {
					System.out.println("You already have this weapon");
					continue;
				} else {
					player.addWeaponToBackpack(farnsworthWeapons.get(choice - 2));
					System.out.println("Weapon Bought and Added to BackPack");
					player.setNixonBucks(player.getNixonBucks() - farnsworthWeapons.get(choice - 2).getPrice());
					continue;
				}
			}
		}
	}

	private void startDumpster(Player player) {
		System.out.println("YOU WALK UP TO THE DUMPSTER'");
		nextLine(scanner);
		System.out.println("Zoidberg: 'I know you saw my gravestone, but look im not dead'");
		nextLine(scanner);
		System.out.println("Zoidberg: 'They all assumed I died but this trusty dumpster protected me'");
		nextLine(scanner);
		System.out.println("Zoidberg: 'I should go and let everyone know I'm okay'");
		nextLine(scanner);
		System.out.println("Zoidberg: 'Hermes must be worried sick'");
		nextLine(scanner);
		System.out.println(
				"Zoidberg: 'Since your here, I've got some nice items in this dumpster you could take a look at why not?'");
		startZoidbergVendor(player);
	}

	private List<Weapon> zoidbergWeapons = WeaponsListCreator.createListWeaponsFromCsvFile("WeaponsZoidberg.csv");

	private void startZoidbergVendor(Player player) {
		int choice = 0;
		while (true) {
			System.out.println("Your NixonBucks: " + player.getNixonBucks());
			System.out.println("1: EXIT");
			for (int i = 0; i < zoidbergWeapons.size(); i++) {
				System.out.println(
						(i + 2) + ": " + zoidbergWeapons.get(i) + " - Price: " + zoidbergWeapons.get(i).getPrice());
			}

			try {
				choice = Integer.valueOf(scanner.nextLine());
				// throw exception if number not typed in
			} catch (Exception e) {
				System.out.println("Type a number that exists in your backpack");
				continue;
			}
			if (choice == Integer.parseInt("1")) {
				break;
			}
			// if number doesnt exist in backpack, continue
			else if (choice > zoidbergWeapons.size() + 1 || choice <= 0) {
				System.out.println("No weapon exists there");
				continue;
			}
			// check you have enough NixonBucks
			else if (player.getNixonBucks() < zoidbergWeapons.get(choice - 2).getPrice()) {
				System.out.println("You don't have enough NixonBucks for this weapon");
				continue;
			} else {
				// else add weapon to backpack weapon
				boolean alreadyInBackpack = player.checkForWeaponInBackpack(zoidbergWeapons.get(choice - 2));
				if (alreadyInBackpack) {
					System.out.println("You already have this weapon");
					continue;
				} else {
					player.addWeaponToBackpack(zoidbergWeapons.get(choice - 2));
					System.out.println("Weapon Bought and Added to BackPack");
					player.setNixonBucks(player.getNixonBucks() - zoidbergWeapons.get(choice - 2).getPrice());
					continue;
				}
			}
		}
	}

	private void afterMainPaths() {
		System.out.println("Okay then, what to do now?");
		nextLine(scanner);
		System.out.println("Professor: 'Stop standing around and go and do something!'");
		nextLine(scanner);
	}

	public void lastPathsSplit(Player player) {
		String choice = "";
		while (true) {
			System.out.println("Where do you wish to venture too?");
			System.out.println("1: MomCorp");
			System.out.println("2: Slurm factory");
			System.out.println("3: Place3");
			System.out.println("4: Professor (Vendor)");
			System.out.println("5: Zoidberg (Vendor)");
			System.out.println("6: Hermes (NixonBucks)");
			System.out.println("7: Leela (Agility)");
			System.out.println("8: Bender (Strenth)");
			System.out.println("9: Continue the journey...to the final Boss!");

			choice = scanner.nextLine();
			if (choice.isBlank()) {
				continue;
			}

			else if (choice.equals("1") && momCorpCompleted == false) {
				momCorpCompleted = startMomCorp(player);
			} else if (choice.equals("1") && momCorpCompleted == true) {
				System.out.println("You've already cleared MomCorp");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("2") && slurmFactoryCompleted == false) {
				slurmFactoryCompleted = startSlurmFactory(player);
			} else if (choice.equals("2") && slurmFactoryCompleted == true) {
				System.out.println("You've already cleared the Slurm Factory");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equals("3") && fishyJoesCompleted == false) {
				fishyJoesCompleted = startFishyJoes(player);
			} else if (choice.equals("3") && fishyJoesCompleted == true) {
				System.out.println("You've already cleared Fishy Joe's");
				System.out.println("--------------------------");
				continue;
			}

			else if (choice.equalsIgnoreCase("4")) {
				startProfessorVendor(player);
				continue;
			} else if (choice.equalsIgnoreCase("5")) {
				startZoidbergVendor(player);
				continue;
			}

			else if (choice.equalsIgnoreCase("6")) {
				startHermes(player);
				continue;
			} else if (choice.equalsIgnoreCase("7")) {
				startLeela(player);
				continue;
			} else if (choice.equalsIgnoreCase("8")) {
				startBender(player);
				continue;
			}

			else if (choice.equals("9") && momCorpCompleted == true && slurmFactoryCompleted == true
					&& fishyJoesCompleted == true) {
				break;
			} else {
				System.out.println("You should probably check out these places first, and clear them out!");
				continue;
			}

		}
	}

	private boolean startMomCorp(Player player) {
		boolean momCorpCompleted = false;
		boolean firstFight = false;
		boolean secondFight = false;
		System.out.println("momcorp - stuff happens");
		firstFight = combat.twoEnemies(player, "Igner", 1, "Larry", 2, true);
		System.out.println("another battle now with MOM");
		secondFight = combat.twoEnemies(player, "Walt", 3, "Mom", 5, true);
		if (firstFight && secondFight) {
			momCorpCompleted = true;
		}
		return momCorpCompleted;
	}

	private boolean startSlurmFactory(Player player) {
		System.out.println("slurm factory - stuff happens, lose ability to crit");
		boolean slurmFactoryCompleted = combat.twoEnemies(player, "ENEMY?", 5, "ENEMY?", 5, false);
		return slurmFactoryCompleted;
	}

	private boolean startFishyJoes(Player player) {
		int initialPlayerStrength = player.getStrength();
		boolean fishyJoesCompletedLocal = false;
		// check that you can actually minus 2 from player strength
		if (player.getStrength() >= 3) {
			System.out.println("fishy joes - stuff happens, minus Strength");
			player.setStrength(initialPlayerStrength - 2);
			fishyJoesCompletedLocal = combat.twoEnemies(player, "ENEMY?", 5, "ENEMY?", 5, true);
		} else {
			fishyJoesCompletedLocal = combat.twoEnemies(player, "ENEMY?", 5, "ENEMY?", 5, true);
		}
		player.setStrength(initialPlayerStrength);
		return fishyJoesCompletedLocal;
	}

	private void startHermes(Player player) {
		System.out.println("Hermes: 'My Manwich!'");
		nextLine(scanner);
		System.out.println("Hermes: 'Seeing that filthy crab back here, I've lost my appetite'");
		nextLine(scanner);
		System.out.println("Hermes: 'But not my appetite for some fun with numbers!'");
		nextLine(scanner);
		System.out.println("INSERT GUESSING MONEY GAME HERE");
	}

	private void startLeela(Player player) {
		System.out.println("Leela: 'Well this is the moment we should have trained for'");
		nextLine(scanner);
		System.out.println("Leela: 'But I have got something for you'");
		nextLine(scanner);
		System.out.println("Leela: 'Heeeeee-YA'");
		nextLine(scanner);
		System.out.println("Your Agility is increased by 1");
		player.setIntelligence(player.getAgility() + 1);
		StoryTeller.printPlayer(player);
	}

	private void startBender(Player player) {
		System.out.println("Bender: 'Bite my shiny metal ASS'");
		nextLine(scanner);
		System.out.println("Bender: 'Worthless Meat Sack..'");
		nextLine(scanner);
		System.out.println("Bender: 'You could do with a bit of Bender-ising baby'");
		nextLine(scanner);
		System.out.println("Your Strength is increased by 1");
		player.setIntelligence(player.getStrength() + 1);
		StoryTeller.printPlayer(player);
	}

	public static void nextLine(Scanner scanner) {
		String input = scanner.nextLine();
		input.strip();
	}

	public static void printPlayer(Player player) {
		System.out.println("--------------------------");
		System.out.println(player);
		System.out.println("--------------------------");
	}

}
