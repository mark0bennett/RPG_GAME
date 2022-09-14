package story.firstpathsplit;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;

public class Graveyard {
	
	private static final int MS_1000 = 1000;
	private static final int MS_1500 = 1500;

	public static boolean startGraveyard(Player player, Scanner scanner) throws InterruptedException {

		Thread.sleep(MS_1000);
		System.out.println("YOU ENTER THE GRAVEYARD...cautiously...");
		Thread.sleep(MS_1000);
		System.out.println("What's that!");
		Thread.sleep(MS_1500);
		System.out.println("...nothing");
		Thread.sleep(MS_1000);
		System.out.println("You wipe some dust from a gravestone to read the inscription...");
		Thread.sleep(MS_1000);
		System.out.println("Here Lies...");
		Thread.sleep(MS_1000);
		System.out.println("...");
		Thread.sleep(MS_1000);
		System.out.println("Here Lies Dr. Zoidberg - Many said that he was...");
		Thread.sleep(MS_1000);
		System.out.println("*uuuuuuuh*");
		System.out.println("Man I hate Zombies, here we go!");
		
		boolean graveyardCompleted = Combat.oneEnemyNoCrits(player, EnemyFactory.createEnemyCustomLevel(player, 0), scanner);
		
		//TODO: fight 2 enemies one after the other in seperate battles, how to return correct boolean once both are won?

		return graveyardCompleted;
	}

}
