package rpggame.story;

import java.util.Scanner;

import rpggame.combat.Combat;
import rpggame.gamecharacter.enemy.Enemy;
import rpggame.gamecharacter.player.Player;
import rpggame.utils.Printer;
import rpggame.utils.SoundPlayer;
import rpggame.utils.StoryTextReader;
import rpggame.vendor.Vendor;
import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public class StoryTeller implements Story {

	private final Scanner scanner;
	private final Combat combat;
	private final StoryTellerService storyTellerService;
	private final Vendor vendor;

	public StoryTeller() {
		this.scanner = new Scanner(System.in);
		this.combat = new Combat(this.scanner);
		this.storyTellerService = new StoryTellerService(this.scanner);
		this.vendor = new Vendor(this.scanner);
	}

	public void intro() {
		StoryTextReader.read("intro.txt");
	}

	public Player createPlayer() {
		return storyTellerService.createPlayer();
	}

	public void startFirstFight(Player player) {
		StoryTextReader.read("startFirstFight.txt");
		combat.createOneEnemy(player, "Zapp Brannigan", 0, false);
	}

	public void firstPathsSplit(Player player) {
		System.out.println("You walk alone for many miles before seeing the path split into three...");
		Printer.nextLine(scanner);
		String choice = "";

		while (true) {
			if (player.getProgress().isChurchCompleted() && player.getProgress().isGraveyardCompleted()
					&& player.getProgress().isForestCompleted()) {
				afterFirstPathsSplit(player);
				break;
			}
			System.out.println("Where do you wish to venture to?");
			System.out.println("1: Church");
			System.out.println("2: Graveyard");
			System.out.println("3: Forest");
			choice = scanner.nextLine();
			if (choice.isBlank()) {
				continue;
			} else if (choice.equals("1") && !player.getProgress().isChurchCompleted()) {
				player.getProgress().setChurchCompleted(startChurch(player));
			} else if (choice.equals("1") && player.getProgress().isChurchCompleted()) {
				System.out.println("You've already cleared the Church");
				Printer.printLineBreak();
				continue;
			} else if (choice.equals("2") && !player.getProgress().isGraveyardCompleted()) {
				player.getProgress().setGraveyardCompleted(startGraveyard(player));
			} else if (choice.equals("2") && player.getProgress().isGraveyardCompleted()) {
				System.out.println("You've already cleared the Graveyard");
				Printer.printLineBreak();
				continue;
			} else if (choice.equals("3") && !player.getProgress().isForestCompleted()) {
				player.getProgress().setForestCompleted(startForest(player));
			} else if (choice.equals("3") && player.getProgress().isForestCompleted()) {
				System.out.println("You've already cleared the Graveyard");
				Printer.printLineBreak();
				continue;
			}
		}
	}

	private boolean startChurch(Player player) {
		if (!player.getProgress().isChurchVisitedOnceAlready()) {
			player.getProgress().setChurchVisitedOnceAlready(true);
			StoryTextReader.read("church.txt");
		} else {
			System.out.println("YOU ENTER THE CHURCH...again");
			Printer.nextLine(scanner);
		}
		boolean churchCompleted = combat.createOneEnemy(player, "Preacherbot", 1, false);
		return churchCompleted;
	}

	private boolean startGraveyard(Player player) {
		if (!player.getProgress().isGraveyardVisitedOnceAlready()) {
			player.getProgress().setGraveyardVisitedOnceAlready(true);
			StoryTextReader.read("graveyard.txt");
		} else {
			System.out.println("YOU ENTER THE GRAVEYARD...again...");
			Printer.nextLine(scanner);
		}
		boolean graveyardCompleted = combat.createOneEnemy(player, "Zombie Jesus", 2, false);
		return graveyardCompleted;
	}

	private boolean startForest(Player player) {
		if (!player.getProgress().isForestVisitedOnceAlready()) {
			player.getProgress().setForestVisitedOnceAlready(true);
			StoryTextReader.read("forest.txt");
		} else {
			System.out.println("YOU ENTER THE FOREST...again...");
			Printer.nextLine(scanner);
		}
		boolean forestCompleted = combat.createOneEnemy(player, "Ranger Park the Park Ranger", 2, false);
		return forestCompleted;
	}

	private void afterFirstPathsSplit(Player player) {
		StoryTextReader.read("afterFirstPaths.txt");
		combat.createOneEnemy(player, "LRRR Ruler of the Planet Omicron Persei 8", 4, true);
	}

	public void meetFirstNPC(Player player) {
		StoryTextReader.read("meetFry.txt");
		player.setIntelligence(player.getIntelligence() + 1);
		Printer.printPlayer(player);
	}

	public void mainPathsSplit(Player player) {
		System.out.println("As you continue, the landscape opens up providing you with many different options...");
		Printer.nextLine(scanner);
		String choice = "";

		while (true) {
			System.out.println("Where do you wish to venture too?");
			System.out.println("1: River");
			System.out.println("2: Applied Chryogenics");
			System.out.println("3: Robot Arms Apts.");
			System.out.println("4: Laboratory (Intelligence Vendor)");
			System.out.println("5: Dumpster (Agility Vendor)");
			System.out.println("6: Continue the journey...");
			choice = scanner.nextLine();
			if (choice.isBlank()) {
				continue;
			} else if (choice.equals("1") && !player.getProgress().isRiverCompleted()) {
				player.getProgress().setRiverCompleted(startRiver(player));
			} else if (choice.equals("1") && player.getProgress().isRiverCompleted()) {
				System.out.println("You've already cleared the River");
				Printer.printLineBreak();
				continue;
			} else if (choice.equals("2") && !player.getProgress().isAppliedChryogenicsCompleted()) {
				player.getProgress().setAppliedChryogenicsCompleted(startAppliedChryogenics(player));
			} else if (choice.equals("2") && player.getProgress().isAppliedChryogenicsCompleted()) {
				System.out.println("You've already cleared Applied Chryogenics");
				Printer.printLineBreak();
				continue;
			} else if (choice.equals("3") && !player.getProgress().isRobotArmsCompleted()) {
				player.getProgress().setRobotArmsCompleted(startRobotArms(player));
			} else if (choice.equals("3") && player.getProgress().isRobotArmsCompleted()) {
				System.out.println("You've already cleared Robot Arms Apartments");
				Printer.printLineBreak();
				continue;
			} else if (choice.equals("4")) {
				startLaboratory(player);
			} else if (choice.equals("5")) {
				startDumpster(player);
			} else if (choice.equals("6") && player.getProgress().isRiverCompleted()
					&& player.getProgress().isAppliedChryogenicsCompleted()
					&& player.getProgress().isRobotArmsCompleted()) {
				afterMainPaths();
				break;
			} else {
				System.out.println("You should probably check out these places, and clear them out!");
				Printer.printLineBreak();
				continue;
			}
		}
	}

	private boolean startRiver(Player player) {
		if (!player.getProgress().isRiverVisitedOnceAlready()) {
			player.getProgress().setRiverVisitedOnceAlready(true);
			StoryTextReader.read("river.txt");
		} else {
			System.out.println("YOU ENTER THE RIVER...again");
			Printer.nextLine(scanner);
		}
		boolean riverCompleted = combat
				.createTwoEnemies(player, "Joan River's Head", 2, "Horrible Gelatinous Blob", 3, true);
		return riverCompleted;
	}

	private boolean startAppliedChryogenics(Player player) {
		if (!player.getProgress().isAppliedVisitedAready()) {
			player.getProgress().setAppliedVisitedAready(true);
			StoryTextReader.read("appliedChryo.txt");
		} else {
			System.out.println("YOU ENTER APPLIED CHRYOGENICS...again");
			Printer.nextLine(scanner);
		}
		boolean appliedChryogenicsCompleted = combat.createTwoEnemies(player, "Michelle", 2, "Ipji", 3, true);
		return appliedChryogenicsCompleted;
	}

	private boolean startRobotArms(Player player) {
		if (!player.getProgress().isRobotArmsVisitedOnceAlready()) {
			player.getProgress().setRobotArmsVisitedOnceAlready(true);
			StoryTextReader.read("robotArms.txt");
		} else {
			System.out.println("YOU ENTER ROBOT ARMS APARTMENTS...again");
			Printer.nextLine(scanner);
		}
		boolean robotArmsCompleted = combat.createTwoEnemies(player, "Robot Devil", 3, "Roberto", 3, true);
		return robotArmsCompleted;
	}

	private void startLaboratory(Player player) {
		if (!player.getProgress().isLaboratoryVisitedOnceAlready()) {
			player.getProgress().setLaboratoryVisitedOnceAlready(true);
			StoryTextReader.read("lab.txt");
		} else {
			System.out.println("Professor: 'Look at my items'");
			Printer.nextLine(scanner);
		}
		vendor.startProfessorVendor(player);
	}

	private void startDumpster(Player player) {
		if (!player.getProgress().isDumpsterVisitedOnceAlready()) {
			player.getProgress().setDumpsterVisitedOnceAlready(true);
			StoryTextReader.read("dumpster.txt");
		} else {
			System.out.println("Zoidberg: 'Look at my items why not'");
			Printer.nextLine(scanner);
		}
		SoundPlayer.playSound("ZoidbergUseful.wav");
		vendor.startZoidbergVendor(player);
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
			System.out.println("4: Professor (Intelligence Vendor)");
			System.out.println("5: Zoidberg (Agility Vendor)");
			System.out.println("6: Hermes (+/-NixonBucks)");
			System.out.println("7: Leela (+Agility)");
			System.out.println("8: Bender (+Strenth)");
			System.out.println("9: Continue the journey...to the final Boss!");

			choice = scanner.nextLine();
			if (choice.isBlank()) {
				continue;
			} else if (choice.equals("1") && !player.getProgress().isMomCorpCompleted()) {
				player.getProgress().setMomCorpCompleted(startMomCorp(player));
			} else if (choice.equals("1") && player.getProgress().isMomCorpCompleted()) {
				System.out.println("You've already cleared MomCorp");
				Printer.printLineBreak();
				continue;
			} else if (choice.equals("2") && !player.getProgress().isSlurmFactoryCompleted()) {
				player.getProgress().setSlurmFactoryCompleted(startSlurmFactory(player));
			} else if (choice.equals("2") && player.getProgress().isSlurmFactoryCompleted()) {
				System.out.println("You've already cleared the Slurm Factory");
				Printer.printLineBreak();
				continue;
			} else if (choice.equals("3") && !player.getProgress().isFishyJoesCompleted()) {
				player.getProgress().setFishyJoesCompleted(startFishyJoes(player));
			} else if (choice.equals("3") && player.getProgress().isFishyJoesCompleted()) {
				System.out.println("You've already cleared Fishy Joe's");
				Printer.printLineBreak();
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
			} else if (choice.equals("9") && player.getProgress().isMomCorpCompleted()
					&& player.getProgress().isSlurmFactoryCompleted() && player.getProgress().isFishyJoesCompleted()) {
				break;
			} else {
				System.out.println("You should probably check out these places, and clear them out!");
				Printer.printLineBreak();
				continue;
			}
		}
	}

	private boolean startMomCorp(Player player) {
		boolean momCorpCompleted = false;
		boolean firstFight = false;
		boolean secondFight = false;
		if (!player.getProgress().isMomVisitedOnceAlready()) {
			player.getProgress().setMomVisitedOnceAlready(true);
			System.out.println("YOU ENTER MOMCORP");
			Printer.nextLine(scanner);
			System.out.println("MOM: 'Igner, Larry, get this crap sack off my property!'");
			Printer.nextLine(scanner);
		} else {
			System.out.println("YOU ENTER MOMCORP...again");
			Printer.nextLine(scanner);
			System.out.println("Mom: 'Come back for more aye!'");
			Printer.nextLine(scanner);
		}
		firstFight = combat.createTwoEnemies(player, "Igner", 3, "Larry", 3, true);
		System.out.println("MOM: 'Those two idiots couldn't beat you but I WILL!'");
		Printer.nextLine(scanner);
		System.out.println("MOM: 'Walt, get over here an show these other two how it's done!'");
		Printer.nextLine(scanner);
		secondFight = combat.createTwoEnemies(player, "Walt", 4, "Mom", 5, true);
		if (firstFight && secondFight) {
			momCorpCompleted = true;
		}
		return momCorpCompleted;
	}

	private boolean startSlurmFactory(Player player) {
		if (!player.getProgress().isSlurmVisitedOnceAlready()) {
			player.getProgress().setSlurmVisitedOnceAlready(true);
			StoryTextReader.read("slurm.txt");
		} else {
			System.out.println("YOU ENTER THE SLURM FACTORY...again");
			Printer.nextLine(scanner);
			System.out.println("Again - The Slurm Queen Covers you with Super Slurm");
			Printer.nextLine(scanner);
			System.out.println("You can't Crit!");
			Printer.nextLine(scanner);
		}
		boolean slurmFactoryCompleted = combat.createTwoEnemies(player, "Grunka Lunka", 5, "Slurm Queen", 6, false);
		return slurmFactoryCompleted;
	}

	private boolean startFishyJoes(Player player) {
		int initialPlayerStrength = player.getStrength();
		boolean fishyJoesCompletedLocal = false;
		if (!player.getProgress().isFishyVisitedOnceAlready()) {
			player.getProgress().setFishyVisitedOnceAlready(true);
			StoryTextReader.read("fishy.txt");
		} else {
			System.out.println("YOU ENTER FISHY JOE'S...again");
			Printer.nextLine(scanner);
			System.out.println("You pick a Poppler up off the ground and eat it...mmmmmm");
			Printer.nextLine(scanner);
		}
		// check that you can actually minus 2 from player strength
		if (player.getStrength() >= 3) {
			player.setStrength(initialPlayerStrength - 2);
			StoryTextReader.read("fishyOpt1.txt");
			fishyJoesCompletedLocal = combat
					.createTwoEnemies(player, "Dirty Protesting Hippy", 5, "Fishy Joe", 5, true);
		} else {
			StoryTextReader.read("fishyOpt2.txt");
			fishyJoesCompletedLocal = combat
					.createTwoEnemies(player, "Dirty Protesting Hippy", 5, "Fishy Joe", 5, true);
		}
		player.setStrength(initialPlayerStrength);
		return fishyJoesCompletedLocal;
	}

	private void startHermes(Player player) {
		if (!player.getProgress().isHermesVisitedOnceAlready()) {
			player.getProgress().setHermesVisitedOnceAlready(true);
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

	private void startLeela(Player player) {
		if (!player.getProgress().isLeelaVisitedOnceAlready()) {
			player.getProgress().setLeelaVisitedOnceAlready(true);
			StoryTextReader.read("leela.txt");
			player.setAgility(player.getAgility() + 1);
			Printer.printPlayer(player);
		} else {
			System.out.println("Leela: 'I'll put my boot up your ass if you think I'm that easy'");
		}
	}

	private void startBender(Player player) {
		if (!player.getProgress().isBenderVisitedOnceAlready()) {
			player.getProgress().setBenderVisitedOnceAlready(true);
			StoryTextReader.read("bender.txt");
			player.setStrength(player.getStrength() + 1);
			Printer.printPlayer(player);
		} else {
			SoundPlayer.playSound("BiteMyHugeAss.wav");
			System.out.println("Bender: 'Bite my Colossal metal ASS'");
		}
	}

	public void finalBoss(Player player) {
		StoryTextReader.read("boss1.txt");
		Weapon angryMachine = new Weapon("Mind Control", 9001, 9001, 9001, WeaponType.INTELLIGENCE, 9001);
		Enemy hypnoToad = new Enemy("THE HYPNOTOAD", 9001, 9001, 9001, angryMachine);
		Printer.printCombatHasBegun();
		Printer.nextLine(scanner);
		Printer.printVersusTextOneEnemy(player, hypnoToad);
		StoryTextReader.read("boss2.txt");
		// game ends
	}

}
