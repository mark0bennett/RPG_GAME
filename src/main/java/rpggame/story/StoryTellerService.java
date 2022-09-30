package rpggame.story;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import rpggame.gamecharacter.player.Player;
import rpggame.utils.WeaponsListCreator;
import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public class StoryTellerService {

	private final Scanner scanner;

	public StoryTellerService(Scanner scanner) {
		this.scanner = scanner;
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
		Player player = new Player(name, strength, agility, intelligence);
		printPlayer(player);
		return player;
	}

	private boolean youCanSellWeapon(Player player, Weapon weapon) {
		boolean weaponCanBeSold = false;
		for (Weapon weaponInBackpack : player.getBackPack()) {
			if ((weaponInBackpack.getAgilityRequired() <= player.getAgility()
					&& weaponInBackpack.getIntelligenceRequired() <= player.getIntelligence())
					&& !weaponInBackpack.equals(weapon)) {
				weaponCanBeSold = true;
			}
		}
		return weaponCanBeSold;
	}

	private void sellItemsMenu(List<Weapon> vendorList, Player player, WeaponType vendorType) {
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
			// BACK TO VENDOR OPTION
			if (choice == (player.getBackPack().size() + 1)) {
				break;
			}
			if (choice > player.getBackPack().size() + 1 || choice < 0) {
				System.out.println("Type a number that exists here");
				continue;
			}
			// if its a valid number in your backpack
			if (choice >= 1 && choice <= player.getBackPack().size()) {
				// check that its not your last equippable weapon to be sold
				boolean weaponCanBeSold = youCanSellWeapon(player, player.getBackPack().get(choice - 1));
				if (!weaponCanBeSold) {
					System.out.println("You can't sell your last equippable weapon");
					break;
				} else {
					// don't add the weapon to vendor list (messy and money exploit)
//					vendorList.add(player.getBackPack().get(choice - 1));
					player.sellWeapon(choice - 1, vendorType);
					System.out.println("Weapon Sold");
					break;
				}
			}
		}
	}

	private List<Weapon> farnsworthWeapons = WeaponsListCreator.createListWeaponsFromCsvFile("WeaponsFarnsworth.csv");

	public void startProfessorVendor(Player player) {
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
				sellItemsMenu(farnsworthWeapons, player, WeaponType.INTELLIGENCE);
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

	private List<Weapon> zoidbergWeapons = WeaponsListCreator.createListWeaponsFromCsvFile("WeaponsZoidberg.csv");

	public void startZoidbergVendor(Player player) {
		int choice = 0;
		while (true) {
			printPlayer(player);
			System.out.println("1: EXIT");
			System.out.println("2: SELL ONE OF YOUR ITEMS");
			for (int i = 0; i < zoidbergWeapons.size(); i++) {
				System.out.println(String.format("%s: %s - Price %s",
						(i + 3),
						zoidbergWeapons.get(i),
						zoidbergWeapons.get(i).getPrice()));
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
				sellItemsMenu(zoidbergWeapons, player, WeaponType.AGILITY);
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

	public void hermesGuessingGame(Player player) {
		while (true) {
			System.out.println("Your NixonBucks: " + player.getNixonBucks());
			Random random = new Random();
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
