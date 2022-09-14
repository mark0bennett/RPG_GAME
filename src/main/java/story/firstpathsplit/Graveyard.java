package story.firstpathsplit;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;
import rpggame.RpgGameApp;

public class Graveyard {
	
	public static boolean startGraveyard(Player player, Scanner scanner) throws InterruptedException {

		System.out.println("YOU ENTER THE GRAVEYARD...cautiously...");
		RpgGameApp.nextLine(scanner);
		System.out.println("What's that!");
		RpgGameApp.nextLine(scanner);
		System.out.println("...nothing");
		RpgGameApp.nextLine(scanner);
		System.out.println("You wipe some dust from a gravestone to read the inscription...");
		RpgGameApp.nextLine(scanner);
		System.out.println("Here Lies...");
		RpgGameApp.nextLine(scanner);
		System.out.println("...");
		RpgGameApp.nextLine(scanner);
		System.out.println("Here Lies Dr. Zoidberg - Many said that he was...");
		RpgGameApp.nextLine(scanner);
		System.out.println(" Noises - *uuuuuuuh*");
		RpgGameApp.nextLine(scanner);
		System.out.println("Man I hate Zombies, have it you!");
		
		boolean graveyardCompleted = Combat.oneEnemy(player, EnemyFactory.createEnemyCustomLevel(player, 0), scanner, false);
		
		//TODO: fight 2 enemies one after the other in seperate battles, how to return correct boolean once both are won?

		return graveyardCompleted;
	}

}
