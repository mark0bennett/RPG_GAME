package story.firstpathsplit;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;

public class Church {
	
	public static boolean startChurch(Player player, Scanner scanner) throws InterruptedException {
		Thread.sleep(500);
		System.out.println("YOU ENTER THE CHURCH...");
		Thread.sleep(500);
		System.out.println("It's quiet, a little too quiet...");
		Thread.sleep(1000);
		System.out.println("...");
		System.out.println("*CRASH!!!!!!!!*");
		Thread.sleep(500);
		System.out.println("Something huge has just smashed through the wall and is coming RIGHT AT YOU!");
		Thread.sleep(500);
		
		boolean churchCompleted = Combat.oneEnemy(player, EnemyFactory.createEqualEnemy(player), scanner);
		
		return churchCompleted;
	}

}
