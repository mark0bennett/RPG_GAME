package rpggame.story;

import java.util.Scanner;

import rpggame.combat.Combat;
import rpggame.person.enemy.Enemy;
import rpggame.person.player.Player;
import rpggame.utils.SoundPlayer;
import rpggame.utils.StoryTextReader;
import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public class StoryTeller implements Story {

	private final Scanner scanner;
	private final Combat combat;
	private final StoryTellerService storyTellerService;

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
		this.storyTellerService = new StoryTellerService(this.scanner);
	}

	public void intro() {
		StoryTextReader.read("intro.txt");
	}

	public Player createPlayer() {
		return storyTellerService.createPlayer();
	}

	public void startFirstFight(Player player) {
		StoryTextReader.read("startFirstFight.txt");
		combat.oneEnemy(player, "Zapp Brannigan", 0, false);
	}

	public void firstPathsSplit(Player player) {
		System.out.println("You walk alone for many miles before seeing the path split into three...");
		StoryTellerService.nextLine(scanner);
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
			} else if (choice.equals("1") && !churchCompleted) {
				churchCompleted = startChurch(player);
			} else if (choice.equals("1") && churchCompleted) {
				System.out.println("You've already cleared the Church");
				StoryTellerService.printLineBreak();
				continue;
			} else if (choice.equals("2") && !graveyardCompleted) {
				graveyardCompleted = startGraveyard(player);
			} else if (choice.equals("2") && graveyardCompleted) {
				System.out.println("You've already cleared the Graveyard");
				StoryTellerService.printLineBreak();
				continue;
			} else if (choice.equals("3") && !forestCompleted) {
				forestCompleted = startForest(player);
			} else if (choice.equals("3") && forestCompleted) {
				System.out.println("You've already cleared the Graveyard");
				StoryTellerService.printLineBreak();
				continue;
			}
		}
	}

	private boolean seenChurchAlready = false;

	private boolean startChurch(Player player) {
		if (!seenChurchAlready) {
			seenChurchAlready = true;
			StoryTextReader.read("church.txt");
		} else {
			System.out.println("YOU ENTER THE CHURCH...again");
			StoryTellerService.nextLine(scanner);
		}
		boolean churchCompleted = combat.oneEnemy(player, "Preacherbot", 1, false);
		return churchCompleted;
	}

	private boolean seenGraveyardAlready = false;

	private boolean startGraveyard(Player player) {
		if (!seenGraveyardAlready) {
			seenGraveyardAlready = true;
			StoryTextReader.read("graveyard.txt");
		} else {
			System.out.println("YOU ENTER THE GRAVEYARD...again...");
			StoryTellerService.nextLine(scanner);
		}
		boolean graveyardCompleted = combat.oneEnemy(player, "Zombie Jesus", 2, false);
		return graveyardCompleted;
	}

	private boolean seenForestAlready = false;

	private boolean startForest(Player player) {
		if (!seenForestAlready) {
			seenForestAlready = true;
			StoryTextReader.read("forest.txt");
		} else {
			System.out.println("YOU ENTER THE FOREST...again...");
			StoryTellerService.nextLine(scanner);
		}
		boolean forestCompleted = combat.oneEnemy(player, "Ranger Park the Park Ranger", 2, false);
		return forestCompleted;
	}

	private void afterFirstPathsSplit(Player player) {
		StoryTextReader.read("afterFirstPaths.txt");
		combat.oneEnemy(player, "LRRR Ruler of the Planet Omicron Persei 8", 4, true);
	}

	public void meetFirstNPC(Player player) {
		StoryTextReader.read("meetFry.txt");
		player.setIntelligence(player.getIntelligence() + 1);
		StoryTellerService.printPlayer(player);
	}

	public void mainPathsSplit(Player player) {
		System.out.println("As you continue, the landscape opens up providing you with many different options...");
		StoryTellerService.nextLine(scanner);
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
			} else if (choice.equals("1") && !riverCompleted) {
				riverCompleted = startRiver(player);
			} else if (choice.equals("1") && riverCompleted) {
				System.out.println("You've already cleared the River");
				StoryTellerService.printLineBreak();
				continue;
			} else if (choice.equals("2") && !appliedChryogenicsCompleted) {
				appliedChryogenicsCompleted = startAppliedChryogenics(player);
			} else if (choice.equals("2") && appliedChryogenicsCompleted) {
				System.out.println("You've already cleared Applied Chryogenics");
				StoryTellerService.printLineBreak();
				continue;
			} else if (choice.equals("3") && !robotArmsCompleted) {
				robotArmsCompleted = startRobotArms(player);
			} else if (choice.equals("3") && robotArmsCompleted) {
				System.out.println("You've already cleared Robot Arms Apartments");
				StoryTellerService.printLineBreak();
				continue;
			} else if (choice.equals("4")) {
				startLaboratory(player);
			} else if (choice.equals("5")) {
				startDumpster(player);
			} else if (choice.equals("6") && riverCompleted && appliedChryogenicsCompleted && robotArmsCompleted) {
				afterMainPaths();
				break;
			} else {
				System.out.println("You should probably check out these places, and clear them out!");
				StoryTellerService.printLineBreak();
				continue;
			}
		}
	}

	private boolean seenRiverAlready = false;

	private boolean startRiver(Player player) {
		if (!seenRiverAlready) {
			seenRiverAlready = true;
			StoryTextReader.read("river.txt");
		} else {
			System.out.println("YOU ENTER THE RIVER...again");
			StoryTellerService.nextLine(scanner);
		}
		boolean riverCompleted = combat.twoEnemies(player, "Joan River's Head", 2, "Horrible Gelatinous Blob", 3, true);
		return riverCompleted;
	}

	private boolean seenAppliedAready = false;

	private boolean startAppliedChryogenics(Player player) {
		if (!seenAppliedAready) {
			seenAppliedAready = true;
			StoryTextReader.read("appliedChryo.txt");
		} else {
			System.out.println("YOU ENTER APPLIED CHRYOGENICS...again");
			StoryTellerService.nextLine(scanner);
		}
		boolean appliedChryogenicsCompleted = combat.twoEnemies(player, "Michelle", 2, "Ipji", 3, true);
		return appliedChryogenicsCompleted;
	}

	private boolean seenRobotArmsAlready = false;

	private boolean startRobotArms(Player player) {
		if (!seenRobotArmsAlready) {
			seenRobotArmsAlready = true;
			StoryTextReader.read("robotArms.txt");
		} else {
			System.out.println("YOU ENTER ROBOT ARMS APARTMENTS...again");
			StoryTellerService.nextLine(scanner);
		}
		boolean robotArmsCompleted = combat.twoEnemies(player, "Robot Devil", 3, "Roberto", 3, true);
		return robotArmsCompleted;
	}

	private boolean doneLaboratoryOnceAlready = false;

	private void startLaboratory(Player player) {
		if (!doneLaboratoryOnceAlready) {
			doneLaboratoryOnceAlready = true;
			StoryTextReader.read("lab.txt");
		} else {
			System.out.println("Professor: 'Look at my items'");
			StoryTellerService.nextLine(scanner);
		}
		storyTellerService.startProfessorVendor(player);
	}

	private boolean doneDumpsterOnceAlready = false;

	private void startDumpster(Player player) {
		if (!doneDumpsterOnceAlready) {
			doneDumpsterOnceAlready = true;
			StoryTextReader.read("dumpster.txt");
		} else {
			System.out.println("Zoidberg: 'Look at my items why not'");
			StoryTellerService.nextLine(scanner);
		}
		SoundPlayer.playSound("ZoidbergUseful.wav");
		storyTellerService.startZoidbergVendor(player);
	}

	private void afterMainPaths() {
		StoryTextReader.read("afterMainPaths.txt");
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
			} else if (choice.equals("1") && !momCorpCompleted) {
				momCorpCompleted = startMomCorp(player);
			} else if (choice.equals("1") && momCorpCompleted) {
				System.out.println("You've already cleared MomCorp");
				StoryTellerService.printLineBreak();
				continue;
			} else if (choice.equals("2") && !slurmFactoryCompleted) {
				slurmFactoryCompleted = startSlurmFactory(player);
			} else if (choice.equals("2") && slurmFactoryCompleted) {
				System.out.println("You've already cleared the Slurm Factory");
				StoryTellerService.printLineBreak();
				continue;
			} else if (choice.equals("3") && !fishyJoesCompleted) {
				fishyJoesCompleted = startFishyJoes(player);
			} else if (choice.equals("3") && fishyJoesCompleted) {
				System.out.println("You've already cleared Fishy Joe's");
				StoryTellerService.printLineBreak();
				continue;
			} else if (choice.equalsIgnoreCase("4")) {
				startLaboratory(player);
				continue;
			} else if (choice.equalsIgnoreCase("5")) {
				startDumpster(player);
				continue;
			} else if (choice.equalsIgnoreCase("6")) {
				startHermes(player);
				continue;
			} else if (choice.equalsIgnoreCase("7")) {
				startLeela(player);
				continue;
			} else if (choice.equalsIgnoreCase("8")) {
				startBender(player);
				continue;
			} else if (choice.equals("9") && momCorpCompleted == true && slurmFactoryCompleted == true
					&& fishyJoesCompleted == true) {
				break;
			} else {
				System.out.println("You should probably check out these places, and clear them out!");
				StoryTellerService.printLineBreak();
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
			StoryTellerService.nextLine(scanner);
			System.out.println("MOM: 'Igner, Larry, get this crap sack off my property!'");
			StoryTellerService.nextLine(scanner);
		} else {
			System.out.println("YOU ENTER MOMCORP...again");
			StoryTellerService.nextLine(scanner);
			System.out.println("Mom: 'Come back for more aye!'");
			StoryTellerService.nextLine(scanner);
		}
		firstFight = combat.twoEnemies(player, "Igner", 3, "Larry", 3, true);
		System.out.println("MOM: 'Those two idiots couldn't beat you but I WILL!'");
		StoryTellerService.nextLine(scanner);
		System.out.println("MOM: 'Walt, get over here an show these other two how it's done!'");
		StoryTellerService.nextLine(scanner);
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
			StoryTextReader.read("slurm.txt");
		} else {
			System.out.println("YOU ENTER THE SLURM FACTORY...again");
			StoryTellerService.nextLine(scanner);
			System.out.println("Again - The Slurm Queen Covers you with Super Slurm");
			StoryTellerService.nextLine(scanner);
			System.out.println("You can't Crit!");
			StoryTellerService.nextLine(scanner);
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
			StoryTextReader.read("fishy.txt");
		} else {
			System.out.println("YOU ENTER FISHY JOE'S...again");
			StoryTellerService.nextLine(scanner);
			System.out.println("You pick a Poppler up off the ground and eat it...mmmmmm");
			StoryTellerService.nextLine(scanner);
		}
		// check that you can actually minus 2 from player strength
		if (player.getStrength() >= 3) {
			player.setStrength(initialPlayerStrength - 2);
			StoryTextReader.read("fishyOpt1.txt");
			fishyJoesCompletedLocal = combat.twoEnemies(player, "Dirty Protesting Hippy", 5, "Fishy Joe", 5, true);
		} else {
			StoryTextReader.read("fishyOpt2.txt");
			fishyJoesCompletedLocal = combat.twoEnemies(player, "Dirty Protesting Hippy", 5, "Fishy Joe", 5, true);
		}
		player.setStrength(initialPlayerStrength);
		return fishyJoesCompletedLocal;
	}

	private boolean doneHermesOnceAlready = false;

	private void startHermes(Player player) {
		if (!doneHermesOnceAlready) {
			doneHermesOnceAlready = true;
			StoryTextReader.read("hermes.txt");
		} else {
			System.out.println("Hermes: 'Come to play again aye'");
		}
		String playOrQuit = "";
		while (!playOrQuit.equalsIgnoreCase("y") || !playOrQuit.equalsIgnoreCase("q")) {
			System.out.println("Hermes: 'Play - press y'");
			System.out.println("Hermes: 'Quit - press q'");
			playOrQuit = scanner.nextLine();
			if (playOrQuit.equalsIgnoreCase("q") || playOrQuit.equalsIgnoreCase("y")) {
				break;
			}
		}
		if (playOrQuit.equalsIgnoreCase("q")) {
		} else if (playOrQuit.equalsIgnoreCase("y")) {
			storyTellerService.hermesGuessingGame(player);
		}
	}

	private boolean alreadyDoneLeela = false;

	private void startLeela(Player player) {
		if (!alreadyDoneLeela) {
			alreadyDoneLeela = true;
			StoryTextReader.read("leela.txt");
			player.setAgility(player.getAgility() + 1);
			StoryTellerService.printPlayer(player);
		} else {
			System.out.println("Leela: 'I'll put my boot up your ass if you think I'm that easy'");
			StoryTellerService.nextLine(scanner);
		}
	}

	private boolean alreadyDoneBender = false;

	private void startBender(Player player) {
		if (!alreadyDoneBender) {
			alreadyDoneBender = true;
			StoryTextReader.read("bender.txt");
			player.setStrength(player.getStrength() + 1);
			StoryTellerService.printPlayer(player);
		} else {
			SoundPlayer.playSound("BiteMyHugeAss.wav");
			System.out.println("Bender: 'Bite my Colossal metal ASS'");
			StoryTellerService.nextLine(scanner);
		}
	}

	public void finalBoss(Player player) {
		StoryTextReader.read("boss1.txt");
		Weapon angryMachine = new Weapon("Mind Control", 9001, 9001, 9001, WeaponType.INTELLIGENCE, 9001);
		Enemy hypnoToad = new Enemy("THE HYPNOTOAD", 9001, 9001, 9001, angryMachine);
		combat.printCombatHasBegun();
		StoryTellerService.nextLine(scanner);
		combat.printVersusText(player, hypnoToad);
		StoryTextReader.read("boss2.txt");
		// game ends
	}

}
