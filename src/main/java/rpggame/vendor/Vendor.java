package rpggame.vendor;

import java.util.List;
import java.util.Scanner;

import rpggame.gamecharacter.player.Player;
import rpggame.story.StoryTellerService;
import rpggame.utils.WeaponsListCreator;
import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public class Vendor {

	private List<Weapon> farnsworthWeapons = WeaponsListCreator.createListWeaponsFromCsvFile("WeaponsFarnsworth.csv");
	private List<Weapon> zoidbergWeapons = WeaponsListCreator.createListWeaponsFromCsvFile("WeaponsZoidberg.csv");
	private final Scanner scanner;

	public Vendor(Scanner scanner) {
		this.scanner = scanner;
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
					player.sellWeapon(choice - 1, vendorType);
					System.out.println("Weapon Sold");
					break;
				}
			}
		}
	}

	public void startProfessorVendor(Player player) {
		int choice = 0;
		while (true) {
			StoryTellerService.printPlayer(player);
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

	public void startZoidbergVendor(Player player) {
		int choice = 0;
		while (true) {
			StoryTellerService.printPlayer(player);
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

}
