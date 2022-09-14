package story.firstpathsplit;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;

public class Church {
	
	private static final int MS_1000 = 1000;
	private static final int MS_1500 = 1500;
	
	public static boolean startChurch(Player player, Scanner scanner) throws InterruptedException {
		Thread.sleep(MS_1000);
		System.out.println("YOU ENTER THE CHURCH...");
		Thread.sleep(MS_1000);
		System.out.println("It's quiet, a little too quiet...");
		Thread.sleep(MS_1500);
		System.out.println("...");
		System.out.println("*CRASH!!!!!!!!*");
		Thread.sleep(MS_1000);
		System.out.println("Something huge has just smashed through the wall and is coming RIGHT AT YOU!");
		Thread.sleep(MS_1000);
		
		boolean churchCompleted = Combat.oneEnemyNoCrits(player, EnemyFactory.createEnemyCustomLevel(player, 0), scanner);
		
		return churchCompleted;
	}

}
