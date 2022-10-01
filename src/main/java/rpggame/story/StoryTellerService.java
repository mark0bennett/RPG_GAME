package rpggame.story;

import java.util.Random;
import java.util.Scanner;

import rpggame.gamecharacter.player.Player;

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
				e.getMessage();
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
