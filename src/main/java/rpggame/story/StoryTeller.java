package rpggame.story;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import rpggame.combat.Combat;
import rpggame.person.enemy.Enemy;
import rpggame.person.player.Player;
import rpggame.utils.AsciiArtReader;
import rpggame.utils.WeaponsListCreator;
import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public class StoryTeller implements Story {

	private final Scanner scanner;
	private final Combat combat;
	private final Random random;

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
		this.random = new Random();
	}

	public void intro() {
		printLineBreak();
		System.out.println("		-- WELCOME TO --");
		printLineBreak();
		AsciiArtReader.readArt("Futurama.txt");
		printLineBreak();
		System.out.println("Press 'Enter' to contiue and skip lines of text");
		printLineBreak();
		nextLine(scanner);
		System.out.println("You are walking minding your own business, as you do, when...");
		nextLine(scanner);
		System.out.println("Something appears from the darkness!");
		nextLine(scanner);
		System.out.println("A strange ball of energy with what looks like an old man inside...");
		nextLine(scanner);
		AsciiArtReader.readArt("Professor.txt");
		printLineBreak();
		nextLine(scanner);
		System.out.println(
				"He quickly explains that there is NO TIME to explain - he forces part of the strange energy at you");
		nextLine(scanner);
		System.out.println("...and it engulfs your whole body");
		nextLine(scanner);
		System.out.println("Before the old man leaves he has a few important questions...");
		printLineBreak();
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
				printLineBreak();
				continue;
			}

			else if (choice.equals("2") && !graveyardCompleted) {
				graveyardCompleted = startGraveyard(player);
			} else if (choice.equals("2") && graveyardCompleted) {
				System.out.println("You've already cleared the Graveyard");
				printLineBreak();
				continue;
			}

			else if (choice.equals("3") && !forestCompleted) {
				forestCompleted = startForest(player);
			} else if (choice.equals("3") && forestCompleted) {
				System.out.println("You've already cleared the Graveyard");
				printLineBreak();
				continue;
			}
		}
	}

	private boolean seenChurchAlready = false;

	private boolean startChurch(Player player) {
		if (!seenChurchAlready) {
			seenChurchAlready = true;
			System.out.println("YOU ENTER THE CHURCH...");
			nextLine(scanner);
			System.out.println("It's quiet, a little too quiet...");
			nextLine(scanner);
			System.out.println("...");
			System.out.println("*CRASH!!!!!!!!*");
			nextLine(scanner);
			System.out.println("Preacherbot: 'Sinner, feel the cold metal love of Robotology!'");
			nextLine(scanner);
		} else {
			System.out.println("YOU ENTER THE CHURCH...again");
			nextLine(scanner);
		}
		boolean churchCompleted = combat.oneEnemy(player, "Preacherbot", 1, false);
		return churchCompleted;
	}

	private boolean seenGraveyardAlready = false;

	private boolean startGraveyard(Player player) {
		if (!seenGraveyardAlready) {
			seenGraveyardAlready = true;
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
		} else {
			System.out.println("YOU ENTER THE GRAVEYARD...again...");
			nextLine(scanner);
		}
		boolean graveyardCompleted = combat.oneEnemy(player, "Zombie Jesus", 2, false);
		return graveyardCompleted;
	}

	private boolean seenForestAlready = false;

	private boolean startForest(Player player) {
		if (!seenForestAlready) {
			seenForestAlready = true;
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
		} else {
			System.out.println("YOU ENTER THE FOREST...again...");
			nextLine(scanner);
		}
		boolean forestCompleted = combat.oneEnemy(player, "Ranger Park the Park Ranger", 2, false);
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
		System.out.println("Old Man: 'This enemy will be 4 levels higher than you, so be careful!'");
		nextLine(scanner);
		System.out.println("*ZAP* - he's gone");
		nextLine(scanner);
		printLineBreak();
		combat.oneEnemy(player, "LRRR Ruler of the Planet Omicron Persei 8", 4, true);
	}

	public void meetFirstNPC(Player player) {
		System.out.println("Unknown: 'Hey There!' - says a voice in the distance");
		nextLine(scanner);
		System.out.println("The voice approaches and it is a young gentleman with pointy Red Hair");
		nextLine(scanner);
		AsciiArtReader.readArt("Fry.txt");
		printLineBreak();
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
				printLineBreak();
				continue;
			}

			else if (choice.equals("2") && !appliedChryogenicsCompleted) {
				appliedChryogenicsCompleted = startAppliedChryogenics(player);
			} else if (choice.equals("2") && appliedChryogenicsCompleted) {
				System.out.println("You've already cleared Applied Chryogenics");
				printLineBreak();
				continue;
			}

			else if (choice.equals("3") && !robotArmsCompleted) {
				robotArmsCompleted = startRobotArms(player);
			} else if (choice.equals("3") && robotArmsCompleted) {
				System.out.println("You've already cleared Robot Arms Apartments");
				printLineBreak();
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
				System.out.println("You should probably check out these places, and clear them out!");
				printLineBreak();
				continue;
			}
		}
	}

	private boolean seenRiverAlready = false;

	private boolean startRiver(Player player) {
		if (!seenRiverAlready) {
			seenRiverAlready = true;
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
		} else {
			System.out.println("YOU ENTER THE RIVER...again");
			nextLine(scanner);
		}
		boolean riverCompleted = combat.twoEnemies(player, "Joan River's Head", 2, "Horrible Gelatinous Blob", 3, true);
		return riverCompleted;
	}

	private boolean seenAppliedAready = false;

	private boolean startAppliedChryogenics(Player player) {
		if (!seenAppliedAready) {
			seenAppliedAready = true;
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
		} else {
			System.out.println("YOU ENTER APPLIED CHRYOGENICS...again");
			nextLine(scanner);
		}
		boolean appliedChryogenicsCompleted = combat.twoEnemies(player, "Michelle", 2, "Ipji", 3, true);
		return appliedChryogenicsCompleted;
	}

	private boolean seenRobotArmsAlready = false;

	private boolean startRobotArms(Player player) {
		if (!seenRobotArmsAlready) {
			seenRobotArmsAlready = true;
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
		} else {
			System.out.println("YOU ENTER ROBOT ARMS APARTMENTS...again");
			nextLine(scanner);
		}
		boolean robotArmsCompleted = combat.twoEnemies(player, "Robot Devil", 3, "Roberto", 3, true);
		return robotArmsCompleted;
	}

	private boolean doneLaboratoryOnceAlready = false;

	private void startLaboratory(Player player) {
		if (!doneLaboratoryOnceAlready) {
			doneLaboratoryOnceAlready = true;
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
		} else {
			System.out.println("Professor: 'Look at my items'");
			nextLine(scanner);
		}
		startProfessorVendor(player);
	}

	private boolean youCanSellWeapon(Player player, Weapon weapon) {
		boolean weaponCanBeSold = false;
		for (Weapon weaponInBackpack : player.getBackPack()) {
			if ((weaponInBackpack.getAgilityRequired() <= player.getAgility()
					|| weaponInBackpack.getIntelligenceRequired() <= player.getIntelligence())
					&& !weaponInBackpack.equals(weapon)) {
				weaponCanBeSold = true;
			}
		}
		return weaponCanBeSold;
	}

	private void sellItemsMenu(List<Weapon> vendorList, Player player) {
		System.out.println("Pick an Item to Sell - Get NixonBucks equal to the damage of weapon");
		player.printBackpack();
		System.out.println((player.getBackPack().size() + 1) + ": BACK TO VENDOR");
		int choice = 0;
		while (true) {
			try {
				choice = Integer.valueOf(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Type a number that exists here");
				continue;
			}
			// cant sell your last weapon
			if (player.getBackPack().size() == 1) {
				System.out.println("You Only Have One Weapon, You Can't Sell It, Stupid!");
				break;
			}
			// check that its not your last equippable weapon to be sold
			boolean weaponCanBeSold = youCanSellWeapon(player, player.getBackPack().get(choice - 1));
			if (!weaponCanBeSold) {
				System.out.println("You can't sell your last equippable weapon");
				break;
			}
			// BACK TO VENDOR OPTION
			if (choice == (player.getBackPack().size() + 1)) {
				break;
			}

			if (choice > player.getBackPack().size() + 1 || choice < 0) {
				System.out.println("Type a number that exists here");
				continue;
			} else if (choice >= 1 && choice <= player.getBackPack().size()) {
				vendorList.add(player.getBackPack().get(choice - 1));
				player.sellWeapon(choice - 1);
				System.out.println("Weapon Sold");
				break;
			}
		}
	}

	private List<Weapon> farnsworthWeapons = WeaponsListCreator.createListWeaponsFromCsvFile("WeaponsFarnsworth.csv");

	private void startProfessorVendor(Player player) {
		int choice = 0;
		while (true) {
			printPlayer(player);
			System.out.println("1: EXIT");
			System.out.println("2: SELL ONE OF YOUR ITEMS");
			for (int i = 0; i < farnsworthWeapons.size(); i++) {
				System.out.println(
						(i + 3) + ": " + farnsworthWeapons.get(i) + " - Price: " + farnsworthWeapons.get(i).getPrice());
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
			if (choice == Integer.parseInt("2")) {
				sellItemsMenu(farnsworthWeapons, player);
			}
			// if number doesnt exist in backpack, continue
			else if (choice > farnsworthWeapons.size() + 2 || choice <= 0) {
				System.out.println("No weapon exists there");
				continue;
			}
			// check you have enough NixonBucks
			else if (player.getNixonBucks() < farnsworthWeapons.get(choice - 3).getPrice()) {
				System.out.println("You don't have enough NixonBucks for this weapon");
				continue;
			} else {
				// else add weapon to backpack weapon
				boolean alreadyInBackpack = player.checkForWeaponInBackpack(farnsworthWeapons.get(choice - 3));
				if (alreadyInBackpack) {
					System.out.println("You already have this weapon");
					continue;
				} else {
					player.addWeaponToBackpack(farnsworthWeapons.get(choice - 3));
					System.out.println("Weapon Bought and Added to BackPack");
					player.setNixonBucks(player.getNixonBucks() - farnsworthWeapons.get(choice - 3).getPrice());
					continue;
				}
			}
		}
	}

	private boolean doneDumpsterOnceAlready = false;

	private void startDumpster(Player player) {
		if (!doneDumpsterOnceAlready) {
			doneDumpsterOnceAlready = true;
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
		} else {
			System.out.println("Zoidberg: 'Look at my items why not'");
			nextLine(scanner);
		}
		startZoidbergVendor(player);
	}

	private List<Weapon> zoidbergWeapons = WeaponsListCreator.createListWeaponsFromCsvFile("WeaponsZoidberg.csv");

	private void startZoidbergVendor(Player player) {
		int choice = 0;
		while (true) {
			printPlayer(player);
			System.out.println("1: EXIT");
			System.out.println("2: SELL ONE OF YOUR ITEMS");
			for (int i = 0; i < zoidbergWeapons.size(); i++) {
				System.out.println(
						(i + 3) + ": " + zoidbergWeapons.get(i) + " - Price: " + zoidbergWeapons.get(i).getPrice());
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
			if (choice == Integer.parseInt("2")) {
				sellItemsMenu(zoidbergWeapons, player);
			}
			// if number doesnt exist in backpack, continue
			else if (choice > zoidbergWeapons.size() + 2 || choice <= 0) {
				System.out.println("No weapon exists there");
				continue;
			}
			// check you have enough NixonBucks
			else if (player.getNixonBucks() < zoidbergWeapons.get(choice - 3).getPrice()) {
				System.out.println("You don't have enough NixonBucks for this weapon");
				continue;
			} else {
				// else add weapon to backpack weapon
				boolean alreadyInBackpack = player.checkForWeaponInBackpack(zoidbergWeapons.get(choice - 3));
				if (alreadyInBackpack) {
					System.out.println("You already have this weapon");
					continue;
				} else {
					player.addWeaponToBackpack(zoidbergWeapons.get(choice - 3));
					System.out.println("Weapon Bought and Added to BackPack");
					player.setNixonBucks(player.getNixonBucks() - zoidbergWeapons.get(choice - 3).getPrice());
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
			System.out.println("3: Fishy Joe's");
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

			else if (choice.equals("1") && !momCorpCompleted) {
				momCorpCompleted = startMomCorp(player);
			} else if (choice.equals("1") && momCorpCompleted) {
				System.out.println("You've already cleared MomCorp");
				printLineBreak();
				continue;
			}

			else if (choice.equals("2") && !slurmFactoryCompleted) {
				slurmFactoryCompleted = startSlurmFactory(player);
			} else if (choice.equals("2") && slurmFactoryCompleted) {
				System.out.println("You've already cleared the Slurm Factory");
				printLineBreak();
				continue;
			}

			else if (choice.equals("3") && !fishyJoesCompleted) {
				fishyJoesCompleted = startFishyJoes(player);
			} else if (choice.equals("3") && fishyJoesCompleted) {
				System.out.println("You've already cleared Fishy Joe's");
				printLineBreak();
				continue;
			}

			else if (choice.equalsIgnoreCase("4")) {
				startLaboratory(player);
				continue;
			} else if (choice.equalsIgnoreCase("5")) {
				startDumpster(player);
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
				System.out.println("You should probably check out these places, and clear them out!");
				printLineBreak();
				continue;
			}

		}
	}

	private boolean seenMomAlready = false;

	private boolean startMomCorp(Player player) {
		boolean momCorpCompleted = false;
		boolean firstFight = false;
		boolean secondFight = false;
		if (!seenMomAlready) {
			seenMomAlready = true;
			System.out.println("YOU ENTER MOMCORP");
			nextLine(scanner);
			System.out.println("MOM: 'Igner, Larry, get this crap sack off my property!'");
			nextLine(scanner);
		} else {
			System.out.println("YOU ENTER MOMCORP...again");
			nextLine(scanner);
			System.out.println("Mom: 'Come back for more aye!'");
			nextLine(scanner);
		}
		firstFight = combat.twoEnemies(player, "Igner", 3, "Larry", 3, true);
		System.out.println("MOM: 'Those two idiots couldn't beat you but I WILL!'");
		nextLine(scanner);
		System.out.println("MOM: 'Walt, get over here an show these other two how it's done!'");
		nextLine(scanner);
		secondFight = combat.twoEnemies(player, "Walt", 4, "Mom", 5, true);
		if (firstFight && secondFight) {
			momCorpCompleted = true;
		}
		return momCorpCompleted;
	}

	private boolean seenSlurmAlready = false;

	private boolean startSlurmFactory(Player player) {
		if (!seenSlurmAlready) {
			seenSlurmAlready = true;
			System.out.println("YOU ENTER THE SLURM FACTORY");
			nextLine(scanner);
			System.out.println("You take a can of nice refreshing Slurm, aaah it's highly addictive");
			nextLine(scanner);
			System.out.println("Just as you are finishing off the can, a shadow is cast over you...");
			nextLine(scanner);
			System.out.println("The Slurm Queen has spotted you taking free Slurm");
			System.out.println("She has slung a huge dollop of Super Slurm all over you!");
			nextLine(scanner);
			System.out.println("mmmm it tastes amazing, but now all you can think about is MORE SLURM");
			nextLine(scanner);
			System.out.println("So much so that you can't remember how to crit!");
			nextLine(scanner);
			System.out.println("and so the great Slurm Battle begins...");
			nextLine(scanner);
		} else {
			System.out.println("YOU ENTER THE SLURM FACTORY...again");
			nextLine(scanner);
			System.out.println("Again - The Slurm Queen Covers you with Super Slurm");
			nextLine(scanner);
			System.out.println("You can't Crit!");
			nextLine(scanner);
		}
		boolean slurmFactoryCompleted = combat.twoEnemies(player, "Grunka Lunka", 5, "Slurm Queen", 6, false);
		return slurmFactoryCompleted;
	}

	private boolean seenFishyAlready = false;

	private boolean startFishyJoes(Player player) {
		int initialPlayerStrength = player.getStrength();
		boolean fishyJoesCompletedLocal = false;
		if (!seenFishyAlready) {
			seenFishyAlready = true;
			System.out.println("YOU ENTER FISHY JOE'S");
			nextLine(scanner);
			System.out.println("Fishy Joe: 'Hey friend, have a Poppler'");
			nextLine(scanner);
			System.out.println("Dirty Hippy: 'Hey man eating animals is wrong'");
			nextLine(scanner);
			System.out.println("You spit out the Poppler");
			System.out.println("Not for moral reasons, it just tastes old and mouldy!");
			nextLine(scanner);
		} else {
			System.out.println("YOU ENTER FISHY JOE'S...again");
			nextLine(scanner);
			System.out.println("You pick a Poppler up off the ground and eat it...mmmmmm");
			nextLine(scanner);
		}
		// check that you can actually minus 2 from player strength
		if (player.getStrength() >= 3) {
			player.setStrength(initialPlayerStrength - 2);
			System.out.println("Fishy Joe: 'Haha, that Poppler was SO old it'll take years off your life!'");
			nextLine(scanner);
			System.out.println("Your Strength is now TWO less");
			nextLine(scanner);
			System.out.println("Dirty Hippy: 'I don't eat animals, but I'm about to kill one now!'");
			nextLine(scanner);
			fishyJoesCompletedLocal = combat.twoEnemies(player, "Dirty Protesting Hippy", 5, "Fishy Joe", 5, true);
		} else {
			System.out.println("Fishy Joe: 'Haha, that Poppler was SO old it'll take years off your life!'");
			nextLine(scanner);
			System.out.println("Dirty Hippy: 'I don't eat animals, but I'm about to kill one now!'");
			nextLine(scanner);
			fishyJoesCompletedLocal = combat.twoEnemies(player, "Dirty Protesting Hippy", 5, "Fishy Joe", 5, true);
		}
		player.setStrength(initialPlayerStrength);
		return fishyJoesCompletedLocal;
	}

	private boolean doneHermesOnceAlready = false;

	private void startHermes(Player player) {
		if (!doneHermesOnceAlready) {
			doneHermesOnceAlready = true;
			System.out.println("Hermes: 'My Manwich!'");
			nextLine(scanner);
			System.out.println("Hermes: 'Seeing that filthy crab back here, I've lost my appetite'");
			nextLine(scanner);
			System.out.println("Hermes: 'But not my appetite for Limbo!'");
			nextLine(scanner);
			System.out.println("Hermes: 'I'm going to set this Limbo at between 1 and 10 centimeters'");
			nextLine(scanner);
			System.out.println("Hermes: 'Guess how high I have set it and I'll give you that amount of NixonBucks'");
			nextLine(scanner);
			System.out.println(
					"Hermes: 'But I'm no fool, 3 guesses and if you get it wrong I take half of that amount of your money!'");
			nextLine(scanner);
		} else {
			System.out.println("Hermes: 'Come to play again aye'");
		}

		while (true) {
			System.out.println("Your NixonBucks: " + player.getNixonBucks());
			int limboHeight = random.nextInt(10) + 1;
			int guess = 0;
			int counter = 0;

			while (true) {
				if (counter == 3) {
					if (player.getNixonBucks() <= 0) {
						player.setNixonBucks(0);
						System.out.println(
								"Hermes: 'Nope, it was " + limboHeight + ", but I can't take money from a poor man!'");
						break;
					} else if (player.getNixonBucks() >= (limboHeight / 2)) {
						System.out.println(
								"Hermes: 'That's three guesses, it was " + limboHeight + ", ...money for me!'");
						player.setNixonBucks(player.getNixonBucks() - (limboHeight / 2));
						break;
					} else if (player.getNixonBucks() > 0 && player.getNixonBucks() < (limboHeight / 2)) {
						player.setNixonBucks(0);
						System.out.println("Hermes: 'It was " + limboHeight + ". I'm taking you for all you got now!'");
						break;
					}
				}
				System.out.println("Hermes: 'Take a guess:'");
				try {
					guess = Integer.parseInt(scanner.nextLine());
				} catch (Exception e) {
					System.out.println("Hermes: 'C'mon mun, take a proper guess'");
					continue;
				}
				if (guess <= 0 || guess > 10) {
					System.out.println("Hermes: 'Between 1 and 10, you would not even make Bureaucrat Level 99'");
					continue;
				} else if (guess == limboHeight) {
					System.out.println("Hermes: 'You got it, here is " + limboHeight + " NixonBucks");
					player.setNixonBucks(player.getNixonBucks() + limboHeight);
					break;
				} else if (guess < limboHeight) {
					if (counter == 2) {
						counter++;
						continue;
					} else {
						System.out.println("Hermes: 'Higher...'");
						counter++;
						continue;
					}
				} else if (guess > limboHeight) {
					if (counter == 2) {
						counter++;
						continue;
					} else {
						System.out.println("Hermes: 'Lower...'");
						counter++;
						continue;
					}
				}
			}
			String choice = "";
			while (!choice.equalsIgnoreCase("y") || !choice.equalsIgnoreCase("q")) {
				System.out.println("Hermes: 'Play again - press y'");
				System.out.println("Hermes: 'Quit - press q'");
				choice = scanner.nextLine();
				if (choice.equalsIgnoreCase("q") || choice.equalsIgnoreCase("y")) {
					break;
				}
			}
			if (choice.equalsIgnoreCase("q")) {
				System.out.println("Your NixonBucks: " + player.getNixonBucks());
				break;
			} else if (choice.equalsIgnoreCase("y")) {
				continue;
			}
		}
	}

	private boolean alreadyDoneLeela = false;

	private void startLeela(Player player) {
		if (!alreadyDoneLeela) {
			alreadyDoneLeela = true;
			System.out.println("Leela: 'Well this is the moment we should have trained for'");
			nextLine(scanner);
			System.out.println("Leela: 'But I have got something for you'");
			nextLine(scanner);
			System.out.println("Leela: 'Heeeeee-YA'");
			nextLine(scanner);
			System.out.println("Your Agility is increased by 1");
			nextLine(scanner);
			player.setAgility(player.getAgility() + 1);
			StoryTeller.printPlayer(player);
		} else {
			System.out.println("Leela: 'I'll plus my boot up your ass if you think I'm that easy'");
			nextLine(scanner);
		}
	}

	private boolean alreadyDoneBender = false;

	private void startBender(Player player) {
		if (!alreadyDoneBender) {
			alreadyDoneBender = true;
			System.out.println("Bender: 'Well hot diggity daffodil!'");
			nextLine(scanner);
			System.out.println("Bender: 'You look like a worthless Meat Bag..'");
			nextLine(scanner);
			System.out.println("Bender: 'You could do with a bit of Bender-ising baby'");
			nextLine(scanner);
			AsciiArtReader.readArt("Bender.txt");
			System.out.println("Your Strength is increased by 1");
			nextLine(scanner);
			player.setStrength(player.getStrength() + 1);
			StoryTeller.printPlayer(player);
		} else {
			System.out.println("Bender: 'Bite my shiny metal ASS'");
			nextLine(scanner);
		}
	}

	public void finalBoss(Player player) {
		System.out.println("You walk down a pretty lane with flowers and grass");
		System.out.println("and get the feeling that all this will be over soon");
		nextLine(scanner);
		System.out.println("The sound of what can only be descibed as an Angry Machine gets louder and louder");
		nextLine(scanner);
		System.out.println("SUDDENLY...");
		nextLine(scanner);
		AsciiArtReader.readArt("Hypnotoad.txt");
		printLineBreak();
		nextLine(scanner);
		Weapon angryMachine = new Weapon("Mind Control", 9001, 9001, 9001, WeaponType.INTELLIGENCE, 9001);
		Enemy hypnoToad = new Enemy("THE HYPNOTOAD", 9001, 9001, 9001, angryMachine);
		combat.printCombatHasBegun();
		nextLine(scanner);
		combat.printVersusText(player, hypnoToad);
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		nextLine(scanner);
		System.out.println("...");
		nextLine(scanner);
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		nextLine(scanner);
		AsciiArtReader.readArt("Hypnotoad.txt");
		nextLine(scanner);
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		System.out.println("ALL GLORY TO THE HYPNOTOAD");
		// game ends
	}

	public static void nextLine(Scanner scanner) {
		String input = scanner.nextLine();
		input.strip();
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

}
